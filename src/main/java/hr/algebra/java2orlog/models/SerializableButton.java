package hr.algebra.java2orlog.models;

import java.io.Serializable;

public class SerializableButton implements Serializable {
    private String btnId;
    private String symbol;
    private Boolean disabled;
    private Boolean visible;

    public SerializableButton(String btnId, String symbol, Boolean disabled, Boolean visible) {
        this.btnId = btnId;
        this.symbol = symbol;
        this.disabled = disabled;
        this.visible = visible;
    }

    public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String btnId) {
        this.btnId = btnId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
