package com.lshadown.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lshadown.example.entities.AuthorityEntity;
import com.lshadown.example.entities.UserEntity;
import com.lshadown.example.models.jwt.AuthorityName;
import com.lshadown.example.models.rest.JwtAuthRequest;
import com.lshadown.example.models.rest.JwtRegisterRequest;
import com.lshadown.example.repositories.AuthorityRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author lshadown
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExampleApplication.class)
@EnableWebMvc
@DataJpaTest
public abstract class BaseIntegrationTest {

    protected UserEntity user1;
    protected UserEntity user2;
    protected JwtRegisterRequest jwtRegisterRequest;
    protected JwtRegisterRequest jwtRegisterRequest2;
    protected JwtRegisterRequest jwtBadRegisterRequest;
    protected ObjectMapper mapper;
    protected MockMvc mockMvc;
    protected ObjectWriter ow;
    protected JwtAuthRequest jwtAuthRequest;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    protected WebApplicationContext context;



    @Before
    public void initialize() {
        AuthorityEntity authorityEntity = authorityRepository.findByName(AuthorityName.ROLE_USER);

        user1 = new UserEntity();
        user1.setUsername("user1");
        user1.setEmail("user1@user1.com");
        user1.setFirstname("User");
        user1.setLastname("User1");
        user1.setEnabled(true);
        user1.setLastPasswordResetDate(new Date());
        user1.setPassword("test");
        user1.setAuthorities(new ArrayList<AuthorityEntity>() {{
            add(authorityEntity);
        }});

        user2 = new UserEntity();
        user2.setUsername("user1");
        user2.setEmail("user1@user1.com");
        user2.setFirstname("User");
        user2.setLastname("User1");

        jwtRegisterRequest = new JwtRegisterRequest();
        jwtRegisterRequest.setEmail("test@test.com");
        jwtRegisterRequest.setFirstname("Test");
        jwtRegisterRequest.setLastname("Test1");
        jwtRegisterRequest.setUserName("user");
        jwtRegisterRequest.setPassword("test123");

        jwtRegisterRequest2 = new JwtRegisterRequest();
        jwtRegisterRequest2.setEmail("test@test.com");
        jwtRegisterRequest2.setFirstname("Test");
        jwtRegisterRequest2.setLastname("Test1");
        jwtRegisterRequest2.setUserName("user12");
        jwtRegisterRequest2.setPassword("test123");

        jwtBadRegisterRequest = new JwtRegisterRequest();
        jwtBadRegisterRequest.setFirstname("Test");
        jwtBadRegisterRequest.setLastname("Test1");
        jwtBadRegisterRequest.setUserName("user");

        this.mockMvc = webAppContextSetup(this.context).apply(springSecurity()).alwaysDo(print()).build();
        this.mapper = new ObjectMapper();
        this.ow = mapper.writer().withDefaultPrettyPrinter();
        jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setUserName("test");
        jwtAuthRequest.setPassword("test123");
    }
}
