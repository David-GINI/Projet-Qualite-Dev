package Thread;

import commande.Commande;
import employe.Cuisinier;
import employe.Employe;
import restaurant.Restaurant;

public class ThreadProcessCommande implements Runnable {
    private final Cuisinier cuisinier;
    private final Restaurant restaurant;

    public ThreadProcessCommande(Cuisinier cuisinier, Restaurant restaurant) {
        this.cuisinier = cuisinier;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            Commande commande = this.cuisinier.getCommande();
            commande.progress += this.cuisinier.efficacite;
            if (commande.progress == commande.temps) {
                commande.isDone = true;
                cuisinier.occupe = false;
                break;
            }
        }
    }
}

