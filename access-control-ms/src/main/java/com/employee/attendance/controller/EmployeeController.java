package com.employee.attendance.controller;

import com.employee.attendance.entity.Employee;
import com.employee.attendance.entity.Entry;
import com.employee.attendance.entity.EntryStatus;
import com.employee.attendance.repository.EmployeeEntryRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee/v1")
public class EmployeeController {

    @Autowired
    EmployeeEntryRepository repository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/generate")
    public ResponseEntity<?> produce() {
        List<Employee> employees = repository.findAll();

        employees.forEach(employee -> {
            List<Entry> sortedEntry = employee.getEntryList().stream()
                    .sorted(Comparator.comparing(Entry::getTimestamp)).toList();
            long duration = 0L;

            for (int i = 0; i < sortedEntry.size(); i += 2) {
                LocalDateTime timestamp1 = employee.getEntryList().get(i).getTimestamp();
                LocalDateTime timestamp2 = employee.getEntryList().get(i + 1).getTimestamp();
                duration = ChronoUnit.SECONDS.between(timestamp1, timestamp2) + duration;
            }

            ProducerRecord<String, String> record = new ProducerRecord<>("topic_1", employee.getName(), Long.toString(duration));
            kafkaTemplate.send(record);
        });

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/swipe/{status}")
    public ResponseEntity<Employee> employee(@PathVariable Long id, @PathVariable String status) {
        Optional<Employee> optionalEmployee = repository.findById(id);
        Employee employee = optionalEmployee.orElseGet(Employee::new);
        EntryStatus entryStatus = switch (status) {
            case "in" -> EntryStatus.SWIPE_IN;
            case "out" -> EntryStatus.SWIPE_OUT;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
        Entry entry = new Entry();
        entry.setEntryStatus(entryStatus);
        entry.setTimestamp(LocalDateTime.now());
        employee.getEntryList().add(entry);
        entry.setEmployee(employee);
        repository.save(employee);
        return ResponseEntity.ok(employee);
    }
}
