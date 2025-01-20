package com.rolegame.game.Roles.CorrupterRole.Support;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class LastJoke extends CorrupterRole {
    private boolean didUsedAbility;
    public LastJoke() {
        super(RoleID.LastJoke, RolePriority.LastJoke, RoleCategory.CorrupterSupport, 3, 0);
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
            choosenPlayer.setCauseOfDeath(LanguageManager.getText("CauseOfDeath.lastJoke"));
            Message.sendMessage(LanguageManager.getText("LastJoke.slainMessage")
                    .replace("{playerName}", choosenPlayer.getName()), getRoleOwner(),true);

            return true;
        }

        return false;
    }

    public boolean isDidUsedAbility() {
        return didUsedAbility;
    }
}


