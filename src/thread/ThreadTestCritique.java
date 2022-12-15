package thread;

import client.Critique;
import restaurant.Restaurant;
/**
 * Thread qui repère les critique et informe de leur arrivée
 * @author G6
 */
public class ThreadTestCritique implements Runnable{
    private final Restaurant RESTO;
    /**
     * Constructeur du Thread qui recherche si un critique vient d'arriver
     * @param RESTO restaurant utilisé
     */
    public ThreadTestCritique(Restaurant RESTO) {
            this.RESTO = RESTO;
    }
    @Override
    /**
     * Fonction qui lance le thread
     * Affiche quand un critique arrive
     */
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
                        System.out.println();
                        RESTO.nombreCritique +=1;
                    }

                }
}
        }
    }
}
