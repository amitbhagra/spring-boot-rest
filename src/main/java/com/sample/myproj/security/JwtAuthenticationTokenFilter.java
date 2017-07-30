package com.sample.myproj.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		logger.info("inside dofilterInternal");
		String authToken = request.getHeader(this.tokenHeader);

		if (authToken != null) {
			// authToken.startsWith("Bearer ")
			// String authToken = header.substring(7);
			String username = jwtTokenUtil.getUsernameFromToken(authToken);

			logger.info("checking authentication for user " + username);

			if (username != null
					&& SecurityContextHolder.getContext().getAuthentication() == null) {

				// It is not compelling necessary to load the use details from
				// the database. You could also store the information
				// in the token and read it from it. It's up to you ;)
				UserDetails userDetails = this.userDetailsService
						.loadUserByUsername(username);

				// For simple validation it is completely sufficient to just
				// check the token integrity. You don't have to call
				// the database compellingly. Again it's up to you ;)
				if (jwtTokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication
							.setDetails(new WebAuthenticationDetailsSource()
									.buildDetails(request));
					logger.info("authenticated user " + username
							+ ", setting security context");
					SecurityContextHolder.getContext().setAuthentication(
							authentication);
				} else {
					logger.info("Invalid Token");
				}
			}
		}

		chain.doFilter(request, response);
	}
}