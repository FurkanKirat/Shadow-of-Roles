package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.GameManagement.Achievement.Achievement;
import com.rolegame.game.GameManagement.GameController;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.AchievementManager;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import com.rolegame.game.Roles.FolkRole.Protector.FolkHero;
import com.rolegame.game.Roles.NeutralRole.Chaos.ChillGuy;
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
import javafx.scene.layout.VBox;

import java.net.URL;
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
    private TableColumn<Player, Role> roleColumn;

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

        SceneController.mainMenuScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameController gameController = GameScreenController.getGameController();
        int rowHeight = 30; // Row height
        int numberOfRows = gameController.getAllPlayers().size(); // Number of players

        gameEndTV.setFixedCellSize(rowHeight); // Set fixed row height
        gameEndTV.setPrefHeight(rowHeight * numberOfRows+30);
        startGamebutton.setText(LanguageManager.getText("EndMenu","startGameButton"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        deathCauseColumn.setCellValueFactory(new PropertyValueFactory<>("causeOfDeath"));
        setupTableView();
        gameEndTV.getItems().addAll(gameController.getAllPlayers());
        hasWonLabel.setText(LanguageManager.getText("EndMenu","hasWon")
                .replace("{teamName}",LanguageManager.getText("Role",gameController.getWinnerTeam().toString())));

        numberColumn.setText(LanguageManager.getText("EndMenu","number"));
        playerColumn.setText(LanguageManager.getText("EndMenu","player"));
        roleColumn.setText(LanguageManager.getText("EndMenu","role"));
        teamColumn.setText(LanguageManager.getText("EndMenu","team"));
        winStatusColumn.setText(LanguageManager.getText("EndMenu","winStatus"));
        aliveStatusColumn.setText(LanguageManager.getText("EndMenu","aliveStatus"));
        deathCauseColumn.setText(LanguageManager.getText("EndMenu","causeOfDeath"));
        setImage();
        progressAchievements();

    }

    private void setImage(){
        String styleTemplate = "-fx-background-image: url(/com/rolegame/game/images/gameend/{team}.jpg);"+
                "-fx-background-size: cover;";

        String team = switch (GameScreenController.getGameController().getWinnerTeam()) {
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
            Role role = cellData.getValue().getRole();
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
    }

    private void progressAchievements(){
        for(Map.Entry<Achievement.AchievementID, Achievement> achievementEntry : AchievementManager.loadAchievements().entrySet()){
            if(achievementEntry.getValue().getCategory() == Achievement.AchievementCategory.PLAY_GAME){
                AchievementManager.addProgressToAchievement(achievementEntry.getKey(), 1);
            }

        }

        for (Player player: GameScreenController.getGameController().getAllPlayers()){
            if(player.getRole() instanceof FolkHero folkHero && folkHero.getAbilityUseCount() == 0 &&
                    player.isHasWon()){
                AchievementManager.completeAchievement(Achievement.AchievementID.LAZY_HERO);
            }
            else if(player.getRole() instanceof ChillGuy && !player.isHasWon()){
                AchievementManager.completeAchievement(Achievement.AchievementID.WIN_SACRIFICE);
            }
        }

    }
}
