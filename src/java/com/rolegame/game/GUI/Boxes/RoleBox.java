package com.rolegame.game.GUI.Boxes;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.RoleProperties.Team;
import com.rolegame.game.Roles.Role;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RoleBox extends VBox {

    public RoleBox(Role role){
        TextArea abilitesTextArea = new TextArea(role.getAbilities());
        abilitesTextArea.setPrefWidth(300);
        abilitesTextArea.setPrefHeight(50);
        abilitesTextArea.setWrapText(true);
        abilitesTextArea.setEditable(false);

        TextField attributesTextField = new TextField(role.getAttributes());
        attributesTextField.setEditable(false);
        attributesTextField.setPrefWidth(300);

        TextField goalTextField = new TextField(role.getGoal());
        goalTextField.setEditable(false);
        goalTextField.setPrefWidth(300);

        Label roleLabel = new Label(role.getName());
        roleLabel.getStyleClass().add("gameLabel");
        roleLabel.getStyleClass().add("roleShowLabel");

        HBox abilities = new HBox(createLabel(LanguageManager.getText("Role.abilities") +":"), abilitesTextArea);
        abilities.setAlignment(Pos.TOP_LEFT);
        abilities.setSpacing(17);

        HBox attributes = new HBox(createLabel(LanguageManager.getText("Role.attributes")+":"), attributesTextField);
        attributes.setAlignment(Pos.CENTER_LEFT);
        attributes.setSpacing(3);

        HBox goal = new HBox(createLabel(LanguageManager.getText("Role.goal")+":"), goalTextField);
        goal.setAlignment(Pos.BOTTOM_LEFT);
        goal.setSpacing(40);

        TextField attackTextField = new TextField(role.getAttack()+"");
        attackTextField.setEditable(false);
        attackTextField.setPrefWidth(92.5);

        TextField defenceTextField = new TextField(role.getDefence()+"");
        defenceTextField.setEditable(false);
        defenceTextField.setPrefWidth(92.5);

        HBox atkDef = new HBox(createLabel(LanguageManager.getText("Role.attack")+":"),attackTextField,createLabel(LanguageManager.getText("Role.defence")+":"),defenceTextField);
        atkDef.setAlignment(Pos.BOTTOM_LEFT);
        atkDef.setSpacing(28);


        if(role.getTeam()== Team.Folk){
            this.getStyleClass().add("folkRoleVbox");
        }else{
            this.getStyleClass().add("corrupterRoleVbox");
        }
        this.getChildren().addAll(roleLabel,abilities,attributes,goal,atkDef);

        this.setAlignment(Pos.CENTER);
        this.paddingProperty().set(new Insets(0,0,0,5));

    }

    public Label createLabel(String text){
        Label label = new Label(text);
        label.getStyleClass().add("gameLabel");
        label.getStyleClass().add("roleShowLabel");
        return label;
    }
}
