package hr.algebra.java2orlog.models;

import java.io.Serializable;
import java.util.List;

public class SerializableButton implements Serializable {
    private String btnId;
    private List<DiceSymbols> diceSymbols;
    private Boolean disabled;
    private Boolean visible;

    public SerializableButton(String btnId, List<DiceSymbols> diceSymbols, Boolean disabled, Boolean visible) {
        this.btnId = btnId;
        this.diceSymbols = diceSymbols;
        this.disabled = disabled;
        this.visible = visible;
    }

    public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String btnId) {
        this.btnId = btnId;
    }

    public List<DiceSymbols> getDiceSymbols() {
        return diceSymbols;
    }

    public void setDiceSymbol(List<DiceSymbols> diceSymbols) {
        this.diceSymbols = diceSymbols;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

}
