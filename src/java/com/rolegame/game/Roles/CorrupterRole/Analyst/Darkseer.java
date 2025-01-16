package com.rolegame.game.Roles.CorrupterRole.Analyst;

import com.rolegame.game.GUI.Controllers.GameScreenController;
import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

import java.util.ArrayList;
import java.util.Collections;

public class Darkseer extends CorrupterRole {
    public Darkseer() {
        super(RoleID.Darkseer, RolePriority.None, RoleCategory.CorrupterAnalyst,
                LanguageManager.getText("Darkseer.name"),
                LanguageManager.getText("Darkseer.attributes"),
                LanguageManager.getText("Darkseer.abilities"), 0, 0);
    }

    @Override
    public boolean performAbility() {
        if(!isCanPerform()){
            Message.sendMessage(LanguageManager.getText("RoleBlock.roleBlockedMessage"),getRoleOwner(),false);
            return false;
        }

        ArrayList<Player> players = new ArrayList<>(GameScreenController.getGameController().getAlivePlayers());

        Collections.shuffle(players);
        String message;

        if (players.size() >= 2) {
            message = LanguageManager.getText("Darkseer.abilityMessage") +
                    players.getFirst().getRole().getName() +", " + players.get(1).getRole().getName();
        }
        else if (players.size()==1) {
            message = LanguageManager.getText("Darkseer.oneLeftMessage") +
                    players.getFirst().getRole().getName();
        }
        else{
            message = LanguageManager.getText("Darkseer.zeroLeftMessage");
        }

        Message.sendMessage(message,getRoleOwner(), false);
        return true;
    }
}
