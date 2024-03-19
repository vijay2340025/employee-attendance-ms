package com.employee.attendance.service;

import com.employee.attendance.dto.EmployeeDto;
import com.employee.attendance.entity.EmployeeEvent;
import com.employee.attendance.entity.EntryStatus;
import com.employee.attendance.repository.EmployeeEntryRepository;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeEntryRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public EmployeeEvent updateEmployeeEvent(EmployeeDto employeeDto) {
        EntryStatus entryStatus = switch (employeeDto.getStatus().toLowerCase()) {
            case "in" -> EntryStatus.SWIPE_IN;
            case "out" -> EntryStatus.SWIPE_OUT;
            default -> throw new IllegalStateException("Unexpected value: " + employeeDto.getEmployeeId());
        };

        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEmployeeId(employeeDto.getEmployeeId());
        employeeEvent.setTimestamp(LocalDateTime.now());
        employeeEvent.setEntryStatus(entryStatus);

        repository.save(employeeEvent);
        return employeeEvent;
    }

    public List<EmployeeEvent> getEmployeeEvents(Integer id) {
        return repository.getEmployeeEventsById(id);
    }

    public void calculateTotalHours() {
        DistinctIterable<Integer> distinct = mongoTemplate.getCollection("entryEvents")
                .distinct("employeeId", Integer.class);
        try (MongoCursor<Integer> iterator = distinct.iterator()) {
            while (iterator.hasNext()) {
                Integer employeeId = iterator.next();
                List<EmployeeEvent> employeeEvents = getEmployeeEvents(employeeId);
                long totalDuration = 0L;
                for (int i = 0; i <= employeeEvents.size() - 2; i += 2) {
                    LocalDateTime after = employeeEvents.get(i + 1).getTimestamp();
                    LocalDateTime before = employeeEvents.get(i).getTimestamp();
                    long duration = ChronoUnit.MINUTES.between(before, after);
                    totalDuration += duration;
                }
                log.info("Employee id=" + employeeId + " | totalHours=" + totalDuration);
                String status = "absent";
                if (totalDuration == 240) {
                    status = "half-day";
                } else if (totalDuration > 240) {
                    status = "present";
                }
                kafkaTemplate.send("attendance_report_topic", employeeId, status);
                if (status.equalsIgnoreCase("absent")) {
                    kafkaTemplate.send("absentee_notification_topic", employeeId, status);
                }
            }
        }

    }
}
