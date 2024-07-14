package com.vladyslavnicko.gmail.config;

//import com.vladyslavnicko.gmail.DTO.AccountDTO;
//import com.vladyslavnicko.gmail.DTO.UserInfo;
//import com.vladyslavnicko.gmail.model.Role;
//import com.vladyslavnicko.gmail.model.User;
//import com.vladyslavnicko.gmail.service.UserService;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@Component
public class AuthHandler {
//extends SavedRequestAwareAuthenticationSuccessHandler {
//
//	UserService userService;
//
//	public AuthHandler(UserService userService) {
//		this.userService = userService;
//	}
//
//	@Override
//	public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
//		OAuth2User auth = token.getPrincipal();
//
//		Map<String, Object> attributes = auth.getAttributes();
//		User user = userService.findByEmail((String) attributes.get("email"));
//		if (user == null) {
//			saveUser((String) attributes.get("email"), (String) attributes.get("name"));
//			response.sendRedirect("/");
//		} 
//		super.onAuthenticationSuccess(request, response, authentication);
////		        httpServletResponse.sendRedirect("/");
//	}
//
//    private void saveUser(String email, String name) {
//		User user = new User();
//		user.setFirstName(name);
//		user.setEmail(email);
//		user.setRole(Role.USER);
//		userService.saveUser(user);
//	}
}
