package com.rolegame.game.gui.components.boxes;

import com.rolegame.game.gamestate.Time;
import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.corrupterroles.support.LastJoke;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.enums.Team;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerSelectionBox extends HBox {
    private static final Color CORRUPTER_COLOR = Color.rgb(219, 57, 62);
    private final Button selectButton;
    private Circle circle;

    private boolean isChosen = false;
    private boolean isPlayerCurrentPlayer;

    private final HBox leftBox = new HBox();

    public PlayerSelectionBox(Player player, Player currentPlayer, Time time) {
        this.getStylesheets().add("/com/rolegame/game/css/PlayerBox.css");
        this.getStyleClass().add("player-selection-box");

        setPlayerCurrentPlayer(player, currentPlayer);


        StackPane playerIcon = createPlayerIcon(player);
        Label playerName = createLabel(player.getName());
        playerName.setTextFill(Color.WHITE);
        leftBox.getChildren().add(playerName);


        selectButton = new Button(LanguageManager.getText("Menu", "select"));
        configureSelectButton(player, currentPlayer, time);


        initializeRoleLabel(player, currentPlayer);
        initializeYouLabel(currentPlayer);
        leftBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playerIcon, leftBox, new Region(), selectButton);
        HBox.setHgrow(this.getChildren().get(2), Priority.ALWAYS);
        initializeBoxColor();
    }

    private StackPane createPlayerIcon(Player player) {
        circle = new Circle(15);
        Label numberLabel = new Label(String.valueOf(player.getNumber()));
        numberLabel.setTextFill(Color.WHITE);
        return new StackPane(circle, numberLabel);
    }

    private void configureSelectButton(Player player, Player currentPlayer, Time time) {
        selectButton.setOnAction((_)-> toggleSelection(player, currentPlayer));

        if (time == Time.DAY) {
            selectButton.setVisible(false);
        }
        else if(time == Time.NIGHT){
            switch (currentPlayer.getRole().getTemplate().getAbilityType()) {

                case ACTIVE_SELF:
                    selectButton.setVisible(isPlayerCurrentPlayer);
                    break;

                case ACTIVE_ALL:
                    selectButton.setVisible(true);
                    break;

                case ACTIVE_OTHERS:
                    selectButton.setVisible(!isPlayerCurrentPlayer);
                    break;

                case OTHER_THAN_CORRUPTER:
                    selectButton.setVisible(player.getRole().getTemplate().getTeam() != Team.CORRUPTER);
                    break;

                default:
                    selectButton.setVisible(false);
            }

            if (currentPlayer.getRole().getTemplate() instanceof Lorekeeper lorekeeper &&
                    lorekeeper.getAlreadyChosenPlayers().contains(player)) {
                selectButton.setVisible(false);
            }

            if (currentPlayer.getRole().getTemplate() instanceof LastJoke && currentPlayer.isAlive()) {
                selectButton.setVisible(false);
            }
        }
        else{
            selectButton.setVisible(!isPlayerCurrentPlayer);
        }
    }

    private void setPlayerCurrentPlayer(Player player, Player currentPlayer){
        isPlayerCurrentPlayer = player.getNumber() == currentPlayer.getNumber();
    }
    private void initializeBoxColor(){

        if(isPlayerCurrentPlayer){
            this.getStyleClass().add("currentplayer");
            circle.getStyleClass().add("player-circle-currentplayer");
        }
        else{
            this.getStyleClass().add("others");
            circle.getStyleClass().add("player-circle-others");
        }

    }
    private void toggleSelection(Player player, Player currentPlayer) {
        isChosen = !isChosen;
        currentPlayer.getRole().setChoosenPlayer(isChosen ? player : null);
        selectButton.setText(LanguageManager.getText("Menu", isChosen ? "unselect" : "select"));

        for (PlayerSelectionBox playerSelectionBox : GameScreenController.getPlayerSelectionBoxes()) {
            if (!playerSelectionBox.equals(this)) {
                playerSelectionBox.setChosen(false);
            }
        }
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
        selectButton.setText(LanguageManager.getText("Menu", isChosen ? "unselect" : "select"));
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("playerLabel");
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private void initializeRoleLabel(Player player, Player currentPlayer) {
        if (player.isRevealed() || (currentPlayer.getRole().getTemplate().getTeam() == Team.CORRUPTER && player.getRole().getTemplate().getTeam() == Team.CORRUPTER)) {
            Label roleLabel = createLabel("(" + player.getRole().getTemplate().getName() + ")");
            roleLabel.setAlignment(Pos.CENTER);
            roleLabel.getStyleClass().add("corrupt-role-label");
            leftBox.getChildren().add(roleLabel);
        }
    }

    private void initializeYouLabel(Player currentPlayer) {
        if (isPlayerCurrentPlayer) {
            Label youLabel = createLabel("(" + LanguageManager.getText("Menu", "you") + ")");
            youLabel.setTextFill(Color.SNOW);
            youLabel.setAlignment(Pos.CENTER);
            youLabel.getStyleClass().add("you-label");
            leftBox.getChildren().add(youLabel);

            if (currentPlayer.getRole().getTemplate().getTeam() == Team.CORRUPTER) {
                youLabel.setTextFill(CORRUPTER_COLOR);
            }
        }
    }
}
