package com.rolegame.game.Roles.NeutralRole.Killing;

import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;

public class Assassin extends NeutralRole {
    public Assassin() {
        super(RoleID.Assassin, RolePriority.None, RoleCategory.NeutralKilling,LanguageManager.getText("Assassin.name"),
                LanguageManager.getText("Assassin.attributes"), Team.Neutral,
                LanguageManager.getText("Assassin.abilities"), LanguageManager.getText("Assassin.goal"), 1, 1);
    }

    @Override
    public boolean performAbility() {

        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.immuneMessage") ,getRoleOwner(),false);
        }

        if(getChoosenPlayer()==null){
            return false;
        }

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
