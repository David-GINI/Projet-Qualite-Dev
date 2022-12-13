package Thread;

import client.Client;
import restaurant.Restaurant;

public class Thread_Attendre implements Runnable{
    private final Client CLIENT;
    private final Restaurant RESTO;

    public Thread_Attendre(Client CLIENT, Restaurant RESTO) {
        this.CLIENT = CLIENT;
        this.RESTO = RESTO;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);  //On avance de une seconde
            } catch (InterruptedException ignored) {
            }
            CLIENT.setAttente_actuel(CLIENT.getAttente_actuel() + 1); // On augmente son temps d'attente actuel
            if (CLIENT.isEstPris()) { //Si il est pris par un cuisinier
                RESTO.listeClientsPris.add(this.CLIENT); // On le met dans la liste des clients pris
                break; //On stop la boucle et donc le thread
            }
            if (CLIENT.getAttente_actuel() == CLIENT.getTemp_attente_max()) { // Si ça dépase son temps d'attente max il part
                System.out.println(CLIENT.getNom() + " est mécontent, il part car il a trop attendu dans la file d'attente");
                CLIENT.setParti(true);
            }

        }
    }
}
