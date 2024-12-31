package com.rolegame.game.Roles.FolkRole.Analyst;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleCatalog;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

import java.util.Random;

public class Detective extends FolkRole {
    public Detective() {
        super(RoleID.Detective, RolePriority.None, RoleCategory.FolkAnalyst,
                LanguageManager.getText("Detective.name"), LanguageManager.getText("Detective.attributes"),
                LanguageManager.getText("Detective.abilities"),0,0);
    }

    @Override
    public boolean performAbility() {

        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.roleBlockedMessage"),getRoleOwner(),false);
            return false;
        }

        if(getChoosenPlayer()==null){
            return false;
        }

        Role randRole = RoleCatalog.getRandomRole(getChoosenPlayer().getRole());

        String message =
                LanguageManager.getText("Detective.role")+": " +
                (new Random().nextBoolean() ? getChoosenPlayer().getRole().getName() + " " + LanguageManager.getText("Detective.or") + " " + randRole.getName() :
                        randRole.getName() + " " + LanguageManager.getText("Detective.or") + " " + getChoosenPlayer().getRole().getName());
        Message.sendMessage(message,getRoleOwner(),false);

        return true;
    }

}
