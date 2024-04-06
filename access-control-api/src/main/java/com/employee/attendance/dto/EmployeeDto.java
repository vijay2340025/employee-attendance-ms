package com.employee.attendance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto implements Serializable {
    private String name;
    private String password;
}
