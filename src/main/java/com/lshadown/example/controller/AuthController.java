package com.lshadown.example.controller;

import com.lshadown.example.jwt.utils.JwtTokenUtil;
import com.lshadown.example.models.rest.JwtAuthRequest;
import com.lshadown.example.models.rest.JwtAuthResponse;
import com.lshadown.example.models.rest.JwtRegisterRequest;
import com.lshadown.example.processing.AuthorizationProcessing;
import com.lshadown.example.repositories.AuthorityRepository;
import com.lshadown.example.repositories.UserJwtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lshadown
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends AuthorizationProcessing {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                          UserDetailsService userDetailsService, UserJwtRepository userRepository,
                          PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {

        super(userRepository, passwordEncoder, authorityRepository);

        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authorization(@RequestBody JwtAuthRequest jwtAuthRequest) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtAuthRequest.getUserName(),
                        jwtAuthRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody @Valid JwtRegisterRequest jwtRegisterRequest) {
        return AuthController.super.registerUser(jwtRegisterRequest);
    }
}
