package com.vladyslavnicko.gmail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vladyslavnicko.gmail.model.Category;


public interface CategoryReposytory extends JpaRepository<Category, Long>{

	Category save(Category category);
	Category findByName(String name);
	
}
