package com.lshadown.example.repositories;

import com.lshadown.example.ExampleApplication;
import com.lshadown.example.entities.AuthorityEntity;
import com.lshadown.example.models.jwt.AuthorityName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ExampleApplication.class)
@DataJpaTest
public class AuthorityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void findByNameAdminRole() {
        //given
        // form flyway

        //when
        AuthorityEntity found = authorityRepository.findByName( AuthorityName.ROLE_ADMIN );

        //then
        assertThat(found.getName()).isEqualTo( AuthorityName.ROLE_ADMIN );

    }

    @Test
    public void findByNameUserRole() {
        //given
        // form flyway

        //when
        AuthorityEntity found = authorityRepository.findByName( AuthorityName.ROLE_USER );

        //then
        assertThat(found.getName()).isEqualTo( AuthorityName.ROLE_USER );

    }
}