package com.example.LearnHowToUserGit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {
    private static final long serialVersionUID = 1L;
    @JsonProperty("token")
    private String token = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
