//package com.vladyslavnicko.gmail.rest.loginController;
//
//import java.util.Map;
//
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.vladyslavnicko.gmail.DTO.AccountDTO;
//import com.vladyslavnicko.gmail.DTO.UserInfo;
//import com.vladyslavnicko.gmail.DTO.result.BadRequestResult;
//import com.vladyslavnicko.gmail.DTO.result.ResultDTO;
//import com.vladyslavnicko.gmail.model.User;
//import com.vladyslavnicko.gmail.service.UserService;
//
//@RestController
//@RequestMapping("/login")
//public class LoginController {
//	
//	private UserService userService;
//
//	public LoginController(UserService userService) {
//		this.userService = userService;
//	}
//	
//	@GetMapping("/log")
//	public UserInfo login(@RequestParam("login") String login, @RequestParam("login") String password) {
//		User user = userService.findByLogin(login);
//		if (user == null) {
//			boolean chack = userService.chackPasssword(password, user);
//			if (chack) {
//				return UserInfo.getUserInfoFromUser(user);
//			}
//		}
//		return null;
//	}
//
////	@GetMapping("/googl_auth")
////	public UserInfo loginGoogl(OAuth2AuthenticationToken auth) {
////		Map<String, Object> attrs = auth.getPrincipal().getAttributes();
////
////		String email = (String) attrs.get("email");
////
////		User user = userService.findByEmail(email);
////		if (user != null) {
////			return UserInfo.getUserInfoFromUser(user);
////		}
////		return null;
////	}
//
//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	public ResponseEntity<ResultDTO> handleException() {
//		return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
//	}
//
//}
