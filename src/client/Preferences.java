package client;

public enum Preferences {
    HALAL("Halal"),
    CASHER("Casher"),
    LAMBDA("Lambda"),
    VEGETARIEN("Vegetarien"),
    POISSON("Poisson");
    private final String value;
    Preferences(String value) {
        this.value = value;
    }
}