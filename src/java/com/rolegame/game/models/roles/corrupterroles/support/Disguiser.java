package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.RoleCatalog;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public final class Disguiser extends CorrupterRole implements ActiveNightAbility {
    public Disguiser() {
        super(RoleID.Disguiser, RolePriority.NONE, RoleCategory.CORRUPTER_SUPPORT, 0, 0,
                new ChanceProperty(15,10));
    }

    @Override
    public boolean executeAbility() {
        Role currentRole = RoleCatalog.getRandomRole(new Disguiser());
        currentRole.setChoosenPlayer(this.getChoosenPlayer());
        currentRole.setRoleOwner(this.getRoleOwner());
        return currentRole.performAbility();
    }
}
