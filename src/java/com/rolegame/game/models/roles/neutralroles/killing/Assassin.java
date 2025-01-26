package com.rolegame.game.models.roles.neutralroles.killing;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public class Assassin extends NeutralRole implements ActiveNightAbility {
    public Assassin() {
        super(RoleID.Assassin, RolePriority.NONE, RoleCategory.NEUTRAL_KILLING, 1, 1
        , new ChanceProperty(40,1));
    }

    @Override
    public boolean performAbility() {

        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","RBimmuneMessage") ,getRoleOwner(),false);
        }

        if(getChoosenPlayer()==null){
            return false;
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","immuneMessage") ,getRoleOwner(),false);
            return false;
        }
        return executeAbility();

    }

    @Override
    public boolean executeAbility() {
        if(getAttack() > getChoosenPlayer().getDefence()){
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath","assassin"));
            sendAbilityMessage(LanguageManager.getText("Assassin","killMessage"), getRoleOwner(),false);
            sendAbilityMessage(LanguageManager.getText("Assassin","slainMessage")
                    .replace("{playerName}",this.getChoosenPlayer().getName()), getRoleOwner(),true);
            return true;
        }
        else{
            sendAbilityMessage(LanguageManager.getText("Assassin","defenceMessage"),
                    getRoleOwner(),false);
            return false;
        }
    }
}

