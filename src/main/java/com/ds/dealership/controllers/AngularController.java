package com.ds.dealership.controllers;

import com.ds.dealership.entities.*;
import com.ds.dealership.models.PurchaseModel;
import com.ds.dealership.models.SearchNewInventoryModel;
import com.ds.dealership.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequestMapping("/angular")
@RestController
public class AngularController {
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
    @Autowired
    MessageRepository messages;
    @Autowired
    SpecialsRepository specials;

    // -- ANGULAR SPECIFIC ----
    //==========================================================


    @GetMapping("/Inventory/new")
    public List<Vehicle> newVehicles() {

        List<Vehicle> allNew = vehicles.findAllNew();
        return  allNew;
    }

    @GetMapping("/Inventory/used")
    public List<Vehicle> usedVehicles() {

        List<Vehicle> allUsed = vehicles.findAllUsed();
        return  allUsed;
    }


    @GetMapping("/featured")
    public List<Vehicle> allFeaturedVehicles() {

        List<Vehicle> allVehicles = vehicles.findAllAvailable();
        for(Vehicle v : allVehicles)
        {
            String imageName = v.getImage().substring(3);
            v.setImage(imageName);
        }
        return  allVehicles;
    }


    @GetMapping("/sales/index")
    public List<Vehicle> allVehicles() {

        List<Vehicle> allVehicles = vehicles.findAllAvailable();
        return  allVehicles;
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
        String searchBar = search.getSearchInput().trim();
        String carMake = searchBar;
        String carModel = "";
        if(searchBar.split("-").length > 1)
        {String []searchInput = searchBar.split("-");
            carMake = searchInput[0].trim();
            carModel = searchInput[1].trim();
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


    //======================================================
       //  Contact US
    //======================================================
    @PostMapping("/contactus/message")
    private String getUsed(@RequestBody Message message, HttpServletRequest request, final ModelMap model) {
        System.out.println(message.getName() + " " + message.getPhone());
        messages.save(message);
        return  "success";
    }

    @GetMapping("/contactus/getMessages")
    public List<Message> message()
    {
        List<Message> allMessages = messages.findAll();
        return allMessages;
    }

    //======================================================
    //      SPECIALS
    //=======================================================



    @GetMapping("/home/specials")
    public List<Specials> getAllSpecials() {

        List<Specials> allSpecials = specials.findAll();
        return  allSpecials;
    }

    //===========================================================
    //             SALES
    //========================================================


    @PostMapping("/sales/purchase")
    @ResponseStatus(HttpStatus.OK)
    public String savePurchasedVehicle(@RequestBody PurchaseModel purchase)
    {
        Purchase newPurchase = new Purchase();
        User user = users.getById(1);
        Optional<Vehicle> vehicle = vehicles.findById( Integer.parseInt(purchase.getVehicleId()));
        newPurchase.setVehicleId(vehicle.get());
        newPurchase.setUserId(user);
        newPurchase.setPrice(purchase.getPrice());
        newPurchase.setPurchaseType(purchase.getPurchaseType());
        newPurchase.setCity(purchase.getCity());
        newPurchase.setEmail(purchase.getEmail());
        newPurchase.setState(purchase.getState());
        newPurchase.setName(purchase.getName());
        newPurchase.setStreet(purchase.getStreet());
        newPurchase.setZip(purchase.getZip());
        purchases.save(newPurchase);
        vehicle.get().setSold(true);
        vehicles.save(vehicle.get());
        System.out.println("vehicle sold");
        return "success";
    }

    @RequestMapping(value="/sales/searchVehicles")
    private List<Vehicle> getAvailable(@RequestBody SearchNewInventoryModel search, HttpServletRequest request, final ModelMap model) {

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
            searchResult = vehicles.findAvailableBySearchInput(minYear,maxYear,minPrice,maxPrice);
        } else {
            searchResult = vehicles.findAvailableBySearchInput(minYear,maxYear,minPrice,maxPrice,carMake,carModel);
        }

        return  searchResult;

    }


    //======================================================================
    //                ADMIN
    //=====================================================================


    @RequestMapping(value="/admin/vehicles")
    private List<Vehicle> getAllAvailableVehicles(@RequestBody SearchNewInventoryModel search, HttpServletRequest request, final ModelMap model) {

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
            searchResult = vehicles.findAvailableBySearchInput(minYear,maxYear,minPrice,maxPrice);
        } else {
            searchResult = vehicles.findAvailableBySearchInput(minYear,maxYear,minPrice,maxPrice,carMake,carModel);
        }

        return  searchResult;

    }
}
