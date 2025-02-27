package com.rolegame.game.gui.controllers.game;

import com.rolegame.game.gamestate.Time;
import com.rolegame.game.gui.components.boxes.DeadPlayerBox;
import com.rolegame.game.gui.components.boxes.MessageBox;
import com.rolegame.game.gui.components.boxes.PlayerSelectionBox;
import com.rolegame.game.gui.components.boxes.RoleBox;
import com.rolegame.game.gui.components.boxes.rolespecificboxes.EntrepreneurBox;
import com.rolegame.game.gui.components.boxes.rolespecificboxes.LorekeeperBox;
import com.rolegame.game.models.roles.enums.AbilityType;
import com.rolegame.game.models.roles.enums.RoleCategory;
import com.rolegame.game.models.roles.enums.Team;
import com.rolegame.game.models.roles.templates.RoleTemplate;
import com.rolegame.game.services.*;
import com.rolegame.game.managers.LanguageManager;
import com.rolegame.game.managers.SceneManager;
import com.rolegame.game.models.roles.folkroles.unique.Entrepreneur;
import com.rolegame.game.models.roles.neutralroles.good.Lorekeeper;
import com.rolegame.game.models.Message;
import com.rolegame.game.models.player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.*;

public class GameScreenController {

    @FXML
    private StackPane root;

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
    private ListView<DeadPlayerBox> graveListView;

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

    private static GameService gameService;

    private static final ArrayList<PlayerSelectionBox> playerSelectionBoxes = new ArrayList<>();

    @FXML
    void useAbilityClicked(ActionEvent event) {


        if(gameService.getCurrentPlayer().getRole().getChoosenPlayer()==null){

            AbilityType abilityType = gameService.getCurrentPlayer().getRole().getTemplate().getAbilityType();
            if(gameService.getTimeService().getTime() == Time.VOTING||
                    (gameService.getTimeService().getTime() == Time.NIGHT &&
                            !(abilityType == AbilityType.NO_ABILITY|| abilityType == AbilityType.PASSIVE))){
                Alert alert = SceneManager.createAlert(Alert.AlertType.CONFIRMATION,LanguageManager.getText("Menu","passAlertTitle"),
                        LanguageManager.getText("Menu","passAlertHead"), LanguageManager.getText("Menu","passAlertMessage"));

                Optional<ButtonType> result = alert.showAndWait();

                if(result.isPresent() && result.get() != ButtonType.OK){
                    return;
                }
            }

        }

        passTurnPane.setVisible(true);
        gameService.sendVoteMessages();

        if(gameService.passTurn()){

            toggleDayNightCycleUI();

        }
        switch (gameService.getTimeService().getTime()){
            case DAY -> setStyleImage(passTurnPane,"day");
            case VOTING -> setStyleImage(passTurnPane,"vote");
            case NIGHT -> setStyleImage(passTurnPane,"night");
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
        dayLabel.setText((gameService.getTimeService().getTime()!=Time.NIGHT ? LanguageManager.getText("Menu","day"): LanguageManager.getText("Menu","night") ) + ": " + gameService.getTimeService().getDayCount());

        announcementsLabel.setText(LanguageManager.getText("Menu","announcement"));
        allRolesLabel.setText(LanguageManager.getText("Menu","allRoles"));
        graveyardLabel.setText(LanguageManager.getText("Menu","graveyard"));
        goalLabel.setText(LanguageManager.getText("Menu","goal"));
        attributesLabel.setText(LanguageManager.getText("Menu","attributes"));
        abilitiesLabel.setText(LanguageManager.getText("Menu","abilities"));
        alivePlayersLabel.setText(LanguageManager.getText("Menu","alivePlayers"));
        useAbilityButton.setText(LanguageManager.getText("Menu","useAbility"));

        passTurnLabel.setText(LanguageManager.getText("PassTurn","turn")
                .replace("{playerName}", gameService.getCurrentPlayer().getName()));
        alivePlayersListView.setSelectionModel(null);
        announcementsListView.setSelectionModel(null);

        setStyleImage(passTurnPane,"night");


    }

    @FXML
    void selectRole(MouseEvent event) {
        TreeItem<Object> selectedRole = rolesTreeView.getSelectionModel().getSelectedItem();
        if(selectedRole!=null&&selectedRole.getValue() instanceof RoleTemplate roleTemplate){
            midBox.getChildren().clear();
            midBox.getChildren().add(new RoleBox(roleTemplate));
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
        roles.setExpanded(true);
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

        for (RoleTemplate role : RoleService.getRolesByCategory(category)) {
            String imageLocation = getImageLocation(category, role);
            Image image = loadImage(imageLocation);

            categoryItem.getChildren().add(new TreeItem<>(role, new ImageView(image)));
        }

        parent.getChildren().add(categoryItem);
    }

    private String getImageLocation(RoleCategory category, RoleTemplate role) {
        String key = (category.getTeam() == Team.NEUTRAL)
                ? LanguageManager.enumToJsonKey(role.getId().name()).toLowerCase()
                : LanguageManager.enumToJsonKey(category.getTeam().name());

        return key.replaceAll("[\\p{Zs}\\s]+", "").trim();
    }



    private Image loadImage(String imageLocation) {
        return new Image(getClass().getResourceAsStream("/com/rolegame/game/images/roles/" + imageLocation + ".jpg"));
    }

    private void changePlayerUI(){
        nameLabel.setText(gameService.getCurrentPlayer().getName());
        numberLabel.setText(LanguageManager.getText("Menu","number")+": "+ gameService.getCurrentPlayer().getNumber());
        abilitiesTextField.setText(gameService.getCurrentPlayer().getRole().getTemplate().getAbilities());
        attributesTextField.setText(gameService.getCurrentPlayer().getRole().getTemplate().getAttributes());
        goalTextField.setText(gameService.getCurrentPlayer().getRole().getTemplate().getGoal());
        roleLabel.setText(gameService.getCurrentPlayer().getRole().getTemplate().getName());

        List<PlayerSelectionBox> boxes = gameService.getAlivePlayers().stream()
                .filter(Player::isAlive)
                .map(player -> new PlayerSelectionBox(player, gameService.getCurrentPlayer(), gameService.getTimeService().getTime()))
                .toList();

        alivePlayersListView.getItems().setAll(boxes);

        playerSelectionBoxes.clear();
        playerSelectionBoxes.addAll(boxes);


        initializeMessages();
        extraPropertiesVbox.getChildren().clear();

        if(gameService.getTimeService().getTime() == Time.NIGHT){
            AbilityType abilityType = gameService.getCurrentPlayer().getRole().getTemplate().getAbilityType();
            if(abilityType == AbilityType.NO_ABILITY || abilityType == AbilityType.PASSIVE){
                useAbilityButton.setText(LanguageManager.getText("Menu","pass"));
            }else{
                useAbilityButton.setText(LanguageManager.getText("Menu","useAbility"));
            }

            switch (gameService.getCurrentPlayer().getRole().getTemplate()){
                case Entrepreneur entrepreneur -> {

                    entrepreneur.setAbilityState(Entrepreneur.ChosenAbility.NONE);
                    extraPropertiesVbox.getChildren().add(new EntrepreneurBox(entrepreneur));
                }
                case Lorekeeper lorekeeper ->{
                    extraPropertiesVbox.getChildren().add(new LorekeeperBox(lorekeeper));
                }
                default -> {}
            }

        }

        passTurnLabel.setText(LanguageManager.getText("PassTurn","turn")
                .replace("{playerName}", gameService.getCurrentPlayer().getName()));


    }

    /**
     * Implements the UI changes when time state is changed
     */
    private void toggleDayNightCycleUI(){

        switch (gameService.getTimeService().getTime()){
            case DAY -> {
                useAbilityButton.setText(LanguageManager.getText("Menu","pass"));
                gameBox.getStyleClass().remove("night");
                gameBox.getStyleClass().remove("voting");
                gameBox.getStyleClass().add("day");
                displayAnnouncements();
            }
            case VOTING -> {
                useAbilityButton.setText(LanguageManager.getText("Menu","vote"));
                gameBox.getStyleClass().remove("night");
                gameBox.getStyleClass().remove("day");
                gameBox.getStyleClass().add("voting");
            }
            case NIGHT -> {
                useAbilityButton.setText(LanguageManager.getText("Menu","useAbility"));
                gameBox.getStyleClass().remove("day");
                gameBox.getStyleClass().remove("voting");
                gameBox.getStyleClass().add("night");
                displayAnnouncements();
            }
        }


        graveListView.getItems().clear();

        for(Player deadPlayer: gameService.getDeadPlayers()){
            DeadPlayerBox deadPlayerBox = new DeadPlayerBox(
                    deadPlayer.getNameAndRole(), deadPlayer.getCausesOfDeathAsString(), graveListView);
            graveListView.getItems().add(deadPlayerBox);
        }

        dayLabel.setText((gameService.getTimeService().getTime()!=Time.NIGHT ?
                LanguageManager.getText("Menu","day") :
                LanguageManager.getText("Menu","night")) + ": " + gameService.getTimeService().getDayCount());
        initializeMessages();

    }

    /**
     * Updates and displays messages list view of a player
     */
    private void initializeMessages(){
        announcementsView.getItems().clear();
        for(Message message: gameService.getMessageService().getMessages()){
            if(message.isPublic() || message.receiver().getNumber() == gameService.getCurrentPlayer().getNumber()){
                announcementsView.getItems().add(new MessageBox(message,announcementsView));
            }
        }

    }

    /**
     * Updates announcements displays the announcement pane
     */
    private void displayAnnouncements(){
        announcementsListView.getItems().clear();
        for(Message message: gameService.getMessageService().getMessages()){

            if(message.isPublic()){

                if(gameService.getTimeService().getTime()==Time.NIGHT && message.isDay() && message.dayCount() == gameService.getTimeService().getDayCount()){
                    announcementsListView.getItems().add(new MessageBox(message,announcementsView));
                } else if(gameService.getTimeService().getTime() == Time.DAY && !message.isDay()&&message.dayCount() == gameService.getTimeService().getDayCount()-1){
                    announcementsListView.getItems().add(new MessageBox(message,announcementsView));
                }

            }


        }
        announceBigVBox.setStyle("-fx-background-image: url(/com/rolegame/game/images/announcements/table.jpg); ");
        announceBigVBox.setVisible(true);
    }


    public static GameService getGameService() {
        return gameService;
    }

    public static ArrayList<PlayerSelectionBox> getPlayerSelectionBoxes() {
        return playerSelectionBoxes;
    }

    public static void setGameService(GameService gameService) {
        GameScreenController.gameService = gameService;
    }
}
