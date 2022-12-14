package thread;

import restaurant.Restaurant;

public class ThreadHandleTableRestaurant implements Runnable{
    private Restaurant resto;

    public ThreadHandleTableRestaurant(Restaurant resto) {
        this.resto = resto;
    }

    @Override
    public void run() {
        while(resto.ouvert){// Tant que le resto est ouvert
            for(int i = 0; i<resto.listeTables.size(); ++i){
                for(int j = 0; j<resto.listeTables.get(i).clientsATable.size(); ++j){ //On parcourt les tables puis les gens présent dans cette table
                    if(resto.listeTables.get(i).clientsATable.get(j).getCommande().isDone){ // Si ils sont servi
                        System.out.println(resto.listeTables.get(i).clientsATable.get(j).getNom() + " vient d'être servi ! ");
                        ThreadClientEat t1 = new ThreadClientEat(resto,resto.listeTables.get(i).clientsATable.get(j)); // On lance le thread pour qu'ils mangent
                        new Thread(t1).start();
                    }
                }
            }
        }
    }
}
