package com.rolegame.game.Roles.FolkRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Observer extends FolkRole implements ActiveNightAbility {
    public Observer() {
        super(RoleID.Observer, RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0,0
        , new ChanceProperty(20,10));
    }

    @Override
    public boolean executeAbility() {
        sendAbilityMessage(LanguageManager.getText("Observer.abilityMessage")
                .replace("{teamName}",this.getChoosenPlayer().getRole().getTeam().name())
                ,getRoleOwner(),false);
        return true;
    }
}
