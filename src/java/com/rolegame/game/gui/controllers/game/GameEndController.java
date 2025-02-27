package com.rolegame.game.gui.controllers.game;

import com.rolegame.game.gamestate.CauseOfDeath;
import com.rolegame.game.models.achievement.Achievement;
import com.rolegame.game.models.roles.templates.RoleTemplate;
import com.rolegame.game.services.GameEndService;
import com.rolegame.game.services.GameService;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.managers.AchievementManager;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import com.rolegame.game.models.roles.folkroles.protector.FolkHero;
import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.enums.Team;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    private TableColumn<Player, String> roleColumn;

    @FXML
    private TableColumn<Player, String> teamColumn;

    @FXML
    private TableColumn<Player, String> winStatusColumn;

    @FXML
    private Button startGamebutton;

    @FXML
    private VBox root;

    @FXML
    void startGameClicked(ActionEvent event) {

        SceneManager.mainMenuScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameService gameService = GameScreenController.getGameService();
        int rowHeight = 30; // Row height
        int numberOfRows = gameService.getAllPlayers().size(); // Number of players

        gameEndTV.setFixedCellSize(rowHeight); // Set fixed row height
        gameEndTV.setPrefHeight(rowHeight * numberOfRows+30);
        startGamebutton.setText(LanguageManager.getText("EndMenu","startGameButton"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        setupTableView();
        gameEndTV.getItems().addAll(gameService.getAllPlayers());

        boolean winnerTeamExists = gameService.getWinnerTeam()!= Team.NONE;
        hasWonLabel.setText(winnerTeamExists ?
                LanguageManager.getText("EndMenu","hasWon")
                .replace("{teamName}",LanguageManager.getText("Role", gameService.getWinnerTeam().toString()))
                : LanguageManager.getText("EndMenu","draw"));

        numberColumn.setText(LanguageManager.getText("EndMenu","number"));
        playerColumn.setText(LanguageManager.getText("EndMenu","player"));
        roleColumn.setText(LanguageManager.getText("EndMenu","role"));
        teamColumn.setText(LanguageManager.getText("EndMenu","team"));
        winStatusColumn.setText(LanguageManager.getText("EndMenu","winStatus"));
        aliveStatusColumn.setText(LanguageManager.getText("EndMenu","aliveStatus"));
        deathCauseColumn.setText(LanguageManager.getText("EndMenu","causeOfDeath"));
        setImage();
        GameEndService.progressAchievements();

    }

    /**
     * Sets the background image according to winner team
     */
    private void setImage(){
        String styleTemplate = "-fx-background-image: url(/com/rolegame/game/images/gameend/{team}.jpg);"+
                "-fx-background-size: cover;";

        String team = switch (GameScreenController.getGameService().getWinnerTeam()) {
            case CORRUPTER -> "corrupt";
            case FOLK -> "folk";
            default -> "neutral";
        };

        String style = styleTemplate.replace("{team}", team);
        root.getStyleClass().clear();
        root.setStyle(style);

    }

    private void setupTableView(){
        teamColumn.setCellValueFactory(cellData -> {
            RoleTemplate role = cellData.getValue().getRole().getTemplate();
            if (role != null && role.getTeam() != null) {
                return new SimpleStringProperty(LanguageManager.getText("Role",role.getTeam().toString()));
            } else {
                return new SimpleStringProperty("-");
            }
        });

        winStatusColumn.setCellValueFactory(cellData -> {
            boolean isHasWon = cellData.getValue().isHasWon();
            return new SimpleStringProperty(isHasWon ? LanguageManager.getText("EndMenu","won") : LanguageManager.getText("EndMenu","lost"));

        });

        aliveStatusColumn.setCellValueFactory(cellData -> {
            boolean isAlive = cellData.getValue().isAlive();
            return new SimpleStringProperty(isAlive ? LanguageManager.getText("EndMenu","alive"): LanguageManager.getText("EndMenu","dead"));

        });

        roleColumn.setCellValueFactory(cellData -> {
            RoleTemplate roleTemplate = cellData.getValue().getRole().getTemplate();
            return new SimpleStringProperty(roleTemplate.getName());
        });

        deathCauseColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCausesOfDeathAsString()));
    }


}
