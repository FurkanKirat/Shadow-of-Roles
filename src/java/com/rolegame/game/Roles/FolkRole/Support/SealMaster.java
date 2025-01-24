package com.rolegame.game.Roles.FolkRole.Support;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class SealMaster extends FolkRole implements ActiveNightAbility {
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
