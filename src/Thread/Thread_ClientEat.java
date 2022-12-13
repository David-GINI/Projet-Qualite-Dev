package Thread;

import client.Client;
import client.Critique;
import restaurant.Restaurant;

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
        int temps_manger = 0; //depuis combien de temps il mange
        while(true){

            try {
                Thread.sleep(1000);//On avance de 1 seconde
            } catch (InterruptedException ignored) {
            }
            temps_manger +=1; // on augmente de 1
            if(temps_manger == CLIENT.TEMPS_MANGER){ // Si il a fini de manger
                CLIENT.setParti(true); // Il part
                System.out.println(CLIENT.getNom() + " a terminer son plat il va payer puis partir");
                RESTO.ajouterArgent(CLIENT.getPourboire() + CLIENT.getCommande().prix); // il paye et cela s'ajoute dans la caisse du restaurant
                System.out.println(CLIENT.getPourboire() + CLIENT.getCommande().prix + " euros viennent d'être ajouté à la caisse");
                if(CLIENT instanceof Critique){ // Si c'était un critique
                    ((Critique) CLIENT).noter(RESTO); // Il note le resto
                }
                Random randomSalir = new Random(); // nombre aléatoire pour salir aléatoirement la table a laquelle le client a manger
                int randomAleatoireSalir = randomSalir.nextInt(2); // Nombre aléatoire entre 0 et 1
                for(int i = 0; i<RESTO.listeTables.size(); ++i){
                    if(RESTO.listeTables.get(i).clientsATable.contains(CLIENT)){ // La table ou était le client
                        if(randomAleatoireSalir == 0){
                            RESTO.listeTables.get(i).salir(); // Une chance sur deux que la table sois sali
                        }
                    }
                }
                break; // On stop le thread
            }

        }
    }
}
