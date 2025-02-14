package com.rolegame.game.gui.components.boxes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DeadPlayerBox extends VBox {

    public DeadPlayerBox(String playerName, String causeOfDeaths, ListView<DeadPlayerBox> deadPlayerBoxListView) {
        Label playerLabel = new Label(playerName);
        playerLabel.setTextFill(Color.WHEAT);
        playerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label deathCauseLabel = new Label("Cause of death(s): " + causeOfDeaths);
        deathCauseLabel.setWrapText(true);
        deathCauseLabel.setTextFill(Color.LIGHTGRAY);
        deathCauseLabel.setFont(Font.font("Arial", 12));

        this.setPadding(new Insets(10));
        this.setSpacing(5);
        this.setStyle("-fx-background-color: #2a2a2a; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 10px; " +
                "-fx-background-radius: 10px; " +
                "-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.2), 5, 0, 0, 2);");

        this.getChildren().addAll(playerLabel, deathCauseLabel);
        this.maxWidthProperty().bind(deadPlayerBoxListView.widthProperty().subtract(30));


    }
}
