package com.rolegame.game.Roles;

import com.rolegame.game.Roles.CorrupterRole.Analyst.DarkRevealer;
import com.rolegame.game.Roles.CorrupterRole.Killing.Psycho;
import com.rolegame.game.Roles.CorrupterRole.Support.Disguiser;
import com.rolegame.game.Roles.CorrupterRole.Support.Interrupter;
import com.rolegame.game.Roles.FolkRole.Analyst.*;
import com.rolegame.game.Roles.FolkRole.Analyst.Observer;
import com.rolegame.game.Roles.FolkRole.Protector.Soulbinder;
import com.rolegame.game.Roles.FolkRole.Support.SealMaster;
import com.rolegame.game.Roles.NeutralRole.Chaos.Clown;
import com.rolegame.game.Roles.NeutralRole.Chaos.SimplePerson;
import com.rolegame.game.Roles.NeutralRole.Killing.Assassin;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.Team;

import java.util.*;

public class RoleCatalog {
    private static final HashMap<Team, List<Role>> rolesMap = new HashMap<>();
    private static final HashMap<RoleCategory, List<Role>> categoryMap = new HashMap<>();

    static {
        addRole(new Detective());
        addRole(new Psycho());
        addRole(new Observer());
        addRole(new Soulbinder());
        addRole(new Stalker());
        addRole(new DarkRevealer());
        addRole(new Interrupter());
        addRole(new SealMaster());
        addRole(new Assassin());
        addRole(new SimplePerson());
        addRole(new Clown());
        addRole(new Disguiser());

    }

    private static void addRole(Role role){
        rolesMap.computeIfAbsent(role.getTeam(), k->new ArrayList<>()).add(role);
        categoryMap.computeIfAbsent(role.getRoleCategory(), k-> new ArrayList<>()).add(role);
    }

    public static List<Role> getRolesByTeam(Team team){
        return rolesMap.getOrDefault(team, Collections.emptyList());
    }

    public static List<Role> getRolesByCategory(RoleCategory roleCategory){

        return categoryMap.getOrDefault(roleCategory, Collections.emptyList());
    }

    public static Role getRandomRole(Role otherRole){
        ArrayList<Role> otherRoles = new ArrayList<>();
        otherRoles.add(new Detective());
        otherRoles.add(new Psycho());
        otherRoles.add(new Observer());
        otherRoles.add(new Soulbinder());
        otherRoles.add(new Stalker());
        otherRoles.add(new DarkRevealer());
        otherRoles.add(new Interrupter());
        otherRoles.add(new SealMaster());
        otherRoles.add(new Assassin());
        otherRoles.add(new SimplePerson());
        otherRoles.add(new Clown());
        otherRoles.add(new Disguiser());
        otherRoles.remove(otherRole);
        return otherRoles.get(new Random().nextInt(otherRoles.size()));
    }

    public static Role getRandomRole(){
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Detective());
        roles.add(new Psycho());
        roles.add(new Observer());
        roles.add(new Soulbinder());
        roles.add(new Stalker());
        roles.add(new DarkRevealer());
        roles.add(new Interrupter());
        roles.add(new SealMaster());
        roles.add(new Assassin());
        roles.add(new SimplePerson());
        roles.add(new Clown());
        roles.add(new Disguiser());
        return roles.get(new Random().nextInt(roles.size()));
    }

    public static ArrayList<Role> initializeRoles(int playerCount){
        ArrayList<Role> roles = switch (playerCount) {
            case 5 -> fivePlayers();
            case 6 -> sixPlayers();
            case 7 -> sevenPlayers();
            case 8 -> eightPlayers();
            case 9 -> ninePlayers();
            case 10 -> tenPlayers();
            default -> throw new IllegalStateException("Unexpected value: " + playerCount);
        };

        Collections.shuffle(roles);
        return roles;
    }

    private static ArrayList<Role> fivePlayers(){

        ArrayList<Role> roles = new ArrayList<>();
//        roles.add(getRandomRoleByCategory(RoleCategory.FolkAnalyst));
        roles.add(new SimplePerson());
        roles.add(getRandomRoleByCategory(RoleCategory.FolkSupport, RoleCategory.FolkSupport));
        roles.add(getRandomRoleByTeam(Team.Folk));
        roles.add(getRandomRoleByCategory(RoleCategory.CorrupterKilling));
        roles.add(getRandomRoleByTeam(Team.Neutral));
        return roles;
    }

    // Same as 5 players but also how an extra random folk
    private static ArrayList<Role> sixPlayers(){
        ArrayList<Role> roles = fivePlayers();
        roles.add(getRandomRoleByTeam(Team.Folk));
        return roles;
    }

    private static ArrayList<Role> sevenPlayers(){

        ArrayList<Role> roles = sixPlayers();

        switch (new Random().nextInt(2)){
            case 0: roles.add(getRandomRoleByTeam(Team.Neutral));
                break;
            case 1: roles.add(getRandomRoleByCategory(RoleCategory.CorrupterSupport,RoleCategory.CorrupterSupport));
                break;
        }

        return roles;
    }

    private static ArrayList<Role> eightPlayers(){

        ArrayList<Role> roles = sixPlayers();

        switch (new Random().nextInt(3)){
            case 0:
                roles.add(getRandomRoleByTeam(Team.Neutral));
                roles.add(getRandomRoleByTeam(Team.Neutral));
                break;
            case 1:
                roles.add(getRandomRoleByCategory(RoleCategory.CorrupterSupport,RoleCategory.CorrupterSupport));
                roles.add(getRandomRoleByTeam(Team.Neutral));
                break;
            case 2:
                roles.add(getRandomRoleByCategory(RoleCategory.CorrupterSupport,RoleCategory.CorrupterSupport));
                roles.add(getRandomRoleByCategory(RoleCategory.CorrupterSupport,RoleCategory.CorrupterSupport));
                break;
        }

        return roles;
    }

    private static ArrayList<Role> ninePlayers(){
        ArrayList<Role> roles = eightPlayers();
        roles.add(getRandomRoleByTeam(Team.Folk));
        return roles;
    }

    private static ArrayList<Role> tenPlayers(){

        ArrayList<Role> roles = sixPlayers();
        roles.add(getRandomRoleByTeam(Team.Folk));
        roles.add(getRandomRoleByCategory(RoleCategory.CorrupterSupport,RoleCategory.CorrupterAnalyst));

        switch (new Random().nextInt(4)){
            case 0:
                roles.add(getRandomRoleByTeam(Team.Neutral));
                roles.add(getRandomRoleByTeam(Team.Neutral));
                break;
            case 1:
                roles.add(getRandomRoleByCategory(RoleCategory.CorrupterSupport,RoleCategory.CorrupterSupport));
                roles.add(getRandomRoleByTeam(Team.Neutral));
                break;
            case 2:
                roles.add(getRandomRoleByCategory(RoleCategory.CorrupterSupport,RoleCategory.CorrupterSupport));
                roles.add(getRandomRoleByTeam(Team.Folk));
                break;
            case 3:
                roles.add(getRandomRoleByTeam(Team.Neutral));
                roles.add(getRandomRoleByTeam(Team.Folk));
                break;
        }

        return roles;
    }

    private static Role getRandomRoleByCategory(RoleCategory roleCategory){
        Random random = new Random();
        List<Role> roles = getRolesByCategory(roleCategory);
        return roles.get(random.nextInt(roles.size()));
    }

    private static Role getRandomRoleByCategory(RoleCategory... roleCategories){
        Random random = new Random();
        List<Role> roles = new ArrayList<>();
        for(RoleCategory roleCategory: roleCategories){
            roles.add(getRandomRoleByCategory(roleCategory));
        }
        return roles.get(random.nextInt(roles.size()));
    }

    private static Role getRandomRoleByTeam(Team team){
        Random random = new Random();
        List<Role> roles = getRolesByTeam(team);
        return roles.get(random.nextInt(roles.size()));
    }

}
