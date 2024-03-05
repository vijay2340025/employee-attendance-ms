package com.employee.attendance;

import com.employee.attendance.entity.Employee;
import com.employee.attendance.repository.EmployeeEntryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AttendanceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AttendanceApplication.class, args);
        EmployeeEntryRepository repository = context.getBean("repository", EmployeeEntryRepository.class);
        Employee employee = new Employee();
        employee.setName("vijay");
        repository.save(employee);
    }

}