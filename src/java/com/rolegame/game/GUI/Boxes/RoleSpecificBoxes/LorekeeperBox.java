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
        String textTemplate = LanguageManager.getText("Lorekeeper.guess");


<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
        String text = textTemplate
                .replace("{guessCount}", lorekeeper.getTrueGuessCount()+"");
        trueGuessLabel.setText(text);

<<<<<<< Updated upstream
        this.getChildren().addAll(rolesCombobox, trueGuessLabel);

=======

        this.getChildren().addAll(rolesCombobox, trueGuessLabel);

        this.getChildren().addAll(rolesCombobox,trueGuessLabel);

>>>>>>> Stashed changes
        this.setAlignment(Pos.CENTER);
    }
}
