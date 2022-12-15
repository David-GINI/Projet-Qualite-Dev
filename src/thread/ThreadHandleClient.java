package thread;

import employe.Cuisinier;
import restaurant.Restaurant;

import java.util.ArrayList;
/**
 * Thread qui gère la gestion des clients
 * @author G6
 */
public class ThreadHandleClient implements Runnable {
    private final Restaurant RESTO;
/**
     * Constructeur du Thread Handle Client
     * @param RESTO
     */
    public ThreadHandleClient(Restaurant RESTO) {
        this.RESTO = RESTO;
    }

    @Override
    /**
     * Fonction qui lance le thread
     * Ajoute le client dans la liste des clients pris si un cuisinier est dispo
     * ,le retire de la file d'attente
     * Le client passe commande puis lancement du thread de preparation de commande pour le cuisinier associé
     */
    public void run() {
        while(RESTO.ouvert){ // Tant que le resto est ouvert
            try {
                Thread.sleep(1000); //On passe une seconde
            } catch (InterruptedException ignored) {
            }
            if(!RESTO.fileDAttente.isEmpty()){
                for(int i = 0; i < RESTO.listeCuisiniers.size(); ++i){ // On parcourt tous les cuisiniers de la liste
                    if(!RESTO.listeCuisiniers.get(i).occupe){ // Si un cuisinier est dispo
                        RESTO.listeCuisiniers.get(i).occupe = true; //Le cuisinier est donc maintenant occupé
                        RESTO.fileDAttente.get(0).passerCommande(); // Ce dernier passe commande
                        RESTO.listeCuisiniers.get(i).argentGenere += RESTO.fileDAttente.get(0).getCommande().prix + RESTO.fileDAttente.get(0).getPourboire(); // On ajoute le prix de la commande et le pourboire du client a l'argent généré du cuisinier qui s'occupe de lui
                        RESTO.listeCuisiniers.get(i).setCommande(RESTO.fileDAttente.get(0).getCommande()); //On attribue cette commande au cuisinier qui l'a pris en charge
                        RESTO.fileDAttente.get(0).setEstPris(true); // On prend le premier dans la file d'attente
                        System.out.println(RESTO.fileDAttente.get(0).getNom() + " viens d'être pris en charge par le cuisinier : " + RESTO.listeCuisiniers.get(i).nom);
                        System.out.println(RESTO.fileDAttente.get(0).getNom() + " vient de passer sa commande !");
                        RESTO.fileDAttente.remove(0); // On le retire de la file d'attente

                        ThreadProcessCommande t1 = new ThreadProcessCommande(RESTO.listeCuisiniers.get(i), RESTO); //On lance le thread de preparation de commande
                        new Thread(t1).start();



                        break;
                    }
                }
            }
            }

        }
    }
