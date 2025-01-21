package com.rolegame.game.Roles.CorrupterRole.Analyst;

import com.rolegame.game.GUI.Controllers.GameScreenController;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.PassiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

import java.util.ArrayList;
import java.util.Collections;

public class Darkseer extends CorrupterRole implements PassiveNightAbility {
    public Darkseer() {
        super(RoleID.Darkseer, RolePriority.NONE, RoleCategory.CORRUPTER_ANALYST, 0, 0);
    }

    @Override
    public boolean performAbility() {
        if(!isCanPerform()){
            sendAbilityMessage(LanguageManager.getText("RoleBlock.roleBlockedMessage"),getRoleOwner(),false);
            return false;
        }
        return executeAbility();
    }

    @Override
    public boolean executeAbility() {
        ArrayList<Player> players = new ArrayList<>(GameScreenController.getGameController().getAlivePlayers());

        Collections.shuffle(players);
        String message;

        if (players.size() >= 2) {
            message = LanguageManager.getText("Darkseer.abilityMessage")
                    .replace("{roleName1}",players.getFirst().getRole().getName())
                    .replace("{roleName2}",players.get(1).getRole().getName());
        }
        else if (players.size()==1) {
            message = LanguageManager.getText("Darkseer.oneLeftMessage")
                    .replace("{roleName}",players.getFirst().getRole().getName());
        }
        else{
            message = LanguageManager.getText("Darkseer.zeroLeftMessage");
        }

        sendAbilityMessage(message,getRoleOwner(), false);
        return true;
    }
}
