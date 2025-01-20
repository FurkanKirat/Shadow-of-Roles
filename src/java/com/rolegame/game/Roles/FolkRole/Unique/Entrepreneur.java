package com.rolegame.game.Roles.FolkRole.Unique;

import com.rolegame.game.GameManagement.Message;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.CorrupterRole.Analyst.DarkRevealer;
import com.rolegame.game.Roles.CorrupterRole.Analyst.Darkseer;
import com.rolegame.game.Roles.CorrupterRole.Killing.Psycho;
import com.rolegame.game.Roles.FolkRole.Analyst.Detective;
import com.rolegame.game.Roles.FolkRole.Analyst.Observer;
import com.rolegame.game.Roles.FolkRole.Analyst.Stalker;
import com.rolegame.game.Roles.FolkRole.FolkRole;
import com.rolegame.game.Roles.FolkRole.Protector.Soulbinder;
import com.rolegame.game.Roles.Role;
import com.rolegame.game.Roles.RoleProperties.ActiveNightAbility;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;

import java.util.Random;

public class Entrepreneur extends FolkRole implements ActiveNightAbility {

    private static final int healPrice = 3;
    private static final int infoPrice = 2;
    private static final int attackPrice = 4;
    private int money;
    private ChosenAbility abilityState;
    public Entrepreneur() {
        super(RoleID.Entrepreneur, RolePriority.None, RoleCategory.FolkUnique, 0, 0);
        this.money = 5;
        this.setAbilityState(ChosenAbility.None);
    }

    @Override
    public boolean performAbility(){
        return super.performAbility();

    }
    @Override
    public boolean executeAbility() {
        rolePriority = RolePriority.None;
        switch (abilityState){

            case Attack -> {
                if(money>=attackPrice){
                    money -= attackPrice;
                    return useOtherAbility(new Psycho());
                }

            }
            case Heal ->{
                if(money>=healPrice){
                    money -= healPrice;
                    return useOtherAbility(new Soulbinder());
                }

            }
            case Info -> {
                if(money>=infoPrice){
                    money -= infoPrice;
                    return gatherInfo();
                }

            }
            default -> {
                return false;
            }
        }
        return insufficientMoney();
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
        String message = LanguageManager.getText("Entrepreneur.insufficientMoney");

        switch (abilityState){
            case Attack -> message += LanguageManager.getText("Entrepreneur.attack");
            case Heal -> message += LanguageManager.getText("Entrepreneur.heal");
            case Info -> message += LanguageManager.getText("Entrepreneur.info");
        }
        Message.sendMessage(message, roleOwner, false);
        return false;
    }


    public enum ChosenAbility{
        Attack,
        Heal,
        Info,
        None
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
