package com.vladyslavnicko.gmail.api.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vladyslavnicko.gmail.api.model.BrandAPI;
import com.vladyslavnicko.gmail.api.model.CategoryAPI;
import com.vladyslavnicko.gmail.api.model.ImageAPI;
import com.vladyslavnicko.gmail.api.model.ProductAPI;
import com.vladyslavnicko.gmail.api.service.APIService;
import com.vladyslavnicko.gmail.dictionary.TranslateService;
import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Brand;
import com.vladyslavnicko.gmail.model.Category;
import com.vladyslavnicko.gmail.model.Image;
import com.vladyslavnicko.gmail.model.Product;
import com.vladyslavnicko.gmail.model.User;
import com.vladyslavnicko.gmail.repository.BrandRepository;
import com.vladyslavnicko.gmail.repository.ImageRepository;
import com.vladyslavnicko.gmail.repository.UserRepository;
import com.vladyslavnicko.gmail.service.BrandService;
import com.vladyslavnicko.gmail.service.CategoryService;
import com.vladyslavnicko.gmail.service.ImageService;
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
	private final CategoryService categoryService;
	private final ImageService imageService;
	private final ImageRepository imageRepository;
	
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

	@Override
	public ProductAPI updateProduct(long productId, ProductAPI productApi) {
		if (productApi == null) {
			throw new ConflictException("product is null");
		}
		
		Product product = productService.updateProduct(productId, ProductAPI.toProduct(productApi));
		
		return ProductAPI.fromProduct(product);
	}

	@Override
	public CategoryAPI saveCategory(CategoryAPI categoryApi) {
		if (categoryApi == null) {
			throw new ConflictException("categoryApi is null");
		}
		
		Category c = categoryService.createNewCategory(CategoryAPI.toCategory(categoryApi));
		return CategoryAPI.fromCategory(c);
	}

	@Override
	public ImageAPI uploadImage(MultipartFile file) {

		if (file == null || file.isEmpty()) {
			throw new ConflictException("File is empty");
		}
		
		Image i = imageService.uploadImage(file);
		return ImageAPI.fromImage(i);
	}

	@Override
	public List<ImageAPI> findImageByIds(List<Long> ids) {
		List<Image> i = imageService.findImageByIds(ids);
		return ImageAPI.fromImages(i);
	}
	
	

}
