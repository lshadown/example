package com.lshadown.example.models.jwt;

/**
 * @author lshadown
 */
public enum AuthorityName {
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    AuthorityName(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}