package thread;

import restaurant.Restaurant;

public class ThreadHandleSurPlace implements Runnable{
    private Restaurant RESTO;

    @Override
    public void run() {
        while (RESTO.ouvert) {// Tant que le resto est ouvert
            int nombreTablesCompletes = 0;
            for (int i = 0; i < RESTO.listeTables.size(); ++i) {

                if (RESTO.listeTables.get(i).nbClients == RESTO.listeTables.get(i).CAPACITE_MAX) {
                    nombreTablesCompletes += 1;

                }

            }
            // On test jusque la si le resto est plein
            if (nombreTablesCompletes == RESTO.listeTables.size()) {
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
                                    RESTO.ajouterArgent(RESTO.listeClientsPris.get(i).getPourboire() + RESTO.listeClientsPris.get(i).getCommande().prix); // il paye et cela s'ajoute dans la caisse du restaurant
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
