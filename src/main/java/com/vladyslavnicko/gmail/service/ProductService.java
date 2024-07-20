package com.vladyslavnicko.gmail.service;

import com.vladyslavnicko.gmail.model.Category;
import com.vladyslavnicko.gmail.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    void deleteProductId(long id);
    Product findProductById(long id);
    List<Product> findByNameProduct(String name);
    Product updateProduct(Product product);
    List<Product> findByCategory(Category category);
    List<Product> findByPrice(double minPrice, double maxPrice);
    //List<Product> findByRating(double minRating);
    List<Product> findByAvailability(boolean available);  //возвращает список продуктов которые находятся в наличии (или нет, в зависимости от значения параметра).
    int countProducts();
	Product findProductBy(long productId);
}
