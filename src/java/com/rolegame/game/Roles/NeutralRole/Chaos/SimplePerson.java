package com.rolegame.game.Roles.NeutralRole.Chaos;

import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.NoNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class SimplePerson extends NeutralRole implements NoNightAbility {
    public SimplePerson() {
        super(RoleID.SimplePerson, RolePriority.None, RoleCategory.NeutralChaos, 0, 0);
    }

    @Override
    public Role createCopy() {
        return new SimplePerson();
    }

    @Override
    public boolean performAbility() {
        return false;
    }

}
