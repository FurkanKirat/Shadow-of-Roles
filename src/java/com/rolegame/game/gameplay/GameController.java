package com.rolegame.game.gameplay;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import com.rolegame.game.models.roles.corrupterroles.support.LastJoke;
import com.rolegame.game.models.roles.neutralroles.chaos.Clown;
import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.RoleCatalog;
import com.rolegame.game.models.roles.roleproperties.RoleComparator;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.Team;
import com.rolegame.game.models.Message;
import com.rolegame.game.models.Player;
import javafx.scene.control.TextField;


import java.util.ArrayList;
import java.util.PriorityQueue;

public class GameController {
    private final ArrayList<Player> allPlayers = new ArrayList<>();
    private final ArrayList<Player> alivePlayers = new ArrayList<>();
    private final ArrayList<Player> deadPlayers = new ArrayList<>();
    private Player currentPlayer;
    private int currentPlayerIndex;
    private int playerCount;
    private int dayCount;
    private boolean isDay;
    private Team winnerTeam;

    public GameController(TextField[] textFields){
        initializePlayers(textFields);
    }

    /**
     * Initializes the players and distributes their roles
     * @param textFields the text fields that consists of players' names
     */
    private void initializePlayers(TextField[] textFields){
        ArrayList<Role> randomRoles = RoleCatalog.initializeRoles(textFields.length);
        for(int i=0;i<textFields.length;i++){
            allPlayers.add(new Player(i+1,textFields[i].getText(), randomRoles.get(i)));
        }
        updateAlivePlayers();
        dayCount=1;
        isDay=false;
        playerCount = textFields.length;
    }

    /**
     *  Performs all abilities according to role priorities
     */
    public void performAllAbilities(){
        PriorityQueue<Role> roleQueue = new PriorityQueue<>(alivePlayers.size(),new RoleComparator());

        for (Player alivePlayer : alivePlayers) {
            roleQueue.add(alivePlayer.getRole());
        }
        for(Role role: roleQueue){
            role.performAbility();
        }

        for(Player alivePlayer: alivePlayers){
            alivePlayer.getRole().setChoosenPlayer(null);
            alivePlayer.setDefence(alivePlayer.getRole().getDefence());
            alivePlayer.getRole().setCanPerform(true);
            alivePlayer.setImmune(false);

        }
    }

    /**
     * After the day voting, executes the max voted player if they get more than half of the votes
     */
    public void executeMaxVoted(){
        Voting.updateMaxVoted();
        if(Voting.getMaxVote()>alivePlayers.size()/2){

            for(Player alivePlayer : alivePlayers){
                if(alivePlayer.equals(Voting.getMaxVoted())){
                    alivePlayer.setAlive(false);
                    alivePlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath","hanging"));
                    break;
                }
            }
            updateAlivePlayers();

            if(Voting.getMaxVoted()!=null){
                Message.sendMessage(LanguageManager.getText("Message","voteExecute")
                                .replace("{playerName}", Voting.getMaxVoted().getName())
                                .replace("{roleName}", Voting.getMaxVoted().getRole().getName()),
                        null, true, true);
            }

        }

        for(Player player: alivePlayers){
            player.getRole().setChoosenPlayer(null);
        }

        Voting.clearVotes();
    }

    /**
     *If it is morning, he casts a vote for the selected player and sends a message stating who he voted for.
     *If it's night, it sends a message about who is using your role.
     */
    public void sendVoteMessages(){
        Player chosenPlayer = currentPlayer.getRole().getChoosenPlayer();
        if(isDay){
            Voting.vote(currentPlayer,chosenPlayer);

            if(chosenPlayer!=null){
                Message.sendMessage(LanguageManager.getText("Message","vote")
                                .replace("{playerName}", chosenPlayer.getName())
                        ,currentPlayer,false, true);
            }else{
                Message.sendMessage(LanguageManager.getText("Message","noVote"), currentPlayer, false, true);
            }

        }
        else{
            if(currentPlayer.getRole() instanceof ActiveNightAbility){
                if(chosenPlayer!=null){
                    Message.sendMessage(LanguageManager.getText("Message","ability")
                                    .replace("{playerName}", chosenPlayer.getName())
                            ,currentPlayer,false, false);
                }
                else{
                    Message.sendMessage(LanguageManager.getText("Message","noAbilityUsed"), currentPlayer, false,false);
                }
            }
        }
    }


    /**
     *  Updates alive players
     */
    public void updateAlivePlayers(){
        alivePlayers.clear();
        for (Player player : allPlayers) {

            if (player.isAlive()) {
                alivePlayers.add(player);
            }

            else{

                /* If players role is last joke, player is dead and player has not used ability
                 * yet adds the player to the alive players to use their ability */
                if(player.getRole() instanceof LastJoke lastJoker && !lastJoker.isDidUsedAbility() && !isDay){
                    alivePlayers.add(player);
                }
            }


        }
        currentPlayerIndex=0;
        currentPlayer = alivePlayers.getFirst();
    }

    /**
     * Updates the dead players
     * @return updated dead players
     */
    public ArrayList<Player> getDeadPlayers() {
        deadPlayers.clear();
        for (Player allPlayer : allPlayers) {
            if (!allPlayer.isAlive()) {
                deadPlayers.add(allPlayer);
            }
        }
        return deadPlayers;
    }

    /**
     * Checks the game if it is finished and finishes the game
     * @return game is finished or not
     */
    public boolean checkGameFinished(){

        // Finishes the game if only 1 player is alive
        if(alivePlayers.size()==1){
            winnerTeam = alivePlayers.getFirst().getRole().getTeam();
            finishGame();
            return true;
        }

        // Finishes the game if nobody is alive
        if(alivePlayers.isEmpty()){
            winnerTeam = Team.NONE;
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
        if(alivePlayers.getFirst().getRole().getTeam()!=Team.NEUTRAL){
            for(Player alivePlayer : alivePlayers){
                winnerTeam = alivePlayer.getRole().getTeam();
            }
            finishGame();
            return true;
        }

        return false;

    }

    /**
     * Finishes the game if the end conditions are taken place
     */
    public void finishGame(){

        if(winnerTeam!=Team.NONE &&winnerTeam!=Team.NEUTRAL){
            for(Player player : allPlayers){
                if(player.getRole().getTeam()==winnerTeam){
                    player.setHasWon(true);
                }
            }
        }

        if(winnerTeam == Team.NEUTRAL){
            alivePlayers.getFirst().setHasWon(true);
        }

        boolean simplePersonExist = false;
        for(Player player: allPlayers){

            switch (player.getRole()){
                case ChillGuy _ -> {
                    if(player.getRole() instanceof ChillGuy){
                        simplePersonExist = true;
                    }
                }
                case Clown _ -> {
                    if(!player.isAlive() && !player.getCauseOfDeath().equals(LanguageManager.getText("CauseOfDeath","hanging"))){
                        player.setHasWon(true);
                    }
                }
                case Lorekeeper lorekeeper -> {
                    int winCount;

                    if(playerCount>6){
                        winCount = 3;
                    }
                    else{
                        winCount = 2;
                    }

                    if(lorekeeper.getTrueGuessCount() >= winCount){
                        player.setHasWon(true);
                    }
                }
                default -> {

                }
            }

        }

        if(simplePersonExist){
            SceneManager.switchScene("/com/rolegame/game/fxml/game/ChillGuyAlert.fxml", SceneManager.SceneType.SIMPLE_PERSON_ALERT, false);
        }
        else{
            SceneManager.switchScene("/com/rolegame/game/fxml/game/EndGame.fxml", SceneManager.SceneType.END_GAME, false);
        }

        Message.resetMessages();
        Voting.clearVotes();

    }

    /**
     * Passes to the turn to the next player
     */
    public void passTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % alivePlayers.size();
        currentPlayer = alivePlayers.get(currentPlayerIndex);

    }

    // Getters and Setters
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

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public ArrayList<Player> getAlivePlayers() {
        return alivePlayers;
    }
}
