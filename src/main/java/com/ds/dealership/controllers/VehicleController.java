package com.ds.dealership.controllers;

import com.ds.dealership.entities.Vehicle;
import com.ds.dealership.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    VehicleRepository vehicles;

    @GetMapping("/featuredVehicles")
    public List<Vehicle> featuredVehicles() {

        List<Vehicle> featuredVehicles = vehicles.findAll();
        return  featuredVehicles;
    }

    @GetMapping("/vehiclePrices")
    public List<String> vehiclePrices() {

        List<String> allPrices = vehicles.findAllPrice();
        Collections.sort(allPrices);
        return  allPrices;
    }

    @GetMapping("/vehicleYears")
    public List<String> vehicleYears() {

        List<String> allYears = vehicles.findAllYears();
        Collections.sort(allYears);
        return  allYears;
    }


    @GetMapping("/Inventory/new")
    public List<Vehicle> newVehicles() {

        List<Vehicle> allNew = vehicles.findAllNew();
        return  allNew;
    }

}
