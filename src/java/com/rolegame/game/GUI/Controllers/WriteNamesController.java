package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.GameManagement.GameController;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WriteNamesController extends VBox {
    private ComboBox<Integer> playerCountComboBox;
    private TextField[] textFields;
    private static GameController gameController;
    private final VBox textFieldsBox;

    public WriteNamesController() {
        this.getStylesheets().add("/com/rolegame/game/css/day.css");
        this.getStyleClass().add("startRoot");
        this.setPrefWidth(1366);
        this.setPrefHeight(768);
        this.setAlignment(Pos.CENTER);

        textFieldsBox = new VBox();
<<<<<<< Updated upstream
        initialize();
    }

    private void playerClicked(){
        if(playerCountTextField==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(LanguageManager.getText("WriteNames.blankAlert"));
            alert.showAndWait();
            return;
        }

        try{
            playerCount = Integer.parseInt(playerCountTextField.getText());
            textFieldsBox.getChildren().clear();

            if(playerCount>10){
                showAlert(LanguageManager.getText("WriteNames.playerCountExceeded"));
                return;
            }
            else if(playerCount<5){
                showAlert(LanguageManager.getText("WriteNames.playerCountInsufficient"));
                return;
            }

        } catch (NumberFormatException e) {
            showAlert(LanguageManager.getText("WriteNames.notNumberAlert"));
            return;
        } catch (Exception e) {
            showAlert(LanguageManager.getText("WriteNames.exception"));
            return;
        }

        textFields = new TextField[playerCount];
        for(int i=0;i<playerCount;i++){
            textFields[i] = new TextField();
            textFields[i].setText(LanguageManager.getText("Menu.player") +" " + (i+1));
            textFields[i].getStyleClass().add("startTextField");
            HBox hBox = new HBox();
            Label nameLabel = new Label(LanguageManager.getText("Menu.player") +" " + (i+1)+": ");
            nameLabel.getStyleClass().add("startLabel");
            hBox.getChildren().add(nameLabel);
            hBox.getChildren().add(textFields[i]);
            hBox.setAlignment(Pos.CENTER);
            textFieldsBox.getChildren().add(hBox);
        }
        Button button = new Button(LanguageManager.getText("Menu.apply"));
        button.getStyleClass().add("startButton");
        button.setOnAction((event)->{
            gameController = new GameController();
            gameController.initializePlayers(textFields);
            SceneController.switchScene("/com/rolegame/game/fxml/GameScreen.fxml", SceneController.SceneType.GAME);
=======
        textFieldsBox.setAlignment(Pos.CENTER);
>>>>>>> Stashed changes

        // ComboBox oluştur ve değerleri ekle
        playerCountComboBox = new ComboBox<>();
        playerCountComboBox.getItems().addAll(5, 6, 7, 8, 9, 10); // Geçerli değerler
        playerCountComboBox.setValue(5); // Varsayılan değer

        // ComboBox'a olay dinleyicisi ekle
        playerCountComboBox.setOnAction(event -> {
            int selectedPlayerCount = playerCountComboBox.getValue();
            updateTextFields(selectedPlayerCount);
        });

        Label comboBoxLabel = new Label(LanguageManager.getText("WriteNames.playerCount"));
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

        // Varsayılan olarak TextFields'ı güncelle
        updateTextFields(playerCountComboBox.getValue());
    }

    private void updateTextFields(int playerCount) {
        textFieldsBox.getChildren().clear(); // Eski textField'leri temizle
        textFields = new TextField[playerCount];

        for (int i = 0; i < playerCount; i++) {
            TextField textField = new TextField();
            textField.setText(LanguageManager.getText("Menu.player") + " " + (i + 1)); // Varsayılan isimler ver
            textField.getStyleClass().add("startTextField");

            Label nameLabel = new Label(LanguageManager.getText("Menu.player") + " " + (i + 1) + ": ");
            nameLabel.getStyleClass().add("startLabel");

            HBox hBox = new HBox(nameLabel, textField);
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);

            textFields[i] = textField;
            textFieldsBox.getChildren().add(hBox);
        }

        Button button = new Button(LanguageManager.getText("Menu.apply"));
        button.getStyleClass().add("startButton");
        button.setOnAction((event) -> {
            gameController = new GameController();
            gameController.initializePlayers(textFields);
            SceneController.switchScene("/com/rolegame/game/fxml/GameScreen.fxml", SceneController.SceneType.GAME);
        });
        textFieldsBox.getChildren().add(button);
    }

    public static GameController getGameController() {
        return gameController;
    }
}
