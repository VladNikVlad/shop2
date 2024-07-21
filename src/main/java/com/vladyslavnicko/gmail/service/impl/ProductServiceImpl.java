package com.vladyslavnicko.gmail.service.impl;

import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Category;
import com.vladyslavnicko.gmail.model.Product;
import com.vladyslavnicko.gmail.repository.ProductRepository;
import com.vladyslavnicko.gmail.service.ProductService;
import com.vladyslavnicko.gmail.service.UserService;

import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public Product saveProduct(Product product) {
    	if (product == null) {
    		throw new ConflictException("Product is null");
    	}
    	
    	if (StringUtils.isBlank(product.getName()) || StringUtils.isBlank(product.getDescription()) 
    			|| product.getPrice().compareTo(BigDecimal.ZERO) == 0) {
    		throw new ConflictException("Name or description is empty or prise equals zero");
    	}
    	
    	Product oldProduct = productRepository.findProductByName(product.getName());
    	if (oldProduct != null) {
    		if (StringUtils.equals(oldProduct.getDescription(), product.getDescription())) {
    			throw new ConflictException("Such a product already exists");
    		}
    	}
    	
    	product.setCreatorUser(userService.getCurrentUser());
    	product.setCreateDate(new Date());
    	
    	product = productRepository.save(product);
    	
       return product;
    }

    @Override
    public void deleteProductId(long id) {
    }

    @Override
    public Product findProductById(long id) {
        return findProductById(id);
    }

    @Override
    public List<Product> findByNameProduct(String name) {
        return findByNameProduct(name);
    }

    @Override
    public Product updateProduct(Product product) {
        Product oldProduct = findProductById(product.getId());
        if (oldProduct == null) {
            throw new ConflictException("Invalid id");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice) {
        return productRepository.findByPrice(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByAvailability(boolean available) {
        return null;
    }

    @Override
    public int countProducts() {
        //return productRepository.countProducts();
        return  1;
    }

	@Override
	public Product findProductBy(long productId) {
		if (productId == 0) {
			throw new ConflictException("productId is 0");
		}
		
		Product product = productRepository.findProductById(productId);
		if (product == null) {
			throw new ConflictException("product not found");
		}
		return product;
	}

	@Override
	public Page<Product> findProducts(String name, String category, String brand, Pageable pageable) {
		return productRepository.findProducts(name, category, brand, pageable);
	}
}
