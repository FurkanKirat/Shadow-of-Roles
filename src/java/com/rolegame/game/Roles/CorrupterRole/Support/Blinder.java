package com.rolegame.game.Roles.CorrupterRole.Support;

import com.rolegame.game.GUI.Controllers.GameScreenController;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.CorrupterRole;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

import java.util.ArrayList;
import java.util.Random;

public class Blinder extends CorrupterRole implements ActiveNightAbility {
    public Blinder() {
        super(RoleID.Blinder, RolePriority.BLINDER, RoleCategory.CORRUPTER_SUPPORT, 0, 0);
    }

    @Override
    public boolean executeAbility() {
        String message = LanguageManager.getText("Blinder.abilityMessage");
        sendAbilityMessage(message,getRoleOwner(), false);
        ArrayList<Player> players = new ArrayList<>(GameScreenController.getGameController().getAlivePlayers());

        players.remove(getChoosenPlayer());

        this.getChoosenPlayer().getRole().setChoosenPlayer(players.get(new Random().nextInt(players.size())));

        sendAbilityMessage(LanguageManager.getText("Blinder.blindMessage"),getChoosenPlayer(),false );

        return true;
    }
}
