package com.rolegame.game.models.roles.folkroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.FolkRole;

public final class Observer extends FolkRole {
    public Observer() {
        super(RoleID.Observer, AbilityType.ACTIVE_OTHERS, RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0,0);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        sendAbilityMessage(LanguageManager.getText("Observer","abilityMessage")
                .replace("{teamName}", choosenPlayer.getRole().getTemplate().getTeam().name())
                ,roleOwner);
        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(20,10);
    }
}
