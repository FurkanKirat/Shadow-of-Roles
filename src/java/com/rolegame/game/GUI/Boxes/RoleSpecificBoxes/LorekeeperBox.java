package com.rolegame.game.GUI.Boxes.RoleSpecificBoxes;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.NeutralRole.Good.Lorekeeper;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleCatalog;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LorekeeperBox extends VBox {

    public LorekeeperBox(Lorekeeper lorekeeper){
        ComboBox<Role> rolesCombobox = new ComboBox<>();

        rolesCombobox.getItems().addAll(RoleCatalog.getAllRoles());

        rolesCombobox.setOnAction( (_) ->
        {
            lorekeeper.setGuessedRole(rolesCombobox.getValue());
        });

        Label trueGuessLabel = new Label();
        trueGuessLabel.getStyleClass().add("time-label");
        String textTemplate = LanguageManager.getText("Lorekeeper.guess");


        String text = textTemplate
                .replace("{guessCount}", lorekeeper.getTrueGuessCount()+"");
        trueGuessLabel.setText(text);

        this.getChildren().addAll(rolesCombobox, trueGuessLabel);

        this.setAlignment(Pos.CENTER);
    }
}
