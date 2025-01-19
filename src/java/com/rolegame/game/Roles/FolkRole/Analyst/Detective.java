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

        String message =
                LanguageManager.getText("Detective.role")+": " +
                        (new Random().nextBoolean() ? getChoosenPlayer().getRole().getName() + " " + LanguageManager.getText("Detective.or") + " " + randRole.getName() :
                                randRole.getName() + " " + LanguageManager.getText("Detective.or") + " " + getChoosenPlayer().getRole().getName());
        Message.sendMessage(message,getRoleOwner(),false);

        return true;
    }

}
