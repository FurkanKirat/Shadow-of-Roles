package com.rolegame.game.managers;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;

import java.util.*;

public class ShortcutManager {
    private static final Map<Set<SceneManager.SceneType>,Shortcut> shortcuts = new HashMap<>();

    public static void addShortcut(Set<SceneManager.SceneType> sceneTypes, Shortcut shortcut) {
        shortcuts.put(sceneTypes, shortcut);
    }

    public static void attachToScene(Scene scene, SceneManager.SceneType sceneType) {

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            for (Map.Entry<Set<SceneManager.SceneType>,Shortcut> shortcuts : shortcuts.entrySet()) {
                Set<SceneManager.SceneType> sceneTypes = shortcuts.getKey();
                if(sceneTypes.contains(sceneType)||sceneTypes.contains(SceneManager.SceneType.MUTUAL)){
                    if (shortcuts.getValue().keyCode().equals(keyCode)) {
                        shortcuts.getValue().action().run();
                    }
                }
            }
        });
    }

    static {
        mutualShortcuts();
        goToMainMenu();
        goToSettings();
        goToMainMenuWithAlert();
    }

    private static void goToMainMenu(){
        Set<SceneManager.SceneType> sceneTypes = Set.of(
                SceneManager.SceneType.SETTINGS, SceneManager.SceneType.WRITE_NAMES, SceneManager.SceneType.CREDITS,
                SceneManager.SceneType.ACHIEVEMENTS, SceneManager.SceneType.GAME_GUIDE
        );

        ShortcutManager.addShortcut(sceneTypes,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to main menu",
                SceneManager::mainMenuScene));
    }
    private static void mutualShortcuts(){
        Set<SceneManager.SceneType> sceneTypes = Set.of(SceneManager.SceneType.MUTUAL);

        ShortcutManager.addShortcut(sceneTypes,new ShortcutManager.Shortcut(KeyCode.F11, "Full Screen", () -> {
            SceneManager.getStage().setFullScreen(!SceneManager.getStage().isFullScreen());

        }));
    }

    private static void goToSettings(){
        Set<SceneManager.SceneType> sceneTypes = new HashSet<>();
        sceneTypes.add(SceneManager.SceneType.CHANGE_LANG);
        ShortcutManager.addShortcut(sceneTypes,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneManager::settingsScene));
    }

    private static void goToMainMenuWithAlert() {
        Set<SceneManager.SceneType> sceneTypes = Set.of(SceneManager.SceneType.GAME);

        ShortcutManager.addShortcut(sceneTypes, new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to main menu", () -> {
            Alert alert = SceneManager.createAlert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to go back to the main menu?",
                    "Main Menu",
                    "If you click OK, you will be redirected to the main menu.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                SceneManager.mainMenuScene();
            }
        }));
    }


    public record Shortcut(KeyCode keyCode, String description, Runnable action) {
    }


}
