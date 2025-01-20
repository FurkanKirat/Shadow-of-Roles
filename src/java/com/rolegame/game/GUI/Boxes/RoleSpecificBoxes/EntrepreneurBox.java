package com.rolegame.game.GUI.Boxes.RoleSpecificBoxes;

import com.rolegame.game.Roles.FolkRole.Unique.Entrepreneur;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EntrepreneurBox extends VBox {

    public EntrepreneurBox(Entrepreneur entrepreneur){
        Button attackButton = new Button("Attack");
        Button healButton = new Button("Heal");
        Button infoButton = new Button("Info");
        Button passAbilityButton = new Button("Pass Ability");
        Label selectedLabel = new Label("Selected: None");
        Label moneyLabel = new Label();
        moneyLabel.setText("Your money: "+entrepreneur.getMoney());

        attackButton.setOnAction((_) ->
        {
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.Attack);
            entrepreneur.setRolePriority(RolePriority.None);
            selectedLabel.setText("Selected: Attack");
        });

        healButton.setOnAction((_) ->
        {
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.Heal);
            entrepreneur.setRolePriority(RolePriority.Soulbinder);
            selectedLabel.setText("Selected: Heal");
        });
        infoButton.setOnAction((_) ->
        {
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.Info);
            entrepreneur.setRolePriority(RolePriority.None);
            selectedLabel.setText("Selected: Info");
        });
        passAbilityButton.setOnAction((_)->
        {
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.None);
            entrepreneur.setRolePriority(RolePriority.None);
            selectedLabel.setText("Selected: None");
        });

        HBox buttonBox = new HBox(attackButton,healButton,infoButton);
        this.getChildren().addAll(buttonBox, selectedLabel, moneyLabel);
        this.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);



    }
}
