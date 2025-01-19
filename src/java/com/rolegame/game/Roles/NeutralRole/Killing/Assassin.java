package com.rolegame.game.Roles.NeutralRole.Killing;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

public class Assassin extends NeutralRole implements ActiveNightAbility {
    public Assassin() {
        super(RoleID.Assassin, RolePriority.None, RoleCategory.NeutralKilling, 1, 1);
    }

    @Override
    public boolean performAbility() {

        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.RBimmuneMessage") ,getRoleOwner(),false);
        }

        if(getChoosenPlayer()==null){
            return false;
        }

        if(choosenPlayer.isImmune()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.immuneMessage") ,getRoleOwner(),false);
            return false;
        }
        return executeAbility();

    }

    @Override
    public boolean executeAbility() {
        if(getAttack() > getChoosenPlayer().getDefence()){
            this.getChoosenPlayer().setAlive(false);
            this.getChoosenPlayer().setCauseOfDeath(LanguageManager.getText("CauseOfDeath.assassin"));
            Message.sendMessage(LanguageManager.getText("Assassin.killMessage"), getRoleOwner(),false);
            Message.sendMessage(this.getChoosenPlayer().getName() + " " + LanguageManager.getText("Assassin.slainMessage"), getRoleOwner(),true);
            return true;
        }
        else{
            Message.sendMessage(LanguageManager.getText("Assassin.defenceMessage"),
                    getRoleOwner(),false);
            return false;
        }
    }
}

