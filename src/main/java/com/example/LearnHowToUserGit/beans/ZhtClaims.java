package com.example.LearnHowToUserGit.beans;

import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Map;

public class ZhtClaims extends DefaultClaims {

    private static final String USERNAME = "username";

    /**
     * Default constructor.
     */
    public ZhtClaims() {
        super();
    }

    /**
     * Constructor from map.
     *
     * @param map Parameters.
     */
    public ZhtClaims(Map<String, Object> map) {
        super(map);
    }

    public String getUsername() {
        return getString(USERNAME);
    }

    public ZhtClaims setUsername(String username) {
        setValue(USERNAME, username);
        return this;
    }
}
