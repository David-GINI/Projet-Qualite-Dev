package thread;

import client.Client;
import client.Critique;
import restaurant.Restaurant;
/**
 * Thread qui gère l'attente d'un client
 * @author G6
 */
public class ThreadAttendre implements Runnable{
    private final Client CLIENT;
    private final Restaurant RESTO;
    /**
     * Constructeur du Thread d'attente
     * @param CLIENT
     * @param RESTO
     */
    public ThreadAttendre(Client CLIENT, Restaurant RESTO) {
        this.CLIENT = CLIENT;
        this.RESTO = RESTO;
    }

    @Override
    /**
     * Fonction qui lance le thread
     * augmente le temps d'attente a chaque tour de boucle 
     * le client part si il a trop attendu ou rejoins la liste des clients pris
     */
    public void run() {

        while(RESTO.ouvert) {
            int nombreCritique = 0;
            try {
                Thread.sleep(1000);  //On avance de une seconde
            } catch (InterruptedException ignored) {
            }
            CLIENT.setAttenteActuelle(CLIENT.getAttenteActuelle() + 1); // On augmente son temps d'attente actuel
            if (CLIENT.isEstPris()) { //Si il est pris par un cuisinier
                RESTO.listeClientsPris.add(this.CLIENT); // On le met dans la liste des clients pris
                break; //On stop la boucle et donc le thread
            }
            if (CLIENT.getAttenteActuelle() >= CLIENT.getTempsAttenteMax()) { // Si ça dépase son temps d'attente max il part
                System.out.println(CLIENT.getNom() + " est mécontent, il part car il a trop attendu dans la file d'attente");
                CLIENT.setParti(true);
                RESTO.fileDAttente.remove(CLIENT);
                break;
            }

        }
    }
}
