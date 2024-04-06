package com.employee.attendance.controller;

import com.employee.attendance.dto.EmployeeDto;
import com.employee.attendance.dto.ReportDto;
import com.employee.attendance.entity.Employee;
import com.employee.attendance.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report/v1")
@Slf4j
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/generate")
    public ResponseEntity<List<ReportDto>> getReport() {
        List<ReportDto> reportDtoList = reportService.getReportData();
        return ResponseEntity.ok(reportDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto e) {
        log.info("Employee {} received for creation", e);
        Employee employee = reportService.createEmployee(e);
        log.info("saved {} into db", employee);
        return ResponseEntity.ok(e);
    }
}
