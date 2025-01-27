package com.rolegame.game.gui.boxes;

import com.rolegame.game.models.achievement.Achievement;
import com.rolegame.game.models.achievement.ProgressiveAchievement;
import com.rolegame.game.managers.LanguageManager;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class AchievementBox extends VBox {
    private static final Color COMPLETED_COLOR = Color.rgb(10, 242, 21);
    private static final Color UNCOMPLETED_COLOR = Color.rgb(232, 5, 12);
    private static final Color LABEL_COLOR = Color.BLACK;
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
        Separator topSeparator = new Separator();

        // Title
        Text titleText = new Text(title);
        titleText.setFont(Font.font("Arial", 16));
        titleText.setFill(isCompleted ? COMPLETED_COLOR : UNCOMPLETED_COLOR);

        // Description
        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font("Arial", 12));
        descriptionLabel.setTextFill(LABEL_COLOR);

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
        categoryLabel.setTextFill(LABEL_COLOR);

        // Add elements to the VBox
        this.getChildren().addAll(topSeparator,titleText, descriptionLabel, statusText, categoryLabel);

        if(max!=0){

            Label progresslabel = new Label(LanguageManager.getText("Achievements","progress")
                    .replace("{progress}",progress+"")
                    .replace("{goal}",max+""));
            double percentage = (double)progress/max;
            if(percentage>=1){
                progresslabel.getStyleClass().add("progress-label");
            } else if (percentage>=0.5) {
                progresslabel.getStyleClass().add("progress-label-medium");
            }else{
                progresslabel.getStyleClass().add("progress-label-low");
            }
            this.getChildren().addAll(progresslabel);
        }
        Separator separator = new Separator();
        this.getChildren().add(separator);

        // Style the VBox
        this.setSpacing(10);
        this.getStyleClass().add("achievement-box");
        this.setAlignment(Pos.CENTER);
        this.setPrefWidth(500);
        this.setMinSize(400,100);
    }

}
