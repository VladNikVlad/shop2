package com.vladyslavnicko.gmail.api.serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vladyslavnicko.gmail.DTO.MyUserDetails;
import com.vladyslavnicko.gmail.api.model.ProductAPI;
import com.vladyslavnicko.gmail.api.model.UserAPI;
import com.vladyslavnicko.gmail.api.service.APIService;
import com.vladyslavnicko.gmail.dictionary.TranslateService;
import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.User;
import com.vladyslavnicko.gmail.repository.UserRepository;
import com.vladyslavnicko.gmail.service.ProductService;
import com.vladyslavnicko.gmail.service.UserService;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class APIServiceImpl implements APIService{

	private final UserService userService;
	private final TranslateService translate;
	private final UserRepository userRepository;
	private final ProductService productService;
	
	@Override
	public User saveNewUser(User user) {
		if (user == null) {
			throw new ConflictException("userApi is null");
		} else if (StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getEmail())) {
			throw new ConflictException("labal_user_data_incomplete");
		}

		if (userService.findByEmail(user.getEmail()) != null) {
			throw new ConflictException("labal_user_exists");
		}

		try  {
			return userRepository.save(user);
		} catch(Exception e) {
			throw new ConflictException("Failed to create new user", e);
		}
	}

	@Override
	public ProductAPI saveProduct(ProductAPI product) {
		if (product == null) {
			throw new ConflictException("Product is null");
		}
		
		return ProductAPI.fromProduct(productService.saveProduct(ProductAPI.toProduct(product)));
	}

	@Override
	public ProductAPI findProductBy(long productId) {
		if (productId == 0) {
			throw new ConflictException("ProductId is 0");
		}
		
		return ProductAPI.fromProduct(productService.findProductBy(productId));
	}

}
