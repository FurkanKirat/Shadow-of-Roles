package com.rolegame.game.GUI;

import com.rolegame.game.GameManagement.GameController;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import com.rolegame.game.Roles.Role;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class GameEndController implements Initializable {

    @FXML
    private TableColumn<Player, String> aliveStatusColumn;

    @FXML
    private TableColumn<Player, String> deathCauseColumn;

    @FXML
    private TableView<Player> gameEndTV;

    @FXML
    private Label hasWonLabel;

    @FXML
    private TableColumn<Player, Integer> numberColumn;

    @FXML
    private TableColumn<Player, String> playerColumn;

    @FXML
    private TableColumn<Player, Role> roleColumn;

    @FXML
    private TableColumn<Player, String> teamColumn;

    @FXML
    private TableColumn<Player, String> winStatusColumn;

    @FXML
    private Button startGamebutton;

    @FXML
    void startGameClicked(ActionEvent event) {

        SceneController.switchScene("/com/rolegame/game/fxml/StartGame.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGamebutton.setText(LanguageManager.getText("EndMenu.startGameButton"));
        GameController gameController = DayController.getGameController();
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        deathCauseColumn.setCellValueFactory(new PropertyValueFactory<>("causeOfDeath"));
        setupTableView();
        gameEndTV.getItems().addAll(gameController.getAllPlayers());
        hasWonLabel.setText(gameController.getWinnerTeam() + " " +LanguageManager.getText("EndMenu.hasWon"));

        numberColumn.setText(LanguageManager.getText("EndMenu.number"));
        playerColumn.setText(LanguageManager.getText("EndMenu.player"));
        roleColumn.setText(LanguageManager.getText("EndMenu.role"));
        teamColumn.setText(LanguageManager.getText("EndMenu.team"));
        winStatusColumn.setText(LanguageManager.getText("EndMenu.winStatus"));
        aliveStatusColumn.setText(LanguageManager.getText("EndMenu.aliveStatus"));
        deathCauseColumn.setText(LanguageManager.getText("EndMenu.causeOfDeath"));

    }

    private void setupTableView(){
        teamColumn.setCellValueFactory(cellData -> {
            Role role = cellData.getValue().getRole();
            if (role != null && role.getTeam() != null) {
                return new SimpleStringProperty(role.getTeam().toString());
            } else {
                return new SimpleStringProperty("-");
            }
        });

        winStatusColumn.setCellValueFactory(cellData -> {
            boolean isHasWon = cellData.getValue().isHasWon();
            return new SimpleStringProperty(isHasWon ? LanguageManager.getText("EndMenu.won") : LanguageManager.getText("EndMenu.lost"));

        });

        aliveStatusColumn.setCellValueFactory(cellData -> {
            boolean isAlive = cellData.getValue().isAlive();
            return new SimpleStringProperty(isAlive ? LanguageManager.getText("EndMenu.alive"): LanguageManager.getText("EndMenu.dead"));

        });
    }
}
