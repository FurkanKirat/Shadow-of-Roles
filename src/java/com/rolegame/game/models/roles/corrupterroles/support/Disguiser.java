package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.templates.RoleTemplate;
import com.rolegame.game.services.GameService;
import com.rolegame.game.services.RoleService;

public final class Disguiser extends CorrupterRole {
    public Disguiser() {
        super(RoleID.Disguiser, AbilityType.ACTIVE_ALL, RolePriority.NONE, RoleCategory.CORRUPTER_SUPPORT, 0, 0, false);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer, GameService gameService) {
        RoleTemplate currentRole = RoleService.getRandomRole(new Disguiser());
        currentRole.executeAbility(roleOwner, choosenPlayer, gameService);
        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(15,10);
    }
}
