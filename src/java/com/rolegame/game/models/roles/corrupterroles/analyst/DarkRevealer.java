package com.rolegame.game.models.roles.corrupterroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.enums.*;

public final class DarkRevealer extends CorrupterRole{
    public DarkRevealer() {
        super(RoleID.DarkRevealer,  AbilityType.OTHER_THAN_CORRUPTER
                ,RolePriority.NONE, RoleCategory.CORRUPTER_ANALYST, 0, 0);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {

        String message = LanguageManager.getText("DarkRevealer","abilityMessage").replace("{roleName}",choosenPlayer.getRole().getTemplate().getName());
        sendAbilityMessage(message,roleOwner);

        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(30,10);
    }
}
