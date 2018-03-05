package com.lshadown.example.controller;

import com.lshadown.example.processing.UserProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/user")
public class UserController extends UserProcessing {

    @Autowired
    public UserController( UserDetailsService userDetailsService ) {
        super( userDetailsService );
    }

    @PreAuthorize("hasRole(T(com.lshadown.example.models.jwt.AuthorityName).ROLE_USER)")
    @RequestMapping(value = "/info", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Callable<?>getUserInfo(){
        return (Callable<Object>) () -> UserController.super.userInfo(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
