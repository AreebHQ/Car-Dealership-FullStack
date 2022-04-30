package com.ds.dealership.controllers;

import com.ds.dealership.entities.Specials;
import com.ds.dealership.entities.Vehicle;
import com.ds.dealership.repositories.SpecialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecialsController {

    @Autowired
    SpecialsRepository specials;

    @GetMapping("/home/specials")
    public List<Specials> getAllSpecials() {

        List<Specials> allSpecials = specials.findAll();
        return  allSpecials;
    }

}
