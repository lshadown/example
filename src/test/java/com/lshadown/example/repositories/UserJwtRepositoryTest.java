package com.lshadown.example.repositories;

import com.lshadown.example.BaseIntegrationTest;
import com.lshadown.example.entities.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author lshadown
 */

public class UserJwtRepositoryTest extends BaseIntegrationTest {


    @Autowired
    private UserJwtRepository userJwtRepository;


    @Test
    public void findByUsernameFlyway() {
        //given
        // form flyway

        //when
        UserEntity found = userJwtRepository.findByUsername("test");

        //then
        assertThat(found.getUsername()).isEqualTo("test");
    }

    @Test()
    public void findByUsername() {
        //given
        userJwtRepository.save(user1);

        //when
        UserEntity found = userJwtRepository.findByUsername(user1.getUsername());

        //then
        assertThat(found.getUsername()).isEqualTo(user1.getUsername());
        assertThat(found.getEmail()).isEqualTo(user1.getEmail());
        assertThat(found.getLastPasswordResetDate()).isEqualTo(user1.getLastPasswordResetDate());

    }

    @Test(expected = ConstraintViolationException.class)
    public void findByUsernameBadValidation() {
        //given
        userJwtRepository.save(user2);

        //when
        UserEntity found = userJwtRepository.findByUsername(user2.getUsername());
    }
}