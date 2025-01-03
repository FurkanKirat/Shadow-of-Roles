package com.rolegame.game.Roles.FolkRole.Protector;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Soulbinder extends FolkRole {
    public Soulbinder() {
        super(RoleID.SoulBinder, RolePriority.Low, RoleCategory.FolkProtector,
                LanguageManager.getText("Soulbinder.name"), LanguageManager.getText("Soulbinder.attributes"),
                LanguageManager.getText("Soulbinder.abilities"),0,0);
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

        this.getChoosenPlayer().setDefence(this.getChoosenPlayer().getDefence()+1);
        return true;
    }
}
