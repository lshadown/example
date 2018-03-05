package com.lshadown.example.repositories;

import com.lshadown.example.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lshadown
 */

public interface UserJwtRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    void deleteByUsername(String username);
}
