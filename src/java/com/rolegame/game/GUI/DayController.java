package com.rolegame.game.GUI;

import com.rolegame.game.GameManagement.*;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.*;
import com.rolegame.game.Roles.RoleProperties.RoleCategory;
import com.rolegame.game.Roles.RoleProperties.RoleID;
import com.rolegame.game.Roles.RoleProperties.RolePriority;
import com.rolegame.game.Roles.RoleProperties.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Optional;

public class DayController {

    @FXML
    private TextField abilitiesTextField;

    @FXML
    private ListView<PlayerSelectionBox> alivePlayersListView;

    @FXML
    private VBox announceVBox;

    @FXML
    private TextField attributesTextField;

    @FXML
    private TextField goalTextField;

    @FXML
    private ListView<String> graveListView;

    @FXML
    private Label roleLabel;

    @FXML
    private TreeView<Role> rolesTreeView;

    @FXML
    private VBox midBox;

    @FXML
    private ImageView dayNightIcon;

    @FXML
    private Label dayLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private VBox leftVBox;

    @FXML
    private Button useAbilityButton;

    @FXML
    private StackPane passTurnPane;

    @FXML
    private StackPane outerStackPane;

    @FXML
    private VBox announceVBoxStart;

    @FXML
    private VBox announceBigVBox;

    @FXML
    private Label announcementsLabel;

    @FXML
    private Label graveyardLabel;

    @FXML
    private Label allRolesLabel;

    @FXML
    private Label goalLabel;

    @FXML
    private Label attributesLabel;

    @FXML
    private Label abilitiesLabel;

    @FXML
    private Label alivePlayersLabel;


    private static GameController gameController;

    private static ArrayList<PlayerSelectionBox> playerSelectionBoxes = new ArrayList<>();

    @FXML
    void useAbilityClicked(ActionEvent event) {

        if(gameController.getCurrentPlayer().getRole().getChoosenPlayer()==null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(LanguageManager.getText("Menu.passAlertTitle"));
            alert.setHeaderText(LanguageManager.getText("Menu.passAlertHead"));
            alert.setContentText(LanguageManager.getText("Menu.passAlertMessage"));

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() != ButtonType.OK){
                return;
            }

        }
        passTurnPane.setVisible(true);
        if(gameController.isDay()){
            Voting.vote(gameController.getCurrentPlayer(),gameController.getCurrentPlayer().getRole().getChoosenPlayer());

        }
        gameController.passTurn();
        if(gameController.getCurrentPlayerIndex()==0){

            toggleDayNightCycle();

        }

        changePlayer();
    }


    @FXML
    void startDayClicked(ActionEvent event) {
        announceBigVBox.setVisible(false);
    }

    @FXML
    void interludeClicked(ActionEvent event) {
        passTurnPane.setVisible(false);
    }

    public void initialize(){

        gameController = WriteNamesController.getGameController();
        changePlayer();
        initializeRolesView();
        initializeMessages();
        dayLabel.setText((gameController.isDay() ? LanguageManager.getText("Menu.day"): LanguageManager.getText("Menu.night") ) + ": " +gameController.getDayCount());
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(announceVBox);
        leftVBox.getChildren().add(scrollPane);

        ScrollPane scrollPane1 = new ScrollPane();
        scrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane1.setContent(announceVBoxStart);
        announceBigVBox.getChildren().add(scrollPane1);

        announcementsLabel.setText(LanguageManager.getText("Menu.announcement"));
        allRolesLabel.setText(LanguageManager.getText("Menu.allRoles"));
        graveyardLabel.setText(LanguageManager.getText("Menu.graveyard"));
        goalLabel.setText(LanguageManager.getText("Menu.goal"));
        attributesLabel.setText(LanguageManager.getText("Menu.attributes"));
        abilitiesLabel.setText(LanguageManager.getText("Menu.abilities"));
        alivePlayersLabel.setText(LanguageManager.getText("Menu.alivePlayers"));
        useAbilityButton.setText(LanguageManager.getText("Menu.vote"));


    }

    @FXML
    void selectRole(MouseEvent event) {
        TreeItem<Role> selectedRole = rolesTreeView.getSelectionModel().getSelectedItem();
        if(selectedRole!=null&&!selectedRole.getValue().equals(new moldClass("Mold", Team.Folk,RoleCategory.FolkAnalyst))){
            midBox.getChildren().clear();
            midBox.getChildren().add(new RoleBox(selectedRole.getValue()));
        }
    }

    private void initializeRolesView(){
        TreeItem<Role> roles = new TreeItem<>(new moldClass(LanguageManager.getText("Role.role"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> folk = new TreeItem<>(new moldClass(LanguageManager.getText("Role.folkRole"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> corrupter = new TreeItem<>(new moldClass(LanguageManager.getText("Role.corrupterRole"), Team.Corrupter ,RoleCategory.CorrupterAnalyst));
        TreeItem<Role> neutral = new TreeItem<>(new moldClass(LanguageManager.getText("Role.neutralRole"), Team.Corrupter ,RoleCategory.CorrupterAnalyst));

        TreeItem<Role> folkAnalyst = new TreeItem<>(new moldClass(LanguageManager.getText("Role.folkAnalyst"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> folkProtector = new TreeItem<>(new moldClass(LanguageManager.getText("Role.folkProtector"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> folkKilling = new TreeItem<>(new moldClass(LanguageManager.getText("Role.folkKilling"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> folkSupport = new TreeItem<>(new moldClass(LanguageManager.getText("Role.folkSupport"), Team.Folk, RoleCategory.FolkAnalyst));

        TreeItem<Role> corrupterAnalyst = new TreeItem<>(new moldClass(LanguageManager.getText("Role.corrupterAnalyst"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> corrupterKilling = new TreeItem<>(new moldClass(LanguageManager.getText("Role.corrupterKilling"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> corrupterSupport = new TreeItem<>(new moldClass(LanguageManager.getText("Role.corrupterSupport"), Team.Folk, RoleCategory.FolkAnalyst));

        TreeItem<Role> neutralEvil = new TreeItem<>(new moldClass(LanguageManager.getText("Role.neutralEvil"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> neutralKilling = new TreeItem<>(new moldClass(LanguageManager.getText("Role.neutralKilling"), Team.Folk, RoleCategory.FolkAnalyst));
        TreeItem<Role> neutralChaos = new TreeItem<>(new moldClass(LanguageManager.getText("Role.neutralChaos"), Team.Folk, RoleCategory.FolkAnalyst));

        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FolkAnalyst)){
            folkAnalyst.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FolkProtector)){
            folkProtector.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FolkKilling)){
            folkKilling.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.FolkSupport)){
            folkSupport.getChildren().add(new TreeItem<>(role));
        }

        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.CorrupterAnalyst)){
            corrupterAnalyst.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.CorrupterKilling)){
            corrupterKilling.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.CorrupterSupport)){
            corrupterSupport.getChildren().add(new TreeItem<>(role));
        }

        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.NeutralEvil)){
            neutralEvil.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.NeutralKilling)){
            neutralKilling.getChildren().add(new TreeItem<>(role));
        }
        for(Role role : RoleCatalog.getRolesByCategory(RoleCategory.NeutralChaos)){
            neutralChaos.getChildren().add(new TreeItem<>(role));
        }


        folk.getChildren().addAll(folkAnalyst,folkProtector,folkKilling,folkSupport);
        corrupter.getChildren().addAll(corrupterAnalyst,corrupterKilling,corrupterSupport);
        neutral.getChildren().addAll(neutralEvil,neutralKilling,neutralChaos);
        roles.getChildren().addAll(folk,corrupter,neutral);


        rolesTreeView.setRoot(roles);

    }
    private void changePlayer(){
        nameLabel.setText(gameController.getCurrentPlayer().getName());
        numberLabel.setText(LanguageManager.getText("Menu.number")+": "+gameController.getCurrentPlayer().getNumber());
        abilitiesTextField.setText(gameController.getCurrentPlayer().getRole().getAbilities());
        attributesTextField.setText(gameController.getCurrentPlayer().getRole().getAttributes());
        goalTextField.setText(gameController.getCurrentPlayer().getRole().getGoal());
        roleLabel.setText(gameController.getCurrentPlayer().getRole().getName());



        alivePlayersListView.getItems().clear();

        for(Player player: gameController.getAlivePlayers()){
            PlayerSelectionBox playerSelectionBox = new PlayerSelectionBox(player,gameController.getCurrentPlayer());
            playerSelectionBoxes.add(playerSelectionBox);
            alivePlayersListView.getItems().add(playerSelectionBox);
        }

        initializeMessages();
    }

    private void toggleDayNightCycle(){
        gameController.setDay(!gameController.isDay());
        if(gameController.isDay()){

            useAbilityButton.setText(LanguageManager.getText("Menu.vote"));
            gameController.performAllAbilities();
            dayStartAnnouncements();

            for(Player player: gameController.getAlivePlayers()){
                player.getRole().setChoosenPlayer(null);

            }

            dayNightIcon.setImage(new Image("/com/rolegame/game/images/day.png"));
            gameController.setDay(true);
            gameController.setDayCount(getGameController().getDayCount()+1);
            gameController.updateAlivePlayers();
            gameController.isGameFinished();

        }
        else{

            gameController.executeMaxVoted();
            gameController.updateAlivePlayers();
            for(Player player: gameController.getAlivePlayers()){
                player.getRole().setChoosenPlayer(null);
            }
            useAbilityButton.setText(LanguageManager.getText("Menu.useAbility"));
            dayNightIcon.setImage(new Image("/com/rolegame/game/images/night.png"));
            gameController.setDay(false);
            gameController.updateAlivePlayers();
        }
        graveListView.getItems().clear();

        for(Player deadPlayer: gameController.getDeadPlayers()){
            graveListView.getItems().add(deadPlayer.toString()+" ("+deadPlayer.getRole().getName()+")");
        }
        dayLabel.setText((gameController.isDay() ? LanguageManager.getText("Menu.day") : LanguageManager.getText("Menu.night")) + ": " +gameController.getDayCount());

        for(Player alivePlayer: gameController.getAlivePlayers()){
            alivePlayer.setDefence(alivePlayer.getRole().getDefence());
            alivePlayer.getRole().setCanPerform(true);
        }
        initializeMessages();


    }

    private void initializeMessages(){
        announceVBox.getChildren().clear();
        for(Message message: Message.getMessages()){
            if(message.isPublic() || message.getReceiver().equals(gameController.getCurrentPlayer())){
                announceVBox.getChildren().add(new MessageBox(message));
            }
        }

    }

    private void dayStartAnnouncements(){
        announceVBoxStart.getChildren().clear();
        for(Message message: Message.getMessages()){
            if(message.isPublic()&&message.getDayCount() == gameController.getDayCount()){
                announceVBoxStart.getChildren().add(new MessageBox(message));
            }
        }
        announceBigVBox.setVisible(true);
    }
    private static class moldClass extends Role{

        public moldClass(String name, Team team , RoleCategory roleCategory) {
            super(RoleID.Mold, RolePriority.None, roleCategory ,name, "", team,"", "",0,0);
        }

        @Override
        public boolean performAbility() {
            return false;
        }
    }

    public static GameController getGameController() {
        return gameController;
    }

    public static ArrayList<PlayerSelectionBox> getPlayerSelectionBoxes() {
        return playerSelectionBoxes;
    }
}
