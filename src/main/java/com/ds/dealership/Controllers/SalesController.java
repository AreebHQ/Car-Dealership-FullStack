package com.ds.dealership.Controllers;
import com.ds.dealership.Entities.*;
import com.ds.dealership.Models.VehicleSearchModel;
import com.ds.dealership.Services.DealershipServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    DealershipServiceLayer service;

     @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @RequestMapping(value="/searchVehicles")
    public ResponseEntity<List<Vehicle>> getAllAvailableVehicles(@RequestBody VehicleSearchModel search, HttpServletRequest request, final ModelMap model) {

      return service.getAllAvailableVehicles(search,request,model);
    }

    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @PostMapping("/purchase/{id}")
    public ResponseEntity<Vehicle> savePurchasedVehicle(@PathVariable("id") Integer id, @RequestBody Purchase purchase)
    {
      return service.savePurchasedVehicle(id,purchase);
    }

    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @GetMapping("/index")
    public ResponseEntity<List<Vehicle>> allVehicles() {

      return service.allVehicles();
    }

    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    @GetMapping("/contactus/getMessages")
    public ResponseEntity<List<Message>> message()
    {
      return service.message();
    }

    @GetMapping("/models")
    public List<Model> getAllModels() {

     return service.getAllModels();
    }


    @GetMapping("/makes")
    public ResponseEntity<List<Make>> getAllMakes() {

     return service.getAllMakes();
    }

      @GetMapping("/vehicleColors")
    public ResponseEntity< List<Color>> vehicleColors() {

        return service.vehicleColors();
    }


    @GetMapping("/vehicleBodies")
    public ResponseEntity<List<Body>> vehicleBody() {

      return service.vehicleBody();
    }



}
