package thread;

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
                Thread.sleep(1000); //On avance de 1 seconde
            } catch (InterruptedException ignored) {
            }
            Commande commande = this.CUISINIER.getCommande(); // La commande a réalisé est celle attribué au cuisinier
            commande.progress += this.CUISINIER.efficacite;// On augmente la progression actuel de  "efficacité" de l'agent d'entretien
            if (commande.progress >= commande.temps) { // Si la progression atteint le temps de preparation de la commande
                commande.isDone = true; //La commande est prête
                System.out.println("le cuisiner "+CUISINIER.nom + " a terminé de préparer sa commander, il est de nouveau en attente de travail !");
                CUISINIER.occupe = false; //Le cuisinier est disponible
                break;
            }
        }
    }
}
