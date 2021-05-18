package com.udacity.jdnd.course3.critter.Service;


/*
Create Service objects that can handle requests from the Controller layer
and make the appropriate calls to the Data layer.
Avoid exposing the starter code’s DTO objects to your Service layer.
The Service layer should work with Entities or primitives, but not DTOs.
 */

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.repo.EmployeeRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

     /* Validation should occur in the Service
     layer rather than the Data layer */

    @Autowired
    EmployeeRepo employeeRepo;

    public Employee saveEmployee(Employee employee) {
        Employee newEmployee = employeeRepo.save(employee);
        return newEmployee;
    }

    public List<Employee> getEmployeesByService(LocalDate date, Set<EmployeeSkill> skills){
        List<Employee> employees = employeeRepo
                .findByDaysAvailable(date.getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
        return employees;
    }

    public Employee getEmployeeById(Long employeeId) {
        Employee employee = employeeRepo.getOne(employeeId);
        return employee;
    }

    public void setEmployeeAvailability(Set<DayOfWeek> days, Long employeeId) {
        Employee employee = employeeRepo.getOne(employeeId);
        employee.setDaysAvailable(days);
        employeeRepo.save(employee);
    }

}
