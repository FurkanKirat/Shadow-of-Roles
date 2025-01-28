package com.rolegame.game.gui.controllers.game;

import com.rolegame.game.gui.components.boxes.MessageBox;
import com.rolegame.game.gui.components.boxes.PlayerSelectionBox;
import com.rolegame.game.gui.components.boxes.RoleBox;
import com.rolegame.game.gui.components.boxes.rolespecificboxes.EntrepreneurBox;
import com.rolegame.game.gui.components.boxes.rolespecificboxes.LorekeeperBox;
import com.rolegame.game.gameplay.*;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.models.roles.*;
import com.rolegame.game.models.roles.folkroles.unique.Entrepreneur;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.roles.roleproperties.*;
import com.rolegame.game.models.Message;
import com.rolegame.game.models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    private Label dayLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button useAbilityButton;

    @FXML
    private VBox passTurnPane;

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
    private VBox extraPropertiesVbox;

    @FXML
    private Label passTurnLabel;

    @FXML
    private HBox gameBox;

    private static GameController gameController;

    private static final ArrayList<PlayerSelectionBox> playerSelectionBoxes = new ArrayList<>();

    @FXML
    void useAbilityClicked(ActionEvent event) {

        if(gameController.getCurrentPlayer().getRole().getChoosenPlayer()==null){

            if(gameController.isDay()||(!gameController.isDay() && gameController.getCurrentPlayer().getRole() instanceof ActiveNightAbility)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(LanguageManager.getText("Menu","passAlertTitle"));
                alert.setHeaderText(LanguageManager.getText("Menu","passAlertHead"));
                alert.setContentText(LanguageManager.getText("Menu","passAlertMessage"));

                Optional<ButtonType> result = alert.showAndWait();

                if(result.isPresent() && result.get() != ButtonType.OK){
                    return;
                }
            }

        }


        passTurnPane.setVisible(true);
        gameController.sendVoteMessages();
        gameController.passTurn();
        if(gameController.getCurrentPlayerIndex()==0){

            toggleDayNightCycle();

        }
        if(gameController.isDay()){
            setStyleImage(passTurnPane,"day");
        }else{
            setStyleImage(passTurnPane,"night");
        }
        changePlayerUI();
    }

    private static void setStyleImage(Parent root, String time) {
        int randNum = new Random().nextInt(4);
        String style = "-fx-background-image: url(/com/rolegame/game/images/lobby/"+time+randNum+".jpg); " +
                "-fx-background-size: cover;";
        root.getStyleClass().clear();
        root.styleProperty().set(style);
    }


    @FXML
    void startDayClicked(MouseEvent event) {
        announceBigVBox.setVisible(false);
    }

    @FXML
    void interludeClicked(MouseEvent event) {
        passTurnPane.setVisible(false);
    }


    public void initialize(){

        changePlayerUI();
        initializeRolesView();
        initializeMessages();
        dayLabel.setText((gameController.isDay() ? LanguageManager.getText("Menu","day"): LanguageManager.getText("Menu","night") ) + ": " +gameController.getDayCount());

        announcementsLabel.setText(LanguageManager.getText("Menu","announcement"));
        allRolesLabel.setText(LanguageManager.getText("Menu","allRoles"));
        graveyardLabel.setText(LanguageManager.getText("Menu","graveyard"));
        goalLabel.setText(LanguageManager.getText("Menu","goal"));
        attributesLabel.setText(LanguageManager.getText("Menu","attributes"));
        abilitiesLabel.setText(LanguageManager.getText("Menu","abilities"));
        alivePlayersLabel.setText(LanguageManager.getText("Menu","alivePlayers"));
        useAbilityButton.setText(LanguageManager.getText("Menu","useAbility"));

        passTurnLabel.setText(LanguageManager.getText("PassTur","turn")
                .replace("{playerName}", gameController.getCurrentPlayer().getName()));
        alivePlayersListView.setSelectionModel(null);
        announcementsListView.setSelectionModel(null);


    }

    @FXML
    void selectRole(MouseEvent event) {
        TreeItem<Object> selectedRole = rolesTreeView.getSelectionModel().getSelectedItem();
        if(selectedRole!=null&&selectedRole.getValue() instanceof Role){
            midBox.getChildren().clear();
            midBox.getChildren().add(new RoleBox((Role) selectedRole.getValue()));
        }
    }

    private void initializeRolesView() {
        TreeItem<Object> roles = new TreeItem<>(LanguageManager.getText("Role", "role"));
        TreeItem<Object> folk = new TreeItem<>(LanguageManager.getText("Role", "folkRole"));
        TreeItem<Object> corrupter = new TreeItem<>(LanguageManager.getText("Role", "corrupterRole"));
        TreeItem<Object> neutral = new TreeItem<>(LanguageManager.getText("Role", "neutralRole"));

        addRoleCategories(folk, RoleCategory.FOLK_ANALYST, "folkAnalyst");
        addRoleCategories(folk, RoleCategory.FOLK_PROTECTOR, "folkProtector");
        addRoleCategories(folk, RoleCategory.FOLK_KILLING, "folkKilling");
        addRoleCategories(folk, RoleCategory.FOLK_SUPPORT, "folkSupport");
        addRoleCategories(folk, RoleCategory.FOLK_UNIQUE, "folkUnique");

        addRoleCategories(corrupter, RoleCategory.CORRUPTER_ANALYST, "corrupterAnalyst");
        addRoleCategories(corrupter, RoleCategory.CORRUPTER_KILLING, "corrupterKilling");
        addRoleCategories(corrupter, RoleCategory.CORRUPTER_SUPPORT, "corrupterSupport");

        addRoleCategories(neutral, RoleCategory.NEUTRAL_EVIL, "neutralEvil");
        addRoleCategories(neutral, RoleCategory.NEUTRAL_KILLING, "neutralKilling");
        addRoleCategories(neutral, RoleCategory.NEUTRAL_CHAOS, "neutralChaos");
        addRoleCategories(neutral, RoleCategory.NEUTRAL_GOOD, "neutralGood");

        roles.getChildren().addAll(folk, corrupter, neutral);

        rolesTreeView.setRoot(roles);
    }

    /**
     * Helper method of the initializeRolesView(), adds the role categories to their parent
     * @param parent
     * @param category
     * @param languageKey
     */
    private void addRoleCategories(TreeItem<Object> parent, RoleCategory category, String languageKey) {
        TreeItem<Object> categoryItem = new TreeItem<>(LanguageManager.getText("Role", languageKey));
        for (Role role : RoleCatalog.getRolesByCategory(category)) {
            categoryItem.getChildren().add(new TreeItem<>(role));
        }
        parent.getChildren().add(categoryItem);
    }

    private void changePlayerUI(){
        nameLabel.setText(gameController.getCurrentPlayer().getName());
        numberLabel.setText(LanguageManager.getText("Menu","number")+": "+gameController.getCurrentPlayer().getNumber());
        abilitiesTextField.setText(gameController.getCurrentPlayer().getRole().getAbilities());
        attributesTextField.setText(gameController.getCurrentPlayer().getRole().getAttributes());
        goalTextField.setText(gameController.getCurrentPlayer().getRole().getGoal());
        roleLabel.setText(gameController.getCurrentPlayer().getRole().getName());

        List<PlayerSelectionBox> boxes = gameController.getAlivePlayers().stream()
                .filter(Player::isAlive)
                .map(player -> new PlayerSelectionBox(player, gameController.getCurrentPlayer(), gameController.isDay()))
                .toList();

        alivePlayersListView.getItems().setAll(boxes);

        playerSelectionBoxes.clear();
        playerSelectionBoxes.addAll(boxes);


        initializeMessages();
        extraPropertiesVbox.getChildren().clear();
        if(gameController.getCurrentPlayer().getRole() instanceof Entrepreneur entrepreneur && !gameController.isDay()){
            entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.NONE);
            extraPropertiesVbox.getChildren().add(new EntrepreneurBox(entrepreneur));
        }
        else if(gameController.getCurrentPlayer().getRole() instanceof Lorekeeper lorekeeper && !gameController.isDay()){
            extraPropertiesVbox.getChildren().add(new LorekeeperBox(lorekeeper));
        }
        passTurnLabel.setText(LanguageManager.getText("PassTurn","turn")
                .replace("{playerName}", gameController.getCurrentPlayer().getName()));
    }

    private void toggleDayNightCycle(){
        gameController.setDay(!gameController.isDay());
        if(gameController.isDay()){

            useAbilityButton.setText(LanguageManager.getText("Menu","vote"));
            gameBox.getStyleClass().remove("night");
            gameBox.getStyleClass().add("day");

            gameController.performAllAbilities();
            dayStartAnnouncements();

            gameController.setDayCount(getGameController().getDayCount()+1);

        }
        else{

            gameController.executeMaxVoted();
            gameController.updateAlivePlayers();

            dayStartAnnouncements();
            useAbilityButton.setText(LanguageManager.getText("Menu","useAbility"));
            if(!(gameController.getCurrentPlayer() instanceof ActiveNightAbility)){
                useAbilityButton.setText("Pass Turn");
            }
            gameBox.getStyleClass().remove("day");
            gameBox.getStyleClass().add("night");
        }
        gameController.updateAlivePlayers();
        gameController.checkGameFinished();
        graveListView.getItems().clear();

        for(Player deadPlayer: gameController.getDeadPlayers()){
            graveListView.getItems().add(deadPlayer.toString()+" ("+deadPlayer.getRole().getName()+")");
        }
        dayLabel.setText((gameController.isDay() ? LanguageManager.getText("Menu","day") : LanguageManager.getText("Menu","night")) + ": " +gameController.getDayCount());


        initializeMessages();


    }

    /**
     * Updates and displays messages list view of a player
     */
    private void initializeMessages(){
        announcementsView.getItems().clear();
        for(Message message: Message.getMessages()){
            if(message.isPublic() || message.receiver().equals(gameController.getCurrentPlayer())){
                announcementsView.getItems().add(new MessageBox(message,announcementsView));
            }
        }

    }

    /**
     * Updates announcements displays the announcement pane
     */
    private void dayStartAnnouncements(){
        announcementsListView.getItems().clear();
        for(Message message: Message.getMessages()){
            if(message.isPublic()&&message.dayCount() == gameController.getDayCount()){
                announcementsListView.getItems().add(new MessageBox(message,announcementsView));
            }
        }
        announceBigVBox.setStyle("-fx-background-image: url(/com/rolegame/game/images/announcements/table.jpg); ");
        announceBigVBox.setVisible(true);
    }


    public static GameController getGameController() {
        return gameController;
    }

    public static ArrayList<PlayerSelectionBox> getPlayerSelectionBoxes() {
        return playerSelectionBoxes;
    }

    public static void setGameController(GameController gameController) {
        GameScreenController.gameController = gameController;
    }
}
