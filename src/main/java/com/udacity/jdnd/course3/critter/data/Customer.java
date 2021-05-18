package com.udacity.jdnd.course3.critter.data;

import javax.persistence.*;
import java.util.List;

/*
Create a Customer entities that represent the storage needs of the application
 */

@Table
@Entity
public class Customer {

    /*
    Data Layer modifies or returns Entities.
    It can join tables to aggregate data across multiple Entity types
    but should avoid performing multiple operations in a single request.
     */

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    public Customer(Long id, String name, String phoneNumber, String notes) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
