package com.rolegame.game.GUI.Boxes;

import com.rolegame.game.GUI.Controllers.GameScreenController;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.RoleProperties.Team;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerSelectionBox extends HBox{
    private final Button selectButton;
    private boolean isChosen = false;
    private Label roleLabel;
    private HBox roleBox;

    public PlayerSelectionBox(Player player, Player currentPlayer) {

        Circle circle = new Circle(15);
        circle.getStyleClass().add("playerCircle");

        Label numberLabel = new Label(player.getNumber()+"");
        numberLabel.setTextFill(Color.WHITE);

        StackPane stackPane = new StackPane(circle,numberLabel);

        Label playerName = new Label(player.getName());
        HBox playerNameBox = new HBox(playerName);
        playerNameBox.setAlignment(Pos.CENTER);

        playerName.setAlignment(Pos.CENTER);


        selectButton = new Button(LanguageManager.getText("Menu.select"));

        selectButton.setOnAction((_)->{


            isChosen = !isChosen;
            if(isChosen){
                currentPlayer.getRole().setChoosenPlayer(player);
                selectButton.setText(LanguageManager.getText("Menu.unselect"));
            }
            else{
                currentPlayer.getRole().setChoosenPlayer(null);
                selectButton.setText(LanguageManager.getText("Menu.select"));
            }

            for(PlayerSelectionBox playerSelectionBox: GameScreenController.getPlayerSelectionBoxes()){
                if(playerSelectionBox.equals(this)){
                    continue;
                }
                playerSelectionBox.setChosen(false);
            }
            });
        this.getChildren().addAll(stackPane,playerNameBox);

        if(currentPlayer.getRole().getTeam()==Team.Corrupter&&player.getRole().getTeam()==Team.Corrupter){

            roleLabel = new Label("("+player.getRole().getName()+")");
            roleLabel.setTextFill(Color.RED);
            roleLabel.setAlignment(Pos.CENTER);

            roleBox = new HBox(roleLabel);
            roleBox.setAlignment(Pos.CENTER);

            playerName.setTextFill(Color.RED);
            this.getChildren().add(roleBox);

        }

        if(currentPlayer.equals(player)){

            Label youLabel = new Label("("+ LanguageManager.getText("Menu.you")+")");
            youLabel.setTextFill(Color.BLACK);
            youLabel.setAlignment(Pos.CENTER);

            HBox youBox = new HBox(youLabel);
            youBox.setAlignment(Pos.CENTER);


            if(currentPlayer.getRole().getTeam()==Team.Corrupter){
                this.getChildren().remove(roleBox);
                youLabel.setTextFill(Color.RED);
            }
            this.getChildren().add(youBox);
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
        selectButton.setText(isChosen ? LanguageManager.getText("Menu.unselect") : LanguageManager.getText("Menu.select"));
    }

    public void setButtonVisible(boolean status){
        selectButton.setVisible(status);
    }
}
