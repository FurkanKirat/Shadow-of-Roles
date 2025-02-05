package com.rolegame.game.models.roles.folkroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.RoleCatalog;
import com.rolegame.game.models.roles.enums.RolePriority;

import java.util.Random;

public final class Detective extends FolkRole implements ActiveNightAbility {
    public Detective() {
        super(RoleID.Detective, RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0,0);
    }

    @Override
    public boolean executeAbility() {

        Role randRole = RoleCatalog.getRandomRole(getChoosenPlayer().getRole());

        boolean firstIsChosen = new Random().nextBoolean();
        String roleName1 = firstIsChosen ? getChoosenPlayer().getRole().getName() : randRole.getName();
        String roleName2 = firstIsChosen ? randRole.getName() : getChoosenPlayer().getRole().getName();

        String message = LanguageManager.getText("Detective","abilityMessage")
                .replace("{roleName1}", roleName1)
                .replace("{roleName2}", roleName2);

        sendAbilityMessage(message, getRoleOwner());

        return true;
    }

    @Override
    public boolean isRoleBlockImmune() {
        return false;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(30,10);
    }


}
