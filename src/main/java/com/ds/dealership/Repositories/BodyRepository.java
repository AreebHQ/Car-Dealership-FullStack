package com.ds.dealership.Repositories;

import com.ds.dealership.Entities.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyRepository extends JpaRepository<Body,Integer> {
}
