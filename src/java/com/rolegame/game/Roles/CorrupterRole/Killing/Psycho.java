package com.rolegame.game.Roles.CorrupterRole.Killing;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Psycho extends CorrupterRole {

    public Psycho() {
        super(RoleID.Psycho, RolePriority.None, RoleCategory.CorrupterKilling,LanguageManager.getText("Psycho.name"),
                LanguageManager.getText("Psycho.attributes"), LanguageManager.getText("Psycho.abilities"),1,0);
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

        if(getAttack() > getChoosenPlayer().getDefence()){
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath.psycho"));
            Message.sendMessage(LanguageManager.getText("Psycho.killMessage")+": ", getRoleOwner(),false);
            Message.sendMessage(this.getChoosenPlayer().getName() + " "+ LanguageManager.getText("Psycho.slainMessage"), getRoleOwner(),true);

            return true;
        }
        else{
            Message.sendMessage(LanguageManager.getText("Psycho.defenceMessage"),
                    getRoleOwner(),false);
            return false;
        }

    }
}
