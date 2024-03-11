package com.employee.attendance.controller;

import com.employee.attendance.dto.EmployeeDto;
import com.employee.attendance.entity.EmployeeEvent;
import com.employee.attendance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/v1")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find/{id}")
    public ResponseEntity<List<EmployeeEvent>> getEventsById(@PathVariable Integer id) {
        List<EmployeeEvent> employeeEvents = employeeService.getEmployeeEvents(id);
        return ResponseEntity.ok(employeeEvents);
    }


    @PostMapping("/swipe")
    public ResponseEntity<EmployeeEvent> swipe(@RequestBody EmployeeDto employeeDto) {
        EmployeeEvent employeeEvent = employeeService.updateEmployeeEvent(employeeDto);
        return ResponseEntity.ok(employeeEvent);
    }
}
