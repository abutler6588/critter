package com.udacity.jdnd.course3.critter.Service;


/*
Create Service objects that can handle requests from the Controller layer
and make the appropriate calls to the Data layer.
Avoid exposing the starter code’s DTO objects to your Service layer.
The Service layer should work with Entities or primitives, but not DTOs.
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

@Service
@Transactional
public class PetService {

    /* Validation should occur in the Service
     layer rather than the Data layer */

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PetRepo petRepo;

    public Pet savePet(Pet pet, Long customerId) {
        Customer customer = customerRepo.getOne(customerId);
        List<Pet> pets = new ArrayList<>();

        pet.setCustomer(customer);
        pet = petRepo.save(pet);
        pets.add(pet);
        customer.setPets(pets);
        customerRepo.save(customer);

        return pet;
    }

    public List<Pet> getPetsByCustomerId(long customerId) {
        List<Pet> pets = petRepo.findPetByCustomerId(customerId);
        return pets;
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = petRepo.findAll();
        return pets;
    }

    public Pet getPetById(Long petId) {
        Pet pet = petRepo.getOne(petId);
        return pet;
    }


}
