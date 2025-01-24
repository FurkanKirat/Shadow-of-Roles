package com.rolegame.game.GameManagement.Achievement;

public class BasicAchievement extends Achievement {

    public BasicAchievement(AchievementID id, boolean isCompleted, AchievementCategory category) {
        super(id, isCompleted, category);
    }

    public BasicAchievement(AchievementID id, String title, String description, boolean isCompleted, AchievementCategory category) {
        super(id, title, description, isCompleted, category);
    }

    public BasicAchievement() {
    }

}
