package com.lshadown.example.models.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lshadown
 */

@Data
public class JwtRegisterRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String email;

    private String firstname;
    private String lastname;

    @Override
    public String toString() {
        return "JwtRegisterRequest{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}