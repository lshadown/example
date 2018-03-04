package com.lshadown.example.processing;

import com.lshadown.example.entities.AuthorityEntity;
import com.lshadown.example.entities.UserEntity;
import com.lshadown.example.models.jwt.AuthorityName;
import com.lshadown.example.models.rest.JwtRegisterRequest;
import com.lshadown.example.repositories.AuthorityRepository;
import com.lshadown.example.repositories.UserJwtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author lshadown
 */

public class AuthorizationProcessing {

    private UserJwtRepository userRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;

    public AuthorizationProcessing(UserJwtRepository userRepository, PasswordEncoder passwordEncoder,
                                   AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    protected ResponseEntity<?> registerUser(JwtRegisterRequest jwtRegisterRequest) {
        AuthorityEntity authority = authorityRepository.findByName(AuthorityName.ROLE_USER);


        UserEntity user = new UserEntity();
        user.setUsername(jwtRegisterRequest.getUserName());
        user.setPassword(passwordEncoder.encode(jwtRegisterRequest.getPassword()));
        user.setEmail(jwtRegisterRequest.getEmail());
        user.setFirstname(jwtRegisterRequest.getFirstname());
        user.setLastname(jwtRegisterRequest.getLastname());
        user.setEnabled(true);
        user.setLastPasswordResetDate(new Date());
        user.setAuthorities(new ArrayList<AuthorityEntity>() {
            {
                add(authority);
            }
        });

        userRepository.save(user);


        return ResponseEntity.ok("");
    }
}