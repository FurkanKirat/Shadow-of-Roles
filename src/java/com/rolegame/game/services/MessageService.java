package com.rolegame.game.services;

import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.models.Message;
import com.rolegame.game.models.Player;

import java.util.LinkedList;

public class MessageService {
    private static final LinkedList<Message> messages = new LinkedList<>();


    public static void resetMessages() {
        messages.clear();
    }


    public static void sendMessage(String message, Player receiver, boolean isPublic, boolean isDay) {
        messages.add(new Message(GameScreenController.getGameService().getDayCount(),
                isDay, message, receiver, isPublic));
    }


    public static LinkedList<Message> getMessages() {
        return messages;
    }

}
