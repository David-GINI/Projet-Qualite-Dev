package commande;

public enum Sauces {
    KETCHUP("Ketchup"),
    MAYONNAISE("Mayonnaise"),
    MOUTARDE("Moutarde"),
    BARBECUE("Barbecue"),
    BIGGY("Biggy");
    private final String value;

    Sauces(String value) {
        this.value = value;
    }
}
