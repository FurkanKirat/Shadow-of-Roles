package com.rolegame.game.gui.controllers.game;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;

import com.rolegame.game.services.GameService;
import com.rolegame.game.services.PlayerNamesService;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PlayerNamesController extends VBox {
    private final ComboBox<Integer> playerCountComboBox;
    private TextField[] textFields;
    private CheckBox[] checkBoxes;
    private final VBox textFieldsBox;
    private final PlayerNamesService writeNamesService;

    public PlayerNamesController() {
        this.writeNamesService = new PlayerNamesService();
        this.getStylesheets().add("/com/rolegame/game/css/GameStyle.css");
        this.setPrefWidth(1366);
        this.setPrefHeight(768);
        this.setAlignment(Pos.CENTER);

        textFieldsBox = new VBox();
        textFieldsBox.setAlignment(Pos.CENTER);
        textFieldsBox.getStyleClass().add("backgroundtransparant");
        textFieldsBox.setMaxWidth(500);

        playerCountComboBox = new ComboBox<>();
        playerCountComboBox.getItems().addAll(5, 6, 7, 8, 9, 10);
        playerCountComboBox.setValue(5);

        playerCountComboBox.setOnAction(event -> {
            int selectedPlayerCount = playerCountComboBox.getValue();
            updateTextFields(selectedPlayerCount);
            initializeBackgroundImage(selectedPlayerCount);
        });

        Label comboBoxLabel = new Label(LanguageManager.getText("WriteNames","playerCount"));
        comboBoxLabel.getStyleClass().add("startLabel");

        HBox comboBoxContainer = new HBox(comboBoxLabel, playerCountComboBox);
        comboBoxContainer.getStyleClass().add("backgroundtransparant");
        comboBoxContainer.setAlignment(Pos.CENTER);
        comboBoxContainer.setSpacing(10);
        comboBoxContainer.setMaxWidth(500);

        this.initializeBackgroundImage(playerCountComboBox.getValue());
        this.getChildren().add(comboBoxContainer);
        this.getChildren().add(textFieldsBox);
        this.getStyleClass().add("backgroundimage");
        updateTextFields(playerCountComboBox.getValue());
    }

    private void updateTextFields(int playerCount) {
        textFieldsBox.getChildren().clear();

        textFields = new TextField[playerCount];
        checkBoxes = new CheckBox[playerCount];
        for (int i = 0; i < playerCount; i++) {
            TextField textField = new TextField();
            textField.setText(LanguageManager.getText("Menu", "player") + " " + (i + 1));
            textField.getStyleClass().add("startTextField");
            textField.setPrefWidth(200);
            textField.setMinWidth(150);
            textField.setMaxWidth(250);
            //textField.getStyleClass().add("gameStartLabel");

            Label nameLabel = new Label(LanguageManager.getText("Menu", "player") + " " + (i + 1) + ": ");
            nameLabel.getStyleClass().add("startLabel");
            nameLabel.setMinWidth(100);
            nameLabel.setAlignment(Pos.CENTER_RIGHT);

            Label AILabel = new Label(LanguageManager.getText("WriteNames","isAI"));
            AILabel.getStyleClass().add("startLabel");
            AILabel.setMinWidth(100);
            AILabel.setAlignment(Pos.CENTER_RIGHT);
            CheckBox isAICheckBox = new CheckBox();

            HBox hBox = new HBox(nameLabel, textField, AILabel, isAICheckBox);
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
            hBox.setPrefWidth(textFieldsBox.getWidth());
            hBox.setMaxWidth(Double.MAX_VALUE);
            hBox.getStyleClass().add("startBox");

            textFields[i] = textField;
            checkBoxes[i] = isAICheckBox;
            textFieldsBox.getChildren().add(hBox);
        }

        textFieldsBox.setSpacing(5);
        textFieldsBox.setAlignment(Pos.CENTER);

        Button button = new Button(LanguageManager.getText("Menu","apply"));
        button.getStyleClass().add("startButton");
        button.setOnAction((_) -> handleApplyButton(playerCount));

        textFieldsBox.getChildren().add(button);
    }

    private void handleApplyButton(int playerCount) {
        ArrayList<NameAndIsAI> information = new ArrayList<>(playerCount);

        boolean allPlayersAI = true;
        for(int i=0;i<playerCount;i++){
            information.add(new NameAndIsAI(textFields[i].getText(),checkBoxes[i].isSelected()));

            if(!allPlayersAI || !checkBoxes[i].isSelected()){
                allPlayersAI = false;
            }
        }

        if(allPlayersAI){
            showAlert("AI alert","All Players Cannot be AI");
            return;
        }

        try {
            GameService gameService = writeNamesService.createGameService(information, playerCount);
            GameScreenController.setGameService(gameService);
            SceneManager.switchScene("/com/rolegame/game/fxml/game/GameScreen.fxml", SceneManager.SceneType.GAME, false);
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void initializeBackgroundImage(int playerCount){
        this.styleProperty().set("-fx-background-image:url(/com/rolegame/game/images/gamestart/"+playerCount+".jpg);");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public record NameAndIsAI(String name, boolean isAI) {
    }
}
