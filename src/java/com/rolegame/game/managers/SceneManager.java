package com.rolegame.game.managers;

import com.rolegame.game.gui.components.LoadingScreen;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class SceneManager {
    private static final int imageCount = 6;
    private static int currentImage;
    private static Stage stage;


    static{
        currentImage = new Random().nextInt(0,imageCount);

    }

    private static void setStyleImage(Parent root, int imageNum) {

        String style = "-fx-background-image: url(/com/rolegame/game/images/backgrounds/background"+imageNum+".jpg); " +
                "-fx-background-size: cover;";

        root.styleProperty().set(style);
    }

    public static void changeStage(Stage newStage){
        stage = newStage;
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }

    public static void switchScene(String fxmlPath, SceneType sceneType, boolean randomImage) {

        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            changeScene(root, sceneType, randomImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void switchScene(Parent root, SceneType sceneType, boolean randomImage){
        changeScene(root, sceneType, randomImage);
    }

    private static void changeScene(Parent root, SceneType sceneType, boolean randomImage) {
        if (randomImage) {
            setStyleImage(root, currentImage);
            currentImage = (currentImage + 1) % imageCount;
        }

        Scene newScene = new Scene(root);

        if (stage.getScene() == null) {
            stage.setScene(newScene);
            return;
        }

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();
        boolean isFullScreen = stage.isFullScreen();

        LoadingScreen loadingScreen = new LoadingScreen();
        Scene loadingScene = new Scene(loadingScreen, stage.getWidth(), stage.getHeight());

        if (isFullScreen) {
            stage.setFullScreen(true);
        }
        stage.setScene(loadingScene);
        int duration = 500; //ms
        PauseTransition pause = new PauseTransition(Duration.millis(duration));
        pause.setOnFinished(_ -> {

            stage.setScene(newScene);

            if (isFullScreen) {
                stage.setFullScreen(true);
            } else {
                stage.setWidth(currentWidth);
                stage.setHeight(currentHeight);
            }

            ShortcutManager.attachToScene(newScene, sceneType);
        });
        loadingScreen.animateProgressBar(duration);
        pause.play();
    }


    public static void settingsScene(){
        switchScene("/com/rolegame/game/fxml/menu/Settings.fxml", SceneType.SETTINGS,true);
    }

    public static void changeLangScene(){
        switchScene("/com/rolegame/game/fxml/menu/ChangeLanguage.fxml", SceneType.CHANGE_LANG,true);
    }

    public static void mainMenuScene(){
        switchScene("/com/rolegame/game/fxml/menu/MainMenu.fxml", SceneType.MAIN_MENU,true);
    }

    public static void onClose(){
        Alert alert = createAlert(Alert.AlertType.CONFIRMATION,"Are You sure to exit?","Exiting","Exit");

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

    public static Alert createAlert(Alert.AlertType alertType, String contentText, String title, String headerText){
        Alert alert = new Alert(alertType);
        alert.setContentText(contentText);
        alert.setTitle(title);
        alert.setHeaderText(headerText);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(SceneManager.class.getResource("/com/rolegame/game/css/Alert.css")).toExternalForm());
        Stage alertStage = (Stage) dialogPane.getScene().getWindow();
        alertStage.setAlwaysOnTop(true);
        alertStage.getIcons().add(new Image("/com/rolegame/game/images/icon.jpg"));


        return alert;
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
        SIMPLE_PERSON_ALERT,
        GAME_GUIDE,
    }
}
