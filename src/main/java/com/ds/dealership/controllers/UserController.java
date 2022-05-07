package com.ds.dealership.controllers;

import com.ds.dealership.entities.User;
import com.ds.dealership.repositories.RoleRepository;
import com.ds.dealership.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository users;
    @Autowired
    RoleRepository roles;

    @GetMapping("/admin/users/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id) {

        Optional<User> user = users.findById(id);

        return user;
    }


   /* public void addUser()
    {
        List<Role> roleList = roles.findAll();


        User user = new User();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        user.setEmail("newUser@gmail.com");
        user.setPassword("abc123");
        user.setRole(roles.getById(1));

        users.save(user);

    } */
}
