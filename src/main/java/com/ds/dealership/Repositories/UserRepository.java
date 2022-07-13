package com.ds.dealership.Repositories;

import com.ds.dealership.Entities.Role;
import com.ds.dealership.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(Role role);
    Optional<User> findByEmail(String email);
}
