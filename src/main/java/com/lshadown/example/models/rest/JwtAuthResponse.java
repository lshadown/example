package com.lshadown.example.models.rest;

import java.io.Serializable;

/**
 * @author lshadown
 */
public class JwtAuthResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
