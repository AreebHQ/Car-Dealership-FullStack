package com.ds.dealership.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    protected void configure(HttpSecurity http) throws Exception {
     // http.cors();
       http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("Admin")
                .antMatchers("/user/**").hasAnyAuthority("Sales","Admin")

                .and()
                .formLogin();
                //.loginPage("http://localhost:4200/login").permitAll();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider (){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

   /* @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }*/
}
