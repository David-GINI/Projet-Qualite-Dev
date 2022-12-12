package Thread;

import restaurant.Restaurant;

public class Thread_Handle_SurPlace implements Runnable{
    private Restaurant RESTO;

    @Override
    public void run() {
        while (RESTO.ouvert) {
            int nombre_table_complete = 0;
            for (int i = 0; i < RESTO.listeTables.size(); ++i) {

                if (RESTO.listeTables.get(i).nbClients == RESTO.listeTables.get(i).CAPACITE_MAX) {
                    nombre_table_complete += 1;

                }

            }
            if (nombre_table_complete == RESTO.listeTables.size()) {
                System.out.println("Restautant Complet !! ");
            } else {
                for (int i = 0; i < RESTO.listeTables.size(); ++i) {
                    if (RESTO.listeTables.get(i).nbClients != RESTO.listeTables.get(i).CAPACITE_MAX) {
                        if (RESTO.listeClientsPris.get(0).isSurPlace()) {
                            RESTO.listeTables.get(i).clientsATable.add(RESTO.listeClientsPris.get(0));
                            RESTO.listeTables.get(i).nbClients += 1;
                            RESTO.listeClientsPris.remove(0);
                        } else {
                            for (int j = 0; j < RESTO.listeClientsPris.size(); ++j) {
                                if (RESTO.listeClientsPris.get(i).getCommande().isDone) {
                                    RESTO.listeClientsPris.get(i).setParti(true);
                                    System.out.println(RESTO.listeClientsPris.get(i).getNom() + " a eu sa commande a emporté, il va payer puis quitter le restaurant");
                                    RESTO.caisse += RESTO.listeClientsPris.get(i).getCommande().prix;
                                    RESTO.listeClientsPris.remove(i);
                                }
                            }

                        }

                    }

                }

            }
        }
    }
}
