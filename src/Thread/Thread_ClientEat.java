package Thread;

import client.Client;
import client.Critique;
import restaurant.Restaurant;
import table.Etat;

import java.util.Random;

public class Thread_ClientEat implements Runnable{
    private final Restaurant RESTO;
    private final Client CLIENT;
    public Thread_ClientEat(Restaurant resto, Client client ) {
        this.RESTO = resto;
        this.CLIENT = client;
    }

    @Override
    public void run() {
        int temps_manger = 0;
        while(true){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            temps_manger +=1;
            if(temps_manger == CLIENT.TEMPS_MANGER){
                CLIENT.setParti(true);
                System.out.println(CLIENT.getNom() + " a terminer son plat il va payer puis partir");
                RESTO.caisse += CLIENT.getCommande().prix;
                RESTO.caisse += CLIENT.getPourboire();
                if(CLIENT instanceof Critique){
                    ((Critique) CLIENT).noter(RESTO);
                }
                Random randomSalir = new Random(); // nombre aléatoire pour salir aléatoirement la table a laquelle le client a manger
                int randomAleatoireSalir = randomSalir.nextInt(2); // Nombre aléatoire entre 0 et 1
                for(int i = 0; i<RESTO.listeTables.size(); ++i){
                    if(RESTO.listeTables.get(i).clientsATable.contains(CLIENT)){
                        if(randomAleatoireSalir == 0){
                            RESTO.listeTables.get(i).salir();
                        }
                    }
                }
                break;
            }

        }
    }
}
