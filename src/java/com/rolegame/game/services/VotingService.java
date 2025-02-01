package com.rolegame.game.services;

import com.rolegame.game.models.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class VotingService {
    private HashMap<Player,Player> votes = new HashMap<>();
    private Player maxVoted;
    private int maxVote;

    /**
     * Casts a vote from the voter player to the voted player
     * @param voter voter player
     * @param voted voted player
     */
    public void vote(Player voter, Player voted){
        votes.put(voter,voted);
    }

    /**
     *
     * @param player the desired player
     * @return player's vote count
     */
    public int getVoteCount(Player player){
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
    public void updateMaxVoted(){

        HashMap<Player,Integer> voteCounts = new HashMap<>();

        for(Player votedPlayer: votes.values()){
            if(votedPlayer!=null) voteCounts.put(votedPlayer, voteCounts.getOrDefault(votedPlayer,0)+1);
        }

        for(Map.Entry<Player, Integer> entry : voteCounts.entrySet()){
            if(entry.getValue()>maxVote){
                maxVoted = entry.getKey();
                maxVote = entry.getValue();
            }
        }
    }

    /**
     * Clears the votes after day is finished
     */
    public void clearVotes(){
        votes.clear();
        maxVoted = null;
        maxVote = 0;
    }

    public void nullifyVotes(){
        votes = null;
        maxVoted = null;
        maxVote = 0;
    }

    // Getters
    public Player getMaxVoted() {
        return maxVoted;
    }

    public int getMaxVote() {
        return maxVote;
    }
}
