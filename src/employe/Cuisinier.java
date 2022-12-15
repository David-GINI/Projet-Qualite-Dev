package employe;

import commande.Commande;

import java.util.concurrent.ThreadLocalRandom;
/**
 * Class Cuisinier qui utilise Employe
 * @author G6
 */
public class Cuisinier extends Employe{
    private Commande commande;
    /**
     * Constructeur de la class Cuisinier : reprend le constructeur de la Employe
     * @param nom
     * @param efficacite
     */
    public Cuisinier(String nom, int efficacite) {
        super(nom, efficacite);
    }

    
    /** 
     * Renvoie la commande affecté au cuisinier
     * @return Commande
     */
    public Commande getCommande() {
        return commande;
    }

    
    /** 
     * Setter de la commande affecté au cuisinier
     * @param commande
     */
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    
    /** 
     * Fonction qui prépare la commande
     * @param commande
     */
    public void prepareCommande(Commande commande) {
        commande.progress = commande.progress + 2 * this.efficacite;
        this.energie = this.energie - ThreadLocalRandom.current().nextInt(5, 16);
    }
}
