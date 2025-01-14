package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.PropertyControllers.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CreditsController {

    @FXML
    private Label backLabel;

    @FXML
    void backClicked(MouseEvent event) {
        SceneController.switchScene("/com/rolegame/game/fxml/MainMenu.fxml", SceneController.SceneType.MainMenu);
    }

}
