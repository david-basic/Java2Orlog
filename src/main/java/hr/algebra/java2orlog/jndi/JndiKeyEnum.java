package hr.algebra.java2orlog.jndi;

public enum JndiKeyEnum {

    RMI_PORT_KEY("rmi.port"),
    CLIENT_1_PORT("client1.port"),
    CLIENT_2_PORT("client2.port");

    private final String key;

    private JndiKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
