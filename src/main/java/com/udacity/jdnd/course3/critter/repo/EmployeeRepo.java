package com.udacity.jdnd.course3.critter.repo;


/*
The application should either use the DAO pattern or Repository pattern to isolate access
to your data source. You should have one DAO or Repository for each Entity you define
and that component will expose CRUD operations to the rest of the program.
 */

import com.udacity.jdnd.course3.critter.data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface  EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByDaysAvailable(DayOfWeek dayOfWeek);
}
