package client;
/**
 * Enumération des status possibles
 * @author JOLY GINI MOUMANE
 */
public enum Status {
    HABITUE("Habitué"),
    NORMAL("Normal");
    private final String value;
    Status(String value) {
        this.value = value;
    }
}