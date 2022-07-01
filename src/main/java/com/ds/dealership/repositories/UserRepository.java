package com.ds.dealership.repositories;

import com.ds.dealership.entities.Role;
import com.ds.dealership.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(Role role);
    //User findByEmail(String email);
    Optional<User> findByEmail(String email);
}
