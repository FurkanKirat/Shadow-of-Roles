package com.rolegame.game.Roles.FolkRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Observer extends FolkRole {
    public Observer() {
        super(RoleID.Observer, RolePriority.None, RoleCategory.FolkAnalyst,LanguageManager.getText("Observer.name"),
                LanguageManager.getText("Observer.attributes"), LanguageManager.getText("Observer.abilities"),0,0);
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


        Message.sendMessage(LanguageManager.getText("Observer.message")+": " +
                        this.getChoosenPlayer().getRole().getTeam(), getRoleOwner(),false);
        return true;
    }
}
