package com.vladyslavnicko.gmail.api.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vladyslavnicko.gmail.DTO.MyUserDetails;
import com.vladyslavnicko.gmail.api.model.BrandAPI;
import com.vladyslavnicko.gmail.api.model.ProductAPI;
import com.vladyslavnicko.gmail.api.model.UserAPI;
import com.vladyslavnicko.gmail.api.service.APIService;
import com.vladyslavnicko.gmail.dictionary.TranslateService;
import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Brand;
import com.vladyslavnicko.gmail.model.Product;
import com.vladyslavnicko.gmail.model.User;
import com.vladyslavnicko.gmail.repository.UserRepository;
import com.vladyslavnicko.gmail.service.BrandService;
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
	private final BrandService brandService;
	
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

	@Override
	public List<ProductAPI> findProducts(String name, String category, String brand, int start, int limit) {
		Pageable pageable = PageRequest.of(start, limit);
		Page<Product> page = productService.findProducts(name, category, brand, pageable);
		return ProductAPI.fromProducts(page.getContent());
	}

	@Override
	public BrandAPI saveBrand(BrandAPI brandApi) {
		if (brandApi == null) {
			throw new ConflictException("Brand is null");
		}
		
		Brand b = brandService.saveNewBrand(BrandAPI.toBrand(brandApi));
		
		return BrandAPI.fromBrand(b);
	}

}
