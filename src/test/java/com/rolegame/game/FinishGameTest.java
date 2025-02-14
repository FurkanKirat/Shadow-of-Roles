//package com.rolegame.game;
//
//import com.rolegame.game.gui.controllers.game.PlayerNamesController;
//import com.rolegame.game.services.GameService;
//import com.rolegame.game.models.player.Player;
//import com.rolegame.game.models.roles.Role;
//import com.rolegame.game.models.roles.folkroles.analyst.Detective;
//import com.rolegame.game.models.roles.folkroles.analyst.Stalker;
//import com.rolegame.game.models.roles.folkroles.unique.Entrepreneur;
//import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;
//import com.rolegame.game.models.roles.neutralroles.chaos.Clown;
//import com.rolegame.game.models.roles.neutralroles.killing.Assassin;
//import com.rolegame.game.models.roles.enums.Team;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class FinishGameTest {
//
//    private GameService gameService;
//
//    @BeforeEach
//    void setUp() {
//        ArrayList<PlayerNamesController.NameAndIsAI> names = new ArrayList<>();
//        names.add(new PlayerNamesController.NameAndIsAI("Furkan",true));
//        names.add(new PlayerNamesController.NameAndIsAI("Turhan",true));
//        names.add(new PlayerNamesController.NameAndIsAI("Tikky",true));
//        names.add(new PlayerNamesController.NameAndIsAI("Enis",true));
//        names.add(new PlayerNamesController.NameAndIsAI("Ege",true));
//        names.add(new PlayerNamesController.NameAndIsAI("Duran",true));
//        names.add(new PlayerNamesController.NameAndIsAI("Kerem",true));
//
//        ArrayList<Role> roles = new ArrayList<>();
//        roles.add(new Detective());
//        roles.add(new Stalker());
//        roles.add(new Assassin());
//        roles.add(new Clown());
//        roles.add(new ChillGuy());
//        roles.add(new Entrepreneur());
//        roles.add(new Assassin());
//
//        ArrayList<Boolean> isPlayerAI = new ArrayList<>();
//        isPlayerAI.add(true);
//        isPlayerAI.add(true);
//        isPlayerAI.add(true);
//        isPlayerAI.add(true);
//        isPlayerAI.add(true);
//        isPlayerAI.add(true);
//        isPlayerAI.add(true);
//        gameService = new GameService(names,roles);
//    }
//
//    @Test
//    void testGameFinishesWithOnePlayerLeft() {
//
//        for(int i=1;i<gameService.getAlivePlayers().size();i++){
//            gameService.getAlivePlayers().get(i).setAlive(false);
//            gameService.getCurrentPlayer().setCauseOfDeath("Assassin");
//        }
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertTrue(result);
//        assertEquals(gameService.getAllPlayers().getFirst().getRole().getTeam(), gameService.getWinnerTeam());
//    }
//
//    @Test
//    void testGameFinishesWithNoPlayersLeft() {
//
//        for(Player player: gameService.getAlivePlayers()){
//            player.setAlive(false);
//            player.setCauseOfDeath("Assassin");
//        }
//        gameService.updateAlivePlayers();
//        boolean result = gameService.checkGameFinished();
//
//        assertTrue(result);
//        assertEquals(Team.NONE, gameService.getWinnerTeam());
//    }
//
//    @Test
//    void testGameDoesNotFinishWithDifferentTeams() {
//
//        for(Player player: gameService.getAlivePlayers()){
//            player.setAlive(false);
//            player.setCauseOfDeath("Assassin");
//        }
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertFalse(result);
//    }
//
//    @Test
//    void testGameFinishesWhenLastTwoPlayersCannotKillEachOther() {
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertTrue(result);
//
//    }
//
//    @Test
//    void testGameFinishesWhenLastTwoPlayersCanKillEachOther() {
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertTrue(result);
//        assertEquals(Team.FOLK, gameService.getWinnerTeam());
//    }
//
//    @Test
//    void testGameFinishesWhenNeutralLastTwoPlayersCanKillEachOther() {
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertTrue(result);
//    }
//
//    @Test
//    void testGameFinishesWhenFolkAndCorrupterLastTwoPlayersCannotKillEachOther() {
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertTrue(result);
//        assertEquals(Team.NONE, gameService.getWinnerTeam());
//    }
//
//    @Test
//    void testGameFinishesWhenAllPlayersHaveSameTeam() {
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertTrue(result);
//        assertEquals(Team.CORRUPTER, gameService.getWinnerTeam());
//    }
//
//    @Test
//    void testGameFinishesWhenTwoNeutralCannotKill() {
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertTrue(result);
//        assertEquals(Team.NEUTRAL, gameService.getWinnerTeam());
//    }
//
//    @Test
//    void testGameFinishesWhenAllPlayersHaveSameTeamNeutral() {
//
//
//
//        gameService.updateAlivePlayers();
//
//        boolean result = gameService.checkGameFinished();
//
//        assertFalse(result);
//
//    }
//}
