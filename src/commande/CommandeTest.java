package commande;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CommandeTest {


	@Test
	public void getCondiments8() {
		Viandes viande = Viandes.BOEUF;
		ArrayList<Condiments> condiments = new ArrayList<>();
		condiments.add(Condiments.SALADE);
		condiments.add(Condiments.OIGNONS);
		Sauces sauce = Sauces.BARBECUE;
		boolean surPlace = false;
		Commande c1 = new Commande(viande, condiments, sauce, surPlace);
		ArrayList<Condiments> actual = c1.getCondiments();
		ArrayList<Condiments> expected= condiments;
		assertEquals(expected, actual);
	}

	@Test
	public void getPrix9() {
		Viandes viande = Viandes.BOEUF;
		ArrayList<Condiments> condiments = new ArrayList<>();
		condiments.add(Condiments.SALADE);
		condiments.add(Condiments.OIGNONS);
		Sauces sauce = Sauces.BARBECUE;
		boolean surPlace = false;
		Commande c1 = new Commande(viande, condiments, sauce, surPlace);
		double expected = 5.80;
		double actual = c1.getPrix();

		assertEquals(expected, actual, 0.0000001D);
	}


	@Test
	public void getSauce11() {
		Viandes viande = Viandes.BOEUF;
		ArrayList<Condiments> condiments = new ArrayList<>();
		condiments.add(Condiments.SALADE);
		condiments.add(Condiments.OIGNONS);
		Sauces sauce = Sauces.BARBECUE;
		boolean surPlace = false;
		Commande c1 = new Commande(viande, condiments, sauce, surPlace);
		Sauces actual = c1.getSauce();
		Sauces expected = Sauces.BARBECUE;
		assertEquals(expected, actual);
	}

	@Test
	public void getTemps12() {
		Viandes viande = Viandes.BOEUF;
		ArrayList<Condiments> condiments = new ArrayList<>();
		condiments.add(Condiments.SALADE);
		condiments.add(Condiments.OIGNONS);
		Sauces sauce = Sauces.BARBECUE;
		boolean surPlace = false;
		Commande c1 = new Commande(viande, condiments, sauce, surPlace);
		int expected = 60;
		int actual = c1.getTemps();

		assertEquals(expected, actual);
	}

	@Test
	public void getViande13() {
		Viandes viande = Viandes.BOEUF;
		ArrayList<Condiments> condiments = new ArrayList<>();
		condiments.add(Condiments.SALADE);
		condiments.add(Condiments.OIGNONS);
		Sauces sauce = Sauces.BARBECUE;
		boolean surPlace = false;
		Commande c1 = new Commande(viande, condiments, sauce, surPlace);
		Viandes actual = c1.getViande();
		Viandes expected = Viandes.BOEUF;
		assertEquals(expected, actual);
	}


	@Test
	public void isSurPlace15() {
		Viandes viande = Viandes.BOEUF;
		ArrayList<Condiments> condiments = new ArrayList<>();
		condiments.add(Condiments.SALADE);
		condiments.add(Condiments.OIGNONS);
		Sauces sauce = Sauces.BARBECUE;
		boolean surPlace = false;
		Commande c1 = new Commande(viande, condiments, sauce, surPlace);
		boolean actual = c1.isSurPlace();
		assertFalse(actual);
	}

}
