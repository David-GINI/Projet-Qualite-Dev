package Thread;

import employe.Employe;
import restaurant.Restaurant;

public class ThreadProcessCommande implements Runnable {
    private Employe employe;
    private Restaurant restaurant;

    public ThreadProcessCommande(Employe employe, Restaurant restaurant) {
        this.employe = employe;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {

    }
}

