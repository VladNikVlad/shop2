package com.vladyslavnicko.gmail.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vladyslavnicko.gmail.model.Comment;
import com.vladyslavnicko.gmail.model.OrderItem;
import com.vladyslavnicko.gmail.model.Product;

import lombok.Data;

@Data
public class ProductAPI {

	private Long id;
    private CategoryAPI category;
    private List<OrderItem> orderItems;
    private List<Comment> comments;
    private BrandAPI brand;
    private List<ImageAPI> images;
    private String name;
    private String description;
    private BigDecimal price;
    private UserAPI user;
    
    public static ProductAPI fromProduct(Product p) {
    	ProductAPI object = new ProductAPI();
    	object.setId(p.getId());
    	if (p.getCategory() != null) {
    		object.setCategory(CategoryAPI.fromCategory(p.getCategory()));
    	}
    	object.setOrderItems(p.getOrderItems());
    	object.setComments(p.getComments());
    	if (p.getImages() != null && p.getImages().size() > 0) {
    		object.setImages(ImageAPI.fromImages(p.getImages()));
    	} 
    	if (p.getBrand() != null) {
    		object.setBrand(BrandAPI.fromBrand(p.getBrand()));
    	}
    	object.setName(p.getName());
    	object.setDescription(p.getDescription());
    	object.setPrice(p.getPrice());
    	if (p.getCreatorUser() != null) {
    		object.setUser(UserAPI.fromUser(p.getCreatorUser()));
    	}
    	
    	return object;
    }
    
    public static Product toProduct(ProductAPI p) {
    	Product object = new Product();
    	object.setId(p.getId());
    	if (p.getCategory() != null) {
    		object.setCategory(CategoryAPI.toCategory(p.getCategory()));
    	}
    	object.setOrderItems(p.getOrderItems());
    	object.setComments(p.getComments());
    	if (p.getImages() != null && p.getImages().size() > 0) {
    		object.setImages(ImageAPI.toImages(p.getImages()));
    	} 
    	if (p.getBrand() != null) {
    		object.setBrand(BrandAPI.toBrand(p.getBrand()));
    	}
    	object.setName(p.getName());
    	object.setDescription(p.getDescription());
    	object.setPrice(p.getPrice());
    	
    	if (p.getUser() != null) {
    		object.setCreatorUser(UserAPI.toUser(p.getUser()));
    	}
    	
    	return object;
    }
    
    public static List<ProductAPI> fromProducts(List<Product> p) {
    	List<ProductAPI> list = new ArrayList<ProductAPI>();
    	
    	for (Product product: p){
    		list.add(fromProduct(product));
    	}
    	
    	return list;
    }
 
}
