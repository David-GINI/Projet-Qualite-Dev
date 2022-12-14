package commande;
/**
 * Enumération des viandes possibles
 * @author JOLY GINI MOUMANE
 */
public enum Viandes {
    BOEUF("Boeuf"),
    POULET("Poulet"),
    POISSON("Poisson"),
    VEGETARIEN("Végétarien"),
    BOEUF_HALAL("Boeuf Halal"),
    BOEUF_CASHER("Boeuf Casher");

    private final String value;

    Viandes(String value) {
        this.value = value;
    }
}
