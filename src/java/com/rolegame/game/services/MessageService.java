package com.rolegame.game.services;

import com.rolegame.game.gui.controllers.game.GameScreenController;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.Message;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.enums.AbilityResult;
import com.rolegame.game.models.roles.enums.RoleID;
import com.rolegame.game.models.roles.enums.RolePriority;

import java.util.LinkedList;

public class MessageService {
    private static final LinkedList<Message> messages = new LinkedList<>();


    public static void resetMessages() {
        messages.clear();
    }


    public static void sendMessage(String message, Player receiver, boolean isPublic, boolean isDay) {
        messages.add(new Message(GameScreenController.getGameService().getTimeService().getDayCount(),
                isDay, message, receiver, isPublic));
    }

    public static void createNightMessage(String message, Player receiver, boolean isPublic, boolean isDay) {
         messages.add(new Message(GameScreenController.getGameService().getTimeService().getDayCount()-1,
                isDay, message, receiver, isPublic));
    }

    /**
     * Send messages to exceptional situations like folk hero's target is role blocked
     * @param alivePlayer any player
     */
    public static void sendSpecificRoleMessages(final Player alivePlayer){

        if(alivePlayer.getRole().getTemplate().isRoleBlockImmune() && !alivePlayer.getRole().isCanPerform()
                && !alivePlayer.isImmune()){
            MessageService.sendMessage(LanguageManager.getText("RoleBlock","RBimmuneMessage"),
                    alivePlayer, false, false);
        }

        if(alivePlayer.getRole().getChoosenPlayer()==null){
            return;
        }
        if(alivePlayer.getRole().getChoosenPlayer().isImmune() &&
                alivePlayer.getRole().getTemplate().getRolePriority().getPriority()<= RolePriority.ROLE_BLOCK.getPriority()){
            MessageService.sendMessage(LanguageManager.getText("RoleBlock","immuneMessage"),
                    alivePlayer, false, false);
        }
    }

    public static void sendAbilityMessage(RoleID roleID, Player receiver, boolean isPublic, AbilityResult abilityResult) {

        String message;
        switch (abilityResult) {
            case SUCCESSFUL -> message = LanguageManager.getText(roleID.toString(), "abilityMessage");
            case NO_ONE_SELECTED -> message = LanguageManager.getText("Message", "noAbilityUsed");
            case ROLE_BLOCKED -> message = LanguageManager.getText("RoleBlock", "roleBlockedMessage");
            case TARGET_IMMUNE -> message = LanguageManager.getText("RoleBlock", "immuneMessage");
            case NO_ABILITY_USE_LEFT -> message = LanguageManager.getText("Ability", "noAbilityUseLeftMessage");
            case ATTACK_INSUFFICIENT -> message = LanguageManager.getText("Ability","defence");
            case INSUFFICIENT_MONEY -> message = LanguageManager.getText(roleID.toString(),"insufficientMoney");
            case NO_ROLE_SELECTED -> message = LanguageManager.getText("Ability", "noRoleSelected");
            case NO_ABILITY_SELECTED -> message = LanguageManager.getText(roleID.toString(), "noAbilitySelected");
            case NO_ABILITY_EXIST -> message = LanguageManager.getText("Ability", "noAbilityExist");
        }

        /*messages.add(new Message(GameScreenController.getGameService().getTimeService().getDayCount(),
                false, message, receiver, isPublic))*/
    }


    public static LinkedList<Message> getMessages() {
        return messages;
    }

}
