package com.employee.attendance.service;

import com.employee.attendance.dto.EmployeeDto;
import com.employee.attendance.entity.EmployeeEvent;
import com.employee.attendance.entity.EntryStatus;
import com.employee.attendance.repository.EmployeeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeEntryRepository repository;

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
}
