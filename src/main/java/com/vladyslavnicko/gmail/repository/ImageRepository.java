package com.vladyslavnicko.gmail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vladyslavnicko.gmail.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);
	Image save(Image i);
	//List<Image> findByIdIn(List<Long> ids);
}
