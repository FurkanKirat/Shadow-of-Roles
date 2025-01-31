package com.rolegame.game.models.roles.folkroles.unique;

import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.corrupterroles.analyst.DarkRevealer;
import com.rolegame.game.models.roles.corrupterroles.analyst.Darkseer;
import com.rolegame.game.models.roles.corrupterroles.killing.Psycho;
import com.rolegame.game.models.roles.folkroles.analyst.Detective;
import com.rolegame.game.models.roles.folkroles.analyst.Observer;
import com.rolegame.game.models.roles.folkroles.analyst.Stalker;
import com.rolegame.game.models.roles.folkroles.FolkRole;
import com.rolegame.game.models.roles.folkroles.protector.Soulbinder;
import com.rolegame.game.models.roles.Role;
import com.rolegame.game.models.roles.roleproperties.ActiveNightAbility;
import com.rolegame.game.models.roles.roleproperties.RoleCategory;
import com.rolegame.game.models.roles.roleproperties.RoleID;
import com.rolegame.game.models.roles.roleproperties.RolePriority;

import java.util.Random;

public class Entrepreneur extends FolkRole implements ActiveNightAbility {

    private static final int HEAL_PRICE = 3;
    private static final int INFO_PRICE = 2;
    private static final int ATTACK_PRICE = 4;
    private int money;
    private ChosenAbility abilityState;
    public Entrepreneur() {
        super(RoleID.Entrepreneur, RolePriority.NONE, RoleCategory.FOLK_UNIQUE, 0, 0);
        this.money = 3;
        this.setAbilityState(ChosenAbility.NONE);
    }

    @Override
    public boolean performAbility(){
        return super.performAbility();

    }
    @Override
    public boolean executeAbility() {
        rolePriority = RolePriority.NONE;
        switch (abilityState){

            case ATTACK -> {
                if(money>= ATTACK_PRICE){
                    money -= ATTACK_PRICE;
                    return useOtherAbility(new Psycho());
                }

            }
            case HEAL ->{
                if(money>= HEAL_PRICE){
                    money -= HEAL_PRICE;
                    return useOtherAbility(new Soulbinder());
                }

            }
            case INFO -> {
                if(money>= INFO_PRICE){
                    money -= INFO_PRICE;
                    return gatherInfo();
                }

            }
            default -> {
                return false;
            }
        }
        return insufficientMoney();
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

    private boolean gatherInfo(){
        Role role;
        switch (new Random().nextInt(5)){
            case 0 -> role = new Darkseer();
            case 1 -> role = new Detective();
            case 2 -> role = new Observer();
            case 3 -> role = new Stalker();
            default -> role = new DarkRevealer();
        }
        return useOtherAbility(role);
    }

    private boolean useOtherAbility(Role role){
        role.setChoosenPlayer(this.getChoosenPlayer());
        role.setRoleOwner(this.getRoleOwner());
        return role.executeAbility();
    }

    private boolean insufficientMoney(){
        String message = LanguageManager.getText("Entrepreneur","insufficientMoney");

        switch (abilityState){
            case ATTACK -> message += LanguageManager.getText("Entrepreneur","attack");
            case HEAL -> message += LanguageManager.getText("Entrepreneur", "heal");
            case INFO -> message += LanguageManager.getText("Entrepreneur","info");
        }
        sendAbilityMessage(message, roleOwner);
        return false;
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
