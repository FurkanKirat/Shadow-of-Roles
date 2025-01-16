package com.rolegame.game.PropertyControllers;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.*;
import com.rolegame.game.GameManagement.Achievement.Achievement;
import com.rolegame.game.GameManagement.Achievement.BasicAchievement;
import com.rolegame.game.GameManagement.Achievement.ProgressiveAchievement;

public class AchievementManager {

    private static final String USER_DATA_PATH = FileManager.getUserDataDirectory() + "\\achievements.json";
    private static final Map<String,Achievement> achievements = new HashMap<>();

    private static final Achievement FIRST_STEPS =
            new ProgressiveAchievement("First Steps","Complete Your First Game",false, Achievement.AchievementCategory.PlayGame,0,1);

    private static final Achievement NOVICE_ADVENTURER =
            new ProgressiveAchievement("Novice Adventurer","Complete Your First 5 Games",false, Achievement.AchievementCategory.PlayGame,0,5);

    private static final Achievement DETERMINED_PLAYER =
            new ProgressiveAchievement("Determined Player","Complete Your First 10 Games",false, Achievement.AchievementCategory.PlayGame,0,10);

    private static final Achievement ON_THE_PATH_TO_MASTERY =
            new ProgressiveAchievement("On the Path to Mastery","Complete Your First 25 Games",false, Achievement.AchievementCategory.PlayGame,0,25);

    private static final Achievement HALFWAY_THERE =
            new ProgressiveAchievement("Halfway There","Complete Your First 50 Games",false, Achievement.AchievementCategory.PlayGame,0,50);

    private static final Achievement LEGENDARY_PLAYER =
            new ProgressiveAchievement("Legendary Player","Complete Your First 99 Games",false, Achievement.AchievementCategory.PlayGame,0,99);


    public static Map<String, Achievement> loadAchievements() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Achievement> achievements = new HashMap<>();
        try {
            File file = new File(USER_DATA_PATH);
            if (!file.exists()) {
                return achievements;
            }

            JsonNode rootNode = mapper.readTree(file);

            for (JsonNode node : rootNode) {
                Achievement achievement;
                if (node.has("max") && !node.get("max").isNull()) {
                    achievement = mapper.treeToValue(node, ProgressiveAchievement.class);
                } else {
                    achievement = mapper.treeToValue(node, BasicAchievement.class);
                }

                achievements.put(achievement.getTitle(), achievement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return achievements;
    }

    public static void saveAchievements() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(USER_DATA_PATH);
            if(file.exists()){
                return;
            }
            file.getParentFile().mkdirs();

            List<Achievement> achievementList = new ArrayList<>(achievements.values());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, achievementList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAchievementInFile(Achievement achievement) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(USER_DATA_PATH);
            Map<String, Achievement> existingAchievements = loadAchievements();
            existingAchievements.remove(achievement.getTitle());
            existingAchievements.put(achievement.getTitle(), achievement);

            List<Achievement> achievementList = new ArrayList<>(existingAchievements.values());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, achievementList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void completeAchievement(String title) {
        Map<String,Achievement> loadAchievements = loadAchievements();
        Achievement achievement = loadAchievements.get(title);
        if (achievement != null) {
            achievement.setCompleted(true);
            updateAchievementInFile(achievement);
        }
    }

    public static void addProgressToAchievement(String title, int amount) {
        Map<String,Achievement> loadAchievements = loadAchievements();
        Achievement achievement = loadAchievements.get(title);
        if (achievement instanceof ProgressiveAchievement progressiveAchievement) {
            progressiveAchievement.updateProgress(amount);
            updateAchievementInFile(progressiveAchievement);
        }
    }
    public static void resetAchievements(){
        File file = new File(USER_DATA_PATH);
        if (file.exists()) {
            file.delete();
            saveAchievements();
        }
    }



    static {
        addAchievement(FIRST_STEPS);
        addAchievement(NOVICE_ADVENTURER);
        addAchievement(DETERMINED_PLAYER);
        addAchievement(ON_THE_PATH_TO_MASTERY);
        addAchievement(HALFWAY_THERE);
        addAchievement(LEGENDARY_PLAYER);
    }

    private static void addAchievement(Achievement achievement){
        achievements.put(achievement.getTitle(), achievement);
    }
}
