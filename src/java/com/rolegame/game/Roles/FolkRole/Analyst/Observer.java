package com.rolegame.game.Roles.FolkRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Observer extends FolkRole implements ActiveNightAbility {
    public Observer() {
        super(RoleID.Observer, RolePriority.None, RoleCategory.FolkAnalyst, 0,0);
    }

    @Override
    public boolean performAbility() {

       return executeAbility();
    }

    @Override
    public boolean executeAbility() {
        Message.sendMessage(LanguageManager.getText("Observer.message")+": " +
                this.getChoosenPlayer().getRole().getTeam(), getRoleOwner(),false);
        return true;
    }
}
