package com.ds.dealership.repositories;

import com.ds.dealership.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query(value = "SELECT DISTINCT sale_price FROM Vehicle", nativeQuery = true)
    List<String> findAllPrice();

    @Query(value = "SELECT DISTINCT year FROM Vehicle", nativeQuery = true)
    List<String> findAllYears();

    @Query(value = "SELECT * FROM vehicle WHERE mileage = 0", nativeQuery = true)
    List<Vehicle> findAllNew();

}
