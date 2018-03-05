package com.lshadown.example.repositories;

import com.lshadown.example.BaseIntegrationTest;
import com.lshadown.example.entities.AuthorityEntity;
import com.lshadown.example.models.jwt.AuthorityName;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class AuthorityRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void findByNameAdminRole() {
        //given
        // form flyway

        //when
        AuthorityEntity found = authorityRepository.findByName(AuthorityName.ROLE_ADMIN);

        //then
        assertThat(found.getName()).isEqualTo(AuthorityName.ROLE_ADMIN);
    }

    @Test
    public void findByNameUserRole() {
        //given
        // form flyway

        //when
        AuthorityEntity found = authorityRepository.findByName(AuthorityName.ROLE_USER);

        //then
        assertThat(found.getName()).isEqualTo(AuthorityName.ROLE_USER);

    }
}