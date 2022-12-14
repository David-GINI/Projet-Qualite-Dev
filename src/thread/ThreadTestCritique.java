package thread;

import client.Client;
import client.Critique;
import restaurant.Restaurant;

public class ThreadTestCritique implements Runnable{
    private final Restaurant RESTO;
    public ThreadTestCritique(Restaurant RESTO) {
            this.RESTO = RESTO;
    }
    @Override
    public void run() {
        while(RESTO.ouvert){
            int nombreCritique = 0;
            try {
                Thread.sleep(1000);  //On avance de une seconde
            } catch (InterruptedException ignored) {
            }
            for(int i = 0; i<RESTO.fileDAttente.size(); ++i){

                if(RESTO.fileDAttente.get(i) instanceof Critique){
                    nombreCritique +=1;

                    if(nombreCritique > RESTO.nombreCritique){
                        System.out.println("Un Critique vient d'arriver au restaurant");
                        RESTO.nombreCritique +=1;
                    }

                }
}
        }
    }
}
