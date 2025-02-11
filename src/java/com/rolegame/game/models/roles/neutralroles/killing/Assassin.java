package com.rolegame.game.models.roles.neutralroles.killing;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.neutralroles.NeutralRole;

public final class Assassin extends NeutralRole {
    public Assassin() {
        super(RoleID.Assassin, AbilityType.ACTIVE_OTHERS, RolePriority.NONE, RoleCategory.NEUTRAL_KILLING, 1, 1);
    }

    @Override
    public AbilityResult performAbility(Player roleOwner, Player choosenPlayer) {

       return performAbilityForBlockImmuneRoles(roleOwner, choosenPlayer);

    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        if(getAttack() > choosenPlayer.getDefence()){
            choosenPlayer.setAlive(false);
            choosenPlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath","assassin"));
            sendAbilityMessage(LanguageManager.getText("Assassin","killMessage"), roleOwner);
            sendAbilityAnnouncement(LanguageManager.getText("Assassin","slainMessage")
                    .replace("{playerName}",choosenPlayer.getName()));
            return AbilityResult.SUCCESSFUL;
        }
        else{
            sendAbilityMessage(LanguageManager.getText("Assassin","defenceMessage"), roleOwner);
            return AbilityResult.ATTACK_INSUFFICIENT;
        }
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(40,1);
    }

    @Override
    public boolean canWinWithOtherTeams() {
        return false;
    }
}

