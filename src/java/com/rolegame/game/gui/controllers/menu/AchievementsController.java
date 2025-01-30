package com.rolegame.game.gui.controllers.menu;

import com.rolegame.game.gui.components.boxes.AchievementBox;
import com.rolegame.game.models.achievement.Achievement;
import com.rolegame.game.managers.AchievementManager;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AchievementsController implements Initializable {

    @FXML
    private VBox achievementBox;

    @FXML
    private Label achievementsLabel;

    @FXML
    private VBox root;

    @FXML
    private ListView<AchievementBox> achievementsListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        achievementsLabel.setText(LanguageManager.getText("Achievements","achievements"));

        Map<Achievement.AchievementID, Achievement> achievementMap = AchievementManager.loadAchievements();

        for(Map.Entry<Achievement.AchievementID,Achievement> achievementEntry : achievementMap.entrySet()){
            achievementsListView.getItems().add(new AchievementBox(achievementEntry.getValue()));
        }
    }
}
