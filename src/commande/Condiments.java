package commande;

public enum Condiments {
    SALADE("Salade"),
    TOMATES("Tomates"),
    OIGNONS("Oignons"),
    CORNICHONS("Cornichons");

    private final String value;

    Condiments(String value) {
        this.value = value;
    }
}
