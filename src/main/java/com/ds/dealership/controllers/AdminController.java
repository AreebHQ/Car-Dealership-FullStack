package com.ds.dealership.controllers;

import com.ds.dealership.entities.*;
import com.ds.dealership.models.UserModel;
import com.ds.dealership.models.VehicleModel;
import com.ds.dealership.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @GetMapping("/admin/makes")
    public List<Make> getAllMakes() {
       List<Make> makeList = makes.findAll();

        return makeList;
    }

    @PostMapping("/admin/makes")
    public String addNewMake(@RequestBody String newMake)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.format(format);

        Make addMake = new Make();
        addMake.setName(newMake);
        addMake.setDate(date);
        addMake.setUserEmail("hardCOded@email.com");
        makes.save(addMake);

        return "success";
    }


    @GetMapping("/admin/models")
    public List<Model> getAllModels() {
        List<Model> modelsList = models.findAll();

        return modelsList;
    }


    @PostMapping("/admin/models")
    public String addNewModel(@RequestBody String newModel)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.format(format);
        String []modelData = newModel.split("::");
        Make make = makes.getById(Integer.parseInt(modelData[0]));
        Model addModel = new Model();
        addModel.setMake(make);
        addModel.setName(modelData[1]);
        addModel.setDate(date);
        addModel.setUserEmail("hardCOded@email.com");
        models.save(addModel);

        return "success";
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
