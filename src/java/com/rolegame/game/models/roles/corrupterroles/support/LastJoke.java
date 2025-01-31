package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public final class LastJoke extends CorrupterRole {
    private boolean didUsedAbility;
    public LastJoke() {
        super(RoleID.LastJoke, RolePriority.LAST_JOKE, RoleCategory.CORRUPTER_SUPPORT, 3, 0);
        this.didUsedAbility = false;
    }

    @Override
    public boolean performAbility() {
        return executeAbility();
    }

    @Override
    public boolean executeAbility() {
        if(!didUsedAbility && !getRoleOwner().isAlive()){
            didUsedAbility = true;

            if(getChoosenPlayer()==null){
                return false;
            }
            choosenPlayer.setAlive(false);
            choosenPlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath","lastJoke"));
            sendAbilityAnnouncement(LanguageManager.getText("LastJoke","slainMessage")
                    .replace("{playerName}", choosenPlayer.getName()));

            return true;
        }

        return false;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(5,1);
    }

    public boolean isDidUsedAbility() {
        return didUsedAbility;
    }
}


