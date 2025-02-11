package com.rolegame.game.models.roles.corrupterroles.analyst;

import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.enums.*;

import java.util.ArrayList;
import java.util.Collections;

public final class Darkseer extends CorrupterRole {
    public Darkseer() {
        super(RoleID.Darkseer, AbilityType.PASSIVE,
                RolePriority.NONE, RoleCategory.CORRUPTER_ANALYST, 0, 0);
    }

    @Override
    public AbilityResult performAbility(Player roleOwner, Player choosenPlayer) {
        return performAbilityForPassiveRoles(roleOwner);
    }

    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        ArrayList<Player> players = new ArrayList<>(GameScreenController.getGameService().getAlivePlayers());

        Collections.shuffle(players);
        String message;

        if (players.size() >= 2) {
            message = LanguageManager.getText("Darkseer","abilityMessage")
                    .replace("{roleName1}",players.getFirst().getRole().getTemplate().getName())
                    .replace("{roleName2}",players.get(1).getRole().getTemplate().getName());
        }
        else if (players.size()==1) {
            message = LanguageManager.getText("Darkseer","oneLeftMessage")
                    .replace("{roleName}",players.getFirst().getRole().getTemplate().getName());
        }
        else{
            message = LanguageManager.getText("Darkseer","zeroLeftMessage");
        }

        sendAbilityMessage(message,roleOwner);
        return AbilityResult.SUCCESSFUL;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(10,10);
    }
}
