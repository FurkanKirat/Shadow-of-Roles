package com.rolegame.game.GUI.Boxes;

import com.rolegame.game.GameManagement.Achievement.Achievement;
import com.rolegame.game.GameManagement.Achievement.ProgressiveAchievement;
import com.rolegame.game.PropertyControllers.LanguageManager;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class AchievementBox extends VBox {
    private static final Color COMPLETED_COLOR = Color.rgb(5, 122, 17);
    private static final Color UNCOMPLETED_COLOR = Color.rgb(181, 55, 38);
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
        titleText.setFill(isCompleted ? COMPLETED_COLOR : UNCOMPLETED_COLOR);

        // Description
        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font("Arial", 12));
        descriptionLabel.setTextFill(Color.GRAY);

        // Completion status
        String status = isCompleted ? LanguageManager.getText("Achievements","completed") :
                LanguageManager.getText("Achievements","notCompleted");
        Text statusText = new Text(status);
        statusText.setFont(Font.font("Arial", 12));
        statusText.setFill(isCompleted ? COMPLETED_COLOR : UNCOMPLETED_COLOR);

        // Category
        Label categoryLabel = new Label(LanguageManager.getText("Achievements","category")
                .replace("{achievementCategory}"
                        ,LanguageManager.getText("AchievementCategory",category.name())));
        categoryLabel.setFont(Font.font("Arial", 12));
        categoryLabel.setTextFill(Color.DARKGRAY);

        // Add elements to the VBox
        this.getChildren().addAll(titleText, descriptionLabel, statusText, categoryLabel);

        if(max!=0){

            Label progresslabel = new Label(LanguageManager.getText("Achievements","progress")
                    .replace("{progress}",progress+"")
                    .replace("{goal}",max+""));
            progresslabel.setFont(Font.font("Arial", 12));
            progresslabel.setTextFill(Color.DARKGRAY);
            this.getChildren().addAll(progresslabel);
        }
        Separator separator = new Separator();
        this.getChildren().add(separator);

        // Style the VBox
        this.setSpacing(10);
        this.setStyle("-fx-padding: 10; -fx-background-color: transparent; -fx-border-radius: 10; -fx-background-radius: 10;");
        this.setAlignment(Pos.CENTER);
        this.setPrefWidth(500);
        this.setMinSize(400,100);
    }

}
