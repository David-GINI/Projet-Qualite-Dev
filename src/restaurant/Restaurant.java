package restaurant;
import client.Client;
import commande.Commande;
import employe.Employe;
import table.Table;

import java.util.ArrayList;

public class Restaurant {
    public EtatProprete etatProprete;
    public ArrayList<Employe> listeEmployes = new ArrayList<Employe>();
    public ArrayList<Client> listeClients = new ArrayList<Client>();
    public ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
    public ArrayList<Table> listeTables = new ArrayList<Table>();
    public double caisse;

    public Restaurant(EtatProprete etatProprete, float caisse) {
        this.etatProprete = EtatProprete.PROPRE;
        this.caisse = 0;
    }

    public void genereClient() {
        
    }
}
