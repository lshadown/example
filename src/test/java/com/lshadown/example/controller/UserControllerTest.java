package com.lshadown.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lshadown.example.BaseIntegrationTest;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

/**
 * @author lshadown
 */
public class UserControllerTest extends BaseIntegrationTest {

    @Test
    public void getUserInfo() throws Exception {
        //given
        MvcResult loginResult = mockMvc.perform(post("/auth/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsBytes(jwtAuthRequest)))
                .andReturn();

        mockMvc.perform(asyncDispatch(loginResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());

        JsonNode node = mapper.readTree(loginResult.getResponse().getContentAsString());
        String token = node.get("token").asText();

        //when
        MvcResult result = mockMvc.perform(get("/user/info")
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .with(user("test")))
                .andExpect(request().asyncResult(notNullValue()))
                .andReturn();

        //then
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", equalTo("test")))
                .andExpect(jsonPath("$.email", equalTo("test@test.pl")));
    }

    @Test(expected = MalformedJwtException.class)
    public void getUserInfoBadJwtTokenFormat() throws Exception {
        //when-then
        mockMvc.perform(get("/user/info")
                .header("Authorization", "Bearer token1232421")
                .header("Accept", "application/json")
                .with(user("test")))
                .andExpect(request().asyncResult(notNullValue()));
    }

    @Test
    public void getUserInfoUnauthorizedUser() throws Exception {
        //when-then
        mockMvc.perform(get("/user/info")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTIwODAyNzA4LCJpYXQiOjE1MjAxOTc5MDh9.dIRuo-tZPUx-EvJV5umBsBOluwvgzL44Q3kujFqqBLcrJCsPR-oIZnjPWfqM9uOUXBtrHFjNtGBQYTPK-NSTNA")
                .header("Accept", "application/json"))
                .andExpect(status().isUnauthorized());
    }
}