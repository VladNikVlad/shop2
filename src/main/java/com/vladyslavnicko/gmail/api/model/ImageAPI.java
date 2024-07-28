package com.vladyslavnicko.gmail.api.model;

import java.util.ArrayList;
import java.util.List;

import com.vladyslavnicko.gmail.model.Image;

import lombok.Data;

@Data
public class ImageAPI {

    private Long id;

    private String name;

    private String type;

    private byte[] imageData;
    
    public static ImageAPI fromImage(Image i) {
    	ImageAPI o = new ImageAPI();
    	o.setId(i.getId());
    	o.setName(i.getName());
    	o.setType(i.getType());
    	o.setImageData(i.getData());
    	
    	return o;
    }
    
    public static Image toImage(ImageAPI i) {
    	Image o = new Image();
    	o.setId(i.getId());
    	o.setName(i.getName());
    	o.setType(i.getType());
    	o.setData(i.getImageData());
    	
    	return o;
    }
    
    public static List<ImageAPI> fromImages(List<Image> images) {
    	List<ImageAPI> o = new ArrayList<ImageAPI>();
    	for (Image i: images) {
    		o.add(fromImage(i));
    	}
    	
    	return o;
    }
    
    public static List<Image> toImages(List<ImageAPI> images) {
    	List<Image> o = new ArrayList<Image>();
    	for (ImageAPI i: images) {
    		o.add(toImage(i));
    	}
    	
    	return o;
    }
}
