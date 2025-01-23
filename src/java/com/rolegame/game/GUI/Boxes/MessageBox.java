package com.rolegame.game.GUI.Boxes;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MessageBox extends Label {
    public MessageBox(Message message, ListView<MessageBox> listView){

        String time = message.isDay() ? LanguageManager.getText("Menu.day") : LanguageManager.getText("Menu.night");
        time += " " + message.dayCount()+ ": ";
        this.setText(time + message.message());
        this.getStyleClass().add("messageBox");
        if (message.isPublic()) {
            this.getStyleClass().add("public");
        } else {
            this.getStyleClass().add("private");
        }
        this.prefWidthProperty().bind(listView.widthProperty());
        this.setWrapText(true);


    }
}
