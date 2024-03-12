package com.employee.attendance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Column
    private LocalDateTime date;

    @Column
    private String status;

}
