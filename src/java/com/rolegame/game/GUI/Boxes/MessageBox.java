package com.rolegame.game.GUI.Boxes;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MessageBox extends Label {
    public MessageBox(Message message, ListView<MessageBox> listView){

        String time = message.isDay() ? LanguageManager.getText("Menu.day") : LanguageManager.getText("Menu.night");
        time += " " + message.getDayCount()+ ": ";
        this.setText(time + message.getMessage());
        this.getStyleClass().add("gameTextArea");
        this.prefWidthProperty().bind(listView.widthProperty());
        this.setWrapText(true);

    }
}
