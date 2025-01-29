package com.rolegame.game.gameplay;

import com.rolegame.game.models.Player;

import java.util.HashMap;
import java.util.Map;

public class Voting {
    private static final HashMap<Player,Player> votes = new HashMap<>();
    private static Player maxVoted;
    private static int maxVote;

    /**
     * Casts a vote from the voter player to the voted player
     * @param voter voter player
     * @param voted voted player
     */
    public static void vote(Player voter, Player voted){
        votes.put(voter,voted);
    }

    /**
     *
     * @param player the desired player
     * @return player's vote count
     */
    public static int getVoteCount(Player player){
        int count = 0;
        for(Player votedPlayer: votes.values()){
            if(votedPlayer.getNumber()==player.getNumber()){
                count++;
            }
        }
        return count;
    }

    /**
     * Updates the max voted player
     */
    public static void updateMaxVoted(){
        HashMap<Player,Integer> voteCounts = new HashMap<>();

        for(Player votedPlayer: votes.values()){
            voteCounts.put(votedPlayer, voteCounts.getOrDefault(votedPlayer,0)+1);
        }

        for(Map.Entry<Player, Integer> entry : voteCounts.entrySet()){
            if(entry.getValue()>maxVote){
                maxVoted = entry.getKey();
                maxVote = entry.getValue();
            }
        }
    }

    /**
     * Clears the votes after game is finished
     */
    public static void clearVotes(){
        votes.clear();
        maxVoted = null;
        maxVote = 0;
    }

    // Getters
    public static Player getMaxVoted() {
        return maxVoted;
    }

    public static int getMaxVote() {
        return maxVote;
    }
}
