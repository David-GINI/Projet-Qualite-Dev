package Thread;

import employe.Cuisinier;
import restaurant.Restaurant;

import java.util.ArrayList;

public class Thead_Handle_Client implements Runnable {
    private final Restaurant resto;

    public Thead_Handle_Client(Restaurant resto) {
        this.resto = resto;
    }

    @Override
    public void run() {
        while(resto.ouvert){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
                ArrayList<Cuisinier> listeCuisinier = resto.getCuisinier();
                for(int i = 0; i < listeCuisinier.size(); ++i){
                        if(!resto.listeEmployes.get(i).occupe){
                            resto.fileDAttente.get(0).setEstPris(true);
                            resto.fileDAttente.get(0).Passer_Commander();
                            listeCuisinier.get(i).setCommande(resto.fileDAttente.get(0).getCommande());
                            ThreadProcessCommande t1 = new ThreadProcessCommande(listeCuisinier.get(i), resto);
                            new Thread(t1).start();
                            resto.listeClientsPris.add(resto.fileDAttente.get(0));
                            resto.fileDAttente.remove(0);
                            resto.listeEmployes.get(i).occupe = true;
                            break;
                    }
                }
            }

        }
    }
