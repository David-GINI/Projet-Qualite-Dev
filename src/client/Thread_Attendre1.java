package client;

public class Thread_Attendre1 implements Runnable{
    private Client client;

    public Thread_Attendre1(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(client.temp_attente_max);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        client.parti = true;
    }
}
