package com.employee.attendance.controller;

import com.employee.attendance.dto.EmployeeDto;
import com.employee.attendance.dto.EmployeeStatusDto;
import com.employee.attendance.dto.UserCredentialDto;
import com.employee.attendance.entity.EmployeeEvent;
import com.employee.attendance.service.EmployeeAuthSrv;
import com.employee.attendance.service.EmployeeManagementSrv;
import com.employee.attendance.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/v1")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeManagementSrv employeeManagementSrv;

    @Autowired
    EmployeeAuthSrv employeeAuthSrv;

    @GetMapping("/find/{id}")
    public ResponseEntity<List<EmployeeEvent>> getEventsById(@PathVariable Integer id) {
        List<EmployeeEvent> employeeEvents = employeeService.getEmployeeEvents(id);
        return ResponseEntity.ok(employeeEvents);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto e) {
        UserCredentialDto userCredentialDto = UserCredentialDto.builder()
                .name(e.getName())
                .password(e.getPassword())
                .build();

        employeeAuthSrv.addNewUser(userCredentialDto);
        log.info("added credentials {}", userCredentialDto);
        ResponseEntity<EmployeeDto> employeeDtoResponseEntity = employeeManagementSrv.create(e);
        log.info("added employee {}", employeeDtoResponseEntity);

        return employeeDtoResponseEntity;
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
