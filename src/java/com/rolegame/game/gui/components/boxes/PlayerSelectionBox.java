package com.rolegame.game.gui.components.boxes;

import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.models.Player;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.support.LastJoke;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.Team;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerSelectionBox extends HBox{
    private static final Color CORRUPTER_COLOR = Color.rgb(219, 57, 62);
    private final Button selectButton;
    private boolean isChosen = false;
    private Label roleLabel;
    private HBox roleBox;

    public PlayerSelectionBox(Player player, Player currentPlayer, boolean isDay) {

        Circle circle = new Circle(15);
        circle.getStyleClass().add("playerCircle");

        Label numberLabel = new Label(player.getNumber()+"");
        numberLabel.setTextFill(Color.WHITE);

        StackPane stackPane = new StackPane(circle,numberLabel);

        Label playerName = createLabel(player.getName());
        playerName.setTextFill(Color.WHITE);
        HBox playerNameBox = new HBox(playerName);
        playerNameBox.setAlignment(Pos.CENTER);

        playerName.setAlignment(Pos.CENTER);


        selectButton = new Button(LanguageManager.getText("Menu","select"));

        selectButton.setOnAction((_)->{


            isChosen = !isChosen;
            if(isChosen){
                currentPlayer.getRole().setChoosenPlayer(player);
                selectButton.setText(LanguageManager.getText("Menu","unselect"));
            }
            else{
                currentPlayer.getRole().setChoosenPlayer(null);
                selectButton.setText(LanguageManager.getText("Menu","select"));
            }

            for(PlayerSelectionBox playerSelectionBox: GameScreenController.getPlayerSelectionBoxes()){
                if (!playerSelectionBox.equals(this)) {
                    playerSelectionBox.setChosen(false);
                }
            }
            });
        this.getChildren().addAll(stackPane,playerNameBox);

        if(currentPlayer.getRole().getTeam()==Team.CORRUPTER &&player.getRole().getTeam()==Team.CORRUPTER){

            roleLabel = createLabel("("+player.getRole().getName()+")");
            roleLabel.setTextFill(CORRUPTER_COLOR);
            roleLabel.setAlignment(Pos.CENTER);

            roleBox = new HBox(roleLabel);
            roleBox.setAlignment(Pos.CENTER);

            playerName.setTextFill(CORRUPTER_COLOR);
            this.getChildren().add(roleBox);

        }

        if(currentPlayer.equals(player)){

            Label youLabel = createLabel("("+ LanguageManager.getText("Menu","you")+")");
            youLabel.setTextFill(Color.SNOW);
            youLabel.setAlignment(Pos.CENTER);

            HBox youBox = new HBox(youLabel);
            youBox.setAlignment(Pos.CENTER);


            if(currentPlayer.getRole().getTeam()==Team.CORRUPTER){
                this.getChildren().remove(roleBox);
                youLabel.setTextFill(CORRUPTER_COLOR);
            }
            this.getChildren().add(youBox);
            selectButton.setVisible(false);
        }

        if(!(currentPlayer.getRole() instanceof ActiveNightAbility) && !isDay){
            selectButton.setVisible(false);
        }

        if(currentPlayer.getRole() instanceof LastJoke lastJoker && !lastJoker.getRoleOwner().isAlive()){
            selectButton.setVisible(true);
        }

        if(currentPlayer.getRole() instanceof Lorekeeper lorekeeper && !isDay &&
                lorekeeper.getAlreadyChosenPlayers().contains(player)){
            selectButton.setVisible(false);
        }

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        this.setSpacing(10);

        this.getChildren().addAll(spacer,selectButton);
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
        selectButton.setText(isChosen ? LanguageManager.getText("Menu","unselect") : LanguageManager.getText("Menu","select"));
    }

    public void setButtonVisible(boolean status){
        selectButton.setVisible(status);
    }

    private Label createLabel(String text){
        Label label = new Label(text);
        label.getStyleClass().add("playerLabel");
        return label;
    }
}
