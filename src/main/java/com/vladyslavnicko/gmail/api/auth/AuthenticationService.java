package com.vladyslavnicko.gmail.api.auth;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladyslavnicko.gmail.DTO.MyUserDetails;
import com.vladyslavnicko.gmail.api.model.UserAPI;
import com.vladyslavnicko.gmail.api.service.APIService;
import com.vladyslavnicko.gmail.config.JwtService;
import com.vladyslavnicko.gmail.config.token.Token;
import com.vladyslavnicko.gmail.config.token.TokenRepository;
import com.vladyslavnicko.gmail.config.token.TokenType;
import com.vladyslavnicko.gmail.model.User;
import com.vladyslavnicko.gmail.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final APIService apiSevice;
	
	public AuthenticationResponse register(UserAPI request) {
		User user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.build();
		User savedUser = apiSevice.saveNewUser(user);
		String jwtToken = jwtService.generateToken(new MyUserDetails(user));
		String refreshToken = jwtService.generateRefreshToken(new MyUserDetails(user));
		saveUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder()
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.build();
	}

	public AuthenticationResponse authenticate(UserAPI request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getEmail(),
	            request.getPassword()
	        )
	    );
	    User user = repository.findByEmail(request.getEmail())
	        .orElseThrow();
	    String jwtToken = jwtService.generateToken(new MyUserDetails(user));
	    String refreshToken = jwtService.generateRefreshToken(new MyUserDetails(user));
	    revokeAllUserTokens(user);
	    saveUserToken(user, jwtToken);
	    return AuthenticationResponse.builder()
	        .accessToken(jwtToken)
	            .refreshToken(refreshToken)
	        .build();
	  }

	  private void saveUserToken(User user, String jwtToken) {
		 Token token = Token.builder()
	        .user(user)
	        .token(jwtToken)
	        .tokenType(TokenType.BEARER)
	        .expired(false)
	        .revoked(false)
	        .build();
	    tokenRepository.save(token);
	  }

	  private void revokeAllUserTokens(User user) {
		List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
	    if (validUserTokens.isEmpty())
	      return;
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenRepository.saveAll(validUserTokens);
	  }

	  public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      User user = this.repository.findByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, new MyUserDetails(user))) {
	        String accessToken = jwtService.generateToken(new MyUserDetails(user));
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
	        AuthenticationResponse authResponse = AuthenticationResponse.builder()
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
	  }

}
