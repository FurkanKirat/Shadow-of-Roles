package com.rolegame.game.models.roles.folkroles;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;
import com.rolegame.game.models.roles.roleproperties.Team;
import com.rolegame.game.models.roles.Role;

public abstract class FolkRole extends Role {
    public FolkRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory,
                    double attack , double defence, ChanceProperty chanceProperty) {
        super(id, rolePriority, roleCategory, Team.FOLK, attack, defence, chanceProperty);
    }

    @Override
    public String getGoal() {
        return LanguageManager.getText("FolkRole","goal");
    }
}
