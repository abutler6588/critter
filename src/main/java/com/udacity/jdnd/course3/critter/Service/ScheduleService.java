package com.udacity.jdnd.course3.critter.Service;


/*
Create Service objects that can handle requests from the Controller layer
and make the appropriate calls to the Data layer.
Avoid exposing the starter code’s DTO objects to your Service layer.
The Service layer should work with Entities or primitives, but not DTOs.
 */

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repo.CustomerRepo;
import com.udacity.jdnd.course3.critter.repo.EmployeeRepo;
import com.udacity.jdnd.course3.critter.repo.PetRepo;
import com.udacity.jdnd.course3.critter.repo.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    /* Validation should occur in the Service
     layer rather than the Data layer */

    @Autowired
    ScheduleRepo scheduleRepo;

    @Autowired
    PetRepo petRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    CustomerRepo customerRepo;

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Pet> pets = petRepo.findAllById(petIds);
        List<Employee> employees = employeeRepo.findAllById(employeeIds);

        schedule.setPets(pets);
        schedule.setEmployee(employees);

        return scheduleRepo.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> allSchedules = scheduleRepo.findAll();
        return allSchedules;
    }

    public List<Schedule> getEmployeeSchedule(Long employeeId) {
        Employee employee = employeeRepo.getOne(employeeId);
        List<Schedule> schedules = scheduleRepo.findByEmployee(employee);
        return schedules;
    }

    public List<Schedule> getPetSchedule(Long petId) {
        Pet pet = petRepo.getOne(petId);
        List<Schedule> schedules = scheduleRepo.findByPets(pet);
        return schedules;
    }

    public List<Schedule> getCustomerSchedule(Long customerId) {
        Customer customer = customerRepo.getOne(customerId);
        List<Schedule> schedules = scheduleRepo.findByPetsIn(customer.getPets());
        return schedules;
    }

}
