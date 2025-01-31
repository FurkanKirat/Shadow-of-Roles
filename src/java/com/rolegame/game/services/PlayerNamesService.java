package com.rolegame.game.services;

import com.rolegame.game.models.roles.RoleCatalog;

import java.util.ArrayList;

public class PlayerNamesService {
    public GameService createGameService(ArrayList<String> playerNames, int playerCount) {
        if (playerNames == null || playerNames.size() != playerCount) {
            throw new IllegalArgumentException("Player count is wrong!");
        }

        return new GameService(playerNames, RoleCatalog.initializeRoles(playerCount));
    }
}
