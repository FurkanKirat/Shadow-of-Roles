package com.rolegame.game.models.roles.neutralroles.chaos;

import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.models.roles.roleproperties.NoNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;


public class ChillGuy extends NeutralRole implements NoNightAbility {
    public ChillGuy() {
        super(RoleID.ChillGuy, RolePriority.NONE, RoleCategory.NEUTRAL_CHAOS, 0, 0
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
