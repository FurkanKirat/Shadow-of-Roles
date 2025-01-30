package com.rolegame.game.gui.controllers.menu;

import com.rolegame.game.managers.LanguageManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditsController implements Initializable {
    @FXML
    private Text creditsText;

    @FXML
    private BorderPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.setCache(true);
        root.setCacheShape(true);
        root.setCacheHint(CacheHint.SPEED);

        creditsText.setText(LanguageManager.getText("Credits", "creditText"));

        animateCreditsText();

    }


    private void animateCreditsText() {

        TranslateTransition translate = new TranslateTransition(Duration.seconds(5), creditsText);
        translate.setFromY(Region.USE_COMPUTED_SIZE);
        translate.setToY(-creditsText.getLayoutBounds().getHeight());
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setAutoReverse(true);

        translate.play();
    }
}
