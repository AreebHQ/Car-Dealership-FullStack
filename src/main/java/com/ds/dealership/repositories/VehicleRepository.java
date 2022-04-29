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

    @Query(value = "SELECT md.name, m.name, v.* FROM Vehicle v join make m on m.make_id = v.make join" +
            " model md on md.model_id = v.model WHERE v.mileage = 0 " +
            "AND year BETWEEN ?1 AND ?2 AND sale_price BETWEEN ?3 AND ?4 AND m.name = ?5  OR md.name =  ?6", nativeQuery = true)
    List<Vehicle> findBySearchInput(String minYear, String maxYear, String minPrice, String maxPrice, String make, String model);


    @Query(value = "SELECT md.name, m.name, v.* FROM Vehicle v join make m on m.make_id = v.make join" +
            " model md on md.model_id = v.model WHERE v.mileage = 0 " +
            "AND year BETWEEN ?1 AND ?2 AND sale_price BETWEEN ?3 AND ?4", nativeQuery = true)
    List<Vehicle> findBySearchInput(String minYear, String maxYear, String minPrice, String maxPrice);


}
