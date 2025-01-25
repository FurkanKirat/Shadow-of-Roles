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
import com.rolegame.game.Roles.NeutralRole.Chaos.ChillGuy;
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

    // Adds all roles to the catalog
    static {
        addRole(new Detective(), new Psycho(), new Observer(), new Soulbinder(), new Stalker(),
                new DarkRevealer(), new Interrupter(), new SealMaster(), new Assassin(),
                new ChillGuy(), new Clown(), new Disguiser(), new Darkseer(), new Blinder(),
                new LastJoke(), new FolkHero(), new Entrepreneur(), new Lorekeeper()
        );

    }

    /**
     * Adds role to the catalog
     * @param roles the roles to be added to the role catalog
     */
    private static void addRole(Role... roles){
        for(Role role: roles){
            rolesMap.computeIfAbsent(role.getTeam(), k->new ArrayList<>()).add(role);
            categoryMap.computeIfAbsent(role.getRoleCategory(), k-> new ArrayList<>()).add(role);
            allRoles.add(role);
        }

    }

    /**
     *
     * @param team the desired team
     * @return a list that consist of the desired team
     */
    public static List<Role> getRolesByTeam(Team team){
        return rolesMap.getOrDefault(team, Collections.emptyList());
    }

    /**
     *
     * @param roleCategory the desired category
     * @return a list that consist of the desired category
     */
    public static List<Role> getRolesByCategory(RoleCategory roleCategory){
        return categoryMap.getOrDefault(roleCategory, Collections.emptyList());
    }

    /**
     *
     * @return a copy array list of all roles
     */
    public static List<Role> getAllRoles(){
        return new ArrayList<>(allRoles);
    }

    /**
     *
     * @param otherRole the role that is not wanted to return
     * @return a random role other than the parameter role
     */
    public static Role getRandomRole(Role otherRole){
        ArrayList<Role> otherRoles = new ArrayList<>(allRoles);
        otherRoles.remove(otherRole);
        return otherRoles.get(new Random().nextInt(otherRoles.size())).copy();
    }

    /**
     *
     * @return a random role in the catalog
     */
    public static Role getRandomRole(){
        return allRoles.get(new Random().nextInt(allRoles.size())).copy();
    }

    /**
     * Selects the role pool in the game according to player count
     * @param playerCount the count of the players
     * @return an array list that is the players' roles
     */
    public static ArrayList<Role> initializeRoles(int playerCount){
        HashMap<Role,Integer> roles = switch (playerCount) {
            case 5 -> configureFivePlayers();
            case 6 -> configureSixPlayers();
            case 7 -> configureSevenPlayers();
            case 8 -> configureEightPlayers();
            case 9 -> configureNinePlayers();
            case 10 -> configureTenPlayers();
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

    /**
     * Adds the role to the roles hashmap
     * @param roles the hash map of the roles that is created currently
     * @param role the role to be added to the hashmap
     */
    private static void putRole(HashMap<Role,Integer> roles, Role role){
        roles.put(role, roles.getOrDefault(role,0)+1);
    }

    /**
     * Configures roles for a 5-player game.
     */
    private static HashMap<Role,Integer> configureFivePlayers(){
        HashMap<Role,Integer> roles = new HashMap<>();
        putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.FOLK_ANALYST));
        putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.FOLK_SUPPORT, RoleCategory.FOLK_PROTECTOR));
        putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
        putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_KILLING));
        putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));

        return roles;
    }

    /**
     * Configures roles for a 6-player game.
     */
    private static HashMap<Role,Integer> configureSixPlayers(){
        HashMap<Role,Integer> roles = configureFivePlayers();
        putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
        return roles;
    }

    /**
     * Configures roles for a 7-player game.
     */
    private static HashMap<Role,Integer> configureSevenPlayers(){
        HashMap<Role,Integer> roles = configureSixPlayers();
        switch (new Random().nextInt(2)){
            case 0: putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 1: putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_ANALYST,RoleCategory.CORRUPTER_SUPPORT));
                break;
        }

        return roles;
    }

    /**
     * Configures roles for an 8-player game.
     */
    private static HashMap<Role,Integer> configureEightPlayers(){

        HashMap<Role,Integer> roles = configureSixPlayers();

        switch (new Random().nextInt(3)){
            case 0:
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 1:
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_ANALYST,RoleCategory.CORRUPTER_SUPPORT));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 2:
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_ANALYST,RoleCategory.CORRUPTER_SUPPORT));
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_ANALYST,RoleCategory.CORRUPTER_SUPPORT));
                break;
        }

        return roles;
    }

    /**
     * Configures roles for a 9-player game.
     */
    private static HashMap<Role,Integer> configureNinePlayers(){
        HashMap<Role,Integer> roles = configureEightPlayers();
        putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
        return roles;
    }

    /**
     * Configures roles for a 10-player game.
     */
    private static HashMap<Role,Integer> configureTenPlayers(){

        HashMap<Role,Integer> roles = configureSixPlayers();
        putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
        putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_SUPPORT,RoleCategory.CORRUPTER_ANALYST));

        switch (new Random().nextInt(4)){
            case 0:
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 1:
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_ANALYST,RoleCategory.CORRUPTER_SUPPORT));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                break;
            case 2:
                putRole(roles, getRoleByCategoryWithProbability(roles,RoleCategory.CORRUPTER_ANALYST,RoleCategory.CORRUPTER_SUPPORT));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
                break;
            case 3:
                putRole(roles, getRoleByTeamWithProbability(roles,Team.NEUTRAL));
                putRole(roles, getRoleByTeamWithProbability(roles,Team.FOLK));
                break;
        }

        return roles;
    }

    /**
     *
     * @param currentRoles the hash map of the roles that is created currently
     * @param roleCategory desired category
     * @return the role that is generated from the category list
     */
    private static Role getRoleByCategoryWithProbability(HashMap<Role,Integer> currentRoles, RoleCategory roleCategory){
        List<Role> roles = new ArrayList<>(getRolesByCategory(roleCategory));
        removeMaxCount(currentRoles,roles);

        return getRoleWithProbability(roles);
    }

    /**
     *
     * @param currentRoles the hash map of the roles that is created currently
     * @param roleCategories desired categories
     * @return the role that is generated from the categories list
     */
    private static Role getRoleByCategoryWithProbability(HashMap<Role,Integer> currentRoles , RoleCategory... roleCategories){
        List<Role> roles = new ArrayList<>();
        for(RoleCategory roleCategory: roleCategories){

            roles.addAll(getRolesByCategory(roleCategory));
        }
        removeMaxCount(currentRoles,roles);
        return getRoleWithProbability(roles);
    }

    /**
     *
     * @param currentRoles the hash map of the roles that is created currently
     * @param team desired team
     * @return the role that is generated from the team list
     */
    private static Role getRoleByTeamWithProbability(HashMap<Role,Integer> currentRoles, Team team){

        List<Role> roles = new ArrayList<>(getRolesByTeam(team));
        removeMaxCount(currentRoles,roles);
        return getRoleWithProbability(roles);
    }

    /**
     * Removes the roles that are already in their max count
     * @param currentRoles the hash map of the roles that is created currently
     * @param randomRoleList the list that consists of desired roles
     */
    private static void removeMaxCount(HashMap<Role,Integer> currentRoles, List<Role> randomRoleList){
        for(Map.Entry<Role,Integer> entry : currentRoles.entrySet()){
            if(entry.getKey().chanceProperty.maxNumber()<=entry.getValue()){
                randomRoleList.remove(entry.getKey());
            }
        }
    }

    /**
     * @param randomRoleList the list that consists of desired roles
     * @return a generated role from the randomRoleList with the probability of the roles
     */
    private static Role getRoleWithProbability(List<Role> randomRoleList){

        int sum = randomRoleList.stream().mapToInt(role -> role.getChanceProperty().chance()).sum();
        int randNum = new Random().nextInt(sum);
        int currentSum = 0;

        for (Role role : randomRoleList) {
            currentSum += role.chanceProperty.chance();

            if (currentSum >= randNum) {
                return role.copy();
            }
        }
        return null;
    }


}
