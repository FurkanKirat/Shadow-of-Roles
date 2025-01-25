package com.rolegame.game.Roles.NeutralRole.Killing;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

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

