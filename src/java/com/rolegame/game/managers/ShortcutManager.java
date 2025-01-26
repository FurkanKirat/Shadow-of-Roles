package com.rolegame.game.managers;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class ShortcutManager {
    private static final Map<SceneManager.SceneType,Shortcut> shortcuts = new HashMap<>();

    public static void addShortcut(SceneManager.SceneType sceneType, Shortcut shortcut ) {
        shortcuts.put(sceneType, shortcut);
    }

    public static void attachToScene(Scene scene, SceneManager.SceneType sceneType) {

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            for (Map.Entry<SceneManager.SceneType,Shortcut> shortcut : shortcuts.entrySet()) {
                if(sceneType == shortcut.getKey() || shortcut.getKey() == SceneManager.SceneType.MUTUAL){
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
        ShortcutManager.addShortcut(SceneManager.SceneType.MUTUAL,new ShortcutManager.Shortcut(KeyCode.F11, "Full Screen", () -> {
            SceneManager.getStage().setFullScreen(!SceneManager.getStage().isFullScreen());

        }));
    }

    private static void settingsShortcuts(){{
        ShortcutManager.addShortcut(SceneManager.SceneType.SETTINGS,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to main menu",
                SceneManager::mainMenuScene));}
    }

    private static void changeLangShortcuts(){
        ShortcutManager.addShortcut(SceneManager.SceneType.CHANGE_LANG,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneManager::settingsScene));
    }

    private static void writeNamesShortcuts(){
        ShortcutManager.addShortcut(SceneManager.SceneType.WRITE_NAMES,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneManager::mainMenuScene));
    }

    private static void creditsShortcuts(){
        ShortcutManager.addShortcut(SceneManager.SceneType.CREDITS,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneManager::mainMenuScene));
    }

    private static void achievementsShortcuts(){
        ShortcutManager.addShortcut(SceneManager.SceneType.ACHIEVEMENTS,new ShortcutManager.Shortcut(KeyCode.ESCAPE, "Go back to settings menu",
                SceneManager::mainMenuScene));
    }


    public record Shortcut(KeyCode keyCode, String description, Runnable action) {
    }


}
