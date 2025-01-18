package com.rolegame.game.Roles.FolkRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Stalker extends FolkRole implements ActiveNightAbility {

    public Stalker() {
        super(RoleID.Stalker, RolePriority.None, RoleCategory.FolkAnalyst, 0, 0);
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

        String message = getChoosenPlayer().getRole().getChoosenPlayer()==null ?
                LanguageManager.getText("Stalker.nobodyMessage"):
                LanguageManager.getText("Stalker.visitMessage")+" " + getChoosenPlayer().getRole().getChoosenPlayer().getName()+ " " + LanguageManager.getText("Stalker.thisNight");
        Message.sendMessage(message,getRoleOwner(),false);
        return true;
    }
}
