package com.employee.attendance.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ReportDto implements Serializable {
    private long employeeId;
    private String name;
    private String status;
    private LocalDate date;
}
