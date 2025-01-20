package com.rolegame.game.GUI.Boxes.RoleSpecificBoxes;

import com.rolegame.game.Roles.NeutralRole.Good.Lorekeeper;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleCatalog;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class LorekeeperBox extends VBox {

    public LorekeeperBox(Lorekeeper lorekeeper){
        ComboBox<Role> rolesCombobox = new ComboBox<>();

        rolesCombobox.getItems().addAll(RoleCatalog.getAllRoles());

        rolesCombobox.setOnAction( (_) ->
        {
            lorekeeper.setGuessedRole(rolesCombobox.getValue());
        });

        this.getChildren().add(rolesCombobox);
    }
}
