package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.GameManagement.GameController;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.SceneController;
import com.rolegame.game.Roles.NeutralRole.Chaos.SimplePerson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class SimplePersonController implements Initializable {

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton noButton;

    @FXML
    private Label questionLabel;

    @FXML
    private Label simplePersonLabel;

    @FXML
    private RadioButton yesButton;

    @FXML
    void buttonClicked(ActionEvent event) {

        GameController gameController = GameScreenController.getGameController();
        for(Player player: gameController.getAllPlayers()){
            if(player.getRole() instanceof SimplePerson){
                player.setHasWon(noButton.isSelected());
                break;
            }
        }
        SceneController.switchScene("/com/rolegame/game/fxml/EndGame.fxml", SceneController.SceneType.EndGame);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noButton.setSelected(true);
    }
}
