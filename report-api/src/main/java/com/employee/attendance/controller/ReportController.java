package com.employee.attendance.controller;

import com.employee.attendance.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report/v1")
public class ReportController {
    @GetMapping("/generate")
    public ResponseEntity<List<Employee>> getReport() {

        return ResponseEntity.ok().build();
    }
}
