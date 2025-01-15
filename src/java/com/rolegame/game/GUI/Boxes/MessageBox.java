package com.rolegame.game.GUI.Boxes;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;


public class MessageBox extends TextArea {
    public MessageBox(Message message){
        this.setEditable(false);
        String time = message.isDay() ? LanguageManager.getText("Menu.day") : LanguageManager.getText("Menu.night");
        time += " " + message.getDayCount()+ ": ";
        this.setText(time + message.getMessage());
        this.maxHeight(100);
        this.maxWidth(300);
        this.getStyleClass().add("gameTextArea");

    }
}
