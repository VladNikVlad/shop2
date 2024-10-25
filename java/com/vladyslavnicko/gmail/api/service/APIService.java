package com.vladyslavnicko.gmail.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vladyslavnicko.gmail.api.model.BrandAPI;
import com.vladyslavnicko.gmail.api.model.CategoryAPI;
import com.vladyslavnicko.gmail.api.model.ImageAPI;
import com.vladyslavnicko.gmail.api.model.ProductAPI;
import com.vladyslavnicko.gmail.model.User;

public interface APIService {

	User saveNewUser(User user);

	ProductAPI saveProduct(ProductAPI product);

	ProductAPI findProductBy(long productId);

	List<ProductAPI> findProducts(String name, String category, String brand, int start, int limit);

	BrandAPI saveBrand(BrandAPI brandApi);

	ProductAPI updateProduct(long productId, ProductAPI product);

	CategoryAPI saveCategory(CategoryAPI categoryApi);

	ImageAPI uploadImage(MultipartFile file);
	
	List<ImageAPI> findImageByIds(List<Long> ids);
}
