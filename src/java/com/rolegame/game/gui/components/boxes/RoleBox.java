package com.rolegame.game.gui.components.boxes;

import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.templates.RoleTemplate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A UI component for displaying the details of a game role in the game guide.
 */
public class RoleBox extends VBox {


    public RoleBox(RoleTemplate role) {
        // Style the container
        setPadding(new Insets(15));
        setStyle("-fx-border-color: #a084ca; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Set background color based on role type
        String backgroundColor = getBackgroundColorByRoleType(role);
        setStyle(getStyle() + "-fx-background-color: " + backgroundColor + ";");

        // Main content layout (VBox)
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

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
        content.getChildren().addAll(roleNameLabel, abilitiesLabel, attributesLabel, goalLabel);

        // Close button
        Button closeButton = new Button("✖");
        closeButton.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-cursor: hand;"
        );
        closeButton.setOnAction(_ -> {
            // Remove this RoleBox from its parent
            ((VBox) this.getParent()).getChildren().remove(this);
        });

        // Close button container (HBox for alignment)
        HBox closeButtonContainer = new HBox();
        closeButtonContainer.setAlignment(Pos.TOP_RIGHT);
        closeButtonContainer.getChildren().add(closeButton);

        // Add content and close button container to the StackPane
        getChildren().addAll(content, closeButtonContainer);
    }


    /**
     * Returns a background color based on the role type.
     *
     * @param role The role whose type determines the color.
     * @return A CSS-compatible color string.
     */
    private String getBackgroundColorByRoleType(RoleTemplate role) {
        return switch (role.getTeam()) { // Assuming Role has a `getType` method
            case CORRUPTER -> "rgba(255, 69, 0, 0.8)"; // Orange-Red for corrupter roles
            case FOLK -> "rgba(50, 205, 50, 0.8)"; // Green for folk roles
            default -> "rgba(46, 13, 37, 0.8)"; // Default background color
        };
    }
}
