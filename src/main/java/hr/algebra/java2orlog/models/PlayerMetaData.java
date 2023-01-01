package hr.algebra.java2orlog.models;

import java.io.Serializable;

public class PlayerMetaData implements Serializable {

    private static final long serialVersionUID = 234234L;

    private String ipAddress;
    private String port;
    private String playerName;
    private Long pid;
    private Boolean playerIsFirst;

    public PlayerMetaData() {

    }

    public PlayerMetaData(String ipAddress, String port, String playerName, Long pid) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.playerName = playerName;
        this.pid = pid;
    }

    public PlayerMetaData(String ipAddress, String port, String playerName, Long pid, Boolean playerIsFirst) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.playerName = playerName;
        this.pid = pid;
        this.playerIsFirst = playerIsFirst;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Boolean getPlayerIsFirst() {
        return playerIsFirst;
    }

    public void setPlayerIsFirst(Boolean playerIsFirst) {
        this.playerIsFirst = playerIsFirst;
    }
}
