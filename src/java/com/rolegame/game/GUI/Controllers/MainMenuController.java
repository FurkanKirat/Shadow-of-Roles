package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {


    @FXML
    private Label startGameLabel;

    @FXML
    private Label gameGuideLabel;

    @FXML
    private Label achievementsLabel;

    @FXML
    private Label settingsLabel;

    @FXML
    private Label creditsLabel;

    @FXML
    private Label exitLabel;

    @FXML
    void achievementsClicked(MouseEvent event) {
        SceneController.switchScene("/com/rolegame/game/fxml/Achievements.fxml", SceneController.SceneType.ACHIEVEMENTS, true);
    }

    @FXML
    void creditsClicked(MouseEvent event) {
        SceneController.switchScene("/com/rolegame/game/fxml/Credits.fxml", SceneController.SceneType.CREDITS, true);
    }

    @FXML
    void exitClicked(MouseEvent event) {
        SceneController.onClose();
    }

    @FXML
    void gameGuide(MouseEvent event) {

    }

    @FXML
    void settingsClicked(MouseEvent event) {
        SceneController.settingsScene();

    }

    @FXML
    void startGame(MouseEvent event) {
        SceneController.switchScene(new WriteNamesController(), SceneController.SceneType.WRITE_NAMES, true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        startGameLabel.setText(LanguageManager.getText("StartMenu.startGame"));
        gameGuideLabel.setText(LanguageManager.getText("StartMenu.gameGuide"));
        achievementsLabel.setText(LanguageManager.getText("StartMenu.achievements"));
        settingsLabel.setText(LanguageManager.getText("StartMenu.settings"));
        creditsLabel.setText(LanguageManager.getText("StartMenu.credits"));
        exitLabel.setText(LanguageManager.getText("StartMenu.exit"));


    }
}

