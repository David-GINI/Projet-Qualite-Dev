package client;

import commande.Commande;

import org.testng.annotations.Test;
import restaurant.Restaurant;

import static org.testng.AssertJUnit.*;

public class ClientTest {

	@Test
	public void getAttenteActuelle3() {

		Restaurant r1 = new Restaurant("test");
		Restaurant resto = r1;
		double pourboire = 110.5;
		String nom = "Lucas";
		int tempsAttenteMax = 10;
		Status status = Status.NORMAL;
		Preferences PREFERENCES = Preferences.LAMBDA;
		boolean surPlace = false;
		Client c1 = new Client(resto, pourboire, nom, tempsAttenteMax, status, PREFERENCES, surPlace);
		int expected = 0;
		int actual = c1.getAttenteActuelle();
		assertEquals(expected, actual);
	}


	@Test
	public void getNom5() {
		Restaurant r1 = new Restaurant("test");
		Restaurant resto = r1;
		double pourboire = 110.5;
		String nom = "Lucas";
		int tempsAttenteMax = 10;
		Status status = Status.NORMAL;
		Preferences PREFERENCES = Preferences.LAMBDA;
		boolean surPlace = false;
		Client c1 = new Client(resto, pourboire, nom, tempsAttenteMax, status, PREFERENCES, surPlace);
		String expected = "Lucas";
		String actual = c1.getNom();

		assertEquals(expected, actual);
	}

	@Test
	public void getPREFERENCES6() {
		Restaurant r1 = new Restaurant("test");
		Restaurant resto = r1;
		double pourboire = 110.5;
		String nom = "Lucas";
		int tempsAttenteMax = 10;
		Status status = Status.NORMAL;
		Preferences PREFERENCES = Preferences.LAMBDA;
		boolean surPlace = false;
		Client c1 = new Client(resto, pourboire, nom, tempsAttenteMax, status, PREFERENCES, surPlace);
		Preferences expected = Preferences.LAMBDA;
		Preferences actual = c1.getPREFERENCES();

		assertEquals(expected, actual);
	}

	@Test
	public void getPourboire7() {
		Restaurant r1 = new Restaurant("test");
		Restaurant resto = r1;
		double pourboire = 110.5;
		String nom = "Lucas";
		int tempsAttenteMax = 10;
		Status status = Status.NORMAL;
		Preferences PREFERENCES = Preferences.LAMBDA;
		boolean surPlace = false;
		Client c1 = new Client(resto, pourboire, nom, tempsAttenteMax, status, PREFERENCES, surPlace);
		double expected = 110.5;
		double actual = c1.getPourboire();
		assertEquals(expected, actual, 0.0000001D);
	}


	@Test
	public void passerCommande13() {
		Restaurant r1 = new Restaurant("test");
		Restaurant resto = r1;
		double pourboire = 110.5;
		String nom = "Lucas";
		int tempsAttenteMax = 10;
		Status status = Status.NORMAL;
		Preferences PREFERENCES = Preferences.LAMBDA;
		boolean surPlace = false;
		Client c1 = new Client(resto, pourboire, nom, tempsAttenteMax, status, PREFERENCES, surPlace);
		c1.passerCommande();
	}

	@Test
	public void setAttenteActuelle14() {
		Restaurant r1 = new Restaurant("test");
		Restaurant resto = r1;
		double pourboire = 110.5;
		String nom = "Lucas";
		int tempsAttenteMax = 10;
		Status status = Status.NORMAL;
		Preferences PREFERENCES = Preferences.LAMBDA;
		boolean surPlace = false;
		Client c1 = new Client(resto, pourboire, nom, tempsAttenteMax, status, PREFERENCES, surPlace);
		int attenteActuelle = 0; // TODO This is a fallback value due to incomplete analysis.
		c1.setAttenteActuelle(attenteActuelle);
	}

	@Test
	public void setEstPris15() {
		Restaurant r1 = new Restaurant("test");
		Restaurant resto = r1;
		double pourboire = 110.5;
		String nom = "Lucas";
		int tempsAttenteMax = 10;
		Status status = Status.NORMAL;
		Preferences PREFERENCES = Preferences.LAMBDA;
		boolean surPlace = false;
		Client c1 = new Client(resto, pourboire, nom, tempsAttenteMax, status, PREFERENCES, surPlace);
		boolean estPris = false;
		c1.setEstPris(estPris);
	}

	@Test
	public void setNom16() {
		Restaurant r1 = new Restaurant("test");
		Restaurant resto = r1;
		double pourboire = 110.5;
		String nom = "Lucas";
		int tempsAttenteMax = 10;
		Status status = Status.NORMAL;
		Preferences PREFERENCES = Preferences.LAMBDA;
		boolean surPlace = false;
		Client c1 = new Client(resto, pourboire, nom, tempsAttenteMax, status, PREFERENCES, surPlace);
		String nom2 = "David";
		c1.setNom(nom2);
	}
}

