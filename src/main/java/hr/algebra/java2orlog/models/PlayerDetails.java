package hr.algebra.java2orlog.models;

public class PlayerDetails {
    private String playerName;
    private Integer numberOfWins;
    private Integer numberOfDraws;
    private Integer numberOfLost;

    public PlayerDetails(String playerName, Integer numberOfWins, Integer numberOfDraws, Integer numberOfLost) {
        this.playerName = playerName;
        this.numberOfWins = numberOfWins;
        this.numberOfDraws = numberOfDraws;
        this.numberOfLost = numberOfLost;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setNumberOfWins(Integer numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
    public void setNumberOfDraws(Integer numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }
    public void setNumberOfLost(Integer numberOfLost) {
        this.numberOfLost = numberOfLost;
    }
    public Integer getNumberOfWins() {
        return numberOfWins;
    }
    public Integer getNumberOfDraws() {
        return numberOfDraws;
    }
    public Integer getNumberOfLost() {
        return numberOfLost;
    }
}
