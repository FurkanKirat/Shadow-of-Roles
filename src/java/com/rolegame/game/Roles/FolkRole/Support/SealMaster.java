package com.rolegame.game.Roles.FolkRole.Support;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class SealMaster extends FolkRole implements ActiveNightAbility {
    public SealMaster() {
        super(RoleID.SealMaster, RolePriority.Roleblock, RoleCategory.FolkSupport, 0,0);
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.RBimmuneMessage") ,getRoleOwner(),false);
        }

        if(choosenPlayer.isImmune()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.immuneMessage") ,getRoleOwner(),false);
            return false;
        }

        Message.sendMessage(LanguageManager.getText("RoleBlock.roleBlockMessage"), getRoleOwner(),false);
        getChoosenPlayer().getRole().setCanPerform(false);
        return true;
    }
}
