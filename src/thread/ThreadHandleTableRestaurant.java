package thread;

import restaurant.Restaurant;
/**
 * Thread qui sers les commandes et fais manger les clients assis a une table
 * @author G6
 */
public class ThreadHandleTableRestaurant implements Runnable{
    private Restaurant RESTO;
/**
     * Constructeur du Thread handle des tables et du restaurant
     * @param RESTO
     */
    public ThreadHandleTableRestaurant(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    /**
     * Fonction qui lance le thread
     * Parcours chaque table, si la commande d'un client assis a une table est prête, il comemnce a manger(ThreadClientEat)
     */
    public void run() {
        while(RESTO.ouvert){// Tant que le resto est ouvert
            for(int i = 0; i< RESTO.listeTables.size(); ++i){
                if(!RESTO.listeTables.get(i).clientsATable.isEmpty()){
                    for(int j = 0; j< RESTO.listeTables.get(i).clientsATable.size(); ++j){ //On parcourt les tables puis les gens présent dans cette table
                        if(RESTO.listeTables.get(i).clientsATable.get(j).getCommande().isDone){ // Si ils sont servi
                            System.out.println(RESTO.listeTables.get(i).clientsATable.get(j).getNom() + " vient d'être servi ! ");
                            ThreadClientEat t1 = new ThreadClientEat(RESTO, RESTO.listeTables.get(i).clientsATable.get(j)); // On lance le thread pour qu'ils mangent
                            new Thread(t1).start();
                            RESTO.listeTables.get(i).clientsATable.remove(j);
                        }
                    }
                }

            }
        }
    }
}
