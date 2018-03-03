package com.lshadown.example.controller;

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

    @RequestMapping(value = "/user", method = GET)
    public Callable<String> getUserName() {
        return () -> "example controller";
    }
}
