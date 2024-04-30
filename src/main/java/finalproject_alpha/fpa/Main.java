package finalproject_alpha.fpa;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
//Main: This class contains the main method that launches the application. It initializes a PlayerService, loads player data into the service,
// initializes a PlayerUI with the service, and builds the user interface.
// It also sets up the primary stage of the application, including setting the title, setting the scene, and showing the stage.



public class Main extends Application {                                                           //This line declares a class named Main that extends Application.


    @Override
    public void start(Stage primaryStage) {                                                        //This line overrides the start method of the Application class to configure the primary stage of the application.
        // Initialize the PlayerService
        PlayerService playerService = new PlayerService();                                       //This line creates a new PlayerService instance.
        // Load players data into the service (Assuming you have a method to do that)
        playerService.loadPlayersFromCSV();                                                       //This line loads player data from a CSV file into the service.

        // Initialize the PlayerUI with the PlayerService
        PlayerUI playerUI = new PlayerUI(playerService);                                         //This line creates a new PlayerUI instance with the PlayerService.

        // Build the UI
        VBox root = playerUI.buildUI();                                                          //This line builds the user interface and stores the root node in a VBox.

        // Create the scene
        Scene scene = new Scene(root, 800, 600);                                             //This line creates a new Scene with the root node as the root and a size of 800x600.

        primaryStage.setOnCloseRequest(e -> {
            playerService.savePlayersToCSV();                                                 //This line saves player data to a CSV file when the application is closed.
        });

        // Configure the primary stage
        primaryStage.setTitle("Player Management System");                                  //This line sets the title of the primary stage.
        primaryStage.setScene(scene);                                                     //This line sets the scene of the primary stage.
        primaryStage.show();                                                        //This line shows the primary stage.
    }

    public static void main(String[] args) {
        launch(args);
    }                                                                                //This line declares the main method that launches the application.
}