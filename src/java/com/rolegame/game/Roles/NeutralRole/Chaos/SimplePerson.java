package com.rolegame.game.Roles.NeutralRole.Chaos;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

public class SimplePerson extends NeutralRole {
    public SimplePerson() {
        super(RoleID.SimplePerson, RolePriority.None, RoleCategory.NeutralChaos, LanguageManager.getText("SimplePerson.name"),
                LanguageManager.getText("SimplePerson.attributes"),
                Team.Neutral, LanguageManager.getText("SimplePerson.abilities"), LanguageManager.getText("SimplePerson.goal"),  0, 0);
    }

    @Override
    public boolean performAbility() {
        return false;
    }
}
