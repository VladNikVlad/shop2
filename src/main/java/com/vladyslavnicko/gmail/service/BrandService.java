package com.vladyslavnicko.gmail.service;

import com.vladyslavnicko.gmail.model.Brand;

public interface BrandService {

	Brand saveNewBrand(Brand brand);

	Brand findBrandByName(String name);

}
