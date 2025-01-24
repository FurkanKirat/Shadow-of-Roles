package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.GUI.Boxes.AchievementBox;
import com.rolegame.game.GameManagement.Achievement.Achievement;
import com.rolegame.game.PropertyControllers.AchievementManager;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
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
    private Label backLabel;

    @FXML
    private VBox bigBox;

    @FXML
    private ListView<AchievementBox> achievementsListView;

    @FXML
    private HBox backBox;

    @FXML
    void backClicked(MouseEvent event) {
        SceneController.switchScene("/com/rolegame/game/fxml/MainMenu.fxml", SceneController.SceneType.MAIN_MENU, true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backLabel.setText(LanguageManager.getText("GeneralMenu.back"));
        achievementsLabel.setText(LanguageManager.getText("Achievements.achievements"));
        bigBox.getChildren().remove(backBox);

        Map<Achievement.AchievementID, Achievement> achievementMap = AchievementManager.loadAchievements();

        for(Map.Entry<Achievement.AchievementID,Achievement> achievementEntry : achievementMap.entrySet()){
            achievementsListView.getItems().add(new AchievementBox(achievementEntry.getValue()));
        }
        bigBox.getChildren().add(backBox);
    }
}
