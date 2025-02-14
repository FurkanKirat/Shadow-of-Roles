package com.rolegame.game.models.roles.folkroles.unique;

import com.rolegame.game.gamestate.CauseOfDeath;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.abilities.AttackAbility;
import com.rolegame.game.models.roles.abilities.ProtectiveAbility;
import com.rolegame.game.models.roles.abilities.InvestigativeAbility;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.services.GameService;

import java.util.Random;

public class Entrepreneur extends FolkRole implements ProtectiveAbility, AttackAbility, InvestigativeAbility {

    private static final int HEAL_PRICE = 3;
    private static final int INFO_PRICE = 2;
    private static final int ATTACK_PRICE = 4;
    private int money;
    private ChosenAbility abilityState;
    public Entrepreneur() {
        super(RoleID.Entrepreneur, AbilityType.ACTIVE_ALL, RolePriority.NONE,
                RoleCategory.FOLK_UNIQUE, 1, 0, false);
        this.money = 3;
        this.setAbilityState(ChosenAbility.NONE);
    }


    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer, GameService gameService) {
        rolePriority = RolePriority.NONE;
        switch (abilityState){

            case ATTACK -> {
                if(money>= ATTACK_PRICE){
                    money -= ATTACK_PRICE;
                    return attack(roleOwner, choosenPlayer, gameService, CauseOfDeath.ENTREPRENEUR);
                }

            }
            case HEAL ->{
                if(money>= HEAL_PRICE){
                    money -= HEAL_PRICE;
                    return heal(roleOwner, choosenPlayer, gameService);
                }

            }
            case INFO -> {
                if(money>= INFO_PRICE){
                    money -= INFO_PRICE;
                    return gatherInfo(roleOwner, choosenPlayer, gameService);
                }

            }
            default -> {
                return AbilityResult.NO_ABILITY_SELECTED;
            }
        }
        return insufficientMoney(roleOwner, gameService);
    }

    @Override
    public ChanceProperty getChanceProperty() {
        return new ChanceProperty(15,1);
    }

    public ChosenAbility getAbilityState() {
        return abilityState;
    }

    public void setAbilityState(ChosenAbility abilityState) {
        this.abilityState = abilityState;
    }

    private AbilityResult gatherInfo(Player roleOwner, Player chosenPlayer, GameService gameService){
        switch (new Random().nextInt(5)){
            case 0 -> {
                return darkSeerAbility(roleOwner, gameService);
            }
            case 1 -> {
                return detectiveAbility(roleOwner, chosenPlayer, gameService);
            }
            case 2 -> {
                return observerAbility(roleOwner, chosenPlayer, gameService);
            }
            case 3 -> {
                return stalkerAbility(roleOwner, chosenPlayer, gameService);
            }
            default -> {
                return darkRevealerAbility(roleOwner, chosenPlayer, gameService);
            }
        }
    }

    private AbilityResult insufficientMoney(Player roleOwner, GameService gameService){
        String message = LanguageManager.getText("Entrepreneur","insufficientMoney");

        switch (abilityState){
            case ATTACK -> message += LanguageManager.getText("Entrepreneur","attack");
            case HEAL -> message += LanguageManager.getText("Entrepreneur", "heal");
            case INFO -> message += LanguageManager.getText("Entrepreneur","info");
        }
        sendAbilityMessage(message, roleOwner, gameService.getMessageService());
        return AbilityResult.INSUFFICIENT_MONEY;
    }


    public enum ChosenAbility{
        ATTACK,
        HEAL,
        INFO,
        NONE
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
