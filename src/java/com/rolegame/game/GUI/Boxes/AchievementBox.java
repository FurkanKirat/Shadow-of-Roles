package com.rolegame.game.GUI.Boxes;

import com.rolegame.game.GameManagement.Achievement.Achievement;
import com.rolegame.game.GameManagement.Achievement.ProgressiveAchievement;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class AchievementBox extends VBox {
    private final String title;
    private final String description;
    private final boolean isCompleted;
    private final Achievement.AchievementCategory category;
    private int progress;
    private int max;

    public AchievementBox(Achievement achievement) {
        this.title = achievement.getTitle();
        this.description = achievement.getDescription();
        this.isCompleted = achievement.isCompleted();
        this.category = achievement.getCategory();

        if(achievement instanceof ProgressiveAchievement progressiveAchievement){
            this.progress = progressiveAchievement.getProgress();
            this.max = progressiveAchievement.getMax();
        }

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

        if(max!=0){

            Label progresslabel = new Label("Progress: " + progress+"/"+ max);
            progresslabel.setFont(Font.font("Arial", 12));
            progresslabel.setTextFill(Color.DARKGRAY);
            this.getChildren().addAll(progresslabel);
        }

        // Style the VBox
        this.setSpacing(10);
        this.setStyle("-fx-padding: 10; -fx-background-color: #f4f4f4; -fx-border-radius: 10; -fx-background-radius: 10;");
        this.setAlignment(Pos.CENTER);
        this.setPrefWidth(500);
        this.setMinSize(400,200);
    }

}
