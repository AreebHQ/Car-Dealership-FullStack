package com.ds.dealership.config;

import com.ds.dealership.entities.User;
import com.ds.dealership.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.findByEmail(username);
        System.out.println("user called in loadByUserName" + username);
//        System.out.println(user.get().getFirstName() +" "+ user.get().getEmail() + " " + user.get().getPassword());
        user.orElseThrow(()->new UsernameNotFoundException("Not Found: " + username));

        return user.map(MyUserDetails::new).get();
    }
}
