package com.rolegame.game.models.player;

import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.services.RoleService;
import com.rolegame.game.models.roles.enums.Team;
import com.rolegame.game.models.roles.folkroles.unique.Entrepreneur;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {
    public AIPlayer(int number, String name, Role role) {
        super(number, name, role);
    }

    @Override
    public boolean isAIPlayer() {
        return true;
    }

    public void chooseRandomPlayerVoting(final ArrayList<Player> players){
        ArrayList<Player> choosablePlayers = new ArrayList<>(players);
        choosablePlayers.remove(this);
        if(getRole().getTemplate().getTeam() == Team.CORRUPTER){
            for(Player player : players){
                if(player.getRole().getTemplate().getTeam() == Team.CORRUPTER){
                    choosablePlayers.remove(player);
                }
            }
        }
        else if(getRole().getTemplate().getTeam() == Team.FOLK){

            for(Player player: players){
                if(player.isRevealed()){

                    if(player.getRole().getTemplate().getTeam() == Team.CORRUPTER
                            || (player.getRole().getTemplate() instanceof NeutralRole neutralRole&&!neutralRole.canWinWithOtherTeams())){
                        getRole().setChoosenPlayer(player);
                        return;
                    }else if(player.getRole().getTemplate().getTeam() == Team.FOLK){
                        choosablePlayers.remove(player);
                    }
                }
            }


        }
        if(choosablePlayers.isEmpty()){
            return;
        }
        int randNum = new Random().nextInt(choosablePlayers.size());
        getRole().setChoosenPlayer(choosablePlayers.get(randNum));
    }

    public void chooseRandomPlayerNight(final ArrayList<Player> players){
        ArrayList<Player> choosablePlayers = new ArrayList<>(players);
        switch (getRole().getTemplate().getAbilityType()){
            case NO_ABILITY, PASSIVE -> {
                return;
            }
            case ACTIVE_SELF -> {
                getRole().setChoosenPlayer(this);
                return;
            }

            case OTHER_THAN_CORRUPTER -> {
                for(Player player : players){
                    if(player.getRole().getTemplate().getTeam() == Team.CORRUPTER){
                        choosablePlayers.remove(player);
                    }
                }
            }

            case ACTIVE_OTHERS -> choosablePlayers.remove(this);
        }

        chooseRoleSpecificValues(choosablePlayers);
        if(choosablePlayers.isEmpty()){
            return;
        }
        int randNum = new Random().nextInt(choosablePlayers.size());
        getRole().setChoosenPlayer(choosablePlayers.get(randNum));

    }

    private void chooseRoleSpecificValues(final ArrayList<Player> choosablePlayers){
        switch (getRole().getTemplate()){

            case Entrepreneur entrepreneur -> {
                boolean randBool= new Random().nextBoolean();
                entrepreneur.setAbilityState(randBool ? Entrepreneur.ChosenAbility.HEAL : Entrepreneur.ChosenAbility.ATTACK);

            }
            case Lorekeeper lorekeeper -> {
                lorekeeper.setGuessedRole(RoleService.getRandomRole());
                choosablePlayers.removeAll(lorekeeper.getAlreadyChosenPlayers());

            }

            default -> {}
        }
    }
}

