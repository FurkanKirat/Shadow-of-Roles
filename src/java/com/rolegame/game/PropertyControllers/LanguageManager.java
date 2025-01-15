package com.rolegame.game.PropertyControllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class LanguageManager {
    private static Map<String, Map<String, String>> translations;

    public static void changeLanguage(String languageCode) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = LanguageManager.class.getResourceAsStream(
                    "/com/rolegame/game/lang/" + languageCode + ".json");
            if (inputStream == null) {
                throw new FileNotFoundException("File could not be found: " + languageCode + ".json");
            }
            translations = mapper.readValue(inputStream, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getText(String text) {
        String category = text.substring(0,text.indexOf('.'));
        String key = text.substring(text.indexOf('.')+1);
        if (translations != null && translations.containsKey(category)) {
            return translations.get(category).getOrDefault(key, key);
        }
        return key;
    }
}
