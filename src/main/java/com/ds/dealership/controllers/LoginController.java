package com.ds.dealership.controllers;

import com.ds.dealership.config.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    MyUserDetailService myUserDetailService;



    @PostMapping("/register")
    public String register()
    {
       // myUserDetailService.loadUserByUsername()

        return "it works";
    }
}
