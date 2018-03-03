package com.lshadown.example.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lshadown
 */

@Entity
@Data
@Table(name="Role")
public class RoleEntity {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;
    private String roleName;

    public RoleEntity(){}

    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

}
