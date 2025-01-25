package com.rolegame.game.gamemanagement;

import com.rolegame.game.GUI.Controllers.GameScreenController;
import com.rolegame.game.models.Player;

import java.util.LinkedList;

public record Message(int dayCount, boolean isDay, String message, Player receiver, boolean isPublic) {
    private static final LinkedList<Message> messages = new LinkedList<>();

    @Override
    public String toString() {
        return "Message{" +
                "dayCount=" + dayCount +
                ", isDay=" + isDay +
                ", message='" + message + '\'' +
                '}';
    }

    public static void resetMessages() {
        messages.clear();
    }


    public static void sendMessage(String message, Player receiver, boolean isPublic, boolean isDay) {
        messages.add(new Message(GameScreenController.getGameController().getDayCount(),
                isDay, message, receiver, isPublic));
    }

    public static LinkedList<Message> getMessages() {
        return messages;
    }

}
