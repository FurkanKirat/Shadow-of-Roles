package com.rolegame.game.Roles.NeutralRole;

import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

public abstract class NeutralRole extends Role {
    public NeutralRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory, String name, String attributes, String abilities, String goal, double attack, double defence) {
        super(id, rolePriority, roleCategory, name, attributes, Team.Neutral, abilities, goal, attack, defence);
    }
}
