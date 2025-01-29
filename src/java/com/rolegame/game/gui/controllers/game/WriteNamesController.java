package com.rolegame.game.gui.controllers.game;

import com.rolegame.game.gameplay.GameController;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WriteNamesController extends VBox {
    private final ComboBox<Integer> playerCountComboBox;
    private TextField[] textFields;
    private static GameController gameController;
    private final VBox textFieldsBox;

    public WriteNamesController() {
        this.getStylesheets().add("/com/rolegame/game/css/GameStyle.css");
        this.getStyleClass().add("startRoot");
        this.setPrefWidth(1366);
        this.setPrefHeight(768);
        this.setAlignment(Pos.CENTER);

        textFieldsBox = new VBox();
        textFieldsBox.setAlignment(Pos.CENTER);

        playerCountComboBox = new ComboBox<>();
        playerCountComboBox.getItems().addAll(5, 6, 7, 8, 9, 10);
        playerCountComboBox.setValue(5);

        playerCountComboBox.setOnAction(event -> {
            int selectedPlayerCount = playerCountComboBox.getValue();
            updateTextFields(selectedPlayerCount);
        });

        Label comboBoxLabel = new Label(LanguageManager.getText("WriteNames","playerCount"));
        comboBoxLabel.getStyleClass().add("startLabel");

        HBox comboBoxContainer = new HBox(comboBoxLabel, playerCountComboBox);
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setSpacing(10);

        this.getChildren().add(comboBoxContainer);
        this.getChildren().add(textFieldsBox);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(textFieldsBox);
        this.getChildren().add(scrollPane);

        updateTextFields(playerCountComboBox.getValue());
    }

    private void updateTextFields(int playerCount) {
        textFieldsBox.getChildren().clear();
        textFields = new TextField[playerCount];

        for (int i = 0; i < playerCount; i++) {
            TextField textField = new TextField();
            textField.setText(LanguageManager.getText("Menu","player") + " " + (i + 1));
            textField.getStyleClass().add("startTextField");

            Label nameLabel = new Label(LanguageManager.getText("Menu","player") + " " + (i + 1) + ": ");
            nameLabel.getStyleClass().add("startLabel");

            HBox hBox = new HBox(nameLabel, textField);
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);

            textFields[i] = textField;
            textFieldsBox.getChildren().add(hBox);
        }

        Button button = new Button(LanguageManager.getText("Menu","apply"));
        button.getStyleClass().add("startButton");
        button.setOnAction((event) -> {
            gameController = new GameController(textFields);
            GameScreenController.setGameController(gameController);
            SceneManager.switchScene("/com/rolegame/game/fxml/game/GameScreen.fxml", SceneManager.SceneType.GAME, false);


        });
        textFieldsBox.getChildren().add(button);
    }

}
