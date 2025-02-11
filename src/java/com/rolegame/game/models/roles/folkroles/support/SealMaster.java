package com.rolegame.game.models.roles.folkroles.support;

import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.FolkRole;

public final class SealMaster extends FolkRole{
    public SealMaster() {
        super(RoleID.SealMaster, AbilityType.ACTIVE_OTHERS, RolePriority.ROLE_BLOCK, RoleCategory.FOLK_SUPPORT, 0,0);
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
        return new ChanceProperty(25,10);
    }
}
