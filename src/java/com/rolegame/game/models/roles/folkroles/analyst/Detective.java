package com.rolegame.game.models.roles.folkroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.templates.RoleTemplate;
import com.rolegame.game.services.RoleService;

import java.util.Random;

public final class Detective extends FolkRole{
    public Detective() {
        super(RoleID.Detective, AbilityType.ACTIVE_OTHERS, RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0,0);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {

        RoleTemplate randRole = RoleService.getRandomRole(choosenPlayer.getRole().getTemplate());

        boolean firstIsChosen = new Random().nextBoolean();
        String roleName1 = firstIsChosen ? choosenPlayer.getRole().getTemplate().getName() : randRole.getName();
        String roleName2 = firstIsChosen ? randRole.getName() : choosenPlayer.getRole().getTemplate().getName();

        String message = LanguageManager.getText("Detective","abilityMessage")
                .replace("{roleName1}", roleName1)
                .replace("{roleName2}", roleName2);

        sendAbilityMessage(message, roleOwner);

        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(25,10);
    }


}
