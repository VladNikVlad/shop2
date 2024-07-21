package com.vladyslavnicko.gmail.api.model;

import com.vladyslavnicko.gmail.model.Brand;

import lombok.Data;

@Data
public class BrandAPI {
	
	private long id;
	private String name;
	private UserAPI user;
	
	
	public static BrandAPI fromBrand(Brand b) {
		BrandAPI object = new BrandAPI();
		object.setId(b.getId());
		object.setName(b.getName());
		if (b.getCreatorUser() != null) {
			object.setUser(UserAPI.fromUser(b.getCreatorUser()));
		}
		
		return object;
	}

	public static Brand toBrand(BrandAPI b) {
		Brand object = new Brand();
		object.setId(b.getId());
		object.setName(b.getName());
		
		return object;
	}
}
