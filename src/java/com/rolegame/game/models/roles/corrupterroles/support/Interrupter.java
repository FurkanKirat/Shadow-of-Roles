package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public final class Interrupter extends CorrupterRole implements ActiveNightAbility {
    public Interrupter() {
        super(RoleID.Interrupter, RolePriority.ROLE_BLOCK, RoleCategory.CORRUPTER_SUPPORT, 0, 0);
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","RBimmuneMessage") ,getRoleOwner());
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","immuneMessage") ,getRoleOwner());
            return false;
        }

        return executeAbility();

    }

    @Override
    public boolean executeAbility() {
        sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockMessage"), getRoleOwner());
        getChoosenPlayer().getRole().setCanPerform(false);
        return true;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(30,10);
    }
}
