package com.employee.attendance.controller;

import com.employee.attendance.dto.EmployeeDto;
import com.employee.attendance.dto.ReportDto;
import com.employee.attendance.entity.Employee;
import com.employee.attendance.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report/v1")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/generate")
    public ResponseEntity<List<ReportDto>> getReport() {
        List<ReportDto> reportDtoList = reportService.getReportData();
        return ResponseEntity.ok(reportDtoList);
    }

    @GetMapping("/create")
    public ResponseEntity<Employee> create(EmployeeDto e) {
        Employee employee = reportService.createEmployee(e);
        return ResponseEntity.ok(employee);
    }
}
