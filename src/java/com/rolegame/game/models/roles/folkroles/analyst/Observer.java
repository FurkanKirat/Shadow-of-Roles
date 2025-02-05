package com.rolegame.game.models.roles.folkroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;

public final class Observer extends FolkRole implements ActiveNightAbility {
    public Observer() {
        super(RoleID.Observer, RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0,0);
    }

    @Override
    public boolean executeAbility() {
        sendAbilityMessage(LanguageManager.getText("Observer","abilityMessage")
                .replace("{teamName}",this.getChoosenPlayer().getRole().getTeam().name())
                ,getRoleOwner());
        return true;
    }

    @Override
    public boolean isRoleBlockImmune() {
        return false;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(20,10);
    }
}
