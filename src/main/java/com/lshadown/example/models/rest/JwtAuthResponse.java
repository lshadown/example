package com.lshadown.example.models.rest;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lshadown
 */
@Data
public class JwtAuthResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
}
