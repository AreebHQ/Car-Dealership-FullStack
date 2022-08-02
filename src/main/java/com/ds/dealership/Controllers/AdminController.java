package com.ds.dealership.Controllers;

import com.ds.dealership.Entities.*;
import com.ds.dealership.Models.VehicleSearchModel;
import com.ds.dealership.Models.UserModel;
import com.ds.dealership.Models.VehicleModel;
import com.ds.dealership.Services.DealershipServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  DealershipServiceLayer service;

    // ============================================================
    //              Users
    // ============================================================

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
      return service.getAllUsers();
    }


    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer userId) {

      return service.deleteUser(userId);
    }


  @PreAuthorize("hasAuthority('Admin')")
  @PostMapping("/addUser")
  public ResponseEntity<?> registerUser(@RequestBody UserModel signUpRequest) {

    return service.registerUser(signUpRequest);
  }

  @PreAuthorize("hasAuthority('Admin')")
  @PutMapping("/updateUser/{id}")
  public ResponseEntity<User> updateUser(@RequestBody UserModel user, @PathVariable("id") Integer id)
  {
    //debug
    System.out.println("User id: "+ id);
    System.out.println("user detail: " + user.getEmail() + " roles: " + user.getRole() );

    return service.updateUser(user, id);
  }

    // ============================================================
    //              Vehicle
    // ============================================================

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/editVehicle")
    public ResponseEntity<Vehicle> editVehicle(@RequestBody VehicleModel vehicle)
    {
      return service.editVehicle(vehicle);
    }


    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody VehicleModel vehicle )
    {
      //debug
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


      return service.addVehicle(vehicle);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping ("/deleteVehicle/{id}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable("id") Integer id)
    {
      return service.deleteVehicle(id);
    }


    @RequestMapping(value="/vehicles")
    public ResponseEntity<List<Vehicle>> getAllAvailableVehicles(@RequestBody VehicleSearchModel search, HttpServletRequest request, final ModelMap model) {

      return service.getAllAvailableVehicles(search,request,model);
    }


}
