package com.employee.attendance.service;

import com.employee.attendance.entity.Employee;
import com.employee.attendance.entity.Report;
import com.employee.attendance.repository.EmployeeRepository;
import com.employee.attendance.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public void updateReportData(Long employeeId, String status) {
        Report report = new Report();
        report.setDate(LocalDate.now());
        report.setStatus(status);
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseGet(Employee::new);
        report.setEmployee(employee);
        reportRepository.save(report);
    }
}