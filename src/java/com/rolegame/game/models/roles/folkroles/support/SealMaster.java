package com.rolegame.game.models.roles.folkroles.support;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

public final class SealMaster extends FolkRole implements ActiveNightAbility {
    public SealMaster() {
        super(RoleID.SealMaster, RolePriority.ROLE_BLOCK, RoleCategory.FOLK_SUPPORT, 0,0
        , new ChanceProperty(30,10));
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","RBimmuneMessage") ,roleOwner,false);
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","immuneMessage") ,roleOwner,false);
            return false;
        }
        return executeAbility();
    }

    @Override
    public boolean executeAbility() {

        sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockMessage"), roleOwner,false);
        choosenPlayer.getRole().setCanPerform(false);
        return true;
    }
}
