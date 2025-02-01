package com.rolegame.game.models.roles.folkroles.support;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.interfaces.ActiveNightAbility;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;

public final class SealMaster extends FolkRole implements ActiveNightAbility {
    public SealMaster() {
        super(RoleID.SealMaster, RolePriority.ROLE_BLOCK, RoleCategory.FOLK_SUPPORT, 0,0);
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","RBimmuneMessage") ,roleOwner);
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","immuneMessage") ,roleOwner);
            return false;
        }
        return executeAbility();
    }

    @Override
    public boolean executeAbility() {

        sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockMessage"), roleOwner);
        choosenPlayer.getRole().setCanPerform(false);
        return true;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(30,10);
    }
}
