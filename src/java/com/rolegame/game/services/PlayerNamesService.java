package com.rolegame.game.services;

import com.rolegame.game.gui.controllers.game.PlayerNamesController;

import java.util.ArrayList;

public class PlayerNamesService {
    public GameService createGameService(ArrayList<PlayerNamesController.NameAndIsAI> playerInfo, int playerCount) {
        if (playerInfo == null || playerInfo.size() != playerCount) {
            throw new IllegalArgumentException("Player count is wrong!");
        }

        return new GameService(playerInfo, RoleService.initializeRoles(playerCount));
    }
}
