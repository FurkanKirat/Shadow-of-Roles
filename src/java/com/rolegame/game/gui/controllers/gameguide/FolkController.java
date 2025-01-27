package com.rolegame.game.gui.controllers.gameguide;

import com.rolegame.game.managers.LanguageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class FolkController {

    @FXML
    private Text detectiveAbility;

    @FXML
    private Label detectiveLabel;

    @FXML
    private Text folkHeroAbility;

    @FXML
    private Text folkHeroAttributes;

    @FXML
    private Label folkHeroLabel;

    @FXML
    private Text observerAbility;

    @FXML
    private Label observerLabel;

    @FXML
    private Text sealMasterAbility;

    @FXML
    private Label sealMasterLabel;

    @FXML
    private Text soulbinderAbility;

    @FXML
    private Label soulbinderLabel;

    @FXML
    private Text stalkerAbility;

    @FXML
    private Label stalkerLabel;

    public void initialize(){
        detectiveLabel.setText(LanguageManager.getText("Menu","Detective"));
        detectiveAbility.setText(LanguageManager.getText("Detective","abilities"));
    }


}
