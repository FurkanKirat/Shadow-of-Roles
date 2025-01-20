package com.rolegame.game.Roles.NeutralRole.Good;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.GameManagement.Player;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.NeutralRole.NeutralRole;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

import java.util.ArrayList;
import java.util.List;

public class Lorekeeper extends NeutralRole implements ActiveNightAbility {
    private List<Player> alreadyChosenPlayers;
    private Role guessedRole;
    private int trueGuessCount;
    public Lorekeeper() {
        super(RoleID.Lorekeeper, RolePriority.Lorekeeper, RoleCategory.NeutralGood, 0, 1);
        trueGuessCount = 0;
        alreadyChosenPlayers = new ArrayList<>();
    }

    @Override
    public boolean performAbility() {
        if(choosenPlayer == null){
            return false;
        }

        if(guessedRole == null){
            return false;
        }
        return executeAbility();
    }

    @Override
    public boolean executeAbility() {
        alreadyChosenPlayers.add(choosenPlayer);

        if(choosenPlayer.getRole().getId() == guessedRole.getId()){
            trueGuessCount++;

            String messageTemplate = LanguageManager.getText("Lorekeeper.abilityMessage");

            String message = messageTemplate
                    .replace("{playerName}", choosenPlayer.getName())
                    .replace("{roleName}", choosenPlayer.getRole().getName());
            Message.sendMessage(message,roleOwner, true);
        }
        return false;
    }

    public Role getGuessedRole() {
        return guessedRole;
    }

    public void setGuessedRole(Role guessedRole) {
        this.guessedRole = guessedRole;
    }

    public int getTrueGuessCount() {
        return trueGuessCount;
    }

    public void setTrueGuessCount(int trueGuessCount) {
        this.trueGuessCount = trueGuessCount;
    }

    public List<Player> getAlreadyChosenPlayers() {
        return alreadyChosenPlayers;
    }

}
