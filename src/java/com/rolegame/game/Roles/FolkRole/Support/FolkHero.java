package com.rolegame.game.Roles.FolkRole.Support;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class FolkHero extends FolkRole implements ActiveNightAbility {

    private int abilityUseCount;

    public FolkHero() {
        super(RoleID.FolkHero, RolePriority.FolkHero, RoleCategory.FolkSupport, 0, 0);
        abilityUseCount = 0;
    }

    @Override
    public Role createCopy() {
        return new FolkHero();
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        abilityUseCount++;

        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.roleBlockedMessage") ,getRoleOwner(),false);
            return false;
        }

        if(abilityUseCount<=2){
            getChoosenPlayer().setImmune(true);
            return true;
        }
        return false;
    }
}
