package com.rolegame.game.Roles.FolkRole.Protector;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Soulbinder extends FolkRole implements ActiveNightAbility {
    public Soulbinder() {
        super(RoleID.Soulbinder, RolePriority.Soulbinder, RoleCategory.FolkProtector, 0,0);
    }

    @Override
    public Role createCopy() {
        return new Soulbinder();
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

        if(choosenPlayer.isImmune()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.immuneMessage") ,getRoleOwner(),false);
            return false;
        }

        this.getChoosenPlayer().setDefence(this.getChoosenPlayer().getDefence()+1);
        return true;
    }
}
