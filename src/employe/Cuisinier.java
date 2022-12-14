package employe;

import commande.Commande;

import java.util.concurrent.ThreadLocalRandom;

public class Cuisinier extends Employe{
    private Commande commande;
    public Cuisinier(String nom, int efficacite) {
        super(nom, efficacite);
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public void prepareCommande(Commande commande) {
        commande.progress = commande.progress + 2 * this.efficacite;
        this.energie = this.energie - ThreadLocalRandom.current().nextInt(5, 16);
    }
}
