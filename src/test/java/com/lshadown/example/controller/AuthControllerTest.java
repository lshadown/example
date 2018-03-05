package com.lshadown.example.controller;

import com.lshadown.example.BaseIntegrationTest;
import com.lshadown.example.models.rest.JwtAuthRequest;
import com.lshadown.example.repositories.UserJwtRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lshadown
 */
public class AuthControllerTest extends BaseIntegrationTest {

    @Autowired
    private UserJwtRepository userJwtRepository;

    private JwtAuthRequest jwtBadAuthRequest;


    @Before
    public void setup() {
        jwtBadAuthRequest = new JwtAuthRequest();
        jwtBadAuthRequest.setUserName("test");
    }

    @After
    public void clean() {
        userJwtRepository.deleteByUsername(jwtRegisterRequest.getUserName());
    }

    @Test
    public void authorization() throws Exception {
        //when
        MvcResult result = mockMvc.perform(post("/auth/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsBytes(jwtAuthRequest)))
                .andReturn();

        //then
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void authorizationMethodNotSupported() throws Exception {
        //when -then
        mockMvc.perform(put("/auth/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsBytes(jwtAuthRequest)))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();

    }

    @Test
    public void authorizationBadRequest() throws Exception {
        //when -then
        mockMvc.perform(post("/auth/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsBytes(jwtBadAuthRequest)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }


    @Test
    public void registration() throws Exception {
        //when
        MvcResult result = mockMvc.perform(post("/auth/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsBytes(jwtRegisterRequest)))
                .andReturn();

        //then
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk());
    }

    @Test
    public void registrationMethodNotSupported() throws Exception {
        //when -then
        mockMvc.perform(put("/auth/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsBytes(jwtRegisterRequest)))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();

    }

    @Test
    public void registrationBadRequest() throws Exception {
        //when -then
        mockMvc.perform(post("/auth/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsBytes(jwtBadRegisterRequest)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
}