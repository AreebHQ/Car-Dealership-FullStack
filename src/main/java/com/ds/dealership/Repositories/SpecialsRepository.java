package com.ds.dealership.Repositories;

import com.ds.dealership.Entities.Specials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialsRepository extends JpaRepository<Specials, Integer> {
}
