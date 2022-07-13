package com.ds.dealership.Controllers;
import com.ds.dealership.Entities.*;
import com.ds.dealership.Models.VehicleSearchModel;
import com.ds.dealership.Repositories.*;
import org.apache.catalina.users.SparseUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    MessageRepository messages;

    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @RequestMapping(value="/inventory/searchVehicles")
    private ResponseEntity<List<Vehicle>> getAllAvailableVehicles(@RequestBody VehicleSearchModel search, HttpServletRequest request, final ModelMap model) {

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
            searchResult = vehicles.findAllAvailable(minYear,maxYear,minPrice,maxPrice);
        } else {
            searchResult = vehicles.findAllAvailable(minYear,maxYear,minPrice,maxPrice,carMake,carModel);
        }

        return  new ResponseEntity<>(searchResult, HttpStatus.OK);

    }



    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @PostMapping("/purchase/{id}")
    public ResponseEntity<Vehicle> savePurchasedVehicle(@PathVariable("id") Integer id, @RequestBody Purchase purchase)
    {

        User user = users.getById(1);
        Optional<Vehicle> vehicle = vehicles.findById(id);
        purchase.setVehicleId(vehicle.get());
        purchase.setUserId(user);
        purchases.save(purchase);
        vehicle.get().setSold(true);
        vehicles.save(vehicle.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @GetMapping("/index")
    public ResponseEntity<List<Vehicle>> allVehicles() {

        System.out.println("sales/index called");
        List<Vehicle> allVehicles = vehicles.findAllAvailable();
        return  new ResponseEntity<>(allVehicles,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @GetMapping("/contactus/getMessages")
    public ResponseEntity<List<Message>> message()
    {
        List<Message> allMessages = messages.findAll();
        return new ResponseEntity<>(allMessages,HttpStatus.OK);
    }

    @GetMapping("/models")
    public List<Model> getAllModels() {
        List<Model> modelsList = models.findAll();

        return modelsList;
    }


    @GetMapping("/makes")
    public ResponseEntity<List<Make>> getAllMakes() {

        List<Make> makeList = makes.findAll();

        return new ResponseEntity<>(makeList,HttpStatus.OK);
    }

      @GetMapping("/vehicleColors")
    public ResponseEntity< List<Color>> vehicleColors() {
        List<Color> colorsList = colors.findAll();
        return  new ResponseEntity<>(colorsList,HttpStatus.OK);
    }


    @GetMapping("/vehicleBodies")
    public ResponseEntity<List<Body>> vehicleBody() {

        List<Body> allBodies = bodies.findAll();
        return  new ResponseEntity<>(allBodies, HttpStatus.OK);
    }



}
