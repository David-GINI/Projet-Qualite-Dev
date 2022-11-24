package restaurant;

public enum EtatProprete {
    PROPRE("Propre"),
    CORRECT("Correct"),
    SALE("Sale");

    private final String etat;

    EtatProprete(String etat) {
        this.etat = etat;
    }

}
