package com.rolegame.game;

import com.rolegame.game.managers.AchievementManager;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        LanguageManager.loadLanguageAndTheme();
        AchievementManager.saveAchievements();
        stage.setOnCloseRequest((event)-> {
            event.consume();
            SceneManager.onClose();
        });
        stage.getIcons().add(new Image("/com/rolegame/game/images/logo.jpg"));
        stage.setTitle("Shadow of Roles");
        Screen screen = Screen.getPrimary();

        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);

        SceneManager.changeStage(stage);
        SceneManager.mainMenuScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}