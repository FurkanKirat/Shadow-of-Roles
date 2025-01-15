package com.rolegame.game.GUI.Boxes;

import com.rolegame.game.GameManagement.Achievement;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class AchievementBox extends VBox {
    private String title;
    private String description;
    private boolean isCompleted;
    private Achievement.AchievementCategory category;

    // Constructor
    public AchievementBox(String title, String description, boolean isCompleted, Achievement.AchievementCategory category) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.category = category;

        // Set up the UI elements
        setupUI();
    }

    public AchievementBox(Achievement achievement) {
        this.title = achievement.getTitle();
        this.description = achievement.getDescription();
        this.isCompleted = achievement.isCompleted();
        this.category = achievement.getCategory();

        // Set up the UI elements
        setupUI();
    }

    private void setupUI() {
        // Title
        Text titleText = new Text(title);
        titleText.setFont(Font.font("Arial", 16));
        titleText.setFill(isCompleted ? Color.GREEN : Color.RED);

        // Description
        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font("Arial", 12));
        descriptionLabel.setTextFill(Color.GRAY);

        // Completion status
        String status = isCompleted ? "Completed" : "Not Completed";
        Text statusText = new Text(status);
        statusText.setFont(Font.font("Arial", 12));
        statusText.setFill(isCompleted ? Color.GREEN : Color.RED);

        // Category
        Label categoryLabel = new Label("Category: " + category);
        categoryLabel.setFont(Font.font("Arial", 12));
        categoryLabel.setTextFill(Color.DARKGRAY);

        // Add elements to the VBox
        this.getChildren().addAll(titleText, descriptionLabel, statusText, categoryLabel);

        // Style the VBox
        this.setSpacing(10);
        this.setStyle("-fx-padding: 10; -fx-background-color: #f4f4f4; -fx-border-radius: 10; -fx-background-radius: 10;");
        this.setAlignment(Pos.TOP_LEFT);
    }

}
