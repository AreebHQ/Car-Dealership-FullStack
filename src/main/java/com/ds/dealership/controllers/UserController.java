package com.ds.dealership.controllers;

import com.ds.dealership.entities.*;
import com.ds.dealership.models.Password;
import com.ds.dealership.models.SearchNewInventoryModel;
import com.ds.dealership.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController("/")
public class UserController {

    @Autowired
    UserRepository users;
    @Autowired
    RoleRepository roles;
    @Autowired
    VehicleRepository vehicles;
    @Autowired
    SpecialsRepository specials;
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
    @Autowired
    MessageRepository messages;

    @GetMapping("Inventory/new")
    public List<Vehicle> newVehicles() {

        List<Vehicle> allNew = vehicles.findAllNew();
        return  allNew;
    }

    @GetMapping("Inventory/used")
    public List<Vehicle> usedVehicles() {

        List<Vehicle> allUsed = vehicles.findAllUsed();
        return  allUsed;
    }

    @GetMapping("featured")
    public List<Vehicle> allFeaturedVehicles() {

        List<Vehicle> allVehicles = vehicles.findAllAvailable();
        for(Vehicle v : allVehicles)
        {
            String imageName = v.getImage().substring(3);
            v.setImage(imageName);
        }
        return  allVehicles;
    }

    @GetMapping("home/specials")
    public List<Specials> getAllSpecials() {

        List<Specials> allSpecials = specials.findAll();
        return  allSpecials;
    }

    @GetMapping("featuredVehicles")
    public List<Vehicle> featuredVehicles() {

        List<Vehicle> featuredVehicles = vehicles.findAllFeatured();
        return  featuredVehicles;
    }

    @GetMapping("vehicleBody")
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


    @PostMapping("/contactus/message")
    private String getUsed(@RequestBody Message message, HttpServletRequest request, final ModelMap model) {

        messages.save(message);
        return  "success";
    }

    @GetMapping("/contactus/getMessages")
    public List<Message> message()
    {
        List<Message> allMessages = messages.findAll();
        return allMessages;
    }

    @GetMapping("/Inventory/details/{id}")
    public Optional<Vehicle> vehicleDetail(@PathVariable("id") Integer id) {

        Optional<Vehicle> vehicle = vehicles.findById(id);

        return  vehicle;
    }

    @RequestMapping(value="/Inventory/searchNewInventory")
    private List<Vehicle> getFormData(@RequestBody SearchNewInventoryModel search, HttpServletRequest request, final ModelMap model) {

        List<Vehicle> searchResult = null;
        String minYear = search.getMinYear();
        String maxYear = search.getMaxYear();
        String minPrice = search.getMinPrice();
        String maxPrice = search.getMaxPrice();
        String searchBar = search.getSearchInput();
        String carMake = searchBar;
        String carModel = "";
        if(searchBar.split("-").length > 1)
        {String []searchInput = searchBar.split("-");
            carMake = searchInput[0];
            carModel = searchInput[1];
        }


        if(minYear.equals("Min"))
        {
            minYear = "0";
        }
        if(minPrice.equals("Min"))
        {
            minPrice = "0";
        }

        if(maxYear.equals("2020+") || maxYear.equals("Max"))
        {
            maxYear = "9999";
        }
        if(maxPrice.equals("25000+") || maxPrice.equals("Max"))
        {
            maxPrice= "999999";

        }


        if(searchBar.isEmpty() || searchBar.isBlank())
        {
            searchResult = vehicles.findNewBySearchInput(minYear,maxYear,minPrice,maxPrice);
        } else {
            searchResult = vehicles.findNewBySearchInput(minYear,maxYear,minPrice,maxPrice,carMake,carModel);
        }

        return  searchResult;

    }


    @RequestMapping(value="/Inventory/searchUsedInventory")
    private List<Vehicle> getUsed(@RequestBody SearchNewInventoryModel search, HttpServletRequest request, final ModelMap model) {

        List<Vehicle> searchResult = null;
        String minYear = search.getMinYear();
        String maxYear = search.getMaxYear();
        String minPrice = search.getMinPrice();
        String maxPrice = search.getMaxPrice();
        String searchBar = search.getSearchInput();
        String carMake = searchBar;
        String carModel = "";
        if(searchBar.split("-").length > 1)
        {String []searchInput = searchBar.split("-");
            carMake = searchInput[0];
            carModel = searchInput[1];
        }


        if(minYear.equals("Min"))
        {
            minYear = "0";
        }
        if(minPrice.equals("Min"))
        {
            minPrice = "0";
        }

        if(maxYear.equals("2020+") || maxYear.equals("Max"))
        {
            maxYear = "9999";
        }
        if(maxPrice.equals("25+") || maxPrice.equals("Max"))
        {
            maxPrice= "999999";

        }


        if(searchBar.isEmpty() || searchBar.isBlank())
        {
            searchResult = vehicles.findUsedBySearchInput(minYear,maxYear,minPrice,maxPrice);
        } else {
            searchResult = vehicles.findUsedBySearchInput(minYear,maxYear,minPrice,maxPrice,carMake,carModel);
        }

        return  searchResult;

    }
}
