package com.rolegame.game.GameManagement;

import com.rolegame.game.GUI.Controllers.GameScreenController;

import java.util.LinkedList;

public class Message {
    private static LinkedList<Message> messages = new LinkedList<>();

    private int dayCount;
    private boolean isDay;
    private String message;
    private Player receiver;
    private boolean isPublic;

    public Message(int dayCount, boolean isDay, String message, Player receiver, boolean isPublic) {
        this.dayCount = dayCount;
        this.isDay = isDay;
        this.message = message;
        this.receiver = receiver;
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return "Message{" +
                "dayCount=" + dayCount +
                ", isDay=" + isDay +
                ", message='" + message + '\'' +
                '}';
    }

    public static void resetMessages(){
        messages.clear();
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public boolean isDay() {
        return isDay;
    }

    public void setDay(boolean day) {
        isDay = day;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void sendMessage(String message, Player receiver, boolean isPublic){
        messages.add(new Message(GameScreenController.getGameController().getDayCount(),
                GameScreenController.getGameController().isDay(),
                message, receiver, isPublic));
    }

    public static LinkedList<Message> getMessages() {
        return messages;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
