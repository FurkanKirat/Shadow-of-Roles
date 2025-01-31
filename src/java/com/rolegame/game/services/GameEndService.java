package com.rolegame.game.services;

import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.models.achievement.Achievement;
import com.rolegame.game.managers.AchievementManager;
import com.rolegame.game.models.Player;
import com.rolegame.game.models.roles.folkroles.protector.FolkHero;
import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;

import java.util.Map;

public class GameEndService {

    public static void progressAchievements() {
        for (Map.Entry<Achievement.AchievementID, Achievement> achievementEntry : AchievementManager.loadAchievements().entrySet()) {
            if (achievementEntry.getValue().getCategory() == Achievement.AchievementCategory.PLAY_GAME) {
                AchievementManager.addProgressToAchievement(achievementEntry.getKey(), 1);
            }
        }

        for (Player player : GameScreenController.getGameService().getAllPlayers()) {
            if (player.getRole() instanceof FolkHero folkHero && folkHero.getAbilityUseCount() == 0 && player.isHasWon()) {
                AchievementManager.completeAchievement(Achievement.AchievementID.LAZY_HERO);
            } else if (player.getRole() instanceof ChillGuy && !player.isHasWon()) {
                AchievementManager.completeAchievement(Achievement.AchievementID.WIN_SACRIFICE);
            }
        }
    }
}
