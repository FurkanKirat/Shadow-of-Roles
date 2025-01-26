package com.rolegame.game.gui.controllers.menu;

import com.rolegame.game.gui.controllers.game.WriteNamesController;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
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
        SceneManager.switchScene("/com/rolegame/game/fxml/menu/Achievements.fxml", SceneManager.SceneType.ACHIEVEMENTS, true);
    }

    @FXML
    void creditsClicked(MouseEvent event) {
        SceneManager.switchScene("/com/rolegame/game/fxml/menu/Credits.fxml", SceneManager.SceneType.CREDITS, true);
    }

    @FXML
    void exitClicked(MouseEvent event) {
        SceneManager.onClose();
    }

    @FXML
    void gameGuide(MouseEvent event) {

    }

    @FXML
    void settingsClicked(MouseEvent event) {
        SceneManager.settingsScene();

    }

    @FXML
    void startGame(MouseEvent event) {
        SceneManager.switchScene(new WriteNamesController(), SceneManager.SceneType.WRITE_NAMES, true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        startGameLabel.setText(LanguageManager.getText("StartMenu","startGame"));
        gameGuideLabel.setText(LanguageManager.getText("StartMenu","gameGuide"));
        achievementsLabel.setText(LanguageManager.getText("StartMenu","achievements"));
        settingsLabel.setText(LanguageManager.getText("StartMenu","settings"));
        creditsLabel.setText(LanguageManager.getText("StartMenu","credits"));
        exitLabel.setText(LanguageManager.getText("StartMenu","exit"));


    }
}

