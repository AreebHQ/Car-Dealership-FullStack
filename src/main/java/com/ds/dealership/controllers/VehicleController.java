package com.ds.dealership.controllers;

import com.ds.dealership.entities.*;
import com.ds.dealership.repositories.*;
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

    @Autowired
    BodyRepository bodies;

    @Autowired
    ColorRepository colors;
    @Autowired
    MakeRepository make;
    @Autowired
    ModelRepository model;

    @GetMapping("/featuredVehicles")
    public List<Vehicle> featuredVehicles() {

        List<Vehicle> featuredVehicles = vehicles.findAllFeatured();
        return  featuredVehicles;
    }

    @GetMapping("/vehicleBody")
    public List<Body> vehicleBody() {

        List<Body> allBodies = bodies.findAll();
        return  allBodies;
    }

    @GetMapping("/vehicleModel")
    public List<Model> vehicleModel() {

        List<Model> modelList = model.findAll();
        return  modelList;
    }

    @GetMapping("/vehicleMake")
    public List<Make> vehicleMake() {

        List<Make> makeList = make.findAll();
        return  makeList;
    }

    @GetMapping("/vehicleColors")
    public List<Color> vehicleColors() {

        List<Color> colorsList = colors.findAll();
        return  colorsList;
    }

    @PostMapping("/sales/purchase/{id}")
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



}
