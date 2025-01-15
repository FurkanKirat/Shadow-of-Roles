package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.GUI.Boxes.AchievementBox;
import com.rolegame.game.GameManagement.Achievement;
import com.rolegame.game.PropertyControllers.AchievementManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AchievementsController implements Initializable {

    @FXML
    private VBox achievementBox;

    @FXML
    private Label achievementsLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<String, Achievement> achievementMap = AchievementManager.loadAchievements();

        for(Map.Entry<String,Achievement> achievementEntry : achievementMap.entrySet()){
            achievementBox.getChildren().add(new AchievementBox(achievementEntry.getValue()));
        }
    }
}
