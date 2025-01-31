package com.rolegame.game.models.roles.folkroles.protector;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public final class Soulbinder extends FolkRole implements ActiveNightAbility {
    public Soulbinder() {
        super(RoleID.Soulbinder, RolePriority.SOULBINDER, RoleCategory.FOLK_PROTECTOR, 0,0);
    }

    @Override
    public boolean executeAbility() {
        sendAbilityMessage(LanguageManager.getText("Soulbinder","abilityMessage") ,roleOwner);
        this.choosenPlayer.setDefence(this.choosenPlayer.getDefence()+1);
        return true;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(20,10);
    }
}
