package com.employee.attendance.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "entryEvents")
public class EmployeeEvent {
    @Id
    @Indexed(unique = true)
    private String id;

    @Getter
    @Setter
    private int employeeId;

    @Setter
    @Getter
    private EntryStatus entryStatus;

    @Setter
    @Getter
    private LocalDateTime timestamp;
}
