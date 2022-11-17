package client;

public enum Preferences {
    HALAL("Halal"),
    CASHER("Casher"),
    LAMBDA("Lambda"),
    VEGETARIEN("Vegetarien");
    private final String value;
    Preferences(String value) {
        this.value = value;
    }
}