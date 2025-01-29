package com.rolegame.game.gui.controllers.gameguide;

import com.rolegame.game.managers.LanguageManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameGuideController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox changingPane;

    @FXML
    private HBox gameRulesHBox;

    @FXML
    private Label gameRulesLabel;

    @FXML
    private HBox mainGoalHBox;

    @FXML
    private Label mainGoalLabel;

    @FXML
    private HBox rolesHBox;

    @FXML
    private Label rolesLabel;

    @FXML
    private Label gameGuideLabel;

    @FXML
    void gameRulesClicked(MouseEvent event) {
        loadPane("/com/rolegame/game/fxml/gameguide/GameRules.fxml");
        applyCss(gameRulesHBox);
    }

    @FXML
    void mainGoalClicked(MouseEvent event) {
        loadPane("/com/rolegame/game/fxml/gameguide/MainGoal.fxml");
        applyCss(mainGoalHBox);
    }

    @FXML
    void rolesClicked(MouseEvent event) {
        loadPane("/com/rolegame/game/fxml/gameguide/Roles.fxml");
        applyCss(rolesHBox);
    }

    public void initialize(){
        changingPane.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.5));
        changingPane.prefHeightProperty().bind(borderPane.heightProperty().multiply(0.5));
    }


    private void applyCss(Node node){
        gameRulesHBox.getStyleClass().remove("gameguide-choice-selected");

        rolesHBox.getStyleClass().remove("gameguide-choice-selected");

        mainGoalHBox.getStyleClass().remove("gameguide-choice-selected");

        node.getStyleClass().add("gameguide-choice-selected");

    }

    private void loadPane(String fxmlFileName) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Node node = loader.load();


            changingPane.getChildren().clear();
            changingPane.getChildren().add(node);


            VBox.setVgrow(node, Priority.ALWAYS);
            HBox.setHgrow(node,Priority.NEVER);



        } catch (IOException e) {

            System.out.println("FXML dosyası yüklenemedi: " + fxmlFileName);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changingPane.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.5));
        changingPane.prefHeightProperty().bind(borderPane.heightProperty().multiply(0.5));

        gameGuideLabel.setText(LanguageManager.getText("GameGuide","GameGuideLabel"));
        gameRulesLabel.setText(LanguageManager.getText("GameGuide","RulesLabel"));
        rolesLabel.setText(LanguageManager.getText("GameGuide","RolesLabel"));
        mainGoalLabel.setText(LanguageManager.getText("GameGuide","MainGoalLabel"));
    }
}
