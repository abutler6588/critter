package com.udacity.jdnd.course3.critter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {

    /**
     * Rubric - Do not put any domain logic into the Controller layer.
     * replace the DTO objects without compromising encapsulation between
     * the data layer and the controller layer?
     */

    @GetMapping("/test")
    public String test(){
        return "Critter Starter installed successfully";
    }
}
