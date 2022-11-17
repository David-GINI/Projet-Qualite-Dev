package journee;

public enum Jour {
    LUNDI("lundi"),
    MARDI("mardi"),
    MERCREDI("mercredi"),
    JEUDI("jeudi"),
    VENDREDI("vendredi"),
    SAMEDI("samedi"),
    DIMANCHE("dimanche");
    private final String value;

    Jour(String value) {
        this.value = value;
    }
}
