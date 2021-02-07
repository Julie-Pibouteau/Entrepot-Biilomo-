package personne;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
* La classe PersonnelTest teste les méthodes de Personnel.
*
* @author Lauréline
*/

public class PersonnelTest {
	private HashMap<Integer, Personnel> hmPersonnelUnderTest = new HashMap<>();
	ChefStock chefStockInactif = new ChefStock("A", "a");
	ChefBrico chefBricoInactif = new ChefBrico("B", "b");
	Ouvrier ouvrier = new Ouvrier("C", "c", PiecesMaison.CHAMBRE, 1);

	@BeforeEach
	public void setUpTest() {
		hmPersonnelUnderTest = new HashMap<>();
	}

	@AfterEach
	public void cleanupTest() {
		hmPersonnelUnderTest = null;
	}

	@Test
	public void testTrouverPersonnelInactifPourStockerDansHashMapVide() {
		// arrange
		Personnel expected = null;
		// act
		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, true, null);
		// assert
		assertEquals(expected, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourMonterMeubleDansHashMapVide() {
		// arrange
		Personnel expected = null;
		// act
		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, false, PiecesMaison.CHAMBRE);
		// assert
		assertEquals(expected, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourStockerDansMapNull() {
		// arrange
		Personnel expected = null;
		// act
		Personnel result = Personnel.trouverPersonnelInactif(null, true, null);
		// assert
		assertEquals(expected, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourStockerLotDansMapAvecUnChefStockInactif() {
		// arrange
		hmPersonnelUnderTest.put(chefStockInactif.getID(), chefStockInactif);
		// act
		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, true, null);
		// assert
		assertEquals(chefStockInactif, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourStockerLotDansMapAvecUnChefBricoInactif() {
		hmPersonnelUnderTest.put(chefBricoInactif.getID(), chefBricoInactif);
		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, true, null);
		assertEquals(null, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourStockerLotDansMapAvecOuvrierInactif() {
		hmPersonnelUnderTest.put(ouvrier.getID(), ouvrier);

		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, true, null);

		assertEquals(ouvrier, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourStockerDansMapAvecOuvrierActif() {
		ouvrier.setActif(true);
		hmPersonnelUnderTest.put(ouvrier.getID(), ouvrier);

		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, true, null);

		assertEquals(null, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourStockerDansMapPersonnelActifEtInactif() {
		ouvrier.setActif(true);
		hmPersonnelUnderTest.put(ouvrier.getID(), ouvrier);
		hmPersonnelUnderTest.put(chefStockInactif.getID(), chefStockInactif);

		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, true, null);

		assertEquals(chefStockInactif, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourMonterMeubleChambreDansHashMapAvecUnChefBricoInactif() {
		hmPersonnelUnderTest.put(chefBricoInactif.getID(), chefBricoInactif);

		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, false, PiecesMaison.CHAMBRE);

		assertEquals(chefBricoInactif, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourMonterMeubleChambreDansHashMapAvecUnOuvrierActifEtChefStock() {
		ouvrier.setActif(true);
		hmPersonnelUnderTest.put(ouvrier.getID(), ouvrier);
		hmPersonnelUnderTest.put(chefStockInactif.getID(), chefStockInactif);

		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, false, PiecesMaison.CHAMBRE);

		assertEquals(null, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourMonterMeubleWCDansHashMapAvecOuvrierChambreInactif() {
		hmPersonnelUnderTest.put(ouvrier.getID(), ouvrier);
		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, false, PiecesMaison.WC);
		assertEquals(null, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourMonterMeubleChambreDansHashMapAvecPersonnelActifEtInactif() {
		ouvrier.setActif(true);
		hmPersonnelUnderTest.put(ouvrier.getID(), ouvrier);
		hmPersonnelUnderTest.put(chefBricoInactif.getID(), chefBricoInactif);
		hmPersonnelUnderTest.put(chefStockInactif.getID(), chefStockInactif);

		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, false, PiecesMaison.WC);
		assertEquals(chefBricoInactif, result);
	}

	@Test
	public void testTrouverPersonnelInactifPourMonterMeublePasPrecise() {
		hmPersonnelUnderTest.put(ouvrier.getID(), ouvrier);
		hmPersonnelUnderTest.put(chefBricoInactif.getID(), chefBricoInactif);

		Personnel result = Personnel.trouverPersonnelInactif(hmPersonnelUnderTest, false, null);
		assertEquals(null, result);
	}

}