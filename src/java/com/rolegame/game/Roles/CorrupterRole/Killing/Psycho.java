package com.rolegame.game.Roles.CorrupterRole.Killing;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Psycho extends CorrupterRole implements ActiveNightAbility {

    public Psycho() {
        super(RoleID.Psycho, RolePriority.None, RoleCategory.CorrupterKilling, 1,0);
    }

    @Override
    public boolean executeAbility() {

        if(getAttack() > getChoosenPlayer().getDefence()){
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath.psycho"));
            Message.sendMessage(LanguageManager.getText("Psycho.killMessage"), getRoleOwner(),false);
            Message.sendMessage( LanguageManager.getText("Psycho.slainMessage").replace("{playerName}",this.getChoosenPlayer().getName()), getRoleOwner(),true);

            return true;
        }
        else{
            Message.sendMessage(LanguageManager.getText("Psycho.defenceMessage"),
                    getRoleOwner(),false);
            return false;
        }
    }
}
