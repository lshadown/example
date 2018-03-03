package com.lshadown.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger logger = LoggerFactory.getLogger( MainController.class );

    @RequestMapping(value = "/user", method = GET)
    public Callable<String> getUserName() {
        logger.info("Incoming request");
        return () -> "example controller test";
    }
}
