package com.rolegame.game.models.roles.neutralroles.killing;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;

public final class Assassin extends NeutralRole implements ActiveNightAbility {
    public Assassin() {
        super(RoleID.Assassin, RolePriority.NONE, RoleCategory.NEUTRAL_KILLING, 1, 1);
    }

    @Override
    public boolean performAbility() {

        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","RBimmuneMessage") ,getRoleOwner());
        }

        if(getChoosenPlayer()==null){
            return false;
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","immuneMessage") ,getRoleOwner());
            return false;
        }
        return executeAbility();

    }

    @Override
    public boolean executeAbility() {
        if(getAttack() > getChoosenPlayer().getDefence()){
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath","assassin"));
            sendAbilityMessage(LanguageManager.getText("Assassin","killMessage"), getRoleOwner());
            sendAbilityAnnouncement(LanguageManager.getText("Assassin","slainMessage")
                    .replace("{playerName}",this.getChoosenPlayer().getName()));
            return true;
        }
        else{
            sendAbilityMessage(LanguageManager.getText("Assassin","defenceMessage"),
                    getRoleOwner());
            return false;
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

