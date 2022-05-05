package com.ds.dealership.controllers;

import com.ds.dealership.entities.Vehicle;
import com.ds.dealership.models.VehicleModel;
import com.ds.dealership.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


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

    @PostMapping("/admin/editVehicle/{id}")
    public String editVehicle(@PathVariable("id") Integer id, @RequestBody VehicleModel vehicle , HttpServletRequest request)
    {


        System.out.println( "ID: "+ id
                + "Make: "+ vehicle.getMake()
                + "Model: "+ vehicle.getModel()
                + "Type: "+ vehicle.getType()
                + "Year: "+ vehicle.getYear()
                + "Transmission: "+ vehicle.getTransmission()
                + "Body: "+ vehicle.getBody()
                + "BodyColor: "+ vehicle.getBodyColor()
                + "InteriorColor: "+ vehicle.getInteriorColor()
                + "Mileage: "+ vehicle.getMileage()
                + "MRSP: "+ vehicle.getMrspPrice()
                + "Sale: "+ vehicle.getSalePrice()
                + "Description: "+ vehicle.getDescription()
                + "imageFile: "+ vehicle.getImage()

        );

        String []imageName = vehicle.getImage().split("\\\\");
        String imagePath = "../images/" + imageName[imageName.length-1];
        System.out.println(imagePath);

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


    @PostMapping("/admin/addVehicle")
    public String addVehicle(@RequestBody VehicleModel vehicle )
    {


        System.out.println( "Make: "+ vehicle.getMake()
                + "Model: "+ vehicle.getModel()
                + "Type: "+ vehicle.getType()
                + "Year: "+ vehicle.getYear()
                + "Transmission: "+ vehicle.getTransmission()
                + "Body: "+ vehicle.getBody()
                + "BodyColor: "+ vehicle.getBodyColor()
                + "InteriorColor: "+ vehicle.getInteriorColor()
                + "Mileage: "+ vehicle.getMileage()
                + "MRSP: "+ vehicle.getMrspPrice()
                + "Sale: "+ vehicle.getSalePrice()
                + "Description: "+ vehicle.getDescription()
                + "imageFile: "+ vehicle.getImage()

        );

        String []imageName = vehicle.getImage().split("\\\\");
        String imagePath = "../images/" + imageName[imageName.length-1];
        System.out.println(imagePath);

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
