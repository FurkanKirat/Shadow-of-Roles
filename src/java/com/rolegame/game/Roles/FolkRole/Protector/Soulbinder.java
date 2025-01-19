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
    public boolean executeAbility() {
        Message.sendMessage(LanguageManager.getText("RoleBlock.abilityMessage") ,roleOwner,false);
        this.choosenPlayer.setDefence(this.choosenPlayer.getDefence()+1);
        return true;
    }
}
