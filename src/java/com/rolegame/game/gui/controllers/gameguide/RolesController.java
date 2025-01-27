package com.rolegame.game.gui.controllers.gameguide;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RolesController {

    @FXML
    private VBox changingPane;

    @FXML
    private HBox corruptHBox;

    @FXML
    private Label corruptLabel;

    @FXML
    private HBox folkHBox;

    @FXML
    private Label folkLabel;

    @FXML
    private HBox neutralHBox;

    @FXML
    private Label neutralLabel;

    @FXML
    void corruptClicked(MouseEvent event) {
        loadScene("/com/rolegame/game/fxml/gameguide/Corrupt.fxml");
        applyCss(corruptHBox);
    }

    @FXML
    void folkClicked(MouseEvent event) {
        loadScene("/com/rolegame/game/fxml/gameguide/Folk.fxml");
        applyCss(folkHBox);
    }

    @FXML
    void neutralClicked(MouseEvent event) {
        loadScene("/com/rolegame/game/fxml/gameguide/Neutral.fxml");
        applyCss(neutralHBox);
    }

    private void applyCss(Node node){
        folkHBox.getStyleClass().remove("gameguide-choice-selected");

        corruptHBox.getStyleClass().remove("gameguide-choice-selected");

        neutralHBox.getStyleClass().remove("gameguide-choice-selected");

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
