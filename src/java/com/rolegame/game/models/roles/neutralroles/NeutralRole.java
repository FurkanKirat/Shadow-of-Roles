package com.rolegame.game.models.roles.neutralroles;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.templates.RoleTemplate;

public abstract class NeutralRole extends RoleTemplate {
    public NeutralRole(RoleID id, AbilityType abilityType, RolePriority rolePriority, RoleCategory roleCategory,
                       double attack, double defence) {
        super(id, abilityType, rolePriority, roleCategory, Team.NEUTRAL, attack, defence);
    }

    @Override
    public String getGoal() {
        return LanguageManager.getText(id.toString() ,"goal");
    }

    public abstract boolean canWinWithOtherTeams();
}
