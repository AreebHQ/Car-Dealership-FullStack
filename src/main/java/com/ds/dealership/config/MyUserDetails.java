package com.ds.dealership.config;

import com.ds.dealership.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private List<SimpleGrantedAuthority> authorities;

    public MyUserDetails(User user)
    {

        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.userName = email;
        this.password = user.getPassword();
        this.role = user.getRole().getRoleName();
        this.authorities = Arrays.stream(user.getRole().getRoleName().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
