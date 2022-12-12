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
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            CLIENT.setAttente_actuel(CLIENT.getAttente_actuel() + 1);
            if (CLIENT.isEstPris()) {
                RESTO.listeClientsPris.add(this.CLIENT);
                break;
            }
            if (CLIENT.getAttente_actuel() == CLIENT.getTemp_attente_max()) {
                CLIENT.setParti(true);
            }

        }
    }
}
