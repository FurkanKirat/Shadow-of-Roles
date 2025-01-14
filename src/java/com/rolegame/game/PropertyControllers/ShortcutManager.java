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
                if(sceneType== shortcut.getKey()|| shortcut.getKey()== SceneController.SceneType.Mutual){
                    if (shortcut.getValue().keyCode().equals(keyCode)) {
                        shortcut.getValue().action().run();
                    }
                }
            }
        });
    }

    static {
        mutualShortcuts();
        settingShortcuts();
        changeLangShortCuts();
    }


    private static void mutualShortcuts(){
        ShortcutManager.addShortcut(SceneController.SceneType.Mutual,new ShortcutManager.Shortcut(KeyCode.F11, "Full Screen", () -> {
            SceneController.getStage().setFullScreen(!SceneController.getStage().isFullScreen());

        }));
    }

    private static void settingShortcuts(){{
        ShortcutManager.addShortcut(SceneController.SceneType.Settings,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to main menu",
                SceneController::mainMenuScene));}
    }

    private static void changeLangShortCuts(){
        ShortcutManager.addShortcut(SceneController.SceneType.ChangeLang,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneController::settingsScene));
    }


    public record Shortcut(KeyCode keyCode, String description, Runnable action) {
    }


}
