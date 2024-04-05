package com.employee.attendance.controller;

import com.employee.attendance.dto.EmployeeDto;
import com.employee.attendance.dto.EmployeeStatusDto;
import com.employee.attendance.entity.EmployeeEvent;
import com.employee.attendance.service.EmployeeManagementSrv;
import com.employee.attendance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/v1")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeManagementSrv employeeManagementSrv;

    @GetMapping("/find/{id}")
    public ResponseEntity<List<EmployeeEvent>> getEventsById(@PathVariable Integer id) {
        List<EmployeeEvent> employeeEvents = employeeService.getEmployeeEvents(id);
        return ResponseEntity.ok(employeeEvents);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> create(EmployeeDto e) {
        return employeeManagementSrv.create(e);
    }

    @PostMapping("/swipe")
    public ResponseEntity<EmployeeEvent> swipe(@RequestBody EmployeeStatusDto employeeStatusDto) {
        EmployeeEvent employeeEvent = employeeService.updateEmployeeEvent(employeeStatusDto);
        return ResponseEntity.ok(employeeEvent);
    }

    @GetMapping("/calculate")
    public ResponseEntity<Void> calculateTotalHours() {
        employeeService.calculateTotalHours();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
