package com.rolegame.game.Roles.CorrupterRole.Support;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Interrupter extends CorrupterRole implements ActiveNightAbility {
    public Interrupter() {
        super(RoleID.Interrupter, RolePriority.ROLE_BLOCK, RoleCategory.CORRUPTER_SUPPORT, 0, 0
        ,new ChanceProperty(30,10));
    }

    @Override
    public boolean performAbility() {

        if(getChoosenPlayer()==null){
            return false;
        }

        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","RBimmuneMessage") ,getRoleOwner(),false);
        }

        if(choosenPlayer.isImmune()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock","immuneMessage") ,getRoleOwner(),false);
            return false;
        }

        return executeAbility();

    }

    @Override
    public boolean executeAbility() {
        sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockMessage"), getRoleOwner(),false);
        getChoosenPlayer().getRole().setCanPerform(false);
        return true;
    }
}
