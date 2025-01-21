package com.rolegame.game.Roles.CorrupterRole.Killing;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Psycho extends CorrupterRole implements ActiveNightAbility {

    public Psycho() {
        super(RoleID.Psycho, RolePriority.NONE, RoleCategory.CORRUPTER_KILLING, 1,0);
    }

    @Override
    public boolean executeAbility() {

        if(getAttack() > getChoosenPlayer().getDefence()){
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath.psycho"));
            sendAbilityMessage(LanguageManager.getText("Psycho.killMessage"), getRoleOwner(),false);
            sendAbilityMessage( LanguageManager.getText("Psycho.slainMessage").replace("{playerName}",this.getChoosenPlayer().getName()), getRoleOwner(),true);

            return true;
        }
        else{
            sendAbilityMessage(LanguageManager.getText("Psycho.defenceMessage"),
                    getRoleOwner(),false);
            return false;
        }
    }
}
