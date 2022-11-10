package hr.algebra.java2orlog.models;

import java.io.Serializable;
import java.util.List;

public class SerializableDiceDetails implements Serializable {
    private SerializableButton button;
    private List<DiceSymbols> diceSymbols;
    private Boolean isChosenFromDiceTray;
    private Boolean canBeSentToCenter;

    public SerializableDiceDetails(SerializableButton button, List<DiceSymbols> diceSymbols, Boolean isChosenFromDiceTray, Boolean canBeSentToCenter) {
        this.button = button;
        this.diceSymbols = diceSymbols;
        this.isChosenFromDiceTray = isChosenFromDiceTray;
        this.canBeSentToCenter = canBeSentToCenter;
    }

    public SerializableButton getButton() {
        return button;
    }

    public void setButton(SerializableButton button) {
        this.button = button;
    }

    public List<DiceSymbols> getDiceSymbols() {
        return diceSymbols;
    }

    public void setDiceSymbols(List<DiceSymbols> diceSymbols) {
        this.diceSymbols = diceSymbols;
    }

    public Boolean getChosenFromDiceTray() {
        return isChosenFromDiceTray;
    }

    public void setChosenFromDiceTray(Boolean chosenFromDiceTray) {
        isChosenFromDiceTray = chosenFromDiceTray;
    }

    public Boolean getCanBeSentToCenter() {
        return canBeSentToCenter;
    }

    public void setCanBeSentToCenter(Boolean canBeSentToCenter) {
        this.canBeSentToCenter = canBeSentToCenter;
    }
}
