package com.vladyslavnicko.gmail.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.vladyslavnicko.gmail.model.Brand;

public interface BrandService {

	@PreAuthorize("hasAuthority('ADMIN')")
	Brand saveNewBrand(Brand brand);

	Brand findBrandByName(String name);

}
