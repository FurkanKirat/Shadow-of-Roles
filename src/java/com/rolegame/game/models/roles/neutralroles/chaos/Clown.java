package com.rolegame.game.models.roles.neutralroles.chaos;

import com.rolegame.game.models.roles.neutralroles.NeutralRole;
import com.rolegame.game.models.roles.roleproperties.NoNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public final class Clown extends NeutralRole implements NoNightAbility {
    public Clown() {
        super(RoleID.Clown, RolePriority.NONE, RoleCategory.NEUTRAL_CHAOS, 0, 0);
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
        return new ChanceProperty(30,1);
    }

    @Override
    public boolean canWinWithOtherTeams() {
        return true;
    }
}
