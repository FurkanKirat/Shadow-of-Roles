package com.rolegame.game.GUI;

import com.rolegame.game.GameManagement.GameController;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WriteNamesController extends VBox {
    private TextField[] textFields;
    private static GameController gameController;
    private final VBox textFieldsBox;

    private TextField playerCountTextField;
    int playerCount;
    public WriteNamesController(){
        this.getStylesheets().add("/com/rolegame/game/css/day.css");
        this.getStyleClass().add("startRoot");
        this.setPrefWidth(1366);
        this.setPrefHeight(768);
        textFieldsBox = new VBox();
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

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(LanguageManager.getText("WriteNames.notNumberAlert"));
            alert.showAndWait();
            return;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(LanguageManager.getText("WriteNames.exception"));
            alert.showAndWait();
            return;
        }
        //

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
            SceneController.switchScene("/com/rolegame/game/fxml/Day.fxml");


        });
        textFieldsBox.getChildren().add(button);
    }

    private void initialize(){
        playerCountTextField = new TextField();
        playerCountTextField.setPromptText(LanguageManager.getText("WriteNames.playerCount"));
        Button numberButton = new Button(LanguageManager.getText("Menu.apply"));
        numberButton.setOnAction((_) ->{
            playerClicked();
        });
        this.getChildren().add(playerCountTextField);
        this.getChildren().add(numberButton);
        this.getChildren().add(textFieldsBox);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(textFieldsBox);
        this.getChildren().add(scrollPane);

    }
    private void changeVBox(){
        textFieldsBox.getChildren().clear();
    }
    public static GameController getGameController() {
        return gameController;
    }
}
