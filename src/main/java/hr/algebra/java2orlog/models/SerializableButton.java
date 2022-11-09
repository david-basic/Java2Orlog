package hr.algebra.java2orlog.models;

import java.io.Serializable;

public class SerializableButton implements Serializable {
    private DiceSymbols symbol;
    private int positionX;
    private int positionY;
    private Boolean playerOneTrayDice; // if they are dice of player 1 then this is true

    public SerializableButton(DiceSymbols symbol, int positionX, int positionY, Boolean playerOneTrayDice) {
        this.symbol = symbol;
        this.positionX = positionX;
        this.positionY = positionY;
        this.playerOneTrayDice = playerOneTrayDice;
    }

    public DiceSymbols getSymbol() {
        return symbol;
    }

    public void setSymbol(DiceSymbols symbol) {
        this.symbol = symbol;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Boolean getPlayerOneTrayDice() {
        return playerOneTrayDice;
    }

    public void setPlayerOneTrayDice(Boolean playerOneTrayDice) {
        this.playerOneTrayDice = playerOneTrayDice;
    }
}
