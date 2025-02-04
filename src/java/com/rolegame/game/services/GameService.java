package com.rolegame.game.services;

import com.rolegame.game.gamestate.Time;
import com.rolegame.game.gui.controllers.game.PlayerNamesController;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import com.rolegame.game.models.player.AIPlayer;
import com.rolegame.game.models.player.HumanPlayer;
import com.rolegame.game.models.roles.corrupterroles.support.LastJoke;
import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.models.roles.neutralroles.chaos.Clown;
import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.Team;
import com.rolegame.game.models.player.Player;


import java.util.*;

public class GameService {
    private final ArrayList<Player> allPlayers = new ArrayList<>();
    private final ArrayList<Player> alivePlayers = new ArrayList<>();
    private final ArrayList<Player> deadPlayers = new ArrayList<>();
    private final VotingService votingService;
    private final TimeService timeService;
    private Player currentPlayer;
    private int currentPlayerIndex;
    private int playerCount;

    private Team winnerTeam;

    public GameService(ArrayList<PlayerNamesController.NameAndIsAI> info, ArrayList<Role> roles){
        initializePlayers(info, roles);
        timeService = new TimeService();
        votingService = new VotingService();
    }


    /**
     * Initializes the players and distributes their roles
     * @param info players' information list
     */
    private void initializePlayers(ArrayList<PlayerNamesController.NameAndIsAI> info, ArrayList<Role> roles){

        playerCount = info.size();

        for(int i=0;i<playerCount;i++){
            if(info.get(i).isAI()){
                allPlayers.add(new AIPlayer(i+1,info.get(i).name(), roles.get(i)));
            }else{
                allPlayers.add(new HumanPlayer(i+1,info.get(i).name(), roles.get(i)));
            }

        }
        updateAlivePlayers();
        
    }

    public void toggleDayNightCycle(){
        timeService.toggleTimeCycle();
        Time time = timeService.getTime();
        switch (time) {
            case DAY -> {
                performAllAbilities();
            }
            case NIGHT -> {
                executeMaxVoted();
            }
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

        for(Role role: roles){
            if(role.getRoleOwner() instanceof AIPlayer aiPlayer){
                aiPlayer.chooseRandomPlayer(alivePlayers);
            }
        }

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
                if(alivePlayer.getNumber() == votingService.getMaxVoted().getNumber()){
                    alivePlayer.setAlive(false);
                    alivePlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath","hanging"));
                    break;
                }
            }
            updateAlivePlayers();

            if(votingService.getMaxVoted()!=null){
                MessageService.sendMessage(LanguageManager.getText("Message","voteExecute")
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

        if(currentPlayer instanceof AIPlayer aiPlayer){
            aiPlayer.chooseRandomPlayer(alivePlayers);
        }

        Player chosenPlayer = currentPlayer.getRole().getChoosenPlayer();
        if(timeService.getTime() == Time.VOTING){
            votingService.vote(currentPlayer,chosenPlayer);

            if(chosenPlayer!=null){
                MessageService.sendMessage(LanguageManager.getText("Message","vote")
                                .replace("{playerName}", chosenPlayer.getName())
                        ,currentPlayer,false, true);
            }else{
                MessageService.sendMessage(LanguageManager.getText("Message","noVote"), currentPlayer, false, true);
            }

        }
        else if(timeService.getTime() == Time.NIGHT){
            if(currentPlayer.getRole() instanceof ActiveNightAbility){
                if(chosenPlayer!=null){
                    MessageService.sendMessage(LanguageManager.getText("Message","ability")
                                    .replace("{playerName}", chosenPlayer.getName())
                            ,currentPlayer,false, false);
                }
                else{
                    MessageService.sendMessage(LanguageManager.getText("Message","noAbilityUsed"), currentPlayer, false,false);
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
                if(player.getRole() instanceof LastJoke lastJoker && !lastJoker.isDidUsedAbility() &&
                        timeService.getTime() == Time.NIGHT){
                    alivePlayers.add(player);
                }
            }


        }
        if(!alivePlayers.isEmpty()){
            moveToFirstHumanPlayer();
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

        boolean chillGuyExist = false;
        for(Player player: allPlayers){

            switch (player.getRole()){
                case ChillGuy _ -> {
                    chillGuyExist = true;
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

        if(chillGuyExist){
            SceneManager.switchScene("/com/rolegame/game/fxml/game/ChillGuyAlert.fxml", SceneManager.SceneType.SIMPLE_PERSON_ALERT, false);
        }
        else{
            SceneManager.switchScene("/com/rolegame/game/fxml/game/EndGame.fxml", SceneManager.SceneType.END_GAME, false);
        }

        MessageService.resetMessages();
        votingService.nullifyVotes();


    }

    /**
     * Passes to the turn to the next player
     */
    public boolean passTurn() {

        if(!doesHumanPlayerExist()){
            while(!checkGameFinished()){
                toggleDayNightCycle();
            }
            finishGame();
            return true;
        }

        boolean firstTurn = true;

        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % alivePlayers.size();
            currentPlayer = alivePlayers.get(currentPlayerIndex);

            if (currentPlayerIndex == 0) {
                toggleDayNightCycle();
                firstTurn = false;
            }

        } while (currentPlayer instanceof AIPlayer && firstTurn);
        return !firstTurn;
    }

    /**
     * Moves the turn to the first human player, skipping AI players.
     */
    public void moveToFirstHumanPlayer() {

        for(int i=0;i<alivePlayers.size();i++){
            if(!alivePlayers.get(i).isAIPlayer()){
                currentPlayerIndex = i;
                currentPlayer = alivePlayers.get(currentPlayerIndex);
                break;
            }
        }
    }



    private boolean doesHumanPlayerExist(){
        for (Player alivePlayer : alivePlayers) {
            if (!alivePlayer.isAIPlayer()) {
                return true;
            }
        }
        return false;

    }


    // Getters and Setters
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

    public TimeService getTimeService() {
        return timeService;
    }
}
