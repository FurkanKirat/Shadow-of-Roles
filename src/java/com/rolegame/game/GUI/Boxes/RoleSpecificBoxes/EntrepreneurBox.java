package com.rolegame.game.GUI.Boxes.RoleSpecificBoxes;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.FolkRole.Unique.Entrepreneur;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EntrepreneurBox extends VBox {

    public EntrepreneurBox(Entrepreneur entrepreneur){
        entrepreneur.setMoney(entrepreneur.getMoney()+2);
        Button attackButton = new Button(LanguageManager.getText("Entrepreneur.attack"));
        Button healButton = new Button(LanguageManager.getText("Entrepreneur.heal"));
        Button infoButton = new Button(LanguageManager.getText("Entrepreneur.info"));
        Button passAbilityButton = new Button(LanguageManager.getText("Entrepreneur.pass"));
        Label selectedLabel = new Label(LanguageManager.getText("Entrepreneur.selected")
                .replace("{abilityType}",LanguageManager.getText("Entrepreneur.none")));
        Label moneyLabel = new Label();
        moneyLabel.setText(LanguageManager.getText("Entrepreneur.money")
                .replace("{money}",entrepreneur.getMoney()+""));

        attackButton.setOnAction((_) ->
        {
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.ATTACK);
            entrepreneur.setRolePriority(RolePriority.NONE);
            selectedLabel.setText(LanguageManager.getText("Entrepreneur.selected")
                    .replace("{abilityType}",LanguageManager.getText("Entrepreneur.attack")));
        });

        healButton.setOnAction((_) ->
        {
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.HEAL);
            entrepreneur.setRolePriority(RolePriority.SOULBINDER);
            selectedLabel.setText(LanguageManager.getText("Entrepreneur.selected")
                    .replace("{abilityType}", LanguageManager.getText("Entrepreneur.heal")));
        });
        infoButton.setOnAction((_) ->
        {
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.INFO);
            entrepreneur.setRolePriority(RolePriority.NONE);
            selectedLabel.setText(LanguageManager.getText("Entrepreneur.selected")
                    .replace("{abilityType}",LanguageManager.getText("Entrepreneur.info")));
        });
        passAbilityButton.setOnAction((_)->
        {
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.NONE);
            entrepreneur.setRolePriority(RolePriority.NONE);
            selectedLabel.setText(LanguageManager.getText("Entrepreneur.selected")
                    .replace("{abilityType}",LanguageManager.getText("Entrepreneur.none")));
        });

        HBox buttonBox = new HBox(attackButton, healButton, infoButton, passAbilityButton);
        this.getChildren().addAll(buttonBox, selectedLabel, moneyLabel);
        this.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);



    }
}
