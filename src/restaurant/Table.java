package restaurant;

import client.Client;

import java.util.ArrayList;

public class Table {
    public ArrayList<Client> clientsATable = new ArrayList<Client>();
    public final int CAPACITE_MAX;
    public int numero;
    public int nbClients;
    public Etat etat;

    public Table(ArrayList<Client> clientsATable, int numero, int capacite_max) {
        this.numero = numero;
        this.clientsATable = clientsATable;
        nbClients = clientsATable.size();
        etat = Etat.PROPRE;
        this.CAPACITE_MAX = capacite_max;
    }

public void salir(){
    etat = this.etat.salir();
}
public void nettoyer(){
        etat = this.etat.nettoyer();
}
}
