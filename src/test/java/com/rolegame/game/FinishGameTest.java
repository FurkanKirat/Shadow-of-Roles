package com.rolegame.game;

import com.rolegame.game.services.GameService;
import com.rolegame.game.models.Player;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.corrupterroles.analyst.DarkRevealer;
import com.rolegame.game.models.roles.corrupterroles.analyst.Darkseer;
import com.rolegame.game.models.roles.corrupterroles.support.Interrupter;
import com.rolegame.game.models.roles.folkroles.analyst.Detective;
import com.rolegame.game.models.roles.folkroles.analyst.Stalker;
import com.rolegame.game.models.roles.folkroles.unique.Entrepreneur;
import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;
import com.rolegame.game.models.roles.neutralroles.chaos.Clown;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.neutralroles.killing.Assassin;
import com.rolegame.game.models.roles.roleproperties.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FinishGameTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Furkan");
        names.add("Turhan");
        names.add("Tikky");
        names.add("Enis");
        names.add("Ege");
        names.add("Duran");
        names.add("Kerem");

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Detective());
        roles.add(new Stalker());
        roles.add(new Assassin());
        roles.add(new Clown());
        roles.add(new ChillGuy());
        roles.add(new Entrepreneur());
        roles.add(new Assassin());
        gameService = new GameService(names,roles);
    }

    @Test
    void testGameFinishesWithOnePlayerLeft() {

        boolean result = gameService.checkGameFinished();

        assertTrue(result);
        assertEquals(Team.FOLK, gameService.getWinnerTeam());
    }

    @Test
    void testGameFinishesWithNoPlayersLeft() {
        for(Player player: gameService.getAlivePlayers()){
            player.setAlive(false);
            player.setCauseOfDeath("Assassin");
        }
        gameService.updateAlivePlayers();
        boolean result = gameService.checkGameFinished();

        assertTrue(result);
        assertEquals(Team.NONE, gameService.getWinnerTeam());
    }

    @Test
    void testGameDoesNotFinishWithDifferentTeams() {

        gameService.updateAlivePlayers();

        boolean result = gameService.checkGameFinished();

        assertFalse(result);
    }

    @Test
    void testGameFinishesWhenLastTwoPlayersCannotKillEachOther() {

        gameService.updateAlivePlayers();

        boolean result = gameService.checkGameFinished();

        assertTrue(result);

    }

    @Test
    void testGameFinishesWhenLastTwoPlayersCanKillEachOther() {

        gameService.updateAlivePlayers();

        boolean result = gameService.checkGameFinished();

        assertTrue(result);
        assertEquals(Team.FOLK, gameService.getWinnerTeam());
    }

    @Test
    void testGameFinishesWhenNeutralLastTwoPlayersCanKillEachOther() {


        Player player2 = new Player(2,"Tikky", new ChillGuy());

        gameService.updateAlivePlayers();

        boolean result = gameService.checkGameFinished();

        assertTrue(result);
    }

    @Test
    void testGameFinishesWhenFolkAndCorrupterLastTwoPlayersCannotKillEachOther() {

        gameService.updateAlivePlayers();

        boolean result = gameService.checkGameFinished();

        assertTrue(result);
        assertEquals(Team.NONE, gameService.getWinnerTeam());
    }

    @Test
    void testGameFinishesWhenAllPlayersHaveSameTeam() {

        gameService.updateAlivePlayers();

        boolean result = gameService.checkGameFinished();

        assertTrue(result);
        assertEquals(Team.CORRUPTER, gameService.getWinnerTeam());
    }

    @Test
    void testGameFinishesWhenTwoNeutralCannotKill() {

        gameService.updateAlivePlayers();

        boolean result = gameService.checkGameFinished();

        assertTrue(result);
        assertEquals(Team.NEUTRAL, gameService.getWinnerTeam());
    }

    @Test
    void testGameFinishesWhenAllPlayersHaveSameTeamNeutral() {



        gameService.updateAlivePlayers();

        boolean result = gameService.checkGameFinished();

        assertFalse(result);

    }
}
