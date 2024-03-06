package com.employee.attendance.repository;

import com.employee.attendance.entity.EmployeeEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "repository")
public interface EmployeeEntryRepository extends MongoRepository<EmployeeEvent, Integer> {
    @Query(value = "{employeeId:?0}", sort = "{timestamp:1}")
    public List<EmployeeEvent> getEmployeeEventsById(Integer employeeId);
}
