package hr.algebra.java2orlog.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerDetails {
    private StringProperty playerName;
    private StringProperty numberOfWins;
    private StringProperty numberOfDraws;
    private StringProperty numberOfLost;

    public PlayerDetails(String playerName, String numberOfWins, String numberOfDraws, String numberOfLost) {
        this.playerName = new SimpleStringProperty(playerName);
        this.numberOfWins = new SimpleStringProperty(numberOfWins);
        this.numberOfDraws = new SimpleStringProperty(numberOfDraws);
        this.numberOfLost = new SimpleStringProperty(numberOfLost);
    }

    public String getPlayerName() {
        return playerName.get();
    }
    public StringProperty playerNameProperty() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public String getNumberOfWins() {
        return numberOfWins.get();
    }
    public StringProperty numberOfWinsProperty() {
        return numberOfWins;
    }
    public void setNumberOfWins(String numberOfWins) {
        this.numberOfWins.set(numberOfWins);
    }

    public String getNumberOfDraws() {
        return numberOfDraws.get();
    }
    public StringProperty numberOfDrawsProperty() {
        return numberOfDraws;
    }
    public void setNumberOfDraws(String numberOfDraws) {
        this.numberOfDraws.set(numberOfDraws);
    }

    public String getNumberOfLost() {
        return numberOfLost.get();
    }
    public StringProperty numberOfLostProperty() {
        return numberOfLost;
    }
    public void setNumberOfLost(String numberOfLost) {
        this.numberOfLost.set(numberOfLost);
    }
}
