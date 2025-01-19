package com.rolegame.game.GUI.Controllers;

import com.rolegame.game.GUI.Boxes.MessageBox;
import com.rolegame.game.GUI.Boxes.PlayerSelectionBox;
import com.rolegame.game.GUI.Boxes.RoleBox;
import com.rolegame.game.GameManagement.*;
import com.rolegame.game.PropertyControllers.LanguageManager;
import com.rolegame.game.Roles.*;
import com.rolegame.game.Roles.RoleProperties.*;
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

public class GameScreenController {

    @FXML
    private TextField abilitiesTextField;

    @FXML
    private ListView<PlayerSelectionBox> alivePlayersListView;

    @FXML
    private ListView<MessageBox> announcementsView;

    @FXML
    private TextField attributesTextField;

    @FXML
    private TextField goalTextField;

    @FXML
    private ListView<String> graveListView;

    @FXML
    private Label roleLabel;

    @FXML
    private TreeView<Object> rolesTreeView;

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
    private ListView<MessageBox> announcementsListView;

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

    @FXML
    private Button startDayButton;


    private static GameController gameController;

    private static final ArrayList<PlayerSelectionBox> playerSelectionBoxes = new ArrayList<>();

    @FXML
    void useAbilityClicked(ActionEvent event) {

        if(gameController.getCurrentPlayer().getRole().getChoosenPlayer()==null){

            if(gameController.isDay()||(!gameController.isDay()&& gameController.getCurrentPlayer().getRole() instanceof ActiveNightAbility)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(LanguageManager.getText("Menu.passAlertTitle"));
                alert.setHeaderText(LanguageManager.getText("Menu.passAlertHead"));
                alert.setContentText(LanguageManager.getText("Menu.passAlertMessage"));

                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() != ButtonType.OK){
                    return;
                }
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
        TreeItem<Object> selectedRole = rolesTreeView.getSelectionModel().getSelectedItem();
        if(selectedRole!=null&&selectedRole.getValue() instanceof Role){
            midBox.getChildren().clear();
            midBox.getChildren().add(new RoleBox((Role) selectedRole.getValue()));
        }
    }

    private void initializeRolesView(){
        TreeItem<Object> roles = new TreeItem<>(LanguageManager.getText("Role.role"));
        TreeItem<Object> folk = new TreeItem<>(LanguageManager.getText("Role.folkRole"));
        TreeItem<Object> corrupter = new TreeItem<>(LanguageManager.getText("Role.corrupterRole"));
        TreeItem<Object> neutral = new TreeItem<>(LanguageManager.getText("Role.neutralRole"));

        TreeItem<Object> folkAnalyst = new TreeItem<>(LanguageManager.getText("Role.folkAnalyst"));
        TreeItem<Object> folkProtector = new TreeItem<>(LanguageManager.getText("Role.folkProtector"));
        TreeItem<Object> folkKilling = new TreeItem<>(LanguageManager.getText("Role.folkKilling"));
        TreeItem<Object> folkSupport = new TreeItem<>(LanguageManager.getText("Role.folkSupport"));

        TreeItem<Object> corrupterAnalyst = new TreeItem<>(LanguageManager.getText("Role.corrupterAnalyst"));
        TreeItem<Object> corrupterKilling = new TreeItem<>(LanguageManager.getText("Role.corrupterKilling"));
        TreeItem<Object> corrupterSupport = new TreeItem<>(LanguageManager.getText("Role.corrupterSupport"));

        TreeItem<Object> neutralEvil = new TreeItem<>(LanguageManager.getText("Role.neutralEvil"));
        TreeItem<Object> neutralKilling = new TreeItem<>(LanguageManager.getText("Role.neutralKilling"));
        TreeItem<Object> neutralChaos = new TreeItem<>(LanguageManager.getText("Role.neutralChaos"));

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
            if(player.isAlive()){
                PlayerSelectionBox playerSelectionBox = new PlayerSelectionBox(player,gameController.getCurrentPlayer(), gameController.isDay());
                playerSelectionBoxes.add(playerSelectionBox);
                alivePlayersListView.getItems().add(playerSelectionBox);
            }

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
            gameController.setDayCount(getGameController().getDayCount()+1);
            gameController.updateAlivePlayers();
            gameController.checkGameFinished();

        }
        else{

            gameController.executeMaxVoted();
            gameController.updateAlivePlayers();
            for(Player player: gameController.getAlivePlayers()){
                player.getRole().setChoosenPlayer(null);
            }

            useAbilityButton.setText(LanguageManager.getText("Menu.useAbility"));
            if(!(gameController.getCurrentPlayer() instanceof ActiveNightAbility)){
                useAbilityButton.setText("Pass Turn");
            }
            dayNightIcon.setImage(new Image("/com/rolegame/game/images/night.png"));
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
            alivePlayer.setImmune(false);

        }
        initializeMessages();


    }

    private void initializeMessages(){
        announcementsView.getItems().clear();
        for(Message message: Message.getMessages()){
            if(message.isPublic() || message.getReceiver().equals(gameController.getCurrentPlayer())){
                announcementsView.getItems().add(new MessageBox(message,announcementsView));
            }
        }

    }

    private void dayStartAnnouncements(){
        announceBigVBox.getChildren().remove(startDayButton);
        announcementsListView.getItems().clear();
        for(Message message: Message.getMessages()){
            if(message.isPublic()&&message.getDayCount() == gameController.getDayCount()){
                announcementsListView.getItems().add(new MessageBox(message,announcementsView));
            }
        }
        announceBigVBox.getChildren().add(startDayButton);
        announceBigVBox.setVisible(true);
    }


    public static GameController getGameController() {
        return gameController;
    }

    public static ArrayList<PlayerSelectionBox> getPlayerSelectionBoxes() {
        return playerSelectionBoxes;
    }
}
