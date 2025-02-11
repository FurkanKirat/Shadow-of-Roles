package com.rolegame.game.models.roles.folkroles.unique;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.player.Player;
import com.rolegame.game.models.roles.corrupterroles.analyst.DarkRevealer;
import com.rolegame.game.models.roles.corrupterroles.analyst.Darkseer;
import com.rolegame.game.models.roles.corrupterroles.killing.Psycho;
import com.rolegame.game.models.roles.enums.*;
import com.rolegame.game.models.roles.folkroles.analyst.Detective;
import com.rolegame.game.models.roles.folkroles.analyst.Observer;
import com.rolegame.game.models.roles.folkroles.analyst.Stalker;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.folkroles.protector.Soulbinder;
import com.rolegame.game.models.roles.templates.RoleTemplate;

import java.util.Random;

public class Entrepreneur extends FolkRole {

    private static final int HEAL_PRICE = 3;
    private static final int INFO_PRICE = 2;
    private static final int ATTACK_PRICE = 4;
    private int money;
    private ChosenAbility abilityState;
    public Entrepreneur() {
        super(RoleID.Entrepreneur, AbilityType.ACTIVE_ALL, RolePriority.NONE, RoleCategory.FOLK_UNIQUE, 0, 0);
        this.money = 3;
        this.setAbilityState(ChosenAbility.NONE);
    }


    @Override
    public AbilityResult executeAbility(Player roleOwner, Player choosenPlayer) {
        rolePriority = RolePriority.NONE;
        switch (abilityState){

            case ATTACK -> {
                if(money>= ATTACK_PRICE){
                    money -= ATTACK_PRICE;
                    return useOtherAbility(new Psycho(), roleOwner, choosenPlayer);
                }

            }
            case HEAL ->{
                if(money>= HEAL_PRICE){
                    money -= HEAL_PRICE;
                    return useOtherAbility(new Soulbinder(), roleOwner, choosenPlayer);
                }

            }
            case INFO -> {
                if(money>= INFO_PRICE){
                    money -= INFO_PRICE;
                    return gatherInfo(roleOwner, choosenPlayer);
                }

            }
            default -> {
                return AbilityResult.NO_ABILITY_SELECTED;
            }
        }
        return insufficientMoney(roleOwner);
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

    private AbilityResult gatherInfo(Player roleOwner, Player chosenPlayer){
        RoleTemplate role;
        switch (new Random().nextInt(5)){
            case 0 -> role = new Darkseer();
            case 1 -> role = new Detective();
            case 2 -> role = new Observer();
            case 3 -> role = new Stalker();
            default -> role = new DarkRevealer();
        }
        return useOtherAbility(role, roleOwner, chosenPlayer);
    }

    private AbilityResult useOtherAbility(RoleTemplate roleTemplate, Player roleOwner, Player choosenPlayer){
        return roleTemplate.executeAbility(roleOwner,choosenPlayer);
    }
    private AbilityResult insufficientMoney(Player roleOwner){
        String message = LanguageManager.getText("Entrepreneur","insufficientMoney");

        switch (abilityState){
            case ATTACK -> message += LanguageManager.getText("Entrepreneur","attack");
            case HEAL -> message += LanguageManager.getText("Entrepreneur", "heal");
            case INFO -> message += LanguageManager.getText("Entrepreneur","info");
        }
        sendAbilityMessage(message, roleOwner);
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
