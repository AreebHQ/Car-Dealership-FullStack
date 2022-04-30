package com.ds.dealership.repositories;

import com.ds.dealership.entities.Specials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialsRepository extends JpaRepository<Specials, Integer> {
}
