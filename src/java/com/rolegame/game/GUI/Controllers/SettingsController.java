package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.PropertyControllers.SceneController;
import com.rolegame.game.PropertyControllers.ShortcutManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private Label changeLanguageLabel;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Label fullScreenLabel;

    @FXML
    private Label resetAchivementsLabel;

    @FXML
    private Label settingsLabel;

    @FXML
    private Label shortcutsLabel;

    @FXML
    private Label backLabel;

    @FXML
    void changeLanguage(MouseEvent event) {
        SceneController.changeLangScene();
    }

    @FXML
    void feedbackClicked(MouseEvent event) {

    }

    @FXML
    void fullScreenClicked(MouseEvent event) {
        SceneController.fullScreen();
    }

    @FXML
    void resetAchievementsClicked(MouseEvent event) {

    }

    @FXML
    void shortcutsClicked(MouseEvent event) {

    }

    @FXML
    void backClicked(MouseEvent event) {
        SceneController.mainMenuScene();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
