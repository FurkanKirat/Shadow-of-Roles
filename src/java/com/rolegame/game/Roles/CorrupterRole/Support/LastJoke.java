package com.rolegame.game.Roles.CorrupterRole.Support;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

public class LastJoke extends CorrupterRole {
    public LastJoke(RoleID id, RolePriority rolePriority, RoleCategory roleCategory, String name, String attributes, Team team, String abilities, String goal, double attack, double defence) {
        super(RoleID.LastJoke, RolePriority.None, RoleCategory.CorrupterSupport, LanguageManager.getText("LastJoke.name"),
                LanguageManager.getText("LastJoke.attributes"), LanguageManager.getText("LastJoke.abilities"),3, 0);
    }

    @Override
    public boolean performAbility() {
        if(getChoosenPlayer()==null){
            return false;
        }




        return false;
    }
}


