package com.rolegame.game.models.roles.folkroles;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.templates.RoleTemplate;

public abstract class FolkRole extends RoleTemplate {
    public FolkRole(RoleID id, AbilityType abilityType, RolePriority rolePriority, RoleCategory roleCategory,
                    double attack , double defence) {
        super(id, abilityType, rolePriority, roleCategory, Team.FOLK, attack, defence);
    }

    @Override
    public String getGoal() {
        return LanguageManager.getText("FolkRole","goal");
    }
}
