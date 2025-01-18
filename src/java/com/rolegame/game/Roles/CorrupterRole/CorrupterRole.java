package com.rolegame.game.Roles.CorrupterRole;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.Role;

public abstract class CorrupterRole extends Role {
    public CorrupterRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory, double attack , double defence) {
        super(id, rolePriority, roleCategory, Team.Corrupter, LanguageManager.getText("CorrupterRole.goal"), attack, defence);
    }
}
