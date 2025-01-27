package com.rolegame.game.gui.controllers.gameguide;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GameGuideController {

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
    void gameRulesClicked(MouseEvent event) {
        loadScene("/com/rolegame/game/fxml/gameguide/GameRules.fxml");
        applyCss(gameRulesHBox);
    }

    @FXML
    void mainGoalClicked(MouseEvent event) {
        loadScene("/com/rolegame/game/fxml/gameguide/MainGoal.fxml");
        applyCss(mainGoalHBox);
    }

    @FXML
    void rolesClicked(MouseEvent event) {
        loadScene("/com/rolegame/game/fxml/gameguide/Roles.fxml");
        applyCss(rolesHBox);
    }


    private void applyCss(Node node){
        gameRulesHBox.getStyleClass().remove("gameguide-choice-selected");

        rolesHBox.getStyleClass().remove("gameguide-choice-selected");

        mainGoalHBox.getStyleClass().remove("gameguide-choice-selected");

        node.getStyleClass().add("gameguide-choice-selected");

    }

    private void loadScene(String fxmlFileName) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Node node = loader.load();


            changingPane.getChildren().clear();
            changingPane.getChildren().add(node);


            VBox.setVgrow(node, Priority.ALWAYS);

        } catch (IOException e) {

            System.out.println("FXML dosyası yüklenemedi: " + fxmlFileName);
        }
    }

}
