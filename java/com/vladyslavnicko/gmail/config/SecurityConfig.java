package com.vladyslavnicko.gmail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.vladyslavnicko.gmail.model.Role;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private static final String[] WHITE_LIST_URL = {
			"/api/v1/login",
			"/api/v1/dictionary",
			"/api/v1/auth/**",
			"/zip", 
			"/registration", 
			"/login", 
			"/css/**", 
			"/js/**"
    		};

	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;
	private final JwtAuthenticationFilter jwtAuthFilter;
	

	public SecurityConfig(AuthenticationProvider authenticationProvider, LogoutHandler logoutHandler,
			JwtAuthenticationFilter jwtAuthFilter) {
		super();
		this.authenticationProvider = authenticationProvider;
		this.logoutHandler = logoutHandler;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(req ->
		req.requestMatchers(WHITE_LIST_URL)
		.permitAll()
		.requestMatchers(GET, "/api/v1/product/**").hasAnyAuthority(Role.USER.name())
		.anyRequest()
		.authenticated()
				)
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout(logout ->
                logout.logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        );
		
//		.httpBasic(Customizer.withDefaults())
//		//		  		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//		.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//				)
//		.logout((logout) -> logout.permitAll());

		return http.build();

	}

}

