package com.rolegame.game.GUI;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeLanguageController implements Initializable {

    @FXML
    private Label engLabel;

    @FXML
    private Label medievalLabel;

    @FXML
    private Label turLabel;

    @FXML
    private Label upperTextLabel;

    @FXML
    void backClicked(MouseEvent event) {
        SceneController.switchScene("/com/rolegame/game/fxml/StartGame.fxml");
    }

    @FXML
    void englishChosen(MouseEvent event) {
        LanguageManager.changeLanguage("en");
        changeLabelLang();
    }

    @FXML
    void medievalThemeChosen(MouseEvent event) {

        changeLabelLang();
    }

    @FXML
    void turkishChosen(MouseEvent event) {
        LanguageManager.changeLanguage("tr");
        changeLabelLang();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       changeLabelLang();
    }

    private void changeLabelLang(){
        engLabel.setText(LanguageManager.getText("ChangeLangMenu.eng"));
        turLabel.setText(LanguageManager.getText("ChangeLangMenu.tur"));
        medievalLabel.setText(LanguageManager.getText("ChangeLangMenu.med"));
        upperTextLabel.setText(LanguageManager.getText("ChangeLangMenu.changeLang"));
    }
}
