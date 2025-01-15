package com.rolegame.game.GameManagement;

public class Achievement {
    private String title;
    private String description;
    private boolean isCompleted;
    private AchievementCategory category;

    public Achievement(String title, String description, boolean isCompleted, AchievementCategory category) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public AchievementCategory getCategory() {
        return category;
    }

    public void setCategory(AchievementCategory category) {
        this.category = category;
    }

    public enum AchievementCategory{
        Chaos
    }
}
