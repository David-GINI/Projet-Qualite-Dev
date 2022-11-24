package restaurant;
import client.Client;
import java.util.ArrayList;

public class Restaurant {
    public EtatProprete etatProprete;
    public ArrayList<Client> listclient = new ArrayList<>();
    public float caisse;

    public Restaurant(EtatProprete etatProprete, ArrayList<Client> listclient, float caisse) {
        this.etatProprete = etatProprete;
        this.listclient = listclient;
        this.caisse = caisse;
    }


}
