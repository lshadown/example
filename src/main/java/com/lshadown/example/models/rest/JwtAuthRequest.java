package com.lshadown.example.models.rest;

import lombok.Data;

/**
 * @author lshadown
 */
@Data
public class JwtAuthRequest {
    private String userName;
    private String password;
}
