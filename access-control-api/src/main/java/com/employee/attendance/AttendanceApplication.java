package com.employee.attendance;

import com.employee.attendance.entity.EmployeeEvent;
import com.employee.attendance.entity.EntryStatus;
import com.employee.attendance.repository.EmployeeEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class AttendanceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AttendanceApplication.class, args);
        EmployeeEntryRepository repository = context.getBean("repository", EmployeeEntryRepository.class);

        EmployeeEvent employeeEvent = new EmployeeEvent();
        employeeEvent.setEntryStatus(EntryStatus.SWIPE_IN);
        employeeEvent.setEmployeeId(1);
        employeeEvent.setTimestamp(LocalDateTime.now());
        repository.save(employeeEvent);

        employeeEvent = new EmployeeEvent();
        employeeEvent.setEntryStatus(EntryStatus.SWIPE_OUT);
        employeeEvent.setEmployeeId(1);
        employeeEvent.setTimestamp(LocalDateTime.now().plusHours(9));
        repository.save(employeeEvent);


        employeeEvent = new EmployeeEvent();
        employeeEvent.setEntryStatus(EntryStatus.SWIPE_IN);
        employeeEvent.setEmployeeId(2);
        employeeEvent.setTimestamp(LocalDateTime.now().plusHours(4));
        repository.save(employeeEvent);

        employeeEvent = new EmployeeEvent();
        employeeEvent.setEntryStatus(EntryStatus.SWIPE_OUT);
        employeeEvent.setEmployeeId(2);
        employeeEvent.setTimestamp(LocalDateTime.now().plusHours(9));
        repository.save(employeeEvent);

        employeeEvent = new EmployeeEvent();
        employeeEvent.setEntryStatus(EntryStatus.SWIPE_IN);
        employeeEvent.setEmployeeId(3);
        employeeEvent.setTimestamp(LocalDateTime.now().plusHours(2));
        repository.save(employeeEvent);

        employeeEvent = new EmployeeEvent();
        employeeEvent.setEntryStatus(EntryStatus.SWIPE_OUT);
        employeeEvent.setEmployeeId(3);
        employeeEvent.setTimestamp(LocalDateTime.now().plusHours(9));
        repository.save(employeeEvent);


    }

}