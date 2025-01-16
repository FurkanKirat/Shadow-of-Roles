package com.rolegame.game;

import com.rolegame.game.PropertyControllers.AchievementManager;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        LanguageManager.changeLanguage(LanguageManager.loadLanguage());
        AchievementManager.saveAchievements();
        stage.setOnCloseRequest((event)-> {
            event.consume();
            SceneController.onClose();
        });
        stage.setTitle("Shadow of Roles");
        SceneController.changeStage(stage);
        SceneController.mainMenuScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}