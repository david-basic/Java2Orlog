package hr.algebra.java2orlog.models;

import java.io.Serializable;

public class SerializableMatchData implements Serializable {
    private Integer p1DamageTaken;
    private Integer p2DamageTaken;
    private Integer p1CoinCount;
    private Integer p2CoinCount;
    private Integer turnCount;
    private Integer roundCount;
    private Boolean playerOneTurn;

    public SerializableMatchData(Integer p1DamageTaken, Integer p2DamageTaken, Integer p1CoinCount, Integer p2CoinCount, Integer turnCount, Integer roundCount, Boolean playerOneTurn) {
        this.p1DamageTaken = p1DamageTaken;
        this.p2DamageTaken = p2DamageTaken;
        this.p1CoinCount = p1CoinCount;
        this.p2CoinCount = p2CoinCount;
        this.turnCount = turnCount;
        this.roundCount = roundCount;
        this.playerOneTurn = playerOneTurn;
    }

    public Integer getP1DamageTaken() {
        return p1DamageTaken;
    }

    public void setP1DamageTaken(Integer p1DamageTaken) {
        this.p1DamageTaken = p1DamageTaken;
    }

    public Integer getP2DamageTaken() {
        return p2DamageTaken;
    }

    public void setP2DamageTaken(Integer p2DamageTaken) {
        this.p2DamageTaken = p2DamageTaken;
    }

    public Integer getP1CoinCount() {
        return p1CoinCount;
    }

    public void setP1CoinCount(Integer p1CoinCount) {
        this.p1CoinCount = p1CoinCount;
    }

    public Integer getP2CoinCount() {
        return p2CoinCount;
    }

    public void setP2CoinCount(Integer p2CoinCount) {
        this.p2CoinCount = p2CoinCount;
    }

    public Integer getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(Integer turnCount) {
        this.turnCount = turnCount;
    }

    public Integer getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(Integer roundCount) {
        this.roundCount = roundCount;
    }

    public Boolean getPlayerOneTurn() {
        return playerOneTurn;
    }

    public void setPlayerOneTurn(Boolean playerOneTurn) {
        this.playerOneTurn = playerOneTurn;
    }
}
