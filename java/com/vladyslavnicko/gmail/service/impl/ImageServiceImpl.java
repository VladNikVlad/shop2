package com.vladyslavnicko.gmail.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Image;
import com.vladyslavnicko.gmail.repository.ImageRepository;
import com.vladyslavnicko.gmail.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	
	private static Logger logger = LogManager.getLogger(ImageServiceImpl.class);
	
	private final ImageRepository imageRepository;
	
	@Override
	public Image uploadImage(MultipartFile imageFile) {
		try {
			Image imageToSave = Image.builder()
					.name(imageFile.getOriginalFilename())
					.type(imageFile.getContentType())
					.data(imageFile.getBytes())
					.build();
			return imageRepository.save(imageToSave);
		} catch(Exception e) {
			logger.error("Failed to save image!", e);
			throw new ConflictException("Failed to save image!");
		}
	}

	@Override
	public Image downloadImage(String imageName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> findImageByIds(List<Long> ids) {
		return imageRepository.findAllById(ids);
	}

}
