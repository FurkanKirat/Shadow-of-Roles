package com.rolegame.game.GameManagement.Achievement;

public abstract class Achievement {
    private String title;
    private String description;
    protected boolean isCompleted;
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

    public enum AchievementCategory{
        Chaos,
        PlayGame,
        Roles
    }
}
