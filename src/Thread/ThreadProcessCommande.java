package Thread;

import commande.Commande;
import employe.Cuisinier;
import restaurant.Restaurant;

public class ThreadProcessCommande implements Runnable {
    private final Cuisinier CUISINIER;
    private final Restaurant RESTO;

    public ThreadProcessCommande(Cuisinier CUISINIER, Restaurant RESTO) {
        this.CUISINIER = CUISINIER;
        this.RESTO = RESTO;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            Commande commande = this.CUISINIER.getCommande();
            commande.progress += this.CUISINIER.efficacite;
            if (commande.progress == commande.temps) {
                commande.isDone = true;
                CUISINIER.occupe = false;
                break;
            }
        }
    }
}

