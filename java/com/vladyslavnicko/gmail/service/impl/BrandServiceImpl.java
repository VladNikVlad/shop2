package com.vladyslavnicko.gmail.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Brand;
import com.vladyslavnicko.gmail.repository.BrandRepository;
import com.vladyslavnicko.gmail.service.BrandService;
import com.vladyslavnicko.gmail.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
	
	private final BrandRepository brandRepository;
	private final UserService userService;

	@Override
	public Brand saveNewBrand(Brand brand) {
		if (brand == null) {
			throw new ConflictException("Brand is null");
		}
		
		Brand oldBrand = findBrandByName(brand.getName());
		if (oldBrand != null) {
			throw new ConflictException("This brand already exists!");
		}
		
		brand.setCreatorUser(userService.getCurrentUser());
		brand.setCreateDate(new Date());
		
		return brandRepository.save(brand);
	}

	@Override
	public Brand findBrandByName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new ConflictException("Name is empty");
		}
		
		return brandRepository.findByName(name);
	}

}
