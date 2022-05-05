package com.ds.dealership.repositories;

import com.ds.dealership.entities.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyRepository extends JpaRepository<Body,Integer> {
}
