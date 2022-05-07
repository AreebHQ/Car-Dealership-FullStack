package com.ds.dealership.controllers;

import com.ds.dealership.entities.Role;
import com.ds.dealership.entities.User;
import com.ds.dealership.entities.Vehicle;
import com.ds.dealership.models.UserModel;
import com.ds.dealership.models.VehicleModel;
import com.ds.dealership.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class AdminController {

    @Autowired
    VehicleRepository vehicles;
    @Autowired
    UserRepository users;

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

    @Autowired
    RoleRepository roles;


    @GetMapping("/admin/users")
    public List<User> allUsers() {
        List<User> allUsers = users.findAll();
        return allUsers;
    }

    @GetMapping("/admin/roles")
    public List<Role> getAllRoles() {
        List<Role> allRoles = roles.findAll();

        return allRoles;
    }


    @PostMapping("/admin/editVehicle/{id}")
    public String editVehicle(@PathVariable("id") Integer id, @RequestBody VehicleModel vehicle , HttpServletRequest request)
    {


        String imagePath="";
        if(vehicle.getImage().contains("../images/"))
        {
            imagePath = vehicle.getImage();

        } else{
        String []imageName = vehicle.getImage().split("\\\\");
        imagePath = "../images/" + imageName[imageName.length-1]; }


        Vehicle editVehicle = vehicles.getById(id);
        editVehicle.setMake(makes.getById(vehicle.getMake()));
        editVehicle.setModel(models.getById(vehicle.getModel()));
        editVehicle.setType(vehicle.getType());
        editVehicle.setBody(bodies.getById(vehicle.getBody()));
        editVehicle.setYear(vehicle.getYear());
        editVehicle.setTransmission(vehicle.getTransmission());
        editVehicle.setBodyColor(colors.getById(vehicle.getBodyColor()));
        editVehicle.setInteriorColor(colors.getById(vehicle.getInteriorColor()));
        editVehicle.setMileage(vehicle.getMileage());
        editVehicle.setVinNumber(vehicle.getVinNumber());
        editVehicle.setMrspPrice(vehicle.getMrspPrice());
        editVehicle.setSalePrice(vehicle.getSalePrice());
        editVehicle.setDescription(vehicle.getDescription());
        editVehicle.setImage(imagePath);
        vehicles.save(editVehicle);

        return "success";
    }


    @PostMapping("/admin/deleteVehicle/{id}")
    public String deleteVehicle(@PathVariable("id") Integer id)
    {
       vehicles.deleteById(id);
        return "success";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@RequestBody UserModel user)
    {
        User newUser = new User();
        Role role = roles.getById(user.getRole());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(role);
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        users.save(newUser);

        return "success";
    }



    @PostMapping("/admin/addVehicle")
    public String addVehicle(@RequestBody VehicleModel vehicle )
    {

        String imagePath="";
        if(vehicle.getImage().contains("../images/"))
        {
            imagePath = vehicle.getImage();

        } else{
            String []imageName = vehicle.getImage().split("\\\\");
            imagePath = "../images/" + imageName[imageName.length-1]; }

        Vehicle addVehicle = new Vehicle();
        addVehicle.setMake(makes.getById(vehicle.getMake()));
        addVehicle.setModel(models.getById(vehicle.getModel()));
        addVehicle.setType(vehicle.getType());
        addVehicle.setBody(bodies.getById(vehicle.getBody()));
        addVehicle.setYear(vehicle.getYear());
        addVehicle.setTransmission(vehicle.getTransmission());
        addVehicle.setBodyColor(colors.getById(vehicle.getBodyColor()));
        addVehicle.setInteriorColor(colors.getById(vehicle.getInteriorColor()));
        addVehicle.setMileage(vehicle.getMileage());
        addVehicle.setVinNumber(vehicle.getVinNumber());
        addVehicle.setMrspPrice(vehicle.getMrspPrice());
        addVehicle.setSalePrice(vehicle.getSalePrice());
        addVehicle.setDescription(vehicle.getDescription());
        addVehicle.setImage(imagePath);
        vehicles.save(addVehicle);

        return "success";
    }
}
