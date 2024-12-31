package com.rolegame.game.PropertyControllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SceneController {

    private static Stage stage;

    public static void changeStage(Stage newStage){
        stage = newStage;
    }

    public static void switchScene(String fxmlPath) {

        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource(fxmlPath));
            Parent root = loader.load();
            changeScene(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void switchScene(Parent root){
        changeScene(root);
    }

    private static void changeScene(Parent root){
        Scene newScene = new Scene(root);
        if(stage.getScene()==null){
            stage.setScene(newScene);
        }

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), stage.getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();

            stage.setScene(newScene);
            stage.setWidth(currentWidth);
            stage.setHeight(currentHeight);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), newScene.getRoot());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        });

        fadeOut.play();
    }
}
