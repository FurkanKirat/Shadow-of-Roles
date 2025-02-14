package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.unique.Entrepreneur;
import com.rolegame.game.models.roles.neutralroles.chaos.ChillGuy;
import com.rolegame.game.models.roles.neutralroles.chaos.Clown;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.templates.RoleTemplate;
import com.rolegame.game.services.GameService;
import com.rolegame.game.services.RoleService;

import java.util.ArrayList;
import java.util.Random;

public final class Disguiser extends CorrupterRole {
    public Disguiser() {
        super(RoleID.Disguiser, AbilityType.ACTIVE_ALL, RolePriority.NONE, RoleCategory.CORRUPTER_SUPPORT, 0, 0, false);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer, GameService gameService) {
        RoleTemplate template = getRandomRole();

        return template.executeAbility(roleOwner, choosenPlayer, gameService);
    }

    private RoleTemplate getRandomRole(){
        ArrayList<RoleTemplate> possibleRoles = new ArrayList<>(RoleService.getAllRoles());
        possibleRoles.remove(new Disguiser());
        possibleRoles.remove(new Entrepreneur());
        possibleRoles.remove(new ChillGuy());
        possibleRoles.remove(new Lorekeeper());
        possibleRoles.remove(new Clown());
        possibleRoles.remove(new LastJoke());
        return possibleRoles.get(new Random().nextInt(possibleRoles.size())).copy();
    }
    
    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(15,10);
    }
}
