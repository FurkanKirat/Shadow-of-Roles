package com.rolegame.game.PropertyControllers;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.*;
import com.rolegame.game.models.achievement.Achievement;
import com.rolegame.game.models.achievement.BasicAchievement;
import com.rolegame.game.models.achievement.ProgressiveAchievement;

public class AchievementManager {

    private static final String USER_DATA_PATH = FileManager.getUserDataDirectory() + File.separator + "achievements.json";
    private static final Map<Achievement.AchievementID,Achievement> achievements = new LinkedHashMap<>();

    private static final Achievement FIRST_STEPS =
            new ProgressiveAchievement(Achievement.AchievementID.FIRST_STEPS,false, Achievement.AchievementCategory.PLAY_GAME,0,1);

    private static final Achievement NOVICE_ADVENTURER =
            new ProgressiveAchievement(Achievement.AchievementID.NOVICE_ADVENTURER,false, Achievement.AchievementCategory.PLAY_GAME,0,5);

    private static final Achievement DETERMINED_PLAYER =
            new ProgressiveAchievement(Achievement.AchievementID.DETERMINED_PLAYER,false, Achievement.AchievementCategory.PLAY_GAME,0,10);

    private static final Achievement ON_THE_PATH_TO_MASTERY =
            new ProgressiveAchievement(Achievement.AchievementID.ON_THE_PATH_TO_MASTERY,false, Achievement.AchievementCategory.PLAY_GAME,0,25);

    private static final Achievement HALFWAY_THERE =
            new ProgressiveAchievement(Achievement.AchievementID.HALFWAY_THERE,false, Achievement.AchievementCategory.PLAY_GAME,0,50);

    private static final Achievement LEGENDARY_PLAYER =
            new ProgressiveAchievement(Achievement.AchievementID.LEGENDARY_PLAYER,false, Achievement.AchievementCategory.PLAY_GAME,0,99);

    private static final Achievement LAZY_HERO =
            new BasicAchievement(Achievement.AchievementID.LAZY_HERO,false, Achievement.AchievementCategory.ROLES);

    private static final Achievement WIN_SACRIFICE =
            new BasicAchievement(Achievement.AchievementID.WIN_SACRIFICE,false, Achievement.AchievementCategory.ROLES);

    public static Map<Achievement.AchievementID, Achievement> loadAchievements() {
        ObjectMapper mapper = new ObjectMapper();
        Map<Achievement.AchievementID, Achievement> achievements = new LinkedHashMap<>();
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
                achievement.langTitleAndDesc();
                achievements.put(achievement.getId(), achievement);
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
            Map<Achievement.AchievementID, Achievement> existingAchievements = loadAchievements();
            existingAchievements.remove(achievement.getId());
            existingAchievements.put(achievement.getId(), achievement);

            List<Achievement> achievementList = new ArrayList<>(existingAchievements.values());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, achievementList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void completeAchievement(Achievement.AchievementID id) {
        Map<Achievement.AchievementID,Achievement> loadAchievements = loadAchievements();
        Achievement achievement = loadAchievements.get(id);
        if (achievement != null) {
            achievement.setCompleted(true);
            updateAchievementInFile(achievement);
        }
    }

    public static void addProgressToAchievement(Achievement.AchievementID id, int amount) {
        Map<Achievement.AchievementID,Achievement> loadAchievements = loadAchievements();
        Achievement achievement = loadAchievements.get(id);
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
        addAchievement(LAZY_HERO);
        addAchievement(WIN_SACRIFICE);
    }

    private static void addAchievement(Achievement achievement){
        achievements.put(achievement.getId(), achievement);
    }
}
