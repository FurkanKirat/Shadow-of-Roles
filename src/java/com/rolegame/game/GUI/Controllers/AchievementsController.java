package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.GUI.Boxes.AchievementBox;
import com.rolegame.game.GameManagement.Achievement.Achievement;
import com.rolegame.game.PropertyControllers.AchievementManager;
import com.rolegame.game.PropertyControllers.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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
    private Label backLabel;

    @FXML
    private VBox bigBox;

    @FXML
    private HBox backBox;

    @FXML
    void backClicked(MouseEvent event) {
        SceneController.switchScene("/com/rolegame/game/fxml/MainMenu.fxml", SceneController.SceneType.MainMenu);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bigBox.getChildren().remove(backBox);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(achievementBox);
        bigBox.getChildren().add(scrollPane);
        Map<String, Achievement> achievementMap = AchievementManager.loadAchievements();

        for(Map.Entry<String,Achievement> achievementEntry : achievementMap.entrySet()){
            achievementBox.getChildren().add(new AchievementBox(achievementEntry.getValue()));
            achievementBox.getChildren().add(new Separator());
        }
        bigBox.getChildren().add(backBox);
    }
}
