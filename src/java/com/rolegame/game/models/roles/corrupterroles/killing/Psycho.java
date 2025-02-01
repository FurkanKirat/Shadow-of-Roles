package com.rolegame.game.models.roles.corrupterroles.killing;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;

public final class Psycho extends CorrupterRole implements ActiveNightAbility {

    public Psycho() {
        super(RoleID.Psycho, RolePriority.NONE, RoleCategory.CORRUPTER_KILLING, 1,0);
    }

    @Override
    public boolean executeAbility() {

        if(getAttack() > getChoosenPlayer().getDefence()){
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath","psycho"));
            sendAbilityMessage(LanguageManager.getText("Psycho","killMessage"), getRoleOwner());
            sendAbilityAnnouncement( LanguageManager.getText("Psycho","slainMessage").replace("{playerName}",this.getChoosenPlayer().getName()));

            return true;
        }
        else{
            sendAbilityMessage(LanguageManager.getText("Psycho","defenceMessage"),
                    getRoleOwner());
            return false;
        }
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(100,1);
    }
}
