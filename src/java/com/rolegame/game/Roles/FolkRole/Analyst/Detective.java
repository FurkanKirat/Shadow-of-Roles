package com.rolegame.game.Roles.FolkRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleCatalog;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

import java.util.Random;

public class Detective extends FolkRole implements ActiveNightAbility {
    public Detective() {
        super(RoleID.Detective, RolePriority.None, RoleCategory.FolkAnalyst, 0,0);
    }

    @Override
    public boolean executeAbility() {

        Role randRole = RoleCatalog.getRandomRole(getChoosenPlayer().getRole());

        boolean firstIsChoosen = new Random().nextBoolean();
        String roleName1 = firstIsChoosen ? getChoosenPlayer().getRole().getName() : randRole.getName();
        String roleName2 = firstIsChoosen ? randRole.getName() : getChoosenPlayer().getRole().getName();

        String message = LanguageManager.getText("Detective.abilityMessage")
                .replace("{roleName1}", roleName1)
                .replace("{roleName2}", roleName2);

        Message.sendMessage(message, getRoleOwner(), false);

        return true;
    }


}
