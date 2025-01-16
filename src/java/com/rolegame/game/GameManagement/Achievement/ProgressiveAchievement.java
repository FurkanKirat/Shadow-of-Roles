package com.rolegame.game.GameManagement.Achievement;

public class ProgressiveAchievement extends Achievement {
    private int progress;
    private int max;

    public ProgressiveAchievement(String title, String description, boolean isCompleted, AchievementCategory category, int progress, int max) {
        super(title, description, isCompleted, category);
        this.progress = progress;
        this.max = max;
    }

    public ProgressiveAchievement() {
    }

    public void updateProgress(int amount) {

       progress += amount;

       if(progress>=max){
           isCompleted = true;
       }

    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
