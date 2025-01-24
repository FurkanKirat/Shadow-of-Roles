package com.rolegame.game.Roles.FolkRole.Protector;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Soulbinder extends FolkRole implements ActiveNightAbility {
    public Soulbinder() {
        super(RoleID.Soulbinder, RolePriority.SOULBINDER, RoleCategory.FOLK_PROTECTOR,
                0,0, new ChanceProperty(20,10));
    }

    @Override
    public boolean executeAbility() {
        sendAbilityMessage(LanguageManager.getText("Soulbinder","abilityMessage") ,roleOwner,false);
        this.choosenPlayer.setDefence(this.choosenPlayer.getDefence()+1);
        return true;
    }
}
