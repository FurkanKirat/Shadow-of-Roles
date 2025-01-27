package com.rolegame.game.models.roles.corrupterroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public final class DarkRevealer extends CorrupterRole implements ActiveNightAbility {
    public DarkRevealer() {
        super(RoleID.DarkRevealer, RolePriority.NONE, RoleCategory.CORRUPTER_ANALYST,
                0, 0, new ChanceProperty(30,10));
    }

    @Override
    public boolean executeAbility() {

        String message = LanguageManager.getText("DarkRevealer","abilityMessage").replace("{roleName}",choosenPlayer.getRole().getName());
        sendAbilityMessage(message,getRoleOwner(), false);

        return true;
    }
}
