package com.rolegame.game.models.roles.neutralroles;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;
import com.rolegame.game.models.roles.roleproperties.Team;

public abstract class NeutralRole extends Role {
    public NeutralRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory,
                       double attack, double defence) {
        super(id, rolePriority, roleCategory, Team.NEUTRAL, attack, defence);
    }

    @Override
    public String getGoal() {
        return LanguageManager.getText(id.toString() ,"goal");
    }
}
