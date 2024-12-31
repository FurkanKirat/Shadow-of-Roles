package com.rolegame.game.GUI;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GameStartController implements Initializable {

    @FXML
    private Label changeLangLabel;

    @FXML
    private Button exitButton;

    @FXML
    private Label gameGuideLabel;

    @FXML
    private Label startGameLabel;

    @FXML
    void changeLanguage(MouseEvent event) {
        SceneController.switchScene("/com/rolegame/game/fxml/ChangeLanguage.fxml");
    }

    @FXML
    void exitClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void gameGuide(MouseEvent event) {

    }

    @FXML
    void startGame(MouseEvent event) {
        SceneController.switchScene(new WriteNamesController());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButton.setText(LanguageManager.getText("StartMenu.exit"));
        changeLangLabel.setText(LanguageManager.getText("StartMenu.changeLanguage"));
        startGameLabel.setText(LanguageManager.getText("StartMenu.startGame"));
        gameGuideLabel.setText(LanguageManager.getText("StartMenu.gameGuide"));

    }
}

