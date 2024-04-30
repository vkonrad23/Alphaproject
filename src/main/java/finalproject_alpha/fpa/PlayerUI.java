package finalproject_alpha.fpa;

import finalproject_alpha.fpa.Player;
import finalproject_alpha.fpa.PlayerService;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
//
//PlayerUI: This class is responsible for the user interface that allows users to interact with the player data. It contains a PlayerService instance to interact with the player data.
// The user interface includes a list view to display players, a text field for searching players, a combo box for filtering players by position,
// a button for sorting players by score, and a text area for displaying player details.
// The buildUI method constructs the user interface.
// The updateListView, sortPlayersByScore, and showPlayerDetails methods are used to update the user interface based on user interactions.
//
//
//
//
//
//
//
//
//

public class PlayerUI {//it means that the class PlayerUI is not a subclass of another class
    private PlayerService playerService; // Assume this service is properly initialized and injected.
    private ListView<Player> playerListView; //it means that the class PlayerUI has a field called playerListView of type ListView<Player>
    private TextField searchField;// means that the class PlayerUI has a field called searchField of type TextField
    private ComboBox<String> positionFilter; //
    private Button sortScoreButton;
    private TextArea playerDetails;

    public PlayerUI(PlayerService service) {
        this.playerService = service;
    }

    public VBox buildUI() {
        VBox vbox = new VBox(10);
        HBox searchAndFilter = new HBox(5);

        // Search field
        searchField = new TextField();
        searchField.setPromptText("Search by name");
        searchField.setOnKeyReleased(e -> updateListView(searchField.getText(), positionFilter.getValue()));

        // Position filter
        positionFilter = new ComboBox<>();
        positionFilter.getItems().addAll("Goalkeeper", "Defender", "Midfielder", "Forward");
        positionFilter.setOnAction(e -> updateListView(searchField.getText(), positionFilter.getValue()));

        // Sort button
        sortScoreButton = new Button("Sort by Score");
        sortScoreButton.setOnAction(e -> sortPlayersByScore());

        // Player details area
        playerDetails = new TextArea();
        playerDetails.setEditable(false);

        // List view for players
        playerListView = new ListView<>();
        playerListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> showPlayerDetails(newSelection));

        playerListView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Player selectedPlayer = playerListView.getSelectionModel().getSelectedItem();
                if (selectedPlayer != null) {
                    new PlayerForm(selectedPlayer).display();
                }
            }
        });

        // Assemble search and filter bar
        searchAndFilter.getChildren().addAll(new Label("Search:"), searchField, new Label("Filter by Position:"), positionFilter, sortScoreButton);

        // Main layout
        vbox.getChildren().addAll(searchAndFilter, playerListView, playerDetails);
        return vbox;
    }

    private void updateListView(String searchText, String filterPosition) {
        List<Player> filteredPlayers = playerService.getAllPlayers();
        if (filterPosition != null && !filterPosition.isEmpty()) {
            filteredPlayers = playerService.filterPlayersByPosition(filterPosition);
        }
        if (searchText != null && !searchText.isEmpty()) {
            filteredPlayers = filteredPlayers.stream()
                    .filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                            player.getSurname().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
        }
        playerListView.getItems().setAll(filteredPlayers);
    }

    private void sortPlayersByScore() {
        playerListView.getItems().setAll(playerService.sortPlayersByScore());
    }

    private void showPlayerDetails(Player player) {
        if (player != null) {
            StringBuilder details = new StringBuilder();
            details.append("Name: ").append(player.getName()).append(" ").append(player.getSurname()).append("\n");
            details.append("Team: ").append(player.getTeam()).append("\n");
            details.append("Position: ").append(player.getPosition()).append("\n");
            details.append("Statistics: ").append(player.getStatistics().toString()).append("\n");
            details.append("Bio: ").append(player.getBio()).append("\n");
            details.append("Main Photo: ").append(player.getMainPhotoPath()).append("\n");
            // Here you can also add an ImageView to show the main photo, etc.
            playerDetails.setText(details.toString());
        }
    }



}