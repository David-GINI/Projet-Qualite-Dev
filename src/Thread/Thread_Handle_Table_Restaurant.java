package Thread;

import restaurant.Restaurant;

public class Thread_Handle_Table_Restaurant implements Runnable{
    private Restaurant resto;

    public Thread_Handle_Table_Restaurant(Restaurant resto) {
        this.resto = resto;
    }

    @Override
    public void run() {
        while(resto.ouvert){
            for(int i = 0; i<resto.listeTables.size(); ++i){
                for(int j = 0; j<resto.listeTables.get(i).clientsATable.size(); ++j){
                    if(resto.listeTables.get(i).clientsATable.get(j).getCommande().isDone){
                        Thread_ClientEat t1 = new Thread_ClientEat(resto,resto.listeTables.get(i).clientsATable.get(j));
                        new Thread(t1).start();
                    }
                }
            }
        }
    }
}
