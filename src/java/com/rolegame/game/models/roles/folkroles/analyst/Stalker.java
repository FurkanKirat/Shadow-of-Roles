package com.rolegame.game.models.roles.folkroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;

public final class Stalker extends FolkRole implements ActiveNightAbility {

    public Stalker() {
        super(RoleID.Stalker, RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0, 0);
    }

    @Override
    public boolean executeAbility() {
        String message = getChoosenPlayer().getRole().getChoosenPlayer()==null ?
                LanguageManager.getText("Stalker","nobodyMessage"):
                LanguageManager.getText("Stalker","visitMessage")
                        .replace("{playerName}", choosenPlayer.getRole().getChoosenPlayer().getName());
        sendAbilityMessage(message,roleOwner);
        return true;
    }

    @Override
    public boolean isRoleBlockImmune() {
        return false;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(25,10);
    }
}
