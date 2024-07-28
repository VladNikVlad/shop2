package com.vladyslavnicko.gmail.api.model;

import java.util.ArrayList;
import java.util.List;

import com.vladyslavnicko.gmail.model.Category;
import com.vladyslavnicko.gmail.model.Product;

import lombok.Data;

@Data
public class CategoryAPI {

	private Long id;
    private String name;
    
    public static CategoryAPI fromCategory(Category c) {
    	CategoryAPI object = new CategoryAPI();
    	object.setId(c.getId());
    	object.setName(c.getName());
    
    	return object;
    }
    
    public static Category toCategory(CategoryAPI c) {
    	Category object = new Category();
    	object.setId(c.getId());
    	object.setName(c.getName());
    	
    	return object;
    }
    
    public static List<CategoryAPI> fromCategorys(List<Category> c) {
    	List<CategoryAPI> list = new ArrayList<CategoryAPI>();
    	for (Category o: c) {
    		list.add(fromCategory(o));
    	}
    	
    	return list;
    }
}
