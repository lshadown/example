package com.lshadown.example.repositories;

import com.lshadown.example.entities.AuthorityEntity;
import com.lshadown.example.models.jwt.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lshadown
 */

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    AuthorityEntity findByName(AuthorityName authorityName);
}