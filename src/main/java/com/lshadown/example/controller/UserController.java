package com.lshadown.example.controller;

import com.lshadown.example.idgenerator.IId;
import com.lshadown.example.processing.UserProcessing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private IId id;

    @Autowired
    public UserController( UserDetailsService userDetailsService, IId id ) {
        super( userDetailsService );
        this.id = id;
    }

    @PreAuthorize("hasRole(T(com.lshadown.example.models.jwt.AuthorityName).ROLE_USER)")
    @RequestMapping(value = "/info", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Callable<?>getUserInfo(){
        String correlationId = id.generate();
        logger.info(correlationId + " Incoming request: get user info");
        return (Callable<Object>) () -> UserController.super.userInfo(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
