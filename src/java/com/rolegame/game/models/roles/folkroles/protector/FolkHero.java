package com.rolegame.game.models.roles.folkroles.protector;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public class FolkHero extends FolkRole implements ActiveNightAbility {

    private int abilityUseCount;

    public FolkHero() {
        super(RoleID.FolkHero, RolePriority.FOLK_HERO, RoleCategory.FOLK_PROTECTOR, 0, 0,
                new ChanceProperty(5,1));
        abilityUseCount = 0;
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        abilityUseCount++;

        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockedMessage") ,roleOwner,false);
            return false;
        }

        return executeAbility();
    }

    @Override
    public boolean executeAbility() {
        if(abilityUseCount<=2){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","abilityMessage") ,roleOwner,false);
            choosenPlayer.setImmune(true);
            return true;
        }
        return false;
    }

    public int getAbilityUseCount() {
        return abilityUseCount;
    }
}
