package hr.algebra.java2orlog.models;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class DiceDetails {
    private Button diceButton;
    private List<DiceSymbols> diceSymbols;
    private Boolean isChosenFromDiceTray;
    private Boolean canBeSentToCenter;

    public DiceDetails(Button diceButton, List<DiceSymbols> diceSymbols, Boolean isChosenFromDiceTray, Boolean canBeSentToCenter) {
        this.diceButton = diceButton;
        this.diceSymbols = new ArrayList<>(diceSymbols);
        this.isChosenFromDiceTray = isChosenFromDiceTray;
        this.canBeSentToCenter = canBeSentToCenter;
    }

    public List<DiceSymbols> getDiceSymbols() {
        return diceSymbols;
        //return new ArrayList<>(diceSymbols); // da ne bi imao problema sa time da se shuffle radi na obje liste samo vrati ovdje kopiju
    }
    public void setDiceSymbols(List<DiceSymbols> diceSymbols) {
        this.diceSymbols = diceSymbols;
    }

    public Boolean getIsChosenFromDiceTray() {
        return isChosenFromDiceTray;
    }
    public void setIsChosenFromDiceTray(Boolean chosenFromDiceTray) {
        isChosenFromDiceTray = chosenFromDiceTray;
    }

    public Button getDiceButton() {
        return diceButton;
    }

    public Boolean getCanBeSentToCenter() {
        return canBeSentToCenter;
    }
    public void setCanBeSentToCenter(Boolean alreadyInMiddle) {
        canBeSentToCenter = alreadyInMiddle;
    }
}
