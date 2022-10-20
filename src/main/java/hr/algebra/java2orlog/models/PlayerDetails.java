package hr.algebra.java2orlog.models;

public class PlayerDetails {
    private String playerName;
    private Integer numberOfWins;
    private Integer numberOfDraws;
    private Integer numberOfLost;

    public PlayerDetails(String playerName) {
        this.playerName = playerName;
        numberOfWins = 0;
        numberOfDraws = 0;
        numberOfLost = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void recordAWin(){
        numberOfWins++;
    }
    public void recordADraw(){
        numberOfDraws++;
    }
    public void recordALoss(){
        numberOfLost++;
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
