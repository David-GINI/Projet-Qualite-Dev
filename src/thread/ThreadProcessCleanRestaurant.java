package thread;

import employe.Nettoyeur;
import restaurant.Restaurant;
/**
 * Thread qui nettoie le restaurant
 * @author G6
 */
public class ThreadProcessCleanRestaurant implements Runnable {
    private final Nettoyeur NETTOYEUR;
    private final Restaurant RESTO;
/**
     * Constructeur du Thread qui clean le restaurant
     * @param NETTOYEUR
     * @param RESTO
     */
    public ThreadProcessCleanRestaurant(Nettoyeur NETTOYEUR, Restaurant RESTO) {
        this.NETTOYEUR = NETTOYEUR;
        this.RESTO = RESTO;
    }
    @Override
    /**
     * Fonction qui lance le thread
     * Augmente le temps en fonction de l'efficacité de l'employe
     * Nettoie le restaurant lorsque le temps de nettoyage est atteint
     */
    public void run() {
        int temps = 0;
        while(RESTO.ouvert) {
            try {
                Thread.sleep(1000); // On avcance de 1 seconde
            } catch (InterruptedException ignored) {
            }
            temps += NETTOYEUR.efficacite; //On ajoute le temps passé de "efficacité" de l'agent d'entretien
            if(temps >= 100){ // Quand le temps est a 100
                RESTO.nettoyer(); // Le resto est néttoyé
                System.out.println("le restaurant est néttoyé ! ");
                System.out.println();
                NETTOYEUR.occupe = false; // L'agent d'entretien est de nouveau disponible
                break;//On stop le thread
            }
        }
    }
}
