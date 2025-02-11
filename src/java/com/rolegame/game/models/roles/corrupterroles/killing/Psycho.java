package com.rolegame.game.models.roles.corrupterroles.killing;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.enums.*;

public final class Psycho extends CorrupterRole {

    public Psycho() {
        super(RoleID.Psycho, AbilityType.OTHER_THAN_CORRUPTER, RolePriority.NONE, RoleCategory.CORRUPTER_KILLING, 1,0);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {

        if(getAttack() > choosenPlayer.getDefence()){
            choosenPlayer.setAlive(false);
            choosenPlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath","psycho"));
            sendAbilityMessage(LanguageManager.getText("Psycho","killMessage"), roleOwner);
            sendAbilityAnnouncement( LanguageManager.getText("Psycho","slainMessage").replace("{playerName}",choosenPlayer.getName()));

            return AbilityResult.SUCCESSFUL;
        }
        else{
            sendAbilityMessage(LanguageManager.getText("Psycho","defenceMessage"), roleOwner);
            return AbilityResult.ATTACK_INSUFFICIENT;
        }
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(100,1);
    }
}
