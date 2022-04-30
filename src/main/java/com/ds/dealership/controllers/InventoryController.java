package com.ds.dealership.controllers;


import com.ds.dealership.entities.Vehicle;
import com.ds.dealership.model.SearchNewInventory;
import com.ds.dealership.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class InventoryController {

    @Autowired
    VehicleRepository vehicles;

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


    @GetMapping("/Inventory/details/{id}")
    public Optional<Vehicle> vehicleDetail(@PathVariable("id") Integer id) {

        Optional<Vehicle> vehicle = vehicles.findById(id);

        return  vehicle;
    }

    @RequestMapping(value="/Inventory/searchNewInventory")
    private List<Vehicle> getFormData(@RequestBody SearchNewInventory search, HttpServletRequest request, final ModelMap model) {

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
            searchResult = vehicles.findBySearchInput(minYear,maxYear,minPrice,maxPrice);
        } else {
            searchResult = vehicles.findBySearchInput(minYear,maxYear,minPrice,maxPrice,carMake,carModel);
        }

     return  searchResult;

    }


    @RequestMapping(value="/Inventory/searchUsedInventory")
    private List<Vehicle> getUsed(@RequestBody SearchNewInventory search, HttpServletRequest request, final ModelMap model) {

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
