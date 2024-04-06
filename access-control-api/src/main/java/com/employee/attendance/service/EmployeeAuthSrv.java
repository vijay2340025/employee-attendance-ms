package com.employee.attendance.service;

import com.employee.attendance.dto.UserCredentialDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "jwt", url = "http://jwt:9898/auth")
public interface EmployeeAuthSrv {
    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredentialDto user);
}
