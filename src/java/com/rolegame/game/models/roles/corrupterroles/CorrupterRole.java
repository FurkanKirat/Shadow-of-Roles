package com.rolegame.game.models.roles.corrupterroles;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RolePriority;
import com.rolegame.game.models.roles.roleproperties.Team;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.Role;

public abstract class CorrupterRole extends Role {
    public CorrupterRole(RoleID id, RolePriority rolePriority, RoleCategory roleCategory, double attack , double defence, ChanceProperty chanceProperty) {
        super(id, rolePriority, roleCategory, Team.CORRUPTER, LanguageManager.getText("CorrupterRole","goal"), attack, defence, chanceProperty);
    }
}
