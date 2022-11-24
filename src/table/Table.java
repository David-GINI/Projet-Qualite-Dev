package table;

import client.Client;

import java.util.ArrayList;

public class Table {
    public ArrayList<Client> clientsATable = new ArrayList<Client>();
    static int CAPACITE_MAX;
    public int nbClients;
    public Etat etat;
    public Table(ArrayList<Client> clientsATable) {
        this.clientsATable = clientsATable;
        nbClients = clientsATable.size();
        etat = Etat.PROPRE;
    }


}
