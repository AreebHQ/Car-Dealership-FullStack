package com.ds.dealership.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('Sales')")
    public String userAccess() {
        return "User Content.";
    }
    @GetMapping("/Sales")
    @PreAuthorize("hasAuthority('Sales') or hasAuthority('Admin')")
    public String moderatorAccess() {
        return "Sales Board.";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('Admin')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
