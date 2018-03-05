package com.lshadown.example.processing;

import com.lshadown.example.BaseIntegrationTest;
import com.lshadown.example.models.jwt.JwtUser;
import com.lshadown.example.repositories.UserJwtRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author lshadown
 */

public class UserProcessingTest extends BaseIntegrationTest {

    @Autowired
    private UserProcessing userProcessing;

    @Autowired
    private UserJwtRepository userJwtRepository;

    @Test
    public void userInfo() {
        //given
        userJwtRepository.save(user1);
        //when
        JwtUser found = userProcessing.userInfo(user1.getUsername());
        //then
        assertThat(found.getUsername()).isEqualTo(user1.getUsername());
        assertThat(found.getEmail()).isEqualTo(user1.getEmail());
        assertThat(found.getLastPasswordResetDate().equals(user1.getLastPasswordResetDate()));
    }
}