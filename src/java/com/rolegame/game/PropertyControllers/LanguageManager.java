package com.rolegame.game.PropertyControllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class LanguageManager {
    private static Map<String, Map<String, String>> translations;

    private static final String LANGUAGE_FILE_PATH = FileManager.getUserDataDirectory() + "\\language.json";

    public static void changeLanguage(String languageCode) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = LanguageManager.class.getResourceAsStream(
                    "/com/rolegame/game/lang/" + languageCode + ".json");
            if (inputStream == null) {
                throw new FileNotFoundException("File could not be found: " + languageCode + ".json");
            }
            translations = mapper.readValue(inputStream, new TypeReference<>() {});
            saveLanguage(languageCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String loadLanguage() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(LANGUAGE_FILE_PATH);
            if (!file.exists()) {
                return "en_us";
            }
            return mapper.readValue(file, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "en_us";
    }


    public static String getText(String text) {
        String category = text.substring(0,text.indexOf('.'));
        String key = text.substring(text.indexOf('.')+1);
        if (translations != null && translations.containsKey(category)) {
            return translations.get(category).getOrDefault(key, key);
        }
        return key;
    }

    private static void saveLanguage(String language) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(LANGUAGE_FILE_PATH);
            file.getParentFile().mkdirs();
            mapper.writeValue(file, language);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
