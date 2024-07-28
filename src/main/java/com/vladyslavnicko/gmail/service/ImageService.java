package com.vladyslavnicko.gmail.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vladyslavnicko.gmail.model.Image;

public interface ImageService {
	
	Image uploadImage(MultipartFile imageFile);
	Image downloadImage(String imageName);

	List<Image> findImageByIds(List<Long> ids);
}
