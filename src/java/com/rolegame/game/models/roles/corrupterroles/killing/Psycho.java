package com.rolegame.game.models.roles.corrupterroles.killing;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public class Psycho extends CorrupterRole implements ActiveNightAbility {

    public Psycho() {
        super(RoleID.Psycho, RolePriority.NONE, RoleCategory.CORRUPTER_KILLING, 1,0
        , new ChanceProperty(100,1));
    }

    @Override
    public boolean executeAbility() {

        if(getAttack() > getChoosenPlayer().getDefence()){
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath","psycho"));
            sendAbilityMessage(LanguageManager.getText("Psycho","killMessage"), getRoleOwner(),false);
            sendAbilityMessage( LanguageManager.getText("Psycho","slainMessage").replace("{playerName}",this.getChoosenPlayer().getName()), getRoleOwner(),true);

            return true;
        }
        else{
            sendAbilityMessage(LanguageManager.getText("Psycho","defenceMessage"),
                    getRoleOwner(),false);
            return false;
        }
    }
}
