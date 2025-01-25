package com.rolegame.game.gamemanagement;

import com.rolegame.game.models.Player;

import java.util.HashMap;
import java.util.Map;

public class Voting {
    private static final HashMap<Player,Player> votes = new HashMap<>();
    private static Player maxVoted;
    private static int maxVote;
    public static void vote(Player voter, Player voted){
        votes.put(voter,voted);
    }

    public static int getVoteCount(Player player){
        int count = 0;
        for(Player votedPlayer: votes.values()){
            if(votedPlayer.equals(player)){
                count++;
            }
        }
        return count;
    }

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

    public static void clearVotes(){
        votes.clear();
        maxVoted = null;
        maxVote = 0;
    }

    public static Player getMaxVoted() {
        return maxVoted;
    }

    public static int getMaxVote() {
        return maxVote;
    }
}
