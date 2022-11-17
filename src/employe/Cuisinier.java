package employe;

import java.util.concurrent.ThreadLocalRandom;

public class Cuisinier extends Employe{

    public Cuisinier(String nom, int efficacite) {
        super(nom, efficacite);
    }

    public void prepareCommande(commande.Commande commande) {
        commande.progress = commande.progress + 2 * this.efficacite;
        this.energie = this.energie - ThreadLocalRandom.current().nextInt(5, 16);
    }
}
