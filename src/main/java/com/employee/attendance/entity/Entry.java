package com.employee.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_entry")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    @Setter
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column
    @Setter
    @Getter
    private EntryStatus entryStatus;

    @Column
    @Setter
    @Getter
    private LocalDateTime timestamp;
}
