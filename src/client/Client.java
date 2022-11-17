package client;

public class Client {
    private commande.Commande commande; //Commande du client
    private int pourboire;
    private String nom;
    private int temps_attente_max; // Si le temps est dépassé il quitte le restaurant
    private Status status; //client.Client habitué ou non si oui il aura toujours la meme commande
    public boolean surPlace; //true = sur place, false = à emporter

    public Client(int pourboire, String nom, int temps_attente_max, Status status, boolean surPlace) {
        this.status = status;
        this.surPlace = surPlace;
        this.temps_attente_max = temps_attente_max;
        this.pourboire = pourboire;
        this.nom = nom;
        this.commande = null;
    }


}
