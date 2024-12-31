package com.rolegame.game.Roles.FolkRole;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;
import com.rolegame.game.Roles.Role;

public abstract class FolkRole extends Role {
    public FolkRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory, String name, String attributes, String abilities, double attack , double defence) {
        super(id, rolePriority, roleCategory, name, attributes, Team.Folk, abilities, LanguageManager.getText("FolkRole.goal"),attack,defence);
    }

}
