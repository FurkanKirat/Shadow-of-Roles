package com.rolegame.game.Roles.CorrupterRole.Support;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class LastJoke extends CorrupterRole {
    private boolean didUsedAbility;
    public LastJoke() {
        super(RoleID.LastJoke, RolePriority.None, RoleCategory.CorrupterSupport,
                LanguageManager.getText("LastJoke.name"),
                LanguageManager.getText("LastJoke.attributes"),
                LanguageManager.getText("LastJoke.abilities"),3, 0);
        this.didUsedAbility = false;
    }

    @Override
    public boolean performAbility() {

        if(!didUsedAbility && !getRoleOwner().isAlive()){
            didUsedAbility = true;

            if(getChoosenPlayer()==null){
                return false;
            }
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath.lastJoke"));
            Message.sendMessage(this.getChoosenPlayer().getName() + " "+ LanguageManager.getText("LastJoke.slainMessage"), getRoleOwner(),true);

            return true;
        }

        return false;
    }

    public boolean isDidUsedAbility() {
        return didUsedAbility;
    }
}


