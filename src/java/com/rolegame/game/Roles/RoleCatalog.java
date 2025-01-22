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
        HashMap<Role,Integer> roles = switch (playerCount) {
            case 5 -> fivePlayers();
            case 6 -> sixPlayers();
            case 7 -> sevenPlayers();
            case 8 -> eightPlayers();
            case 9 -> ninePlayers();
            case 10 -> tenPlayers();
            default -> throw new IllegalStateException("Unexpected player count: " + playerCount);
        };
        ArrayList<Role> rolesList = new ArrayList<>();
        for(Map.Entry<Role,Integer> entry : roles.entrySet()){

            for(int i=0;i<entry.getValue();i++){
                rolesList.add(entry.getKey().copy());
            }
        }
        Collections.shuffle(rolesList);
        return rolesList;
    }

    private static void putRole(HashMap<Role,Integer> roles, Role role){
        roles.put(role, roles.getOrDefault(role,0)+1);
    }
    private static HashMap<Role,Integer> fivePlayers(){

        HashMap<Role,Integer> roles = new HashMap<>();
        putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.FOLK_ANALYST));
        putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.FOLK_SUPPORT, RoleCategory.FOLK_SUPPORT));
        putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
        putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_KILLING));
        putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
        return roles;
    }



    // Same as 5 players but also how an extra random folk
    private static HashMap<Role,Integer> sixPlayers(){
        HashMap<Role,Integer> roles = fivePlayers();
        putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
        return roles;
    }

    private static HashMap<Role,Integer> sevenPlayers(){

        HashMap<Role,Integer> roles = sixPlayers();

        switch (new Random().nextInt(2)){
            case 0: putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 1: putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                break;
        }

        return roles;
    }

    private static HashMap<Role,Integer> eightPlayers(){

        HashMap<Role,Integer> roles = sixPlayers();

        switch (new Random().nextInt(3)){
            case 0:
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 1:
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 2:
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                break;
        }

        return roles;
    }

    private static HashMap<Role,Integer> ninePlayers(){
        HashMap<Role,Integer> roles = eightPlayers();
        putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
        return roles;
    }

    private static HashMap<Role,Integer> tenPlayers(){

        HashMap<Role,Integer> roles = sixPlayers();
        putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
        putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_ANALYST));

        switch (new Random().nextInt(4)){
            case 0:
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 1:
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 2:
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_SUPPORT));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
                break;
            case 3:
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
                break;
        }

        return roles;
    }

    private static Role getRoleByCategoryWithProbability(HashMap<Role,Integer> currentRoles, RoleCategory roleCategory){
        List<Role> roles = new ArrayList<>(getRolesByCategory(roleCategory));
        removeMaxCount(currentRoles,roles);

        return getRoleWithProbability(currentRoles,roles);
    }

    private static Role getRoleByCategoryWithProbability(HashMap<Role,Integer> currentRoles , RoleCategory... roleCategories){
        List<Role> roles = new ArrayList<>();
        for(RoleCategory roleCategory: roleCategories){

            roles.addAll(getRolesByCategory(roleCategory));
        }
        removeMaxCount(currentRoles,roles);
        return getRoleWithProbability(currentRoles,roles);
    }

    private static Role getRoleByTeamWithProbability(HashMap<Role,Integer> currentRoles, Team team){

        List<Role> roles = new ArrayList<>(getRolesByTeam(team));
        removeMaxCount(currentRoles,roles);
        return getRoleWithProbability(currentRoles,roles);
    }

    private static void removeMaxCount(HashMap<Role,Integer> currentRoles, List<Role> currentRoleList){
        for(Map.Entry<Role,Integer> entry : currentRoles.entrySet()){
            if(entry.getKey().chanceProperty.maxNumber()<=entry.getValue()){
                currentRoleList.remove(entry.getKey());
            }
        }
    }

    private static Role getRoleWithProbability(HashMap<Role,Integer> currentRoles, List<Role> currentRoleList){

        int sum = 0;
        for (Role role : currentRoleList) {
            sum += role.chanceProperty.chance();
        }
        int randNum = new Random().nextInt(sum);
        int currentSum = 0;

        for (Role role : currentRoleList) {
            currentSum += role.chanceProperty.chance();

            if (currentSum >= randNum) {
                return role.copy();
            }
        }
        return null;
    }


}
