package com.vladyslavnicko.gmail.dictionary.controller;

import java.io.InputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladyslavnicko.gmail.dictionary.TranslateService;
import com.vladyslavnicko.gmail.model.Language;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {
	
	private final TranslateService transletService;
	
	@PostMapping
	public ResponseEntity<String> saveNewDictionary(InputStream file){
		transletService.saveNewTranslate(file);
		return ResponseEntity.ok("Ok");
	}
	
	@GetMapping
	public ResponseEntity<String> getDictionary(){
		String response = transletService.translate(Language.LANGUAGE_UA, "labal_user_exists");
		return ResponseEntity.ok(response);
	}
}
