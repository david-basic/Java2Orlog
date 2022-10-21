package hr.algebra.java2orlog.models;

import java.util.List;

public class MoveDetails {
    private Integer roundNumber;
    private Integer turnNumber;
    private String playerOnTurnName;
    private String player1Name;
    private String player2Name;
    private List<DiceSymbols> symbolsPlayerPlayed;
    private List<DiceSymbols> symbolsPlayerOnTurnDidntPlay;
    private Boolean isTurnOver;
    private Integer player1DamageTaken;
    private Integer player2DamageTaken;
    private Integer player1CurrentCoins;
    private Integer player2CurrentCoins;
    private String player1GodFavorUsed;
    private String player2GodFavorUsed;
    private Boolean isRoundOver;
    private Boolean isGameOver;
    private String winnerNameOrDraw;

    public MoveDetails(Integer roundNumber, Integer turnNumber, String playerOnTurnName, String player1Name, String player2Name, List<DiceSymbols> symbolsPlayerPlayed, List<DiceSymbols> symbolsPlayerOnTurnDidntPlay, Boolean isTurnOver, Integer player1DamageTaken, Integer player2DamageTaken, Integer player1CurrentCoins, Integer player2CurrentCoins, String player1GodFavorUsed, String player2GodFavorUsed, Boolean isRoundOver, Boolean isGameOver, String winnerNameOrDraw) {
        this.roundNumber = roundNumber;
        this.turnNumber = turnNumber;
        this.playerOnTurnName = playerOnTurnName;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.symbolsPlayerPlayed = symbolsPlayerPlayed;
        this.symbolsPlayerOnTurnDidntPlay = symbolsPlayerOnTurnDidntPlay;
        this.isTurnOver = isTurnOver;
        this.player1DamageTaken = player1DamageTaken;
        this.player2DamageTaken = player2DamageTaken;
        this.player1CurrentCoins = player1CurrentCoins;
        this.player2CurrentCoins = player2CurrentCoins;
        this.player1GodFavorUsed = player1GodFavorUsed;
        this.player2GodFavorUsed = player2GodFavorUsed;
        this.isRoundOver = isRoundOver;
        this.isGameOver = isGameOver;
        this.winnerNameOrDraw = winnerNameOrDraw;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public String getPlayerOnTurnName() {
        return playerOnTurnName;
    }

    public void setPlayerOnTurnName(String playerOnTurnName) {
        this.playerOnTurnName = playerOnTurnName;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public List<DiceSymbols> getSymbolsPlayerPlayed() {
        return symbolsPlayerPlayed;
    }

    public void setSymbolsPlayerPlayed(List<DiceSymbols> symbolsPlayerPlayed) {
        this.symbolsPlayerPlayed = symbolsPlayerPlayed;
    }

    public List<DiceSymbols> getSymbolsPlayerOnTurnDidntPlay() {
        return symbolsPlayerOnTurnDidntPlay;
    }

    public void setSymbolsPlayerOnTurnDidntPlay(List<DiceSymbols> symbolsPlayerOnTurnDidntPlay) {
        this.symbolsPlayerOnTurnDidntPlay = symbolsPlayerOnTurnDidntPlay;
    }

    public Boolean getTurnOver() {
        return isTurnOver;
    }

    public void setTurnOver(Boolean turnOver) {
        isTurnOver = turnOver;
    }

    public Integer getPlayer1DamageTaken() {
        return player1DamageTaken;
    }

    public void setPlayer1DamageTaken(Integer player1DamageTaken) {
        this.player1DamageTaken = player1DamageTaken;
    }

    public Integer getPlayer2DamageTaken() {
        return player2DamageTaken;
    }

    public void setPlayer2DamageTaken(Integer player2DamageTaken) {
        this.player2DamageTaken = player2DamageTaken;
    }

    public Integer getPlayer1CurrentCoins() {
        return player1CurrentCoins;
    }

    public void setPlayer1CurrentCoins(Integer player1CurrentCoins) {
        this.player1CurrentCoins = player1CurrentCoins;
    }

    public Integer getPlayer2CurrentCoins() {
        return player2CurrentCoins;
    }

    public void setPlayer2CurrentCoins(Integer player2CurrentCoins) {
        this.player2CurrentCoins = player2CurrentCoins;
    }

    public String getPlayer1GodFavorUsed() {
        return player1GodFavorUsed;
    }

    public void setPlayer1GodFavorUsed(String player1GodFavorUsed) {
        this.player1GodFavorUsed = player1GodFavorUsed;
    }

    public String getPlayer2GodFavorUsed() {
        return player2GodFavorUsed;
    }

    public void setPlayer2GodFavorUsed(String player2GodFavorUsed) {
        this.player2GodFavorUsed = player2GodFavorUsed;
    }

    public Boolean getRoundOver() {
        return isRoundOver;
    }

    public void setRoundOver(Boolean roundOver) {
        isRoundOver = roundOver;
    }

    public Boolean getGameOver() {
        return isGameOver;
    }

    public void setGameOver(Boolean gameOver) {
        isGameOver = gameOver;
    }

    public String getWinnerNameOrDraw() {
        return winnerNameOrDraw;
    }

    public void setWinnerNameOrDraw(String winnerNameOrDraw) {
        this.winnerNameOrDraw = winnerNameOrDraw;
    }
}
