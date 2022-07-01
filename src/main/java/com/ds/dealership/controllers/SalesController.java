package com.ds.dealership.controllers;

import com.ds.dealership.entities.Purchase;
import com.ds.dealership.entities.User;
import com.ds.dealership.entities.Vehicle;
import com.ds.dealership.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/sales")
public class SalesController {


    @Autowired
    UserRepository users;

    @Autowired
    VehicleRepository vehicles;

    @Autowired
    PurchaseRepository purchases;

    @Autowired
    BodyRepository bodies;

    @Autowired
    ColorRepository colors;
    @Autowired
    MakeRepository make;
    @Autowired
    ModelRepository model;

    @PostMapping("/purchase/{id}")
    public String savePurchasedVehicle(@PathVariable("id") Integer id, @RequestBody Purchase purchase)
    {
        User user = users.getById(1);
        Optional<Vehicle> vehicle = vehicles.findById(id);
        purchase.setVehicleId(vehicle.get());
        purchase.setUserId(user);
        purchases.save(purchase);
        vehicle.get().setSold(true);
        vehicles.save(vehicle.get());

        return "success";
    }


    @GetMapping("/index")
    public List<Vehicle> allVehicles() {

        List<Vehicle> allVehicles = vehicles.findAllAvailable();
        return  allVehicles;
    }

}
