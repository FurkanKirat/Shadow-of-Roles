package com.rolegame.game.models.roles.folkroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public class Stalker extends FolkRole implements ActiveNightAbility {

    public Stalker() {
        super(RoleID.Stalker, RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0, 0,
                new ChanceProperty(25,10));
    }

    @Override
    public boolean executeAbility() {
        String message = getChoosenPlayer().getRole().getChoosenPlayer()==null ?
                LanguageManager.getText("Stalker","nobodyMessage"):
                LanguageManager.getText("Stalker","visitMessage")
                        .replace("playerName", choosenPlayer.getRole().getChoosenPlayer().getName());
        sendAbilityMessage(message,roleOwner,false);
        return true;
    }
}
