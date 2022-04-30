package com.ds.dealership.controllers;


import com.ds.dealership.entities.Vehicle;
import com.ds.dealership.model.SearchNewInventory;
import com.ds.dealership.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


        System.out.println(minPrice +" "+ maxPrice +" "+ minYear +" "+ maxYear+ " "+ carMake + " " +carModel);

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

        System.out.println("After Validation");
        System.out.println(minPrice +" "+ maxPrice +" "+ minYear +" "+ maxYear+ " "+searchBar);


        if(searchBar.isEmpty() || searchBar.isBlank())
        {
            searchResult = vehicles.findBySearchInput(minYear,maxYear,minPrice,maxPrice);
        } else {
            searchResult = vehicles.findBySearchInput(minYear,maxYear,minPrice,maxPrice,carMake,carModel);
        }


        for(Vehicle vehicle : searchResult)
        {
            System.out.println(vehicle.getMake().getName() + " " + vehicle.getYear() + " " +  vehicle.getSalePrice());
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


        System.out.println(minPrice +" "+ maxPrice +" "+ minYear +" "+ maxYear+ " "+ carMake + " " +carModel);

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

        System.out.println("After Validation");
        System.out.println(minPrice +" "+ maxPrice +" "+ minYear +" "+ maxYear+ " "+searchBar);


        if(searchBar.isEmpty() || searchBar.isBlank())
        {
            searchResult = vehicles.findUsedBySearchInput(minYear,maxYear,minPrice,maxPrice);
        } else {
            searchResult = vehicles.findUsedBySearchInput(minYear,maxYear,minPrice,maxPrice,carMake,carModel);
        }


        for(Vehicle vehicle : searchResult)
        {
            System.out.println(vehicle.getMake().getName() + " " + vehicle.getYear() + " " +  vehicle.getSalePrice());
        }

        return  searchResult;

    }

}
