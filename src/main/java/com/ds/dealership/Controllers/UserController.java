package com.ds.dealership.Controllers;

import com.ds.dealership.Entities.*;
import com.ds.dealership.Models.VehicleSearchModel;
import com.ds.dealership.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    VehicleRepository vehicles;
    @Autowired
    SpecialsRepository specials;
    @Autowired
    MessageRepository messages;

    @GetMapping("inventory/new")
    public ResponseEntity<List<Vehicle>>  newVehicles() {

        List<Vehicle> allNew = vehicles.findAllNew();
        return  new ResponseEntity<>(allNew, HttpStatus.OK);
    }

    @GetMapping("inventory/used")
    public ResponseEntity<List<Vehicle>> usedVehicles() {

        List<Vehicle> allUsed = vehicles.findAllUsed();
        return  new ResponseEntity<>(allUsed, HttpStatus.OK);
    }

    @GetMapping("featured")
    public ResponseEntity<List<Vehicle>> allFeaturedVehicles() {

        List<Vehicle> allVehicles = vehicles.findAllAvailable();
        for(Vehicle v : allVehicles)
        {
            String imageName = v.getImage().substring(3);
            v.setImage(imageName);
        }
        return  new ResponseEntity<>(allVehicles,HttpStatus.OK);
    }

    @GetMapping("home/specials")
    public ResponseEntity<List<Specials> > getAllSpecials() {

        List<Specials> allSpecials = specials.findAll();
        return  new ResponseEntity<>(allSpecials,HttpStatus.OK);
    }


    @PostMapping("/contactus/message")
    private ResponseEntity<HttpStatus> getUsed(@RequestBody Message message, HttpServletRequest request, final ModelMap model) {

        messages.save(message);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/contactus/getMessages")
    public ResponseEntity<List<Message>> message()
    {
        List<Message> allMessages = messages.findAll();
        return new ResponseEntity<>(allMessages,HttpStatus.OK);
    }

    @GetMapping("/inventory/details/{id}")
    public ResponseEntity<Optional<Vehicle>> vehicleDetail(@PathVariable("id") Integer id) {

        Optional<Vehicle> vehicle = vehicles.findById(id);
        return  new ResponseEntity<>(vehicle,HttpStatus.OK);
    }

    @RequestMapping(value="/inventory/searchNewInventory")
    private ResponseEntity<List<Vehicle>> getFormData(@RequestBody VehicleSearchModel search, HttpServletRequest request, final ModelMap model) {

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

        return  new ResponseEntity<>(searchResult,HttpStatus.OK);

    }


    @RequestMapping(value="/inventory/searchUsedInventory")
    private ResponseEntity<List<Vehicle>> getUsed(@RequestBody VehicleSearchModel search, HttpServletRequest request, final ModelMap model) {

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

        return  new ResponseEntity<>(searchResult, HttpStatus.OK);

    }
}
