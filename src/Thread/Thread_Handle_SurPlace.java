package Thread;

import restaurant.Restaurant;

public class Thread_Handle_SurPlace implements Runnable{
    private Restaurant resto;

    @Override
    public void run() {
        while (resto.ouvert) {
            int nombre_table_complete = 0;
            for (int i = 0; i < resto.listeTables.size(); ++i) {

                if (resto.listeTables.get(i).nbClients == resto.listeTables.get(i).CAPACITE_MAX) {
                    nombre_table_complete += 1;

                }

            }
            if (nombre_table_complete == resto.listeTables.size()) {
                System.out.println("Restautant Complet !! ");
            } else {
                for (int i = 0; i < resto.listeTables.size(); ++i) {
                    if (resto.listeTables.get(i).nbClients != resto.listeTables.get(i).CAPACITE_MAX) {
                        if (resto.listeClientsPris.get(0).isSurPlace()) {
                            resto.listeTables.get(i).clientsATable.add(resto.listeClientsPris.get(0));
                            resto.listeTables.get(i).nbClients += 1;
                            resto.listeClientsPris.remove(0);
                        } else {
                            for (int j = 0; j < resto.listeClientsPris.size(); ++j) {
                                if (resto.listeClientsPris.get(i).getCommande().isDone) {
                                    resto.listeClientsPris.get(i).setParti(true);

                                    resto.listeClientsPris.remove(i);
                                }
                            }

                        }

                    }

                }

            }
        }
    }
}
