package com.rolegame.game.GameManagement;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.PropertyControllers.SceneController;
import com.rolegame.game.Roles.CorrupterRole.Support.LastJoke;
import com.rolegame.game.Roles.NeutralRole.Chaos.Clown;
import com.rolegame.game.Roles.NeutralRole.Chaos.SimplePerson;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleCatalog;
import com.rolegame.game.Roles.RoleComparator;
import com.rolegame.game.Roles.RoleProperties.Team;
import javafx.scene.control.TextField;


import java.util.ArrayList;
import java.util.PriorityQueue;

public class GameController {
    private ArrayList<Player> allPlayers = new ArrayList<>();
    private ArrayList<Player> alivePlayers = new ArrayList<>();
    private ArrayList<Player> deadPlayers = new ArrayList<>();
    private Player currentPlayer;
    private int currentPlayerIndex;
    private int playerCount;
    private int dayCount;
    private boolean isDay;
    private Team winnerTeam;

    public GameController(){

    }
    public void performAllAbilities(){
        PriorityQueue<Role> roleQueue = new PriorityQueue<>(alivePlayers.size(),new RoleComparator());

        for (Player alivePlayer : alivePlayers) {
            roleQueue.add(alivePlayer.getRole());
        }
        for(Role role: roleQueue){
            role.performAbility();
        }
    }

    public void executeMaxVoted(){
        Voting.updateMaxVoted();
        if(Voting.getMaxVote()>alivePlayers.size()/2){

            for(Player alivePlayer : alivePlayers){
                if(alivePlayer.equals(Voting.getMaxVoted())){
                    alivePlayer.setAlive(false);
                    alivePlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath.hanging"));
                    break;
                }
            }
            updateAlivePlayers();
        }
        Voting.clearVotes();
    }


    public void initializePlayers(TextField[] textFields){
        ArrayList<Role> randomRoles = RoleCatalog.initializeRoles(textFields.length);
        for(int i=0;i<textFields.length;i++){
            allPlayers.add(new Player(i+1,textFields[i].getText(), randomRoles.get(i)));
        }
        alivePlayers = (ArrayList<Player>) allPlayers.clone();
        updateAlivePlayers();
        currentPlayerIndex=0;
        currentPlayer = alivePlayers.getFirst();
        dayCount=1;
        isDay=true;
        playerCount = textFields.length;
    }

    // Getter and Setters
    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public ArrayList<Player> getAlivePlayers() {
        return alivePlayers;
    }
    public void updateAlivePlayers(){
        alivePlayers = new ArrayList<>();
        for (Player player : allPlayers) {

            if (player.isAlive()) {
                alivePlayers.add(player);
            }

            else{
                if(player.getRole() instanceof LastJoke lastJoker && !lastJoker.isDidUsedAbility() && !isDay){
                    alivePlayers.add(player);
                }
            }


        }
        currentPlayerIndex=0;
        currentPlayer = alivePlayers.getFirst();
    }

    public ArrayList<Player> getDeadPlayers() {
        deadPlayers = new ArrayList<>();
        for (Player allPlayer : allPlayers) {
            if (!allPlayer.isAlive()) {
                deadPlayers.add(allPlayer);
            }
        }
        return deadPlayers;
    }

    public boolean checkGameFinished(){

        // Finishes the game if only 1 player is alive
        if(alivePlayers.size()==1){
            winnerTeam = alivePlayers.getFirst().getRole().getTeam();
            finishGame();
            return true;
        }

        // Finishes the game if nobody is alive
        if(alivePlayers.isEmpty()){
            winnerTeam = Team.None;
            finishGame();
            return true;
        }

        // Continues the game if all players have the same team
        for(int i=0;i<alivePlayers.size()-1;i++){
            if(!alivePlayers.get(i).getRole().getTeam().equals(alivePlayers.get(i+1).getRole().getTeam())){
                return false;
            }
        }

        // Checks if the living players are neutral if so game continues because they are independent
        if(alivePlayers.getFirst().getRole().getTeam()!=Team.Neutral){
            for(Player alivePlayer : alivePlayers){
                winnerTeam = alivePlayer.getRole().getTeam();
            }
            finishGame();
            return true;
        }

        return false;

    }

    public void finishGame(){

        if(winnerTeam!=Team.None&&winnerTeam!=Team.Neutral){
            for(Player player : allPlayers){
                if(player.getRole().getTeam()==winnerTeam){
                    player.setHasWon(true);
                }
            }
        }

        if(winnerTeam == Team.Neutral){
            alivePlayers.getFirst().setHasWon(true);
        }

        boolean simplePersonExist = false;
        for(Player player: allPlayers){
            if(player.getRole() instanceof SimplePerson){
                simplePersonExist = true;
            } else if (player.getRole() instanceof Clown) {
                if(!player.isAlive() && !player.getCauseOfDeath().equals(LanguageManager.getText("CauseOfDeath.hanging"))){
                    player.setHasWon(true);
                }
            }
        }

        if(simplePersonExist){
            SceneController.switchScene("/com/rolegame/game/fxml/SimplePersonAlert.fxml", SceneController.SceneType.SimplePersonAlert);
        }
        else{
            SceneController.switchScene("/com/rolegame/game/fxml/EndGame.fxml", SceneController.SceneType.EndGame);
        }

        Message.resetMessages();
        Voting.clearVotes();



    }

    public void passTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % alivePlayers.size();
        currentPlayer = alivePlayers.get(currentPlayerIndex);



    }
    public boolean isDay() {
        return isDay;
    }

    public void setDay(boolean day) {
        isDay = day;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
        this.currentPlayer = alivePlayers.get(currentPlayerIndex);
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }
}
