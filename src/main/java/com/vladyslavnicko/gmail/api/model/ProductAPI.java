package com.vladyslavnicko.gmail.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vladyslavnicko.gmail.model.Category;
import com.vladyslavnicko.gmail.model.Comment;
import com.vladyslavnicko.gmail.model.Image;
import com.vladyslavnicko.gmail.model.OrderItem;
import com.vladyslavnicko.gmail.model.Product;

import lombok.Data;

@Data
public class ProductAPI {

	private Long id;
    private Category category;
    private List<OrderItem> orderItems = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<Image> images = new ArrayList<>();
    private String name;
    private String description;
    private BigDecimal price;
    
    public static ProductAPI fromProduct(Product p) {
    	ProductAPI object = new ProductAPI();
    	object.setId(p.getId());
    	object.setCategory(p.getCategory());
    	object.setOrderItems(p.getOrderItems());
    	object.setComments(p.getComments());
    	object.setImages(p.getImages());
    	object.setName(p.getName());
    	object.setDescription(p.getDescription());
    	object.setPrice(p.getPrice());
    	
    	return object;
    }
    
    public static Product toProduct(ProductAPI p) {
    	Product object = new Product();
    	object.setId(p.getId());
    	object.setCategory(p.getCategory());
    	object.setOrderItems(p.getOrderItems());
    	object.setComments(p.getComments());
    	//object.setImages(p.getImages());
    	object.setName(p.getName());
    	object.setDescription(p.getDescription());
    	object.setPrice(p.getPrice());
    	
    	return object;
    }
}
