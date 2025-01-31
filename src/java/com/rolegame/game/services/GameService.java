package com.rolegame.game.services;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import com.rolegame.game.models.roles.corrupterroles.support.LastJoke;
import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.models.roles.neutralroles.chaos.Clown;
import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.Team;
import com.rolegame.game.models.Message;
import com.rolegame.game.models.Player;


import java.util.*;

public class GameService {
    private final ArrayList<Player> allPlayers = new ArrayList<>();
    private final ArrayList<Player> alivePlayers = new ArrayList<>();
    private final ArrayList<Player> deadPlayers = new ArrayList<>();
    private final VotingService votingService;
    private Player currentPlayer;
    private int currentPlayerIndex;
    private int playerCount;
    private int dayCount = 1;
    private boolean isDay = false;
    private Team winnerTeam;

    public GameService(ArrayList<String> names, ArrayList<Role> roles){
        initializePlayers(names, roles);
        votingService = new VotingService();
    }


    /**
     * Initializes the players and distributes their roles
     * @param names players' names list
     */
    private void initializePlayers(ArrayList<String> names, ArrayList<Role> roles){

        playerCount = names.size();

        for(int i=0;i<playerCount;i++){
            allPlayers.add(new Player(i+1,names.get(i), roles.get(i)));
        }
        updateAlivePlayers();
        
    }

    public void toggleDayNightCycle(){
        isDay = !isDay;
        if(isDay){
            performAllAbilities();
            dayCount++;
        }else{
            executeMaxVoted();
        }

        if(checkGameFinished()){
            finishGame();
        }
    }

    /**
     *  Performs all abilities according to role priorities
     */
    public void performAllAbilities(){
        List<Role> roles = new ArrayList<>(new ArrayList<>(alivePlayers).stream().map(Player::getRole).toList());
        roles.sort(Comparator.comparing((Role role) -> role.getRolePriority().getPriority()).reversed());

        for(Role role: roles){
            role.performAbility();
        }

        for(Player alivePlayer: alivePlayers){
            alivePlayer.getRole().setChoosenPlayer(null);
            alivePlayer.setDefence(alivePlayer.getRole().getDefence());
            alivePlayer.getRole().setCanPerform(true);
            alivePlayer.setImmune(false);

        }
        updateAlivePlayers();
    }

    /**
     * After the day voting, executes the max voted player if they get more than half of the votes
     */
    public void executeMaxVoted(){
        votingService.updateMaxVoted();
        if(votingService.getMaxVote()>alivePlayers.size()/2){

            for(Player alivePlayer : alivePlayers){
                if(alivePlayer.getNumber()== votingService.getMaxVoted().getNumber()){
                    alivePlayer.setAlive(false);
                    alivePlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath","hanging"));
                    break;
                }
            }
            updateAlivePlayers();

            if(votingService.getMaxVoted()!=null){
                Message.sendMessage(LanguageManager.getText("Message","voteExecute")
                                .replace("{playerName}", votingService.getMaxVoted().getName())
                                .replace("{roleName}", votingService.getMaxVoted().getRole().getName()),
                        null, true, true);
            }

        }

        for(Player player: alivePlayers){
            player.getRole().setChoosenPlayer(null);
        }
        votingService.clearVotes();
    }

    /**
     *If it is morning, he casts a vote for the selected player and sends a message stating who he voted for.
     *If it's night, it sends a message about who is using your role.
     */
    public void sendVoteMessages(){
        Player chosenPlayer = currentPlayer.getRole().getChoosenPlayer();
        if(isDay){
            votingService.vote(currentPlayer,chosenPlayer);

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
        if(!alivePlayers.isEmpty()){
            currentPlayerIndex=0;
            currentPlayer = alivePlayers.getFirst();
        }

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
            return true;
        }

        // Finishes the game if nobody is alive
        if(alivePlayers.isEmpty()){
            winnerTeam = Team.NONE;
            return true;
        }


        // If only 2 players are alive checks the game if it is finished
        if(alivePlayers.size()==2){
            Player player1 = alivePlayers.getFirst();
            Player player2 = alivePlayers.get(1);

            Optional<Player> player = alivePlayers.stream()
                    .filter(p -> p.getRole() instanceof NeutralRole neutralRole && neutralRole.canWinWithOtherTeams())
                    .findFirst();

            // If one of the players' role is neutral role and the role can win with other teams finishes the game
            if(player.isPresent()){
                winnerTeam = player1.getRole().getTeam() == Team.NEUTRAL ? player2.getRole().getTeam() : player1.getRole().getTeam();
                return true;
            }

            // Finishes the game if the last two players cannot kill each other
            if(player1.getRole().getTeam()!=player2.getRole().getTeam()
                    &&player2.getRole().getAttack()<=player1.getRole().getDefence()
                    &&player1.getRole().getAttack()<=player2.getDefence()) {
                winnerTeam = Team.NONE;
                return true;
            }

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
        votingService.nullifyVotes();
        GameEndService.progressAchievements();

    }

    /**
     * Passes to the turn to the next player
     */
    public void passTurn(){
        currentPlayerIndex = (currentPlayerIndex + 1) % alivePlayers.size();
        currentPlayer = alivePlayers.get(currentPlayerIndex);

        if(currentPlayerIndex == 0){
            toggleDayNightCycle();
        }
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

    public void setPlayers(ArrayList<Player> players) {
        this.allPlayers.addAll(players);
        updateAlivePlayers();
    }
}
