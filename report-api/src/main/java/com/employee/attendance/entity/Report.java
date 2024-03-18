package com.employee.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_report")
@DynamicUpdate
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "emp_id")
    @Getter
    @Setter
    private Employee employee;

    @Column
    @Getter
    @Setter
    private LocalDate date;

    @Column
    @Getter
    @Setter
    private String status;

}
