package com.rolegame.game.gui.controllers.menu;

import com.rolegame.game.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CreditsController {

    @FXML
    private Label backLabel;

    @FXML
    void backClicked(MouseEvent event) {
        SceneManager.switchScene("/com/rolegame/game/fxml/menu/MainMenu.fxml", SceneManager.SceneType.MAIN_MENU, true);
    }

}
