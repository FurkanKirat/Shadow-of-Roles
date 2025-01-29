package com.rolegame.game.gui.components.boxes;

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

    public AchievementBox(Achievement achievement) {

        // Set up the UI elements
        setupUI(achievement);
    }

    private void setupUI(Achievement achievement) {
        Separator topSeparator = new Separator();

        // Title
        Text titleText = new Text(achievement.getTitle());
        titleText.setFont(Font.font("Arial", 16));
        titleText.setFill(achievement.isCompleted() ? COMPLETED_COLOR : UNCOMPLETED_COLOR);

        // Description
        Label descriptionLabel = new Label(achievement.getDescription());
        descriptionLabel.setFont(Font.font("Arial", 12));
        descriptionLabel.setTextFill(LABEL_COLOR);

        // Completion status
        String status = achievement.isCompleted() ? LanguageManager.getText("Achievements","completed") :
                LanguageManager.getText("Achievements","notCompleted");
        Text statusText = new Text(status);
        statusText.setFont(Font.font("Arial", 12));
        statusText.setFill(achievement.isCompleted() ? COMPLETED_COLOR : UNCOMPLETED_COLOR);

        // Category
        Label categoryLabel = new Label(LanguageManager.getText("Achievements","category")
                .replace("{achievementCategory}"
                        ,LanguageManager.getText("AchievementCategory",achievement.getCategory().name())));
        categoryLabel.setFont(Font.font("Arial", 12));
        categoryLabel.setTextFill(LABEL_COLOR);

        // Add elements to the VBox
        this.getChildren().addAll(topSeparator,titleText, descriptionLabel, statusText, categoryLabel);

        if(achievement instanceof ProgressiveAchievement progressiveAchievement){

            Label progresslabel = new Label(LanguageManager.getText("Achievements","progress")
                    .replace("{progress}", progressiveAchievement.getProgress()+"")
                    .replace("{goal}",progressiveAchievement.getMax()+""));
            double percentage = progressiveAchievement.getProgressPercentage();
            if(percentage>=1){
                progresslabel.getStyleClass().add("progress-label-finished");
            } else if (percentage>=0.5) {
                progresslabel.getStyleClass().add("progress-label-medium");
            }else{
                progresslabel.getStyleClass().add("progress-label-low");
            }
            this.getChildren().addAll(progresslabel);
        }
        Separator separator = new Separator();
        this.getChildren().add(separator);

        this.setSpacing(10);
        this.getStyleClass().add("achievement-box");
        this.setAlignment(Pos.CENTER);
        this.setPrefWidth(500);
        this.setMinSize(400,100);
    }

}
