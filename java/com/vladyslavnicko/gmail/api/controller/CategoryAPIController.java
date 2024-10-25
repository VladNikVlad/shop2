package com.vladyslavnicko.gmail.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladyslavnicko.gmail.api.model.CategoryAPI;
import com.vladyslavnicko.gmail.api.service.APIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryAPIController {
	
	private final APIService apiService;

	@PostMapping
	public ResponseEntity<?> creatAddBreand(@RequestBody CategoryAPI categoryApi){
		return ResponseEntity.status(HttpStatus.OK)
                .body(apiService.saveCategory(categoryApi));
	}

}
