package com.ds.dealership.Controllers;

import com.ds.dealership.Entities.*;
import com.ds.dealership.Models.VehicleSearchModel;
import com.ds.dealership.Services.DealershipServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    DealershipServiceLayer service;

    @GetMapping("inventory/new")
    public ResponseEntity<List<Vehicle>>  newVehicles() {

        return  service.newVehicles();
    }

    @GetMapping("inventory/used")
    public ResponseEntity<List<Vehicle>> usedVehicles() {

    return service.usedVehicles();
    }

    @GetMapping("featured")
    public ResponseEntity<List<Vehicle>> allFeaturedVehicles() {

      return service.allFeaturedVehicles();
    }

    @GetMapping("home/specials")
    public ResponseEntity<List<Specials> > getAllSpecials() {

      return service.getAllSpecials();
    }

    @GetMapping("/inventory/details/{id}")
    public ResponseEntity<Optional<Vehicle>> vehicleDetail(@PathVariable("id") Integer id) {

      return  service.vehicleDetail(id);
    }

    @RequestMapping(value="/inventory/searchNewInventory")
    public ResponseEntity<List<Vehicle>> getFormData(@RequestBody VehicleSearchModel search) {

       return service.getFormData(search);
    }

    @RequestMapping(value="/inventory/searchUsedInventory")
    public ResponseEntity<List<Vehicle>> getUsed(@RequestBody VehicleSearchModel search) {

      return service.getUsed(search);
    }
}
