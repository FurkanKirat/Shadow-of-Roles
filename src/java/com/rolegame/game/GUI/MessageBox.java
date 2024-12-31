package com.rolegame.game.GUI;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;


public class MessageBox extends HBox {
    MessageBox(Message message){
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        String time = message.isDay() ? LanguageManager.getText("Menu.day") : LanguageManager.getText("Menu.night");
        time += " " + message.getDayCount()+ ": ";
        textArea.setText(time+ message.getMessage());
        this.getChildren().add(textArea);

    }
}
