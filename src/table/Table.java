package table;

import client.Client;

import java.util.ArrayList;

public class Table {
    public ArrayList<client.Client> clientsATable = new ArrayList<client.Client>();
    static int CAPACITE_MAX;
    public int nbClients;


    public Table(ArrayList<Client> clientsATable) {
        this.clientsATable = clientsATable;
        nbClients = clientsATable.size();
    }


}
