package com.vladyslavnicko.gmail.dictionary;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Language;


@Service
public class TranslateServiceImpl implements TranslateService {
	
	private final String DICTIONARY_PATH = "src/main/resources/dictionary/translations.csv";
	
	private static Map<String, Integer> mapLang;
	
	static {
		mapLang = new HashMap<>();
		mapLang.put("EN", 1);
		mapLang.put("UA", 2);
	}

	@Override
	public void saveNewTranslate(InputStream file) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
				FileWriter writer = new FileWriter(DICTIONARY_PATH, StandardCharsets.UTF_8)) {

			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				for (int i = 0; i < fields.length; i++) {
					writer.append(fields[i]);
					if (i < fields.length - 1) {
						writer.append(",");
					}
				}
				writer.append("\n");
			}
		} catch (IOException e) {
			throw new ConflictException("Error writing CSV file: ", e);
		}
	}

	@Override
	public String translate(Language lang, String key) {
		
		if (lang == null) {
			return key;
		}
		
		String lang2 = StringUtils.substringAfter(lang.name(), "LANGUAGE_");
		Path csvPath = Paths.get(DICTIONARY_PATH);

        if (Files.exists(csvPath)) {
            try (BufferedReader reader = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
                String line;
                List<String[]> listDictionary = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    listDictionary.add(fields);
                }
                
                int number = 0;
                if (mapLang.containsKey(lang2)) {
                	number = mapLang.get(lang2);
                }
                
                if (number > 0 && listDictionary != null && listDictionary.size() > 0) {
                	for (String[] d: listDictionary) {
                		if (StringUtils.equals(d[0], key)) {
                			return d[number];
                		}
                	}
                } else {
                	return key;
                }
                
            } catch (IOException e) {
            	throw new ConflictException("Error reading CSV file: ", e);
            }
        }
        
        return key;
	}
	
	
	

}
