package Thread;

import employe.Nettoyeur;
import restaurant.Restaurant;

public class ThreadProcessCleanRestaurant implements Runnable {
    private final Nettoyeur NETTOYEUR;
    private final Restaurant RESTO;

    public ThreadProcessCleanRestaurant(Nettoyeur NETTOYEUR, Restaurant RESTO) {
        this.NETTOYEUR = NETTOYEUR;
        this.RESTO = RESTO;
    }
    @Override
    public void run() {
        int temps = 0;
        while(true) {
            try {
                Thread.sleep(1000); // On avcance de 1 seconde
            } catch (InterruptedException ignored) {
            }
            temps += NETTOYEUR.efficacite; //On ajoute le temps passé de "efficacité" de l'agent d'entretien
            if(temps >= 100){ // Quand le temps est a 100
                RESTO.nettoyer(); // Le resto est néttoyé
                System.out.println("le restaurant est néttoyé ! ");
                NETTOYEUR.occupe = false; // L'agent d'entretien est de nouveau disponible
                break;//On stop le thread
            }
        }
    }
}
