package thread;

import commande.Commande;
import employe.Cuisinier;
import restaurant.Restaurant;
/**
 * Thread qui prepare une commande
 * @author G6
 */
public class ThreadProcessCommande implements Runnable {
    private final Cuisinier CUISINIER;
    private final Restaurant RESTO;
/**
     * Constructeur du Thread qui prepare la commande
     * @param CUISINIER
     * @param RESTO
     */
    public ThreadProcessCommande(Cuisinier CUISINIER, Restaurant RESTO) {
        this.CUISINIER = CUISINIER;
        this.RESTO = RESTO;
    }

    @Override
    /**
     * Fonction qui lance le thread
     * prepare la commande en augmentant la progression chaque seconde
     * si la commande est prete le cuisinier est de nouveau disponible
     *
     */
    public void run() {
        while(RESTO.ouvert) {
            try {
                Thread.sleep(2000); //On avance de 1 seconde
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

