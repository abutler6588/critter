package com.udacity.jdnd.course3.critter.Service;


/*
 A sensible transaction boundary will likely occur at the Service layer.
 You can begin transactions by annotating the methods @Transactional,
 or you can specify an entire class as @Transactional to mark all methods transactional.
 */

/*
Service Layer retrieves information from one or more data sources.
It can handle coordination between multiple data sources to solve multi-step problems
 */

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repo.CustomerRepo;
import com.udacity.jdnd.course3.critter.repo.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    /*Validation should occur in the Service layer
    rather than the Data layer */

    @Autowired
    PetRepo petRepo;
    @Autowired
    CustomerRepo customerRepo;

    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> customerPets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            customerPets = petIds.stream().map((petId) -> petRepo.getOne(petId)).collect(Collectors.toList());
        }
        customer.setPets(customerPets);
        return customerRepo.save(customer);
    }

    public Customer getCustomerByPetId(Long petId) {
        Customer customer = petRepo.getOne(petId).getCustomer();
        return customer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return customers;
    }

}
