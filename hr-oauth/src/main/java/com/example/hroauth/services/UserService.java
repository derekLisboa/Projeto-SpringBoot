package com.example.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hroauth.entities.User;
import com.example.hroauth.feignclients.UserFeignClient;

@Service
public class UserService implements UserDetailsService{
    
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email){

        User user = userFeignClient.findByEmail(email).getBody();
        if(user == null){
            logger.error("Email not found: ");
            throw new IllegalArgumentException("Email not found" + email);
        }
        logger.info("Email found: " + email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeignClient.findByEmail(username).getBody();
        if(user == null){
            logger.error("Email not found: ");
            throw new UsernameNotFoundException("Email not found" + username);
        }
        logger.info("Email found: " + username);
        return user;
    }
}