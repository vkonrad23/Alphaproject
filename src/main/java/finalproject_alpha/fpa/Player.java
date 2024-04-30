package finalproject_alpha.fpa;

import java.util.List;
import java.util.Map;
//Player: This class represents a player in the system.
// It has attributes such as id, name, surname, team, physicalParameters, position, statistics, mainPhotoPath, additionalPhotos, description, and bio.
// It also has corresponding getter and setter methods for these attributes.
// The toString method provides a string representation of the player.

public class Player {
    private int id;
    private String name;
    private String surname;
    private String team;
    private Map<String, Double> physicalParameters;
    private String position;
    private Map<String, Integer> statistics;
    private String mainPhotoPath;
    private List<String> additionalPhotos;
    private String description;
    private String bio;

    public Player(int id, String name, String surname, String team, Map<String, Double> physicalParameters, String position, Map<String, Integer> statistics, String mainPhotoPath, String description, String bio) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.team = team;
        this.physicalParameters = physicalParameters;
        this.position = position;
        this.statistics = statistics;
        this.mainPhotoPath = mainPhotoPath;
        this.additionalPhotos = additionalPhotos;
        this.description = description;
        this.bio = bio;
    }

    public int getId() { // Getter for 'ID' field
        return id;
    }

    public void setId(int id) { // Setter for 'ID' field
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Map<String, Double> getPhysicalParameters() {
        return physicalParameters;
    }

    public void setPhysicalParameters(Map<String, Double> physicalParameters) {
        this.physicalParameters = physicalParameters;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<String, Integer> statistics) {
        this.statistics = statistics;
    }

    public String getMainPhotoPath() {
        return mainPhotoPath;
    }

    public void setMainPhotoPath(String mainPhotoPath) {
        this.mainPhotoPath = mainPhotoPath;
    }

    public List<String> getAdditionalPhotos() {
        return additionalPhotos;
    }

    public void setAdditionalPhotos(List<String> additionalPhotos) {
        this.additionalPhotos = additionalPhotos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " (" + this.team + ")";
    }


}