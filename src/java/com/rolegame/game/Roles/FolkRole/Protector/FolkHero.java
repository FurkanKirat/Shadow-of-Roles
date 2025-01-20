package com.rolegame.game.Roles.FolkRole.Protector;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class FolkHero extends FolkRole implements ActiveNightAbility {

    private int abilityUseCount;

    public FolkHero() {
        super(RoleID.FolkHero, RolePriority.FolkHero, RoleCategory.FolkProtector, 0, 0);
        abilityUseCount = 0;
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        abilityUseCount++;

        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.roleBlockedMessage") ,roleOwner,false);
            return false;
        }

        return executeAbility();
    }

    @Override
    public boolean executeAbility() {
        if(abilityUseCount<=2){
            Message.sendMessage(LanguageManager.getText("RoleBlock.abilityMessage") ,roleOwner,false);
            choosenPlayer.setImmune(true);
            return true;
        }
        return false;
    }

    public int getAbilityUseCount() {
        return abilityUseCount;
    }
}
