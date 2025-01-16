package com.rolegame.game.Roles.NeutralRole.Chaos;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Clown extends NeutralRole {
    public Clown() {
        super(RoleID.Clown, RolePriority.None, RoleCategory.NeutralChaos, LanguageManager.getText("Clown.name"),
                LanguageManager.getText("Clown.attributes"), LanguageManager.getText("Clown.abilities"), LanguageManager.getText("Clown.goal"), 0, 0);
    }

    @Override
    public boolean performAbility() {
        return false;
    }
}
