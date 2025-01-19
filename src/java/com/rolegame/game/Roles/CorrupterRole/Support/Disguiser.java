package com.rolegame.game.Roles.CorrupterRole.Support;

import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleCatalog;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Disguiser extends CorrupterRole implements ActiveNightAbility {
    public Disguiser() {
        super(RoleID.Disguiser, RolePriority.None, RoleCategory.CorrupterSupport, 0, 0);
    }

    @Override
    public boolean executeAbility() {
        Role currentRole = RoleCatalog.getRandomRole(new Disguiser());
        currentRole.setChoosenPlayer(this.getChoosenPlayer());
        currentRole.setRoleOwner(this.getRoleOwner());
        return currentRole.performAbility();
    }
}
