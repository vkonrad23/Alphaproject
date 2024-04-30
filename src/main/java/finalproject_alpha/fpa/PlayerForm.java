package finalproject_alpha.fpa;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerForm {
    private Player player;
    private TextField nameField;
    private TextField surnameField;
    private TextField teamField;
    //
    //PlayerService: This class is not shown in the provided code, but it seems to be responsible for managing player data.
    // It is likely that this class contains methods for loading and saving player data, as well as for manipulating it (e.g., filtering and sorting players).
    public PlayerForm(Player player) {
        this.player = player;
    }//This line declares a constructor for PlayerForm that takes a Player as a parameter and assigns it to the player field.

    public void display() {//This line declares a method named display that shows a modal window for editing player information.
        Stage window = new Stage();//This line creates a new Stage.
        window.initModality(Modality.APPLICATION_MODAL); //This line sets the modality of the window to APPLICATION_MODAL, making it a modal window.
        window.setTitle("Edit Player");//This line sets the title of the window to "Edit Player".

        GridPane grid = new GridPane();//This line creates a new GridPane.
        grid.setVgap(10);//This line sets the vertical gap between cells in the grid to 10.
        grid.setHgap(10);//This line sets the horizontal gap between cells in the grid to 10.

        // Name field
        nameField = new TextField(player.getName());//This line creates a new TextField with the player's name as the initial text.
        grid.add(new Label("Name:"), 0, 0);//This line adds a label "Name:" to the grid at position (0, 0).
        grid.add(nameField, 1, 0);//This line adds the name field to the grid at position (1, 0).

        // Surname field
        surnameField = new TextField(player.getSurname());//This line creates a new TextField with the player's surname as the initial text.
        grid.add(new Label("Surname:"), 0, 1);//This line adds a label "Surname:" to the grid at position (0, 1).
        grid.add(surnameField, 1, 1);//This line adds the surname field to the grid at position (1, 1).

        // Team field
        teamField = new TextField(player.getTeam());//This line creates a new TextField with the player's team as the initial text.
        grid.add(new Label("Team:"), 0, 2);//This line adds a label "Team:" to the grid at position (0, 2).
        grid.add(teamField, 1, 2);//This line adds the team field to the grid at position (1, 2).

        // Save button
        Button saveButton = new Button("Save");//This line creates a new Button with the text "Save".
        saveButton.setOnAction(e -> savePlayerInfo());//This line sets an event handler that calls the savePlayerInfo method when the button is clicked.
        grid.add(saveButton, 1, 3);//This line adds the save button to the grid at position (1, 3).

        Scene scene = new Scene(grid, 300, 200);//This line creates a new Scene with the grid as the root node and a size of 300x200.
        window.setScene(scene);//This line sets the scene of the window to the created scene.
        window.showAndWait();//This line shows the window as a modal dialog and waits for it to be closed.
    }

    private void savePlayerInfo() {//This line declares a method named savePlayerInfo that updates the player information based on the input fields.
        player.setName(nameField.getText());//This line sets the player's name to the text in the name field.
        player.setSurname(surnameField.getText());//This line sets the player's surname to the text in the surname field.
        player.setTeam(teamField.getText());//This line sets the player's team to the text in the team field.
    }
}
//A linked list is a linear data structure where each element is a separate object, known as a node. Each node contains two fields: data and a reference (link) to the next node in the sequence.
//In this example, Node is a class that represents a node in a linked list. It has two fields: data (which stores the data for the node) and next (which is a reference to the next node in the list). The Node constructor initializes these fields.