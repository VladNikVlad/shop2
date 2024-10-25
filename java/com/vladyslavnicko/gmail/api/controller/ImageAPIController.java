package com.vladyslavnicko.gmail.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vladyslavnicko.gmail.api.model.ImageAPI;
import com.vladyslavnicko.gmail.api.service.APIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageAPIController {

	private final APIService apiService;

	@PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		ImageAPI uploadImage = apiService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }
	
	@GetMapping
    public ResponseEntity<?> findImages(@RequestParam("ids") long id) throws IOException {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		List<ImageAPI> images = apiService.findImageByIds(ids);
        return ResponseEntity.status(HttpStatus.OK).body(images);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteImageById(@PathVariable Long id) {
        //productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

	public void test(){}
}
