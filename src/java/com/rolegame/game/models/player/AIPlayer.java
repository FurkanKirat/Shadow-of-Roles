package com.rolegame.game.models.player;

import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.enums.Team;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {
    public AIPlayer(int number, String name, Role role) {
        super(number, name, role);
    }

    @Override
    public boolean isAIPlayer() {
        return true;
    }

    public void chooseRandomPlayerVoting(ArrayList<Player> players){
        ArrayList<Player> choosablePlayers = new ArrayList<>(players);
        choosablePlayers.remove(this);
        if(getRole().getTeam() == Team.CORRUPTER){
            for(Player player : players){
                if(player.getRole().getTeam() == Team.CORRUPTER){
                    choosablePlayers.remove(player);
                }
            }
        }
        int randNum = new Random().nextInt(choosablePlayers.size());
        getRole().setChoosenPlayer(choosablePlayers.get(randNum));
    }

    public void chooseRandomPlayerNight(ArrayList<Player> players){

        if(!(getRole() instanceof ActiveNightAbility)){
            return;
        }

        ArrayList<Player> choosablePlayers = new ArrayList<>(players);
        choosablePlayers.remove(this);
        if(getRole().getTeam() == Team.CORRUPTER){
            for(Player player : players){
                if(player.getRole().getTeam() == Team.CORRUPTER){
                    choosablePlayers.remove(player);
                }
            }
        }
        int randNum = new Random().nextInt(choosablePlayers.size());
        getRole().setChoosenPlayer(choosablePlayers.get(randNum));
    }
}

