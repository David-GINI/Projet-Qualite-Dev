package table;

public enum Etat {
    PROPRE("Propre"),
    CORRECT("Correct"),
    SALE("Sale");

    private final String value;

    Etat(String value) {
        this.value = value;
    }
}
