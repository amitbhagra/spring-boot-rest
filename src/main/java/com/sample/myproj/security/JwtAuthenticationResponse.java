package com.sample.myproj.security;

import java.io.Serializable;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    
    private final UserDetails userDetails;

    public JwtAuthenticationResponse(String token, UserDetails details) {
        this.token = token;
        this.userDetails = details;
    }

    public String getToken() {
        return this.token;
    }
    
    public UserDetails getUserDetails() {
        return this.userDetails;
    }
}
