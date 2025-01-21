package com.rolegame.game.Roles;

import com.rolegame.game.Roles.CorrupterRole.Analyst.DarkRevealer;
import com.rolegame.game.Roles.CorrupterRole.Analyst.Darkseer;
import com.rolegame.game.Roles.CorrupterRole.Killing.Psycho;
import com.rolegame.game.Roles.CorrupterRole.Support.Blinder;
import com.rolegame.game.Roles.CorrupterRole.Support.Disguiser;
import com.rolegame.game.Roles.CorrupterRole.Support.Interrupter;
import com.rolegame.game.Roles.CorrupterRole.Support.LastJoke;
import com.rolegame.game.Roles.FolkRole.Analyst.*;
import com.rolegame.game.Roles.FolkRole.Analyst.Observer;
import com.rolegame.game.Roles.FolkRole.Protector.Soulbinder;
import com.rolegame.game.Roles.FolkRole.Support.SealMaster;
import com.rolegame.game.Roles.NeutralRole.Chaos.Clown;
import com.rolegame.game.Roles.NeutralRole.Chaos.SimplePerson;
import com.rolegame.game.Roles.FolkRole.Protector.FolkHero;
import com.rolegame.game.Roles.FolkRole.Unique.Entrepreneur;
import com.rolegame.game.Roles.NeutralRole.Good.Lorekeeper;
import com.rolegame.game.Roles.NeutralRole.Killing.Assassin;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.Team;

import java.util.*;

public class RoleCatalog {
    private static final HashMap<Team, List<Role>> rolesMap = new HashMap<>();
    private static final HashMap<RoleCategory, List<Role>> categoryMap = new HashMap<>();
    private static final List<Role> allRoles = new ArrayList<>();

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
        addRole(new Darkseer());
        addRole(new Blinder());
        addRole(new LastJoke());
        addRole(new FolkHero());
        addRole(new Entrepreneur());
        addRole(new Lorekeeper());

    }

    private static void addRole(Role role){
        rolesMap.computeIfAbsent(role.getTeam(), k->new ArrayList<>()).add(role);
        categoryMap.computeIfAbsent(role.getRoleCategory(), k-> new ArrayList<>()).add(role);
        allRoles.add(role);
    }

    public static List<Role> getRolesByTeam(Team team){
        return rolesMap.getOrDefault(team, Collections.emptyList());
    }

    public static List<Role> getRolesByCategory(RoleCategory roleCategory){

        return categoryMap.getOrDefault(roleCategory, Collections.emptyList());
    }

    public static List<Role> getAllRoles(){
        return new ArrayList<>(allRoles);
    }

    public static Role getRandomRole(Role otherRole){
        ArrayList<Role> otherRoles = new ArrayList<>(allRoles);
        otherRoles.remove(otherRole);
        return otherRoles.get(new Random().nextInt(otherRoles.size())).copy();
    }

    public static Role getRandomRole(){
        return allRoles.get(new Random().nextInt(allRoles.size())).copy();
    }

    public static ArrayList<Role> initializeRoles(int playerCount){
        ArrayList<Role> roles = switch (playerCount) {
            case 5 -> fivePlayers();
            case 6 -> sixPlayers();
            case 7 -> sevenPlayers();
            case 8 -> eightPlayers();
            case 9 -> ninePlayers();
            case 10 -> tenPlayers();
            default -> throw new IllegalStateException("Unexpected player count: " + playerCount);
        };

        Collections.shuffle(roles);
        return roles;
    }

    private static ArrayList<Role> fivePlayers(){

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(getRandomRoleByCategory(RoleCategory.FOLK_ANALYST));
        roles.add(getRandomRoleByCategory(RoleCategory.FOLK_SUPPORT, RoleCategory.FOLK_SUPPORT));
        roles.add(getRandomRoleByTeam(Team.FOLK));
        roles.add(getRandomRoleByCategory(RoleCategory.CORRUPTER_KILLING));
        roles.add(getRandomRoleByTeam(Team.NEUTRAL));
        return roles;
    }

    // Same as 5 players but also how an extra random folk
    private static ArrayList<Role> sixPlayers(){
        ArrayList<Role> roles = fivePlayers();
        roles.add(getRandomRoleByTeam(Team.FOLK));
        return roles;
    }

    private static ArrayList<Role> sevenPlayers(){

        ArrayList<Role> roles = sixPlayers();

        switch (new Random().nextInt(2)){
            case 0: roles.add(getRandomRoleByTeam(Team.NEUTRAL));
                break;
            case 1: roles.add(getRandomRoleByCategory(RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                break;
        }

        return roles;
    }

    private static ArrayList<Role> eightPlayers(){

        ArrayList<Role> roles = sixPlayers();

        switch (new Random().nextInt(3)){
            case 0:
                roles.add(getRandomRoleByTeam(Team.NEUTRAL));
                roles.add(getRandomRoleByTeam(Team.NEUTRAL));
                break;
            case 1:
                roles.add(getRandomRoleByCategory(RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                roles.add(getRandomRoleByTeam(Team.NEUTRAL));
                break;
            case 2:
                roles.add(getRandomRoleByCategory(RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                roles.add(getRandomRoleByCategory(RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                break;
        }

        return roles;
    }

    private static ArrayList<Role> ninePlayers(){
        ArrayList<Role> roles = eightPlayers();
        roles.add(getRandomRoleByTeam(Team.FOLK));
        return roles;
    }

    private static ArrayList<Role> tenPlayers(){

        ArrayList<Role> roles = sixPlayers();
        roles.add(getRandomRoleByTeam(Team.FOLK));
        roles.add(getRandomRoleByCategory(RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_ANALYST));

        switch (new Random().nextInt(4)){
            case 0:
                roles.add(getRandomRoleByTeam(Team.NEUTRAL));
                roles.add(getRandomRoleByTeam(Team.NEUTRAL));
                break;
            case 1:
                roles.add(getRandomRoleByCategory(RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                roles.add(getRandomRoleByTeam(Team.NEUTRAL));
                break;
            case 2:
                roles.add(getRandomRoleByCategory(RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                roles.add(getRandomRoleByTeam(Team.FOLK));
                break;
            case 3:
                roles.add(getRandomRoleByTeam(Team.NEUTRAL));
                roles.add(getRandomRoleByTeam(Team.FOLK));
                break;
        }

        return roles;
    }

    private static Role getRandomRoleByCategory(RoleCategory roleCategory){
        Random random = new Random();
        List<Role> roles = getRolesByCategory(roleCategory);
        return roles.get(random.nextInt(roles.size())).copy();
    }

    private static Role getRandomRoleByCategory(RoleCategory... roleCategories){
        Random random = new Random();
        List<Role> roles = new ArrayList<>();
        for(RoleCategory roleCategory: roleCategories){
            roles.add(getRandomRoleByCategory(roleCategory));
        }
        return roles.get(random.nextInt(roles.size())).copy();
    }

    private static Role getRandomRoleByTeam(Team team){
        Random random = new Random();
        List<Role> roles = getRolesByTeam(team);
        return roles.get(random.nextInt(roles.size())).copy();
    }

}
