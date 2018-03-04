package com.lshadown.example.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lshadown
 */

@Data
@Entity
@Table(name = "UserRole")
public class UserRoleEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn( name = "role_id")
    private AuthorityEntity role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}