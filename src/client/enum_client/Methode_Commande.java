package client.enum_client;

public enum Methode_Commande {
    SUR_PLACE("Sur place"),
    A_EMPORTER("A Emporter");
    private final String value;

    Methode_Commande(String value) {
        this.value = value;
    }
}
