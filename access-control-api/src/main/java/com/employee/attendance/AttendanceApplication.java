package com.employee.attendance;

import com.employee.attendance.repository.EmployeeEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class AttendanceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AttendanceApplication.class, args);
        EmployeeEntryRepository repository = context.getBean("repository", EmployeeEntryRepository.class);
        /*Employee employee = new Employee();
        employee.setName("vijay");
        Entry entry = new Entry();
        entry.setTimestamp(LocalDateTime.now());
        entry.setEntryStatus(EntryStatus.SWIPE_IN);
        employee.setEntryList(List.of(entry));
        repository.save(employee);*/

    }

}