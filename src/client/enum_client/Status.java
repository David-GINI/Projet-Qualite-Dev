package client.enum_client;

public enum Status {
    HABUTUE("Habitue"),
    NORMAL("Normal");
    private final String value;
    Status(String value) {
        this.value = value;
    }
}