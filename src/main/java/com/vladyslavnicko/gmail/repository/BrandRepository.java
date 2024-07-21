package com.vladyslavnicko.gmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vladyslavnicko.gmail.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Brand save(Brand brand);

}
