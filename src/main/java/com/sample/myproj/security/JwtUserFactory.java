package com.sample.myproj.security;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sample.myproj.entities.Authority;
import com.sample.myproj.entities.User;

public final class JwtUserFactory {

	private static final Log logger = LogFactory.getLog(JwtUserFactory.class);
	
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
    	logger.info("inside create with User " + user.getUserName());
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getContact(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnabled()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
    	logger.info("inside mapToGrantedAuthorities ");
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
