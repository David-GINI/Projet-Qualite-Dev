package thread;

import restaurant.Restaurant;
/**
 * Thread qui gère l'attente de tous les clients
 * @author G6
 */
public class ThreadClientWait implements Runnable{
    public final Restaurant RESTO;
    /**
     * Constructeur du Thread d'attente de tous les clients
     * @param RESTO
     */
    public ThreadClientWait(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    /**
     * Fonction qui lance le thread
     * Elle fait attendre tous les clients tant qu'ils ne sont pas pris *
     */
    @Override
    public void run() {
        while(RESTO.ouvert)
        {
            for(int i = 0; i<RESTO.fileDAttente.size(); ++i){
                RESTO.fileDAttente.get(i).attendre();
            }
            try {
                Thread.sleep(1500);  //On avance de une seconde et demi
            } catch (InterruptedException ignored) {
            }
        }
    }
}
