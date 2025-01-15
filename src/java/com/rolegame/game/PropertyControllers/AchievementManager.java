package com.rolegame.game.PropertyControllers;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.rolegame.game.GameManagement.Achievement;

public class AchievementManager {

    private static final String USER_DATA_PATH = FileManager.getUserDataDirectory() + "\\achievements.json";
    private static final Map<String,Achievement> achievements = new HashMap<>();

    private static final Achievement achievement = new Achievement("Hi","B",false, Achievement.AchievementCategory.Chaos);

    public static void saveAchievements() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(USER_DATA_PATH), achievements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Achievement> loadAchievements() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(USER_DATA_PATH);
            if (!file.exists()) {
                return new HashMap<>();
            }
            return mapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    static {
        addAchievement(achievement);
    }

    private static void addAchievement(Achievement achievement){
        achievements.put(achievement.getTitle(), achievement);
    }
}
