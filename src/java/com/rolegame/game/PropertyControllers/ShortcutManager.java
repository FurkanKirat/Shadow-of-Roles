package com.rolegame.game.PropertyControllers;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class ShortcutManager {
    private static final Map<SceneController.SceneType,Shortcut> shortcuts = new HashMap<>();

    public static void addShortcut(SceneController.SceneType sceneType, Shortcut shortcut ) {
        shortcuts.put(sceneType, shortcut);
    }

    public static void attachToScene(Scene scene, SceneController.SceneType sceneType) {

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            for (Map.Entry<SceneController.SceneType,Shortcut> shortcut : shortcuts.entrySet()) {
                if(sceneType == shortcut.getKey() || shortcut.getKey() == SceneController.SceneType.MUTUAL){
                    if (shortcut.getValue().keyCode().equals(keyCode)) {
                        shortcut.getValue().action().run();
                    }
                }
            }
        });
    }

    static {
        mutualShortcuts();
        settingsShortcuts();
        changeLangShortcuts();
        writeNamesShortcuts();
        creditsShortcuts();
        achievementsShortcuts();
    }


    private static void mutualShortcuts(){
        ShortcutManager.addShortcut(SceneController.SceneType.MUTUAL,new ShortcutManager.Shortcut(KeyCode.F11, "Full Screen", () -> {
            SceneController.getStage().setFullScreen(!SceneController.getStage().isFullScreen());

        }));
    }

    private static void settingsShortcuts(){{
        ShortcutManager.addShortcut(SceneController.SceneType.SETTINGS,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to main menu",
                SceneController::mainMenuScene));}
    }

    private static void changeLangShortcuts(){
        ShortcutManager.addShortcut(SceneController.SceneType.CHANGE_LANG,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneController::settingsScene));
    }

    private static void writeNamesShortcuts(){
        ShortcutManager.addShortcut(SceneController.SceneType.WRITE_NAMES,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneController::mainMenuScene));
    }

    private static void creditsShortcuts(){
        ShortcutManager.addShortcut(SceneController.SceneType.CREDITS,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneController::mainMenuScene));
    }

    private static void achievementsShortcuts(){
        ShortcutManager.addShortcut(SceneController.SceneType.ACHIEVEMENTS,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneController::mainMenuScene));
    }


    public record Shortcut(KeyCode keyCode, String description, Runnable action) {
    }


}
