package Thread;

import restaurant.Restaurant;

public class Thread_Handle_SurPlace implements Runnable{
    private Restaurant RESTO;

    @Override
    public void run() {
        while (RESTO.ouvert) {// Tant que le resto est ouvert
            int nombre_table_complete = 0;
            for (int i = 0; i < RESTO.listeTables.size(); ++i) {

                if (RESTO.listeTables.get(i).nbClients == RESTO.listeTables.get(i).CAPACITE_MAX) {
                    nombre_table_complete += 1;

                }

            }
            // On test jusque la si le resto est plein
            if (nombre_table_complete == RESTO.listeTables.size()) {
                System.out.println("Restautant Complet !! ");
            } else { // Sinon
                for (int i = 0; i < RESTO.listeTables.size(); ++i) { // On parcourt la liste des tables
                    if (RESTO.listeTables.get(i).nbClients != RESTO.listeTables.get(i).CAPACITE_MAX) { // Si la table "i" n'est pas pleine
                        if (RESTO.listeClientsPris.get(0).isSurPlace()) { // Si le client premier dans la liste des clients pris mange sur place
                            RESTO.listeTables.get(i).clientsATable.add(RESTO.listeClientsPris.get(0)); // On l'affecte à cette table
                            RESTO.listeTables.get(i).nbClients += 1;
                            System.out.println(RESTO.listeClientsPris.get(0) + " va à la table numéro "+ RESTO.listeTables.get(i).numero + " et sera bientot servi");
                            RESTO.listeClientsPris.remove(0); // On le retire de la liste des clients pris
                        } else { // Si il ne mange pas sur place
                            for (int j = 0; j < RESTO.listeClientsPris.size(); ++j) { // On parcours tous les clients pris
                                if (RESTO.listeClientsPris.get(i).getCommande().isDone) { //Si leurs commandes sont prêtes
                                    RESTO.listeClientsPris.get(i).setParti(true); // Ils partent
                                    System.out.println(RESTO.listeClientsPris.get(i).getNom() + " a eu sa commande a emporté, il va payer puis quitter le restaurant ! ");
                                    RESTO.caisse += RESTO.listeClientsPris.get(i).getCommande().prix; // ils payent et cela s'ajoute à la caisse du restaurant
                                    RESTO.caisse += RESTO.listeClientsPris.get(i).getPourboire();
                                    System.out.println(RESTO.listeClientsPris.get(i).getPourboire() + RESTO.listeClientsPris.get(i).getCommande().prix + " euros viennent d'être ajouté à la caisse");
                                    RESTO.listeClientsPris.remove(i); // On les retire de la liste des gens pris
                                }
                            }

                        }

                    }

                }

            }
        }
    }
}
