package commande;
/**
 * Enumération des condiments possibles
 * @author JOLY GINI MOUMANE
 */
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
