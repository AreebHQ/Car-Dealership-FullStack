package com.ds.dealership.repositories;

import com.ds.dealership.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

}
