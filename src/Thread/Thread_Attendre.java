package Thread;

import client.Client;
import restaurant.Restaurant;

public class Thread_Attendre implements Runnable{
    private final Client client;
    private final Restaurant resto;

    public Thread_Attendre(Client client, Restaurant resto) {
        this.client = client;
        this.resto = resto;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            client.setAttente_actuel(client.getAttente_actuel() + 1);
            if (client.isEstPris()) {
                resto.listeClientsPris.add(this.client);
                break;
            }
            if (client.getAttente_actuel() == client.getTemp_attente_max()) {
                client.setParti(true);
            }

        }
    }
}
