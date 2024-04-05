package com.employee.attendance.service;

import com.employee.attendance.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "report-api", url = "http://report-service:8802/report/v1")
public interface EmployeeManagementSrv {
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> create(EmployeeDto e);
}
