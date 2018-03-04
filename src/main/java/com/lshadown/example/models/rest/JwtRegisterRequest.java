package com.lshadown.example.models.rest;

import lombok.Data;

/**
 * @author lshadown
 */

@Data
public class JwtRegisterRequest {
    private String userName;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
}