package com.sample.myproj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sample.myproj.security.JwtAuthenticationEntryPoint;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;
	
    
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//    }
//    
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder(11);
		return encoder;
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and()
		// we don't need CSRF because our token is invulnerable
				.csrf().disable().headers().frameOptions().disable();
	}
	
//	@Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors().and()
//                // we don't need CSRF because our token is invulnerable
//                .csrf().disable()
//
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//
//                // don't create session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//                .authorizeRequests()
//                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//
//                // allow anonymous resource requests
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.png",
//                        "/**/*.jpg",
//                        "/**/*.jpeg",
//                        "/**/*.bmp",
//                        "/**/*.gif",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
//                .antMatchers("/**/login/**")
//                .permitAll()
//                .antMatchers("/**/signup/**")
//                .permitAll()
//                .anyRequest().authenticated();
//
//        // Custom JWT based security filter
//        httpSecurity
//                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//        // disable page caching
//        httpSecurity.headers().cacheControl();
//    }
	
	
	
	
	
	
	
	
}
