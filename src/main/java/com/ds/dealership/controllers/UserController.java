package com.ds.dealership.controllers;

import com.ds.dealership.entities.Role;
import com.ds.dealership.entities.User;
import com.ds.dealership.repositories.RoleRepository;
import com.ds.dealership.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository users;
    @Autowired
    RoleRepository roles;


   /* @GetMapping("/")
    public String index(Model model) {
       // model.addAttribute("stores", users.findAll());
        addUser();
        return "index";
    }
*/

    @GetMapping("/users")
    public List<User> allUsers() {
        List<User> allUsers = users.findAll();
        System.out.println("got users called ====== ");
        return users.findAll();
    }


    public void addUser()
    {
        List<Role> roleList = roles.findAll();


        User user = new User();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        user.setEmail("newUser@gmail.com");
        user.setPassword("abc123");
        user.setRole(roles.getById(1));

        users.save(user);

    }
}
