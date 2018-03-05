package com.lshadown.example.jwt;

import com.lshadown.example.entities.UserEntity;
import com.lshadown.example.repositories.UserJwtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lshadown
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserJwtRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserJwtRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (null == user) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
