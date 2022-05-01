package com.ds.dealership.controllers;

import com.ds.dealership.entities.Purchase;
import com.ds.dealership.entities.User;
import com.ds.dealership.entities.Vehicle;
import com.ds.dealership.repositories.PurchaseRepository;
import com.ds.dealership.repositories.UserRepository;
import com.ds.dealership.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    UserRepository users;

    @Autowired
    VehicleRepository vehicles;

    @Autowired
    PurchaseRepository purchases;

    @GetMapping("/featuredVehicles")
    public List<Vehicle> featuredVehicles() {

        List<Vehicle> featuredVehicles = vehicles.findAllAvailable();
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

    @PostMapping("/sales/purchase/{id}")
    public String savePurchasedVehicle(@PathVariable("id") Integer id, @RequestBody Purchase purchase)
    {
        System.out.println("purchase called");

        System.out.println(purchase.getName() + " "+ purchase.getPurchaseType()+" "+purchase.getState()+" "+ purchase.getPrice());

        User user = users.getById(1);
        Optional<Vehicle> vehicle = vehicles.findById(id);
        purchase.setVehicleId(vehicle.get());
        purchase.setUserId(user);
        purchases.save(purchase);
        System.out.println("updating vehicle");
        vehicle.get().setSold(true);
        vehicles.save(vehicle.get());
        System.out.println("done");


        return "success";
    }



}
