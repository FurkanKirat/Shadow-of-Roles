package com.rolegame.game.Roles.NeutralRole;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

public abstract class NeutralRole extends Role {
    public NeutralRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory,
                       double attack, double defence, ChanceProperty chanceProperty) {
        super(id, rolePriority, roleCategory, Team.NEUTRAL, LanguageManager.getText(id.toString() ,"goal"),
                attack, defence, chanceProperty);
    }
}
