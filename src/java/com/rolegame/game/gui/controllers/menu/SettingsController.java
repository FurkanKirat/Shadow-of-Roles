package com.rolegame.game.gui.controllers.menu;

import com.rolegame.game.managers.AchievementManager;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private VBox root;

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
    private Label backLabel;

    @FXML
    void changeLanguage(MouseEvent event) {
        SceneManager.changeLangScene();
    }

    @FXML
    void feedbackClicked(MouseEvent event) {

            try {

                if (Desktop.isDesktopSupported()) {

                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI("""
                            https://github.com/FurkanKirat/Shadow-of-Roles/issues"""));

                } else {
                    Alert alert = SceneManager.createAlert(Alert.AlertType.INFORMATION,
                            "Supporting","Desktop API","Desktop API is not supported.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @FXML
    void fullScreenClicked(MouseEvent event) {
        SceneManager.fullScreen();
    }

    @FXML
    void resetAchievementsClicked(MouseEvent event) {
        Alert alert = SceneManager.createAlert(Alert.AlertType.CONFIRMATION,"Deleting achievements",
                "Achievements will be resetting", "Are you sure to reset achievements");


        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() != ButtonType.OK){
            return;
        }
        AchievementManager.resetAchievements();
    }

    @FXML
    void backClicked(MouseEvent event) {
        SceneManager.mainMenuScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeLanguageLabel.setText(LanguageManager.getText("Settings","changeLang"));
        feedbackLabel.setText(LanguageManager.getText("Settings","feedback"));
        fullScreenLabel.setText(LanguageManager.getText("Settings","fullScreen"));
        resetAchivementsLabel.setText(LanguageManager.getText("Settings","resetAchievements"));
        settingsLabel.setText(LanguageManager.getText("Settings","settings"));
        backLabel.setText(LanguageManager.getText("GeneralMenu","back"));
    }
}
