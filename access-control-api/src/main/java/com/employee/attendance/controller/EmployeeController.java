package com.employee.attendance.controller;

import com.employee.attendance.dto.EmployeeDto;
import com.employee.attendance.entity.EmployeeEvent;
import com.employee.attendance.entity.EntryStatus;
import com.employee.attendance.repository.EmployeeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employee/v1")
public class EmployeeController {

    @Autowired
    EmployeeEntryRepository repository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/find/{id}")
    public ResponseEntity<List<EmployeeEvent>> produce(@PathVariable Integer id) {
        List<EmployeeEvent> employeeEvents = repository.getEmployeeEventsById(id);
        return ResponseEntity.ok(employeeEvents);
    }

    @PostMapping("/swipe")
    public ResponseEntity<EmployeeEvent> employee(@RequestBody EmployeeDto employeeDto) {

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
        return ResponseEntity.ok(employeeEvent);
    }
}
