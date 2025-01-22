package com.rolegame.game.Roles.FolkRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Stalker extends FolkRole implements ActiveNightAbility {

    public Stalker() {
        super(RoleID.Stalker, RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0, 0,
                new ChanceProperty(25,10));
    }

    @Override
    public boolean executeAbility() {
        String message = getChoosenPlayer().getRole().getChoosenPlayer()==null ?
                LanguageManager.getText("Stalker.nobodyMessage"):
                LanguageManager.getText("Stalker.visitMessage")
                        .replace("playerName", choosenPlayer.getRole().getChoosenPlayer().getName());
        sendAbilityMessage(message,roleOwner,false);
        return true;
    }
}
