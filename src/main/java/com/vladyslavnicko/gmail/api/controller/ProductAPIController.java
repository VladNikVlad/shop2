package com.vladyslavnicko.gmail.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladyslavnicko.gmail.api.model.ProductAPI;
import com.vladyslavnicko.gmail.api.service.APIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductAPIController {

	private final APIService apiService;
	
	
	@PostMapping
	public ResponseEntity<ProductAPI> creatNewProduct(@RequestBody ProductAPI product){
		return ResponseEntity.ok(apiService.saveProduct(product));
	}
	
	@GetMapping
	@RequestMapping("/{productId}")
	public ResponseEntity<ProductAPI> findProductById(@PathVariable long productId){
		return ResponseEntity.ok(apiService.findProductBy(productId));
	}
	
}
