package com.rolegame.game.models.roles.folkroles;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;
import com.rolegame.game.models.roles.enums.Team;
import com.rolegame.game.models.roles.Role;

public abstract class FolkRole extends Role {
    public FolkRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory,
                    double attack , double defence) {
        super(id, rolePriority, roleCategory, Team.FOLK, attack, defence);
    }

    @Override
    public String getGoal() {
        return LanguageManager.getText("FolkRole","goal");
    }
}
