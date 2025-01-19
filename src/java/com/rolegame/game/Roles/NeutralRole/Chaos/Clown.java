package com.rolegame.game.Roles.NeutralRole.Chaos;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.NoNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Clown extends NeutralRole implements NoNightAbility {
    public Clown() {
        super(RoleID.Clown, RolePriority.None, RoleCategory.NeutralChaos, 0, 0);
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
