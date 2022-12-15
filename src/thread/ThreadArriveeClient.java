package thread;

import restaurant.Restaurant;

import java.util.Random;

public class ThreadArriveeClient implements Runnable{
    public final Restaurant RESTO;

    public ThreadArriveeClient(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    public void run() {
        while(RESTO.ouvert){
            Random randomizer = new Random();
            int randomClient = randomizer.nextInt(3);
                if(randomClient == 0){
                    RESTO.genereClients(1);
                    System.out.println("1 Client vient d'arriver");
                }
                else if(randomClient == 1){
                    RESTO.genereClients(2);
                    System.out.println("2 Clients viennent d'arriver");
                }
                else{
                    RESTO.genereClients(3);
                    System.out.println("3 Clients viennent d'arriver");
            }
            try {
                Thread.sleep(15000);//On avance de 15 seconde
            } catch (InterruptedException ignored) {
            }


        }

    }
}
