package com.lshadown.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author lshadown
 */

@RestController
@RequestMapping("/main")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @PreAuthorize("hasRole(T(com.lshadown.example.models.jwt.AuthorityName).ROLE_USER)")
    @RequestMapping(value = "/user", method = GET)
    public Callable<String> getUserName() {
        return () -> "example controller test";
    }
}
