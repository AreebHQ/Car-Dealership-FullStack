package com.ds.dealership.controllers;

import com.ds.dealership.entities.*;
import com.ds.dealership.models.PurchaseModel;
import com.ds.dealership.models.SearchNewInventoryModel;
import com.ds.dealership.models.UserModel;
import com.ds.dealership.models.VehicleModel;
import com.ds.dealership.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // ============================================================
    //              User
    // ============================================================

    @GetMapping("/admin/users")
    public List<User> allUsers() {
        List<User> allUsers = users.findAll();
        return allUsers;
    }

    @PostMapping("/admin/deleteUser")
    public void deleteUser(@RequestBody Integer userId) {
        System.out.println("USer ID integer: " + userId);
        //users.delete(users.getById(userId));
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


    // ============================================================
    //              Admin
    // ============================================================

    @GetMapping("/admin/vehicleColors")
    public List<Color> vehicleColors() {
        List<Color> colorsList = colors.findAll();
        return  colorsList;
    }

    @GetMapping("/admin/vehicleBodies")
    public List<Body> vehicleBody() {

        List<Body> allBodies = bodies.findAll();
        return  allBodies;
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


    @PostMapping("/admin/editVehicle")
    public String editVehicle(@RequestBody VehicleModel vehicle , HttpServletRequest request)
    {


        String imagePath="";
        if(vehicle.getImage().contains("../images/"))
        {
            imagePath = vehicle.getImage();

        } else{
            String []imageName = vehicle.getImage().split("\\\\");
            imagePath = "../images/" + imageName[imageName.length-1]; }


        Vehicle editVehicle = vehicles.getById(vehicle.getId());
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
        System.out.println(vehicle.getId());
        System.out.println(vehicle.getMake());
        System.out.println(vehicle.getModel());
        System.out.println(vehicle.getBody());
        System.out.println(vehicle.getType());
        System.out.println(vehicle.getBodyColor());
        System.out.println(vehicle.getInteriorColor());
        System.out.println(vehicle.getYear());
        System.out.println(vehicle.getMileage());
        System.out.println(vehicle.getMrspPrice());
        System.out.println(vehicle.getSalePrice());
        System.out.println(vehicle.getTransmission());
        System.out.println(vehicle.getVinNumber());
        System.out.println(vehicle.getDescription());
        System.out.println(vehicle.getImage());

        String imagePath="";
        if(vehicle.getImage().isEmpty() || vehicle.getImage().isBlank())
        {
            imagePath = "../images/defaultcar.jpeg";
        }
        else {

            if(vehicle.getImage().contains("../images/"))
            {
                imagePath = vehicle.getImage();

            } else{
                String []imageName = vehicle.getImage().split("\\\\");
                imagePath = "../images/" + imageName[imageName.length-1]; }
        }


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

    @PostMapping("/admin/deleteVehicle/{id}")
    public String deleteVehicle(@PathVariable("id") Integer id)
    {
        vehicles.deleteById(id);
        return "success";
    }




    // ============================================================
    //      Inventory
    // ============================================================

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
