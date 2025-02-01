package com.rolegame.game.gui.components.boxes;

import com.rolegame.game.models.Message;
import com.rolegame.game.managers.LanguageManager;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MessageBox extends Label {
    public MessageBox(Message message, ListView<MessageBox> listView){

        String time = message.isDay() ? LanguageManager.getText("Menu","day") : LanguageManager.getText("Menu","night");
        time += " " + message.dayCount()+ ": ";
        this.setText(time + message.message());
        this.getStyleClass().add("messageBox");
        if (message.isPublic()) {
            this.getStyleClass().add("public");
        } else {
            this.getStyleClass().add("private");
        }
        this.prefWidthProperty().bind(listView.widthProperty());
        this.maxWidthProperty().bind(listView.widthProperty());
        this.minWidthProperty().bind(listView.widthProperty());
        this.setWrapText(true);
<<<<<<< Updated upstream
        
=======
        this.setMaxWidth(Double.MAX_VALUE );

>>>>>>> Stashed changes
    }
}
