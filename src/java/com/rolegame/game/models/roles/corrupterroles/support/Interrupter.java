package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.enums.*;

public final class Interrupter extends CorrupterRole {
    public Interrupter() {
        super(RoleID.Interrupter, AbilityType.ACTIVE_OTHERS,
                RolePriority.ROLE_BLOCK, RoleCategory.CORRUPTER_SUPPORT, 0, 0);
    }

    @Override
    public AbilityResult performAbility(Player roleOwner, Player choosenPlayer) {

        return performAbilityForBlockImmuneRoles(roleOwner, choosenPlayer);

    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        choosenPlayer.getRole().setCanPerform(false);

        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(30,10);
    }
}
