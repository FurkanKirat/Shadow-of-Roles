package com.rolegame.game.gui.components;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class LoadingScreen extends StackPane {

    private final Label loadingLabel;
    private final ProgressBar progressBar;

    public LoadingScreen() {
        // Background color and border radius for a more modern look
        setStyle("-fx-background-color: #34495e; -fx-background-radius: 10px;");
        setPrefSize(400, 200);

        // Create a label for the loading text
        loadingLabel = new Label("Loading...");
        loadingLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ecf0f1;");
        loadingLabel.setFont(Font.font("Arial", 30));
        loadingLabel.setTextAlignment(TextAlignment.CENTER);

        // Create a progress bar with style
        progressBar = new ProgressBar();
        progressBar.setProgress(-1f); // Indeterminate progress
        progressBar.setStyle("-fx-accent: #1abc9c;");
        progressBar.setPrefWidth(300);
        progressBar.setMaxHeight(10);

        // VBox layout for the label and progress bar
        VBox vbox = new VBox(20, loadingLabel, progressBar);
        vbox.setAlignment(Pos.CENTER);
        getChildren().add(vbox);

        // Animate the loading text
        animateLoadingText();
    }

    private void animateLoadingText() {
        // Simple scale transition for a pulse effect
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), loadingLabel);
        scaleTransition.setByX(0.1);
        scaleTransition.setByY(0.1);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    public void animateProgressBar(int durationMillis) {
        // Create a timeline to animate the progress bar over the duration of the pause transition
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(durationMillis), new KeyValue(progressBar.progressProperty(), 1.0));
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
