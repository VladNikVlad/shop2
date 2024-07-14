package com.vladyslavnicko.gmail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vladyslavnicko.gmail.model.Category;
import com.vladyslavnicko.gmail.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Product save(Product product);
    List<Product> findByCategory(Category category);
    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    List<Product> findByPrice(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
//    int countProducts();
}
