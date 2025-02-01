package com.rolegame.game.models.roles.neutralroles.chaos;

import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.models.roles.interfaces.NoNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;


public final class ChillGuy extends NeutralRole implements NoNightAbility {
    public ChillGuy() {
        super(RoleID.ChillGuy, RolePriority.NONE, RoleCategory.NEUTRAL_CHAOS, 0, 0);

    }

    @Override
    public boolean performAbility() {
        return false;
    }

    @Override
    public boolean executeAbility() {
        return false;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(10,1);
    }


    @Override
    public boolean canWinWithOtherTeams() {
        return true;
    }
}
