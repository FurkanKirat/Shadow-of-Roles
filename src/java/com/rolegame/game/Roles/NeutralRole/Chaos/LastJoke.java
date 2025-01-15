package com.rolegame.game.Roles.NeutralRole.Chaos;

import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

public class LastJoke extends NeutralRole {
    public LastJoke(RoleID id, RolePriority rolePriority, RoleCategory roleCategory, String name, String attributes, Team team, String abilities, String goal, double attack, double defence) {
        super(RoleID.LastJoke, rolePriority, roleCategory, name, attributes, abilities, goal, attack, defence);
    }

    @Override
    public boolean performAbility() {
        return false;
    }
}


