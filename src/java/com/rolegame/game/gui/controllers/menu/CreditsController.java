package com.rolegame.game.gui.controllers.menu;

import com.rolegame.game.managers.LanguageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditsController implements Initializable {
    @FXML
    private Text creditsText;

    @FXML
    private BorderPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creditsText.setText(LanguageManager.getText("Credits", "creditText"));

        root.setOnMouseClicked(this::handleMouseClicked);

        root.setFocusTraversable(true);
    }

    private void handleMouseClicked(MouseEvent event) {
        double clickedX = event.getSceneX()-root.getWidth()/2;
        double clickedY = event.getSceneY()-root.getHeight()/2;

        creditsText.setTranslateX(clickedX);
        creditsText.setTranslateY(clickedY);
    }
}
