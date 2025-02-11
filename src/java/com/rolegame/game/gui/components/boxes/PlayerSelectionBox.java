package com.rolegame.game.gui.components.boxes;

import com.rolegame.game.gamestate.Time;
import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.support.LastJoke;
import com.rolegame.game.models.roles.enums.AbilityType;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.enums.Team;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerSelectionBox extends HBox{
    private static final Color CORRUPTER_COLOR = Color.rgb(219, 57, 62);
    private final Button selectButton;
    private Label roleLabel;
    private boolean isChosen = false;
    private HBox roleBox;

    public PlayerSelectionBox(Player player, Player currentPlayer, Time time) {

        this.getStyleClass().add("player-selection-box");
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

        if(player.isRevealed()){
            roleLabel = createLabel("(" + player.getRole().getTemplate().getName() + ")");
            roleLabel.setAlignment(Pos.CENTER);
        }
        if(currentPlayer.getRole().getTemplate().getTeam()==Team.CORRUPTER &&
                player.getRole().getTemplate().getTeam()==Team.CORRUPTER){

            roleLabel = createLabel("(" + player.getRole().getTemplate().getName() + ")");
            roleLabel.setAlignment(Pos.CENTER);


            playerName.setTextFill(CORRUPTER_COLOR);
            roleLabel.getStyleClass().add("corrupt-role-label");
            playerName.getStyleClass().add("corrupt-role-label");


            if(currentPlayer.getRole().getTemplate().getAbilityType() == AbilityType.OTHER_THAN_CORRUPTER){
                selectButton.setVisible(false);
            }

        }
        if(roleLabel != null){
            roleBox = new HBox(roleLabel);
            roleBox.setAlignment(Pos.CENTER);
            this.getChildren().add(roleBox);
        }
        if(currentPlayer.getNumber()==player.getNumber()){

            Label youLabel = createLabel("("+ LanguageManager.getText("Menu","you")+")");
            youLabel.setTextFill(Color.SNOW);
            youLabel.setAlignment(Pos.CENTER);

            HBox youBox = new HBox(youLabel);
            youBox.setAlignment(Pos.CENTER);


            if(currentPlayer.getRole().getTemplate().getTeam()==Team.CORRUPTER){
                this.getChildren().remove(roleBox);
                youLabel.setTextFill(CORRUPTER_COLOR);
            }
            youLabel.getStyleClass().add("you-label");
            this.getChildren().add(youBox);

            if(time!=Time.NIGHT){
                selectButton.setVisible(false);
            }
            else{
                switch (currentPlayer.getRole().getTemplate().getAbilityType()){
                    case PASSIVE, ACTIVE_OTHERS, OTHER_THAN_CORRUPTER, NO_ABILITY:
                        selectButton.setVisible(false);
                }
            }


        }

        if(time==Time.DAY){
            selectButton.setVisible(false);
        }

        else if(time == Time.NIGHT){
            switch (currentPlayer.getRole().getTemplate().getAbilityType()) {
                case ACTIVE_SELF, NO_ABILITY:
                    selectButton.setVisible(false);
            }

            if(currentPlayer.getRole().getTemplate() instanceof Lorekeeper lorekeeper &&
                    lorekeeper.getAlreadyChosenPlayers().contains(player)){
                selectButton.setVisible(false);
            }
        }


        if(currentPlayer.getRole().getTemplate() instanceof LastJoke && !currentPlayer.isAlive()){
            selectButton.setVisible(true);
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
