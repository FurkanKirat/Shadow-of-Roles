package com.rolegame.game.models.roles.folkroles.protector;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.FolkRole;

public final class FolkHero extends FolkRole {

    private int abilityUseCount;

    public FolkHero() {
        super(RoleID.FolkHero, AbilityType.ACTIVE_ALL, RolePriority.FOLK_HERO, RoleCategory.FOLK_PROTECTOR, 0, 0);
        abilityUseCount = 0;
    }

    @Override
    public AbilityResult performAbility(Player roleOwner, Player choosenPlayer) {

        if(choosenPlayer==null){
            return AbilityResult.NO_ONE_SELECTED;
        }

        if(!roleOwner.getRole().isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockedMessage") ,roleOwner);
            return AbilityResult.ROLE_BLOCKED;
        }

        return executeAbility(roleOwner, choosenPlayer);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        if(abilityUseCount<=2){
            sendAbilityMessage(LanguageManager.getText("FolkHero","abilityMessage") ,roleOwner);
            choosenPlayer.setImmune(true);
            abilityUseCount++;
            return AbilityResult.SUCCESSFUL;
        }
        return AbilityResult.NO_ABILITY_USE_LEFT;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(25,1);
    }

    public int getAbilityUseCount() {
        return abilityUseCount;
    }
}
