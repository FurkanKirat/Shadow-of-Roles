package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public class LastJoke extends CorrupterRole {
    private boolean didUsedAbility;
    public LastJoke() {
        super(RoleID.LastJoke, RolePriority.LAST_JOKE, RoleCategory.CORRUPTER_SUPPORT, 3, 0,
                new ChanceProperty(5,1));
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
            sendAbilityMessage(LanguageManager.getText("LastJoke","slainMessage")
                    .replace("{playerName}", choosenPlayer.getName()), getRoleOwner(),true);

            return true;
        }

        return false;
    }

    public boolean isDidUsedAbility() {
        return didUsedAbility;
    }
}


