package com.rolegame.game.Roles.CorrupterRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class DarkRevealer extends CorrupterRole implements ActiveNightAbility {
    public DarkRevealer() {
        super(RoleID.DarkRevealer, RolePriority.None, RoleCategory.CorrupterAnalyst , 0, 0);
    }

    @Override
    public boolean performAbility() {

        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.roleBlockedMessage"),getRoleOwner(),false);
            return false;
        }

        if(getChoosenPlayer()==null){
            return false;
        }
        String message = LanguageManager.getText("DarkRevealer.abilityMessage")+": " + getChoosenPlayer().getRole().getName();
        Message.sendMessage(message,getRoleOwner(), false);


        return true;
    }
}
