package com.rolegame.game.Roles.CorrupterRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class DarkRevealer extends CorrupterRole implements ActiveNightAbility {
    public DarkRevealer() {
        super(RoleID.DarkRevealer, RolePriority.NONE, RoleCategory.CORRUPTER_ANALYST, 0, 0);
    }

    @Override
    public boolean executeAbility() {

        String message = LanguageManager.getText("DarkRevealer.abilityMessage").replace("{roleName}",choosenPlayer.getRole().getName());
        sendAbilityMessage(message,getRoleOwner(), false);

        return true;
    }
}
