package com.lshadown.example.processing;

import com.lshadown.example.BaseIntegrationTest;
import com.lshadown.example.entities.UserEntity;
import com.lshadown.example.models.rest.JwtRegisterRequest;
import com.lshadown.example.repositories.UserJwtRepository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author lshadown
 */
public class AuthorizationProcessingTest extends BaseIntegrationTest {

    @Autowired
    private AuthorizationProcessing authorizationProcessing;

    @Autowired
    private UserJwtRepository userJwtRepository;


    @Test
    public void registerUser() {
        //given
        authorizationProcessing.registerUser(jwtRegisterRequest2);
        //when
        UserEntity found = userJwtRepository.findByUsername(jwtRegisterRequest2.getUserName());
        //then
        assertThat(found.getUsername()).isEqualTo(jwtRegisterRequest2.getUserName());
        assertThat(found.getEmail()).isEqualTo(jwtRegisterRequest2.getEmail());
        assertThat(found.getLastname()).isEqualTo(jwtRegisterRequest2.getLastname());
    }
}