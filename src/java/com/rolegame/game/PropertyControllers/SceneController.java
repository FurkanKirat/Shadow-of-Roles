package com.rolegame.game.PropertyControllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class SceneController {

    private static Stage stage;

    public static void changeStage(Stage newStage){
        stage = newStage;
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }

    public static void switchScene(String fxmlPath, SceneType sceneType) {

        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource(fxmlPath));
            Parent root = loader.load();
            changeScene(root, sceneType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void switchScene(Parent root, SceneType sceneType){
        changeScene(root, sceneType);
    }

    private static void changeScene(Parent root, SceneType sceneType){
        Scene newScene = new Scene(root);
        if(stage.getScene()==null){
            stage.setScene(newScene);
        }

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), stage.getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(_ -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            boolean isFullScreen = stage.isFullScreen();

            stage.setScene(newScene);

            if(isFullScreen){
                stage.setFullScreen(true);
            }
            else{
                stage.setWidth(currentWidth);
                stage.setHeight(currentHeight);

            }


            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), newScene.getRoot());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        });

        fadeOut.play();
        ShortcutManager.attachToScene(newScene, sceneType);
    }

    public static void settingsScene(){
        switchScene("/com/rolegame/game/fxml/Settings.fxml", SceneType.SETTINGS);
    }

    public static void changeLangScene(){
        switchScene("/com/rolegame/game/fxml/ChangeLanguage.fxml", SceneType.CHANGE_LANG);
    }

    public static void mainMenuScene(){
        switchScene("/com/rolegame/game/fxml/MainMenu.fxml", SceneType.MAIN_MENU);
    }

    public static void onClose(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are You sure to exit?");
        alert.setTitle("Exiting");
        alert.setHeaderText("Exit");
        alert.showAndWait().ifPresent(response -> {
            if(ButtonType.OK == response){
                System.exit(0);
            }
        });
    }

    public static void fullScreen(){
        stage.setFullScreen(!stage.isFullScreen());
    }

    public static Stage getStage() {
        return stage;
    }

    public enum SceneType{
        MUTUAL,
        MAIN_MENU,
        SETTINGS,
        CHANGE_LANG,
        GAME,
        END_GAME,
        WRITE_NAMES,
        CREDITS,
        ACHIEVEMENTS,
        SIMPLE_PERSON_ALERT
    }
}
