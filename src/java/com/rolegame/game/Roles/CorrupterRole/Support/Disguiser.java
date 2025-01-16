package com.rolegame.game.Roles.CorrupterRole.Support;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleCatalog;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Disguiser extends CorrupterRole {
    public Disguiser() {
        super(RoleID.Disguiser, RolePriority.None, RoleCategory.CorrupterSupport,
                LanguageManager.getText("Disguiser.name"),
                LanguageManager.getText("Disguiser.attributes"),
                LanguageManager.getText("Disguiser.abilities"), 0, 0);
    }

    @Override
    public boolean performAbility() {
        Role currentRole = RoleCatalog.getRandomRole(new Disguiser());
        currentRole.setChoosenPlayer(this.getChoosenPlayer());
        currentRole.setRoleOwner(this.getRoleOwner());
        return currentRole.performAbility();

    }
}
