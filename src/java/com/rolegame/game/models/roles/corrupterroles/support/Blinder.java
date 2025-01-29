package com.rolegame.game.models.roles.corrupterroles.support;

import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.models.Player;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.CorrupterRole;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

import java.util.ArrayList;
import java.util.Random;

public final class Blinder extends CorrupterRole implements ActiveNightAbility {
    public Blinder() {
        super(RoleID.Blinder, RolePriority.BLINDER, RoleCategory.CORRUPTER_SUPPORT, 0, 0);
    }

    @Override
    public boolean executeAbility() {
        String message = LanguageManager.getText("Blinder","abilityMessage");
        sendAbilityMessage(message,getRoleOwner(), false);
        ArrayList<Player> players = new ArrayList<>(GameScreenController.getGameController().getAlivePlayers());

        players.remove(getChoosenPlayer());

        this.getChoosenPlayer().getRole().setChoosenPlayer(players.get(new Random().nextInt(players.size())));

        sendAbilityMessage(LanguageManager.getText("Blinder","blindMessage"),getChoosenPlayer(),false );

        return true;
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(25,10);
    }
}
