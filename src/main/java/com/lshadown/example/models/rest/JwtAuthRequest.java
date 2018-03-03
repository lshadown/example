package com.lshadown.example.models.rest;

/**
 * @author lshadown
 */
public class JwtAuthRequest {
    private String userName;
    private String password;

    public JwtAuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public JwtAuthRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
