package com.rolegame.game.PropertyControllers;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {

    private static Locale locale = new Locale("en");

    private static ResourceBundle rolesBundle = ResourceBundle.getBundle("com.rolegame.game.i18n.roles",locale);

    public static void changeLanguage(String languageCode){
        locale = new Locale(languageCode);
        rolesBundle = ResourceBundle.getBundle("com.rolegame.game.i18n.roles",locale);
    }
    public static String getText(String key) {
        return rolesBundle.getString(key);
    }
}
