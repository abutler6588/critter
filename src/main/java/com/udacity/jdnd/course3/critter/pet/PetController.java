package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Service.PetService;
import com.udacity.jdnd.course3.critter.data.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    /**
     * For the purpose of this program you will need to convert your Entity data back
     * into the provided DTO format.
     */

    private PetDTO convertPetToPetDTO(Pet pet) {
        return new PetDTO(pet.getId(), pet.getType(), pet.getName(), pet.getCustomer().getId(), pet.getBirthDate(), pet.getNotes());
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet(petDTO.getType(), petDTO.getName(), petDTO.getBirthDate(), petDTO.getNotes());
        PetDTO convertedPet;
        try {
            convertedPet = convertPetToPetDTO(petService.savePet(pet, petDTO.getOwnerId()));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet could not be saved", exception);
        }
        return convertedPet;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet;
        try {
            pet = petService.getPetById(petId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet with id: " + petId + " not found", exception);
        }
        return convertPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> pets = petService.getAllPets();
        return pets.stream().map(this::convertPetToPetDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets;
        try {
            pets = petService.getPetsByCustomerId(ownerId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner pet with id " + ownerId + " not found", exception);
        }
        return pets.stream().map(this::convertPetToPetDTO).collect(Collectors.toList());
    }
}