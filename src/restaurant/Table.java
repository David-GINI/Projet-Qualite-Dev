package restaurant;

import client.Client;

import java.util.ArrayList;
/**
 * Class table
 * @author G6
 */
public class Table {
    public ArrayList<Client> clientsATable = new ArrayList<Client>();
    public final int CAPACITE_MAX;
    public int numero;
    public int nbClients;
    public Etat etat;
    /**
     * Constructeur de la class table
     * @param clientsATable
     * @param numero
     * @param capacite_max
     */
    public Table(ArrayList<Client> clientsATable, int numero, int capacite_max) {
        this.numero = numero;
        this.clientsATable = clientsATable;
        nbClients = clientsATable.size();
        etat = Etat.PROPRE;
        this.CAPACITE_MAX = capacite_max;
    }
/**
 * Salir la table
 */
public void salir(){
    etat = this.etat.salir();
}
/**
 * Nettoie la table
 */
public void nettoyer(){
        etat = this.etat.nettoyer();
}
}
