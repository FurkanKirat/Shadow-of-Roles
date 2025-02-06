package com.rolegame.game.models.roles.folkroles.protector;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;

public final class FolkHero extends FolkRole implements ActiveNightAbility {

    private int abilityUseCount;

    public FolkHero() {
        super(RoleID.FolkHero, RolePriority.FOLK_HERO, RoleCategory.FOLK_PROTECTOR, 0, 0);
        abilityUseCount = 0;
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockedMessage") ,roleOwner);
            return false;
        }

        return executeAbility();
    }

    @Override
    public boolean executeAbility() {
        if(abilityUseCount<=2){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","abilityMessage") ,roleOwner);
            choosenPlayer.setImmune(true);
            abilityUseCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean isRoleBlockImmune() {
        return false;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(5,1);
    }

    public int getAbilityUseCount() {
        return abilityUseCount;
    }
}
