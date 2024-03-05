package com.employee.attendance.repository;

import com.employee.attendance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "repository")
public interface EmployeeEntryRepository extends JpaRepository<Employee, Long> {
}
