package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;

public final class Interrupter extends CorrupterRole implements ActiveNightAbility {
    public Interrupter() {
        super(RoleID.Interrupter, RolePriority.ROLE_BLOCK, RoleCategory.CORRUPTER_SUPPORT, 0, 0);
    }

    @Override
    public boolean performAbility() {

        return performAbilityForBlockImmuneRoles();

    }

    @Override
    public boolean executeAbility() {
        sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockMessage"), getRoleOwner());
        choosenPlayer.getRole().setCanPerform(false);

        return true;
    }

    @Override
    public boolean isRoleBlockImmune() {
        return true;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(30,10);
    }
}
