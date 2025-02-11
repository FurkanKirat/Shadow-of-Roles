package com.rolegame.game.gui.controllers.game;

import com.rolegame.game.services.GameService;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.managers.SceneManager;
import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ChillGuyAlertController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton noButton;

    @FXML
    private Label questionLabel;

    @FXML
    private Label chillGuyLabel;

    @FXML
    private RadioButton yesButton;

    @FXML
    void buttonClicked(ActionEvent event) {

        GameService gameService = GameScreenController.getGameService();
        for(Player player: gameService.getAllPlayers()){
            if(player.getRole().getTemplate() instanceof ChillGuy){
                player.setHasWon(noButton.isSelected());
                break;
            }
        }
        SceneManager.switchScene("/com/rolegame/game/fxml/game/EndGame.fxml", SceneManager.SceneType.END_GAME, false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noButton.setSelected(true);
    }
}
