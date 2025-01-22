package com.rolegame.game.Roles.NeutralRole.Chaos;

import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.RoleProperties.NoNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;


public class ChillGuy extends NeutralRole implements NoNightAbility {
    public ChillGuy() {
        super(RoleID.SimplePerson, RolePriority.NONE, RoleCategory.NEUTRAL_CHAOS, 0, 0
        ,new ChanceProperty(10,1));

    }

    @Override
    public boolean performAbility() {
        return false;
    }

    @Override
    public boolean executeAbility() {
        return false;
    }


}
