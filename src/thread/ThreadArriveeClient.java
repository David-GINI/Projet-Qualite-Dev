package thread;

import restaurant.Restaurant;

/**
 * Thread de Géneration des clients
 */
public class ThreadArriveeClient implements Runnable{
    public final Restaurant RESTO;

    /**
     * Constructeur du Thread de generation des clients
     * @param RESTO
     */
    public ThreadArriveeClient(Restaurant RESTO) {
        this.RESTO = RESTO;
    }
    /**
     * Fonction qui lance le thread
     * génére des clients en fonction de l'age du restaurant
     */
    @Override
    public void run() {
        while(RESTO.ouvert){
            RESTO.genereClients((int)Math.round(1 + 1.5 * RESTO.age));
            try {
                Thread.sleep(15000);//On avance de 15 seconde
            } catch (InterruptedException ignored) {
            }


        }

    }
}
