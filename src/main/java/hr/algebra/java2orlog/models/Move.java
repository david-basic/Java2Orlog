package hr.algebra.java2orlog.models;

import java.time.LocalDateTime;

public class Move {
    private String player;
    private String buttonID;
    private LocalDateTime timeStamp;

    public Move(String player, String buttonID, LocalDateTime timeStamp) {
        this.player = player;
        this.buttonID = buttonID;
        this.timeStamp = timeStamp;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getButtonID() {
        return buttonID;
    }

    public void setButtonID(String buttonID) {
        this.buttonID = buttonID;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
