package com.rolegame.game.models.roles.folkroles.analyst;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.FolkRole;

public final class Stalker extends FolkRole {

    public Stalker() {
        super(RoleID.Stalker, AbilityType.ACTIVE_OTHERS,
                RolePriority.NONE, RoleCategory.FOLK_ANALYST, 0, 0);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        String message;
        if(choosenPlayer.getRole().getChoosenPlayer()==null||!choosenPlayer.getRole().isCanPerform()){
            message = LanguageManager.getText("Stalker","nobodyMessage");
        }
        else{
            message = LanguageManager.getText("Stalker","visitMessage")
                    .replace("{playerName}", choosenPlayer.getRole().getChoosenPlayer().getName());
        }

        sendAbilityMessage(message,roleOwner);
        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(25,10);
    }
}
