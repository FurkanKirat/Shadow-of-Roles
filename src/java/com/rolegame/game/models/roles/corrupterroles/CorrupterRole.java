package com.rolegame.game.models.roles.corrupterroles;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.templates.RoleTemplate;

public abstract class CorrupterRole extends RoleTemplate {
    public CorrupterRole(RoleID id, AbilityType abilityType,
                         RolePriority rolePriority, RoleCategory roleCategory, double attack , double defence) {
        super(id, abilityType, rolePriority, roleCategory, Team.CORRUPTER, attack, defence);
    }

    @Override
    public String getGoal() {
        return LanguageManager.getText("CorrupterRole","goal");
    }
}
