package com.lshadown.example.processing;

import com.lshadown.example.models.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserProcessing {

    private UserDetailsService userDetailsService;

    @Autowired
    public UserProcessing(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    protected JwtUser userInfo(String userName) {
       return (JwtUser) userDetailsService.loadUserByUsername(userName);
    }
}
