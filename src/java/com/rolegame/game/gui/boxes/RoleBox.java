package com.rolegame.game.gui.boxes;

import com.rolegame.game.models.roles.Role;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A UI component for displaying the details of a game role in the game guide.
 */
public class RoleBox extends VBox {

    public RoleBox(Role role) {
        // Style the box
        setSpacing(10);
        setPadding(new Insets(15));
        setStyle("-fx-border-color: #a084ca; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Set background color based on role type
        String backgroundColor = getBackgroundColorByRoleType(role);
        setStyle(getStyle() + "-fx-background-color: " + backgroundColor + ";");

        // Add the role name
        Label roleNameLabel = new Label("Role: " + role.getName());
        roleNameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        // Add abilities
        Label abilitiesLabel = new Label("Abilities: " + role.getAbilities());
        abilitiesLabel.setStyle("-fx-text-fill: #d3d3d3;");

        // Add attributes
        Label attributesLabel = new Label("Attributes: " + role.getAttributes());
        attributesLabel.setStyle("-fx-text-fill: #d3d3d3;");

        // Add goal
        Label goalLabel = new Label("Goal: " + role.getGoal());
        goalLabel.setStyle("-fx-text-fill: #d3d3d3;");

        // Add components to the VBox
        getChildren().addAll(roleNameLabel, abilitiesLabel, attributesLabel, goalLabel);
    }

    /**
     * Returns a background color based on the role type.
     *
     * @param role The role whose type determines the color.
     * @return A CSS-compatible color string.
     */
    private String getBackgroundColorByRoleType(Role role) {
        return switch (role.getTeam()) { // Assuming Role has a `getType` method
            case CORRUPTER -> "rgba(255, 69, 0, 0.8)"; // Orange-Red for aggressive roles
            case FOLK -> "rgba(50, 205, 50, 0.8)"; // Green for support roles
            case NEUTRAL, NONE -> "rgba(64, 64, 64, 0.8)"; // Dark Gray for stealthy roles
        };
    }
}
