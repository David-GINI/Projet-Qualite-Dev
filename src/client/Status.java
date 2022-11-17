package client;

public enum Status {
    HABITUE("Habitué"),
    NORMAL("Normal");
    private final String value;
    Status(String value) {
        this.value = value;
    }
}