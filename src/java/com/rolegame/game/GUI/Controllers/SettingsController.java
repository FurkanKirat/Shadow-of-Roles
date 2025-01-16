package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.PropertyControllers.AchievementManager;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import com.rolegame.game.PropertyControllers.ShortcutManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.Optional;
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
    private Label backLabel;

    @FXML
    void changeLanguage(MouseEvent event) {
        SceneController.changeLangScene();
    }

    @FXML
    void feedbackClicked(MouseEvent event) {

            try {

                if (Desktop.isDesktopSupported()) {

                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI("""
                            https://github.com/FurkanKirat/Shadow-of-Roles/issues"""));

                } else {
                    System.out.println("Desktop API is not supported.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @FXML
    void fullScreenClicked(MouseEvent event) {
        SceneController.fullScreen();
    }

    @FXML
    void resetAchievementsClicked(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting achievements");
        alert.setHeaderText(LanguageManager.getText("Menu.passAlertHead"));
        alert.setContentText(LanguageManager.getText("Menu.passAlertMessage"));

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() != ButtonType.OK){
            return;
        }
        AchievementManager.resetAchievements();
    }

    @FXML
    void backClicked(MouseEvent event) {
        SceneController.mainMenuScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
