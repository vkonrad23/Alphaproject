package finalproject_alpha.fpa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PlayerService {//PlayerService: This class is responsible for managing player data. It contains a list of players and provides methods to search, filter, and sort players.
    private List<Player> players;//players: A list of Player objects. This list is used to store player data.

    public List<Player> getAllPlayers() {//This line declares a method named getAllPlayers that returns the list of players.
        return players;

    }

    public PlayerService() {
        this.players = new ArrayList<>();
    }//This line declares a constructor for PlayerService that initializes the list of players as an empty ArrayList.

//    public List<Player> searchPlayersByName(String query) {
//        return players.stream()
//                .filter(player -> player.getName().toLowerCase().contains(query.toLowerCase()))
//                .collect(Collectors.toList());
//    }

    public List<Player> searchPlayersByName(String query) {                                                                  //This line declares a method named searchPlayersByName that takes a query string as a parameter and returns a list of players whose names contain the query.
        return players.stream()                                                                                                                   //This line creates a stream from the list of players.
                .filter(player -> player.getName().toLowerCase().contains(query.toLowerCase()) ||             //This line filters the players based on whether the player's name contains the query (case-insensitive).
                        player.getSurname().toLowerCase().contains(query.toLowerCase()))                          //This line filters the players based on whether the player's surname contains the query (case-insensitive).
                .collect(Collectors.toList());                                                                                                    //This line collects the filtered players into a list and returns it.
    }

    public List<Player> filterPlayersByPosition(String position) {                                                             //This line declares a method named filterPlayersByPosition that takes a position string as a parameter and returns a list of players with the specified position.
        return players.stream()                                                                                                                   //This line creates a stream from the list of players.
                .filter(player -> player.getPosition().equalsIgnoreCase(position))                                                    //This line filters the players based on whether the player's position matches the specified position (case-insensitive).
                .collect(Collectors.toList());                                                                                                      //This line collects the filtered players into a list and returns it.
    }

    public List<Player> sortPlayersByScore() {//This line declares a method named sortPlayersByScore that sorts the players by the number of goals scored and returns the sorted list.
        return players.stream()//This line creates a stream from the list of players.
                .sorted((p1, p2) -> {//This line sorts the players based on the number of goals scored in descending order.
                    Integer goalsScored1 = p1.getStatistics().containsKey("goalsScored") ? p1.getStatistics().get("goalsScored") : 0; //This line gets the number of goals scored by the first player, or 0 if the statistic is not present.
                    Integer goalsScored2 = p2.getStatistics().containsKey("goalsScored") ? p2.getStatistics().get("goalsScored") : 0;//This line gets the number of goals scored by the second player, or 0 if the statistic is not present.
                    return Integer.compare(goalsScored2, goalsScored1);//This line compares the number of goals scored by the two players in descending order.
                }).collect(Collectors.toList());//This line collects the sorted players into a list and returns it.
    }

    public void loadPlayersFromCSV() {//This line declares a method named loadPlayersFromCSV that reads player data from a CSV file and populates the list of players.
        try (Scanner scanner = new Scanner(Paths.get("players.csv"))) {//This line creates a new Scanner to read from the players.csv file.
            // Skip the first line
            if (scanner.hasNextLine()) {//This line checks if there is a next line in the file.
                scanner.nextLine();//This line skips the first line of the file.
            }

            while (scanner.hasNextLine()) {//This line reads player data from the file line by line and adds the players to the list.
                String line = scanner.nextLine();//This line reads a line from the file.
                String[] parts = line.split(",");//
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String surname = parts[2];
                String team = parts[3];
                HashMap<String, Double> physicalParameters = new HashMap<>();
                physicalParameters.put("height", Double.parseDouble(parts[4]));
                physicalParameters.put("weight", Double.parseDouble(parts[5]));
                String position = parts[6];
                HashMap<String, Integer> statistics = new HashMap<>();
                statistics.put("gamesPlayed", Integer.parseInt(parts[7]));
                statistics.put("goalsScored", Integer.parseInt(parts[8]));
                String mainPhotoPath = parts[9];
                String description = parts[10];
                String bio = parts[11];

                Player player = new Player(id, name, surname, team, physicalParameters, position, statistics, mainPhotoPath, description, bio);
                players.add(player);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void savePlayersToCSV() {
        try (PrintWriter writer = new PrintWriter(new File("players.csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("id,name,surname,team,height,weight,position,gamesPlayed,goalsScored,mainPhotoPath,description,bio\n");
            for (Player player : players) {
                sb.append(player.getId()).append(",");
                sb.append(player.getName()).append(",");
                sb.append(player.getSurname()).append(",");
                sb.append(player.getTeam()).append(",");
                sb.append(player.getPhysicalParameters().get("height")).append(",");
                sb.append(player.getPhysicalParameters().get("weight")).append(",");
                sb.append(player.getPosition()).append(",");
                sb.append(player.getStatistics().get("gamesPlayed")).append(",");
                sb.append(player.getStatistics().get("goalsScored")).append(",");
                sb.append(player.getMainPhotoPath()).append(",");
                sb.append(player.getDescription()).append(",");
                sb.append(player.getBio()).append("\n");
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePlayer(Player updatedPlayer) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == updatedPlayer.getId()) {
                players.set(i, updatedPlayer);
                break;
            }
        }
        savePlayersToCSV();
    }


}
