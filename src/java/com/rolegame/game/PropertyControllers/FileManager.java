package com.rolegame.game.PropertyControllers;

import java.io.IOException;
import java.nio.file.*;

public class FileManager {

    public static Path getUserDataDirectory() {
        String userHome = System.getProperty("user.home");
        Path appDataDir = Paths.get(userHome, "Shadow of Roles", "data");
        if (!Files.exists(appDataDir)) {
            try {
                Files.createDirectories(appDataDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return appDataDir;
    }
}
