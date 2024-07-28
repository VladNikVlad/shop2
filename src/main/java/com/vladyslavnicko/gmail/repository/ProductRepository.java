package com.vladyslavnicko.gmail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vladyslavnicko.gmail.model.Category;
import com.vladyslavnicko.gmail.model.Product;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Product save(Product product);
    Product findProductByName(String name);
    Product findProductById(long id);
    List<Product> findByCategory(Category category);
    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    List<Product> findByPrice(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
//    int countProducts();
    
    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR p.name LIKE %:name%) AND " +
            "(:category IS NULL OR p.category LIKE %:category%) AND " +
            "(:brand IS NULL OR p.brand LIKE %:brand%)")
     Page<Product> findProducts(@Param("name") String name, 
                                 @Param("category") String category, 
                                 @Param("brand") String brand, 
                                 Pageable pageable);
    
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET " +
           "p.name = COALESCE(:name, p.name), " +
           "p.price = COALESCE(:price, p.price), " +
           "p.description = COALESCE(:description, p.description), " +
           "p.editDate = :editDate " +
           "WHERE p.id = :id")
    int updateProduct(@Param("id") long id, 
                      @Param("name") String name, 
                      @Param("price") BigDecimal price, 
                      @Param("description") String description, 
                      @Param("editDate") Date editDate);
    
}
