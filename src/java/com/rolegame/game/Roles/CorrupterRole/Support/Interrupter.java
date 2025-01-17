package com.rolegame.game.Roles.CorrupterRole.Support;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Interrupter extends CorrupterRole implements ActiveNightAbility {
    public Interrupter() {
        super(RoleID.Interrupter, RolePriority.Extreme, RoleCategory.CorrupterSupport,
                LanguageManager.getText("Interrupter.name"),
                LanguageManager.getText("Interrupter.attributes"),
                LanguageManager.getText("Interrupter.abilities"), 0, 0);
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.immuneMessage") ,getRoleOwner(),false);
        }

        Message.sendMessage(LanguageManager.getText("RoleBlock.roleBlockMessage"), getRoleOwner(),false);
        getChoosenPlayer().getRole().setCanPerform(false);
        return true;
    }
}
