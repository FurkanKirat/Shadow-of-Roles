package com.rolegame.game.models.roles.corrupterroles;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RolePriority;
import com.rolegame.game.models.roles.enums.Team;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.Role;

public abstract class CorrupterRole extends Role {
    public CorrupterRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory, double attack , double defence) {
        super(id, rolePriority, roleCategory, Team.CORRUPTER, attack, defence);
    }

    @Override
    public String getGoal() {
        return LanguageManager.getText("CorrupterRole","goal");
    }
}
