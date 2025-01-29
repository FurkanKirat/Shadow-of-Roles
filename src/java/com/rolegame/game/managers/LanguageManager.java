package com.rolegame.game.managers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

public class LanguageManager {
    private static Map<String, Map<String, String>> translations;
    private static Map<String, Map<String, String>> roles;
    private static Map<String, Map<String, String>> achievements;
    public static String currentLang;
    public static String currentTheme;

    private static final String LANGUAGE_FILE_PATH = FileManager.getUserDataDirectory() + File.separator + "language.json";

    public static void changeLanguage(String languageCode, boolean isStart) {

        // If the current language is the same as the language you want to change, it returns
        if(currentLang.equals(languageCode) && !isStart){
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            String langFilePath = "/com/rolegame/game/lang/" + languageCode + ".json";
            InputStream inputStream = LanguageManager.class.getResourceAsStream(langFilePath);
            if (inputStream == null) {
                throw new FileNotFoundException(langFilePath);
            }
            translations = mapper.readValue(inputStream, new TypeReference<>() {});
            currentLang = languageCode;
            changeTheme(currentTheme);
            saveLanguage(currentLang, currentTheme);
            loadAchievements(currentLang);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeTheme(String theme) {
        ObjectMapper mapper = new ObjectMapper();
        String filePos = "/com/rolegame/game/lang/roles/" + currentLang + "_" + theme + ".json";
        try {
            InputStream inputStream = LanguageManager.class.getResourceAsStream(filePos);
            if (inputStream == null) {
                throw new FileNotFoundException("File could not be found: " +filePos);
            }
            roles = mapper.readValue(inputStream, new TypeReference<>() {
            });

            currentTheme = theme;
            saveLanguage(currentLang,currentTheme);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadLanguageAndTheme() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(LANGUAGE_FILE_PATH);
            if (file.exists()) {
                LanguageThemeData data = mapper.readValue(file, LanguageThemeData.class);
                currentLang = data.language;
                currentTheme = data.theme;
            } else {
                // Defaults
                currentLang = "en_us";
                currentTheme = "normal";
                saveLanguage(currentLang, currentTheme);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Fallback to defaults in case of error
            currentLang = "en_us";
            currentTheme = "normal";
        }
        changeLanguage(currentLang, true);
        changeTheme(currentTheme);
        loadAchievements(currentLang);
    }

    public static void loadAchievements(String languageCode) {
        ObjectMapper mapper = new ObjectMapper();
        String filePos = "/com/rolegame/game/lang/achievements/" + languageCode + ".json";
        try {
            InputStream inputStream = LanguageManager.class.getResourceAsStream(filePos);
            if (inputStream == null) {
                throw new FileNotFoundException("Achievement file not found: " + filePos);
            }
            achievements = mapper.readValue(inputStream, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
            achievements = null;
        }
    }

    public static String getText(String category, String key) {
        if (translations != null && translations.containsKey(category)) {
            return translations.get(category).getOrDefault(key, key);
        }
        return key;
    }

    public static String getRoleText(String category, String key){

        if (roles != null && roles.containsKey(category)) {
            return roles.get(category).getOrDefault(key, key);
        }
        return key;
    }

    public static String getAchievementText(String category, String key){
        if (achievements != null && achievements.containsKey(category)) {
            return achievements.get(category).getOrDefault(key, key);
        }
        return key;
    }


    private static void saveLanguage(String language, String theme) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(LANGUAGE_FILE_PATH);
            file.getParentFile().mkdirs();

            LanguageThemeData data = new LanguageThemeData(language, theme);
            mapper.writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String jsonKeyToEnum(String jsonKey) {
        return jsonKey.replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase(Locale.ROOT);
    }

    public static String enumToJsonKey(String enumName) {
        StringBuilder jsonKey = new StringBuilder();


        String[] parts = enumName.split("_");
        jsonKey.append(parts[0].toLowerCase(Locale.ROOT));
        for (int i = 1; i < parts.length; i++) {
            jsonKey.append(parts[i].substring(0, 1).toUpperCase(Locale.ROOT))
                    .append(parts[i].substring(1).toLowerCase(Locale.ROOT));
        }
        return jsonKey.toString();
    }


    private static class LanguageThemeData {
        public String language;
        public String theme;

        public LanguageThemeData(String language, String theme) {
            this.language = language;
            this.theme = theme;
        }

        public LanguageThemeData() {
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }
    }
}
