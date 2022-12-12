package Thread;

import client.Client;

public class Thread_Attendre1 implements Runnable{
    private Client client;

    public Thread_Attendre1(Client client) {
        this.client = client;
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
                break;
            }
            if (client.getAttente_actuel() == client.getTemp_attente_max()) {
                client.setParti(true);
            }

        }
    }
}
