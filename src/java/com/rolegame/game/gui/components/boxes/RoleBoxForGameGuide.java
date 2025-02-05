package com.rolegame.game.gui.components.boxes;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.Role;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * A UI component for displaying the details of a game role in the game guide.
 */
public class RoleBoxForGameGuide extends VBox {

    public RoleBoxForGameGuide(Role role) {
        // Style the container
        setPadding(new Insets(15));
        setStyle("-fx-border-color: #a084ca; -fx-border-radius: 10; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 5, 0, 2, 2);");

        // Set background color based on role type
        String backgroundColor = getBackgroundColorByRoleType(role);
        setStyle(getStyle() + "-fx-background-color: " + backgroundColor + ";");

        // Main content layout (VBox)
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        // Add the role name
        Label roleNameLabel = new Label(role.getName());
        roleNameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #ffffff;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 5, 0, 2, 2);");

        // Add abilities
        Label abilitiesLabel = new Label(LanguageManager.getText("GameGuide","RoleAbilities")
                .replace("{abilities}",role.getAbilities()));
        abilitiesLabel.setStyle("-fx-text-fill: #d3d3d3;");

        // Add attributes
        Label attributesLabel = new Label(LanguageManager.getText("GameGuide","RoleAttributes")
                .replace("{attributes}",role.getAttributes()));
        attributesLabel.setStyle("-fx-text-fill: #d3d3d3;");

        // Add goal
        Label goalLabel = new Label(LanguageManager.getText("GameGuide","RoleGoal")
                .replace("{goal}",role.getGoal()));
        goalLabel.setStyle("-fx-text-fill: #d3d3d3;");

        // Add components to the VBox
        content.getChildren().addAll(roleNameLabel, abilitiesLabel, attributesLabel, goalLabel);

        // Add content to the VBox
        getChildren().addAll(content);

        VBox.setVgrow(this, Priority.ALWAYS);
        HBox.setHgrow(this, Priority.NEVER);

    }

    /**
     * Returns a background color based on the role type.
     *
     * @param role The role whose type determines the color.
     * @return A CSS-compatible color string.
     */
    private String getBackgroundColorByRoleType(Role role) {
        return switch (role.getTeam()) {
            case CORRUPTER -> "rgba(255, 69, 0, 0.8)";
            case FOLK -> "rgba(50, 205, 50, 0.8)";
            case NEUTRAL -> "rgba(135, 206, 250, 0.8)";
            default -> "rgba(46, 13, 37, 0.8)";
        };
    }
}
