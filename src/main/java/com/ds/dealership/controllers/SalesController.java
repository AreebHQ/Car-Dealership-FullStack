package com.ds.dealership.controllers;

import com.ds.dealership.entities.*;
import com.ds.dealership.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sales")
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
    MakeRepository makes;
    @Autowired
    ModelRepository models;

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

    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @GetMapping("/index")
    public List<Vehicle> allVehicles() {

        System.out.println("sales/index called");
        List<Vehicle> allVehicles = vehicles.findAllAvailable();
        return  allVehicles;
    }


    @GetMapping("/models")
    public List<Model> getAllModels() {
        List<Model> modelsList = models.findAll();

        return modelsList;
    }


    @GetMapping("/makes")
    public List<Make> getAllMakes() {

        List<Make> makeList = makes.findAll();

        return makeList;
    }

      @GetMapping("/vehicleColors")
    public List<Color> vehicleColors() {
        List<Color> colorsList = colors.findAll();
        return  colorsList;
    }


    @GetMapping("/vehicleBodies")
    public List<Body> vehicleBody() {

        List<Body> allBodies = bodies.findAll();
        return  allBodies;
    }

}
