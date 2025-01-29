package com.rolegame.game.gui.controllers.gameguide;

import com.rolegame.game.managers.LanguageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainGoalController implements Initializable {

    @FXML
    private Text goalText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goalText.setText(LanguageManager.getText("GameGuide","MainGoal"));
    }
}
