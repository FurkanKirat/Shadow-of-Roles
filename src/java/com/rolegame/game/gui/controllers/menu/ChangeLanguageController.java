package com.rolegame.game.gui.controllers.menu;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeLanguageController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private Label engLabel;

    @FXML
    private Button changeThemeButton;

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
        SceneManager.mainMenuScene();
    }


    @FXML
    void englishChosen(MouseEvent event) {
        LanguageManager.changeLanguage("en_us", false);
        changeLangTexts();
    }

    @FXML
    void turkishChosen(MouseEvent event) {
        LanguageManager.changeLanguage("tr_tr", false);
        changeLangTexts();
    }

    @FXML
    void themeChanged(ActionEvent event) {
        String theme;
        if(themeComboBox.getValue().equalsIgnoreCase(LanguageManager.getText("ChangeLangMenu","medieval"))){
            theme = "medieval";
        }
        else{
            theme = "normal";
        }
        LanguageManager.changeTheme(theme);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       changeLangTexts();
    }

    private void changeLangTexts(){
        engLabel.setText(LanguageManager.getText("ChangeLangMenu","eng"));
        turLabel.setText(LanguageManager.getText("ChangeLangMenu","tur"));
        upperTextLabel.setText(LanguageManager.getText("ChangeLangMenu","changeLang"));
        backLabel.setText(LanguageManager.getText("GeneralMenu","back"));
        changeThemeButton.setText(LanguageManager.getText("ChangeLangMenu","changeTheme"));
        themeComboBox.getItems().clear();
        themeComboBox.getItems().addAll(LanguageManager.getText("ChangeLangMenu","medieval")
                ,LanguageManager.getText("ChangeLangMenu","normal"));
        themeComboBox.setValue(LanguageManager.getText("ChangeLangMenu",LanguageManager.currentTheme));

    }
}
