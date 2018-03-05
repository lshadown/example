package com.lshadown.example.controller;

import com.lshadown.example.idgenerator.IId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private IId id;

    @Autowired
    public MainController(IId id){
        this.id = id;
    }

    @PreAuthorize("hasRole(T(com.lshadown.example.models.jwt.AuthorityName).ROLE_USER)")
    @RequestMapping(value = "/example", method = GET)
    public Callable<String> example() {
        String correlationId = id.generate();
        logger.info(correlationId + " Incoming request ");
        return () -> "example controller";
    }
}
