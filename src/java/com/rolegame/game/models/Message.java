package com.rolegame.game.models;

import com.rolegame.game.models.player.Player;

public record Message(int dayCount, boolean isDay, String message, Player receiver, boolean isPublic) {

    @Override
    public String toString() {
        return "Message{" +
                "dayCount=" + dayCount +
                ", isDay=" + isDay +
                ", message='" + message + '\'' +
                '}';
    }

}
