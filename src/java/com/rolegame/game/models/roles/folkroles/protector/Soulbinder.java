package com.rolegame.game.models.roles.folkroles.protector;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.FolkRole;

public final class Soulbinder extends FolkRole{
    public Soulbinder() {
        super(RoleID.Soulbinder, AbilityType.ACTIVE_OTHERS, RolePriority.SOULBINDER, RoleCategory.FOLK_PROTECTOR, 0,0);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        sendAbilityMessage(LanguageManager.getText("Soulbinder","abilityMessage") ,roleOwner);
        choosenPlayer.setDefence(choosenPlayer.getDefence()+1);
        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(20,10);
    }
}
