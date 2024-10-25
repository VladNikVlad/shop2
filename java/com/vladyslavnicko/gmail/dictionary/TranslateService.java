package com.vladyslavnicko.gmail.dictionary;

import java.io.InputStream;

import com.vladyslavnicko.gmail.model.Language;

public interface TranslateService {
	void saveNewTranslate(InputStream file);
	String translate(Language lang, String key);
}
