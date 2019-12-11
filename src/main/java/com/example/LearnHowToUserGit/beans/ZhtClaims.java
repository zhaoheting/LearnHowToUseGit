package com.example.LearnHowToUserGit.beans;

import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Map;

public class ZhtClaims extends DefaultClaims {

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
}
