package com.lshadown.example.models.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lshadown
 */
@Data
public class JwtAuthRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
