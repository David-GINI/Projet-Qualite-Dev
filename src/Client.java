import Enum_Client.Methode_Commande;
import Enum_Client.Status;

public class Client {
    private Commande commande; //Commande du client
    private int pourboire;
    private String nom;
    private int temp_attente_max; // Si le temps est dépassé il quitte le restaurant
    private Status status; //Client habitué ou non si oui il aura toujours la meme commande
    private Methode_Commande methode_commande;  //Sur place ou a emporter

    public Client(int pourboire, String nom, int temp_attente_max, Status status, Methode_Commande methode_commande) {
        this.status = status;
        this.methode_commande = methode_commande;
        this.temp_attente_max = temp_attente_max;
        this.pourboire = pourboire;
        this.nom = nom;
        this.commande = null;
    }

    public void Passer_Commander(Commande commande){
        this.commande = commande;
    }
}
