package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLanguageController implements Initializable {

    @FXML
    private Label engLabel;

    @FXML
    private Label turLabel;

    @FXML
    private Label upperTextLabel;

    @FXML
    private Label backLabel;

    @FXML
    private ComboBox<String> themeComboBox;

    @FXML
    void backClicked(MouseEvent event) {
        SceneController.mainMenuScene();
    }


    @FXML
    void englishChosen(MouseEvent event) {
        LanguageManager.changeLanguage("en_us");
        changeLabelLang();
    }

    @FXML
    void turkishChosen(MouseEvent event) {
        LanguageManager.changeLanguage("tr_tr");
        changeLabelLang();
    }

    @FXML
    void themeChanged(ActionEvent event) {
        String theme;
        if(themeComboBox.getValue().equalsIgnoreCase("Medieval")){
            theme = "medieval";
        }
        else{
            theme = "normal";
        }
        LanguageManager.changeTheme(theme);
    }

    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       changeLabelLang();
       themeComboBox.getItems().addAll("Medieval","Normal");
       themeComboBox.setValue(capitalizeFirstLetter(LanguageManager.currentTheme));
    }

    private void changeLabelLang(){
        engLabel.setText(LanguageManager.getText("ChangeLangMenu.eng"));
        turLabel.setText(LanguageManager.getText("ChangeLangMenu.tur"));
        upperTextLabel.setText(LanguageManager.getText("ChangeLangMenu.changeLang"));

    }
}
