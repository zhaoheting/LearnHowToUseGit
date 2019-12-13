package com.example.LearnHowToUserGit.beans;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Map;

public class ZhtClaims extends DefaultClaims {

    private static final String USERNAME = "username";
    private static final String IDKEY = "IDKey";

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

    public String getIdKey() {
        return getString(IDKEY);
    }

    public ZhtClaims setIdKey(String idKey) {
        setValue(IDKEY, idKey);
        return this;
    }
}
