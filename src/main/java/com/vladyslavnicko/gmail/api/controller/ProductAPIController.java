package com.vladyslavnicko.gmail.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vladyslavnicko.gmail.api.model.ProductAPI;
import com.vladyslavnicko.gmail.api.service.APIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductAPIController {

	private final APIService apiService;
	
	@GetMapping
	@RequestMapping("/{productId}")
	public ResponseEntity<ProductAPI> findProductById(@PathVariable long productId){
		return ResponseEntity.ok(apiService.findProductBy(productId));
	}
	
	@GetMapping
    public ResponseEntity<List<ProductAPI>> getProducts(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "limit", defaultValue = "50") int limit) {
        List<ProductAPI> products = apiService.findProducts(name, category, brand, start, limit);
        return ResponseEntity.ok(products);
    }
	
	@PostMapping
	public ResponseEntity<ProductAPI> creatNewProduct(@RequestBody ProductAPI product){
		return ResponseEntity.ok(apiService.saveProduct(product));
	}
	
	
	
}
