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

        return performAbilityForBlockImmuneRoles();
    }

    @Override
    public boolean executeAbility() {

        sendAbilityMessage(LanguageManager.getText("RoleBlock","roleBlockMessage"), roleOwner);

        if(!choosenPlayer.getRole().isRoleBlockImmune()){
            choosenPlayer.getRole().setCanPerform(false);
            choosenPlayer.getRole().setChoosenPlayer(null);
            return true;
        }

        return false;
    }

    @Override
    public boolean isRoleBlockImmune() {
        return true;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(30,10);
    }
}
