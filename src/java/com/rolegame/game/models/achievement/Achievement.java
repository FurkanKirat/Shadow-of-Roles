package com.rolegame.game.models.achievement;

import com.rolegame.game.managers.LanguageManager;

public abstract class Achievement {
    private AchievementID id;
    private String title;
    private String description;
    boolean isCompleted;
    private AchievementCategory category;

    public Achievement(AchievementID id, boolean isCompleted, AchievementCategory category) {
        this.id = id;
        this.title = LanguageManager.getAchievementText(id.toString(),"title");
        this.description = LanguageManager.getAchievementText(id.toString(),"description");
        this.isCompleted = isCompleted;
        this.category = category;
    }

    public Achievement(AchievementID id, String title, String description, boolean isCompleted, AchievementCategory category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.category = category;
    }


    public Achievement() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public AchievementCategory getCategory() {
        return category;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public AchievementID getId() {
        return id;
    }

    public void langTitleAndDesc(){
        this.title = LanguageManager.getAchievementText(id.toString(),"title");
        this.description = LanguageManager.getAchievementText(id.toString(),"description");
    }

    public enum AchievementCategory{
        CHAOS,
        PLAY_GAME,
        ROLES
    }

    public enum AchievementID{
        FIRST_STEPS,
        NOVICE_ADVENTURER,
        DETERMINED_PLAYER,
        ON_THE_PATH_TO_MASTERY,
        HALFWAY_THERE,
        LEGENDARY_PLAYER,
        LAZY_HERO,
        WIN_SACRIFICE
    }
}
