package personne;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entrepot.Entrepot;
import meuble.LotDePiecesDetachees;
import meuble.Meuble;


/**
 * La classe GestionStockTest teste les méthodes de GestionStock
 * 
 * @author Lauréline et Julie
 */

public class GestionStockTest {
	ChefStock chefStock = new ChefStock("A", "a");
	int m;
	int n;
	int[][] tabEntrepot;
	LotDePiecesDetachees lot = new LotDePiecesDetachees("vis", 1, 1, 2); // volume 2
	HashMap<Integer, Personnel> hmPersonnel = new HashMap<Integer, Personnel>();

	@Test
	public void testRechercherEmplacementLibreDansEntrepotComplet() {
		int[][] tabEntrepot = { { 1 } }; // on a un entrepot rempli de 1 case
		Tuple<Integer, Integer> expected = null;

		Tuple<Integer, Integer> result = chefStock.rechercherEmplacementLibre(1, tabEntrepot);

		assertEquals(expected, result);
	}

	@Test
	public void testRechercherEmplacementLibreDansEntrepotNull() {
		tabEntrepot = null;

		Tuple<Integer, Integer> result = chefStock.rechercherEmplacementLibre(1, tabEntrepot);

		assertNull(result);
	}

	@Test
	public void testRechercherEmplacementLibreObjectTropVolumineux() {
		tabEntrepot = new int[1][3];
		tabEntrepot[0][1] = 1;

		Tuple<Integer, Integer> result = chefStock.rechercherEmplacementLibre(3, tabEntrepot);

		assertNull(result);
	}

	@Test
	public void testRechercherEmplacementLibreFonctionneObjectVolume2() {
		tabEntrepot = new int[1][4];
		tabEntrepot[0][0] = 1;
		Tuple<Integer, Integer> expected = new Tuple<Integer, Integer>(0, 1);

		Tuple<Integer, Integer> result = chefStock.rechercherEmplacementLibre(2, tabEntrepot);

		assertEquals(expected, result);
	}

	@Test
	public void testRechercherEmplacementLibreEntrepotVide() {
		tabEntrepot = new int[1][3];
		Tuple<Integer, Integer> expected = new Tuple<Integer, Integer>(0, 0);

		Tuple<Integer, Integer> result = chefStock.rechercherEmplacementLibre(1, tabEntrepot);

		assertEquals(expected, result);
	}

	@Test
	public void testRechercherEmplacementLibreEntrepot2Rangees() {
		tabEntrepot = new int[2][3];
		tabEntrepot[0][1] = 1;
		Tuple<Integer, Integer> expected = new Tuple<Integer, Integer>(1, 0);

		Tuple<Integer, Integer> result = chefStock.rechercherEmplacementLibre(2, tabEntrepot);

		assertEquals(expected, result);
	}

	@Test
	public void testRechercherDernierEmplacementLibreEntrepot2Rangees() {
		// tabEntrepot = new int[2][3];
		int[][] tabEntrepot = { { 1, 0, 0 }, { 1, 0, 0 }, { 0, 0, 1 } };
		Tuple<Integer, Integer> expected = new Tuple<Integer, Integer>(2, 0);
		Tuple<Integer, Integer> result = chefStock.rechercherDernierEmplacementLibre(2, tabEntrepot);

		assertEquals(expected, result);
	}

	@Test
	public void testDeplacerLotEmplacementVide() {
		tabEntrepot = new int[1][4];
		Tuple<Integer, Integer> AncienneCoord = new Tuple<Integer, Integer>(0, 0);
		lot.setCoordonnees(AncienneCoord);
		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(0, 2);

		boolean result = chefStock.deplacerLot(lot, coord, tabEntrepot);

		assertTrue(result);
		assertEquals(0, tabEntrepot[0][0]);
		assertEquals(0, tabEntrepot[0][1]);
		assertEquals(lot.getID(), tabEntrepot[0][2]);
		assertEquals(lot.getID(), tabEntrepot[0][3]);
	}

	@Test
	public void testDeplacerLotCoordonneesNegatives() {
		tabEntrepot = new int[1][3];
		Tuple<Integer, Integer> AncienneCoord = new Tuple<Integer, Integer>(0, 0);
		lot.setCoordonnees(AncienneCoord);

		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(-1, -1);

		boolean result = chefStock.deplacerLot(lot, coord, tabEntrepot);

		assertFalse(result);
	}

	@Test
	public void testDeplacerLotPasAssezDePlace() {
		tabEntrepot = new int[1][2];
		Tuple<Integer, Integer> AncienneCoord = new Tuple<Integer, Integer>(0, 0);
		lot.setCoordonnees(AncienneCoord);

		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(0, 1);

		boolean result = chefStock.deplacerLot(lot, coord, tabEntrepot);

		assertFalse(result);
	}

	@Test
	public void testDeplacerLotCoordHorsTableau() {
		tabEntrepot = new int[1][2];
		Tuple<Integer, Integer> AncienneCoord = new Tuple<Integer, Integer>(0, 0);
		lot.setCoordonnees(AncienneCoord);

		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(1, 1);

		boolean result = chefStock.deplacerLot(lot, coord, tabEntrepot);

		assertFalse(result);
	}

	@Test
	public void testDeplacerLotMalInitialise() {
		tabEntrepot = new int[1][2];
		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(null, null); // ???

		boolean result = chefStock.deplacerLot(lot, coord, tabEntrepot);

		assertFalse(result);
	}

	@Test
	public void testDeplacerLotPasDansLeTableauAvant() {
		tabEntrepot = new int[1][3];
		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(0, 1);

		boolean result = chefStock.deplacerLot(lot, coord, tabEntrepot);

		assertFalse(result);
	}

	/*
	 * @Test public void testRegrouperLots1PasDeTemps() { //recréer un environnement
	 * d'entrepôt
	 * 
	 * //clean up Personnel.setCptHistorique(); Meuble.setNbMeublesEnConstruction();
	 * LotDePiecesDetachees.setNbLotsAjoutes(); Ouvrier.setCptOuvrier();
	 * ChefEquipe.setNbChefs(); //fin clean up
	 * 
	 * 
	 * Map<Integer, ChefEquipe> dicoChefUnderTest = new HashMap<>(); Map<Integer,
	 * Personnel> dicoPersonnelUnderTest = new HashMap<>(); Map<Integer, Meuble>
	 * dicoMeubleUnderTest = new HashMap<>(); Map<Integer, LotDePiecesDetachees>
	 * dicoLotUnderTest = new HashMap<>(); tabEntrepot = new int[8][7]; Entrepot e =
	 * new Entrepot(100, dicoChefUnderTest, dicoPersonnelUnderTest,
	 * dicoMeubleUnderTest, dicoLotUnderTest); e.tabEntrepot = tabEntrepot;
	 * 
	 * for (int i = 0; i < 8; i++) { for (int j = 0; j < 7; j++) { tabEntrepot[i][j]
	 * = 0; } }
	 * 
	 * ChefStock chefStock1 = new ChefStock("Dumbledore", "Albus"); ChefStock
	 * chefStock2 = new ChefStock("McGonagall", "Minerva"); ChefStock chefStock3 =
	 * new ChefStock("Rogue", "Severus"); ChefBrico chefBrico4 = new
	 * ChefBrico("Lupin", "Remus"); ChefBrico chefBrico5 = new ChefBrico("Flitwick",
	 * "Filius"); ChefBrico chefBrico6 = new ChefBrico("Lockhart", "Gilderoy");
	 * Ouvrier ouvrier1 = new Ouvrier("Abbot", "Hannah", PiecesMaison.CHAMBRE, 1);
	 * Ouvrier ouvrier2 = new Ouvrier("Bell", "Katie", PiecesMaison.CUISINE, 2);
	 * Ouvrier ouvrier3 = new Ouvrier("Brown", "Lavande", PiecesMaison.WC, 2);
	 * Ouvrier ouvrier4 = new Ouvrier("Chang", "Cho", PiecesMaison.SALON, 1);
	 * Ouvrier ouvrier5 = new Ouvrier("Delacour", "Fleur", PiecesMaison.SALLEdeBAIN,
	 * 3); Ouvrier ouvrier6 = new Ouvrier("Diggory", "Cédric",
	 * PiecesMaison.SALLEaMANGER, 4); Ouvrier ouvrier7 = new Ouvrier("Finnigan",
	 * "Seamus", PiecesMaison.CUISINE, 6); Ouvrier ouvrier8 = new Ouvrier("Granger",
	 * "Hermione", PiecesMaison.SALON, 6); Ouvrier ouvrier9 = new
	 * Ouvrier("Londubat", "Neville", PiecesMaison.SALLEdeBAIN, 3); Ouvrier
	 * ouvrier10 = new Ouvrier("Potter", "Harry", PiecesMaison.CHAMBRE, 6); Ouvrier
	 * ouvrier11 = new Ouvrier("Weasley", "Ron", PiecesMaison.CHAMBRE, 3); Ouvrier
	 * ouvrier12 = new Ouvrier("Thomas", "Dean", PiecesMaison.CUISINE, 6); //
	 * initialise dicoChef dicoChefUnderTest.put(1, chefStock1);
	 * dicoChefUnderTest.put(2, chefStock2); dicoChefUnderTest.put(3, chefStock3);
	 * dicoChefUnderTest.put(4, chefBrico4); dicoChefUnderTest.put(5, chefBrico5);
	 * dicoChefUnderTest.put(6, chefBrico6); // initialise dicoPersonnelUnderTest
	 * dicoPersonnelUnderTest.put(1, chefStock1); dicoPersonnelUnderTest.put(2,
	 * chefStock2); dicoPersonnelUnderTest.put(3, chefStock3);
	 * dicoPersonnelUnderTest.put(4, chefBrico4); dicoPersonnelUnderTest.put(5,
	 * chefBrico5); dicoPersonnelUnderTest.put(6, chefBrico6);
	 * dicoPersonnelUnderTest.put(7, ouvrier1); dicoPersonnelUnderTest.put(8,
	 * ouvrier2); dicoPersonnelUnderTest.put(9, ouvrier3);
	 * dicoPersonnelUnderTest.put(10, ouvrier4); dicoPersonnelUnderTest.put(11,
	 * ouvrier5); dicoPersonnelUnderTest.put(12, ouvrier6);
	 * dicoPersonnelUnderTest.put(13, ouvrier7); dicoPersonnelUnderTest.put(14,
	 * ouvrier8); dicoPersonnelUnderTest.put(15, ouvrier9);
	 * dicoPersonnelUnderTest.put(16, ouvrier10); dicoPersonnelUnderTest.put(17,
	 * ouvrier11); dicoPersonnelUnderTest.put(18, ouvrier12); // initialise les
	 * tableaux des ouvriers des chefs ArrayList<Ouvrier> tabOuvriers1 =
	 * chefStock1.getTabOuvrier(); ArrayList<Ouvrier> tabOuvriers2 =
	 * chefStock2.getTabOuvrier(); ArrayList<Ouvrier> tabOuvriers3 =
	 * chefStock3.getTabOuvrier(); ArrayList<Ouvrier> tabOuvriers4 =
	 * chefBrico4.getTabOuvrier(); ArrayList<Ouvrier> tabOuvriers5 =
	 * chefBrico5.getTabOuvrier(); ArrayList<Ouvrier> tabOuvriers6 =
	 * chefBrico6.getTabOuvrier(); tabOuvriers1.addAll(Arrays.asList(ouvrier1,
	 * ouvrier4)); tabOuvriers2.addAll(Arrays.asList(ouvrier2, ouvrier3));
	 * tabOuvriers3.addAll(Arrays.asList(ouvrier5, ouvrier9, ouvrier11));
	 * tabOuvriers4.add(ouvrier6); tabOuvriers6.addAll(Arrays.asList(ouvrier7,
	 * ouvrier8, ouvrier10, ouvrier12)); // initialise les lots LotDePiecesDetachees
	 * lot1 = new LotDePiecesDetachees("Vis", 0.8, 10, 3); LotDePiecesDetachees lot2
	 * = new LotDePiecesDetachees("Vis", 0.8, 11, 2); LotDePiecesDetachees lot3 =
	 * new LotDePiecesDetachees("Vis", 2.3, 25.33, 2); LotDePiecesDetachees lot4 =
	 * new LotDePiecesDetachees("Vis", 0.8, 3.2, 4); LotDePiecesDetachees lot5 = new
	 * LotDePiecesDetachees("Planche", 4.5, 10.79, 3); LotDePiecesDetachees lot6 =
	 * new LotDePiecesDetachees("Planche", 8, 15.20, 6); LotDePiecesDetachees lot7 =
	 * new LotDePiecesDetachees("Planche", 4.5, 10.79, 3); LotDePiecesDetachees lot8
	 * = new LotDePiecesDetachees("Poignée", 0.5, 3.99, 1); LotDePiecesDetachees
	 * lot9 = new LotDePiecesDetachees("Poignée", 0.7, 4.05, 1);
	 * LotDePiecesDetachees lot10 = new LotDePiecesDetachees("Clou", 0.2, 2.99, 1);
	 * LotDePiecesDetachees lot11 = new LotDePiecesDetachees("Porte", 8, 46, 5);
	 * LotDePiecesDetachees lot12 = new LotDePiecesDetachees("PiedChaise", 1.2,
	 * 15.2, 2); LotDePiecesDetachees lot13 = new LotDePiecesDetachees("PiedChaise",
	 * 1.2, 15.2, 2);
	 * 
	 * // initialise dicoLotUnderTest dicoLotUnderTest.put(1, lot1);
	 * dicoLotUnderTest.put(2, lot2); dicoLotUnderTest.put(3, lot3);
	 * dicoLotUnderTest.put(4, lot4); //dicoLotUnderTest.put(5, lot5);
	 * dicoLotUnderTest.put(6, lot6); //dicoLotUnderTest.put(7, lot7);
	 * //dicoLotUnderTest.put(8, lot8); //dicoLotUnderTest.put(9, lot9);
	 * //dicoLotUnderTest.put(10, lot10); dicoLotUnderTest.put(11, lot11);
	 * //dicoLotUnderTest.put(12, lot12); //dicoLotUnderTest.put(13, lot13);
	 * 
	 * //tabEntrepotInitial
	 * 
	 * Tuple<Integer,Integer> coord1 = new Tuple<Integer,Integer>(0,0);
	 * lot1.setCoordonnees(coord1); chefStock1.ajouterLot(lot1, tabEntrepot);
	 * 
	 * Tuple<Integer,Integer> coord2 = new Tuple<Integer,Integer>(0,3);
	 * lot2.setCoordonnees(coord2); chefStock1.ajouterLot(lot2, tabEntrepot);
	 * 
	 * Tuple<Integer,Integer> coord3 = new Tuple<Integer,Integer>(0,5);
	 * lot3.setCoordonnees(coord3); chefStock1.ajouterLot(lot3, tabEntrepot);
	 * 
	 * Tuple<Integer,Integer> coord4 = new Tuple<Integer,Integer>(1,0);
	 * lot4.setCoordonnees(coord4); chefStock1.ajouterLot(lot4, tabEntrepot);
	 * 
	 * Tuple<Integer,Integer> coord6 = new Tuple<Integer,Integer>(2,0);
	 * lot6.setCoordonnees(coord6); chefStock1.ajouterLot(lot6, tabEntrepot);
	 * 
	 * Tuple<Integer,Integer> coord11 = new Tuple<Integer,Integer>(3,0);
	 * lot11.setCoordonnees(coord11); chefStock1.ajouterLot(lot11, tabEntrepot);
	 * 
	 * ArrayList<LotDePiecesDetachees> listeLotsARegrouper = new
	 * ArrayList<>(dicoLotUnderTest.values()); // les nouveaux lots ajoutés et ceux
	 * supprimés de dicoLot sont donc directement bougés dans/hors
	 * listeLotsARegrouper Collections.sort(listeLotsARegrouper,
	 * Comparator.comparing(LotDePiecesDetachees::getVolume));
	 * Collections.reverse(listeLotsARegrouper); boolean
	 * listeLotsARegrouperBienRemplie = (listeLotsARegrouper.get(0) == lot6) &&
	 * (listeLotsARegrouper.get(1) == lot11) && (listeLotsARegrouper.get(2) == lot4)
	 * && (listeLotsARegrouper.get(3) == lot1) && (listeLotsARegrouper.get(4) ==
	 * lot3) && (listeLotsARegrouper.get(5) == lot2);
	 * 
	 * chefStock1.regrouperLots(e, listeLotsARegrouper);
	 * 
	 * boolean check0 = Arrays.equals(tabEntrepot[0],new int[] {1, 1, 1, 2, 2, 3,
	 * 3}); boolean check1 = Arrays.equals(tabEntrepot[1],new int[] {0, 0, 0, 0, 0,
	 * 0, 0}); boolean check2 = Arrays.equals(tabEntrepot[2],new int[] {0, 0, 0, 0,
	 * 0, 0, 0}); boolean check3 = Arrays.equals(tabEntrepot[3],new int[] {0, 0, 0,
	 * 0, 0, 0, 0}); boolean check4 = Arrays.equals(tabEntrepot[4],new int[] {0, 0,
	 * 0, 0, 0, 0, 0}); boolean check5 = Arrays.equals(tabEntrepot[5],new int[] {0,
	 * 0, 0, 4, 4, 4, 4}); boolean check6 = Arrays.equals(tabEntrepot[6],new int[]
	 * {0, 0, 11, 11, 11, 11, 11}); boolean check7 =
	 * Arrays.equals(tabEntrepot[7],new int[] {0, 6, 6, 6, 6, 6, 6});
	 * //Arrays.equals(tabEntrepot,new int [][]{{1, 1, 1, 2, 2, 3, 3}, {0, 0, 0, 0,
	 * 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0,
	 * 0}, {0, 0, 0, 4, 4, 4, 4}, {0, 0, 11, 11, 11, 11, 11}, {0, 6, 6, 6, 6, 6, 6}}
	 * ); boolean tabEntrepotBienRempli = check0 && check1 && check2 && check3 &&
	 * check4 && check5 && check6 && check7;
	 * 
	 * assertTrue(listeLotsARegrouperBienRemplie && tabEntrepotBienRempli); }
	 */
	
	
	
	@Test
	public void testRegrouperLots2PasDeTemps() {
		// recréer un environnement d'entrepôt

		// clean up
		Personnel.setCptHistorique();
		LotDePiecesDetachees.setNbLotsAjoutes();
		Ouvrier.setCptOuvrier();
		ChefEquipe.setNbChefs();
		// fin clean up

		Map<Integer, ChefEquipe> dicoChefUnderTest = new HashMap<>();
		Map<Integer, Personnel> dicoPersonnelUnderTest = new HashMap<>();
		Map<Integer, Meuble> dicoMeubleUnderTest = new HashMap<>();
		Map<Integer, LotDePiecesDetachees> dicoLotUnderTest = new HashMap<>();
		tabEntrepot = new int[8][7];
		Entrepot e = new Entrepot(100, dicoChefUnderTest, dicoPersonnelUnderTest, dicoMeubleUnderTest,
				dicoLotUnderTest);
		e.tabEntrepot = tabEntrepot;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				tabEntrepot[i][j] = 0;
			}
		}

		ChefStock chefStock1 = new ChefStock("Dumbledore", "Albus");
		ChefStock chefStock2 = new ChefStock("McGonagall", "Minerva");
		ChefStock chefStock3 = new ChefStock("Rogue", "Severus");
		ChefBrico chefBrico4 = new ChefBrico("Lupin", "Remus");
		ChefBrico chefBrico5 = new ChefBrico("Flitwick", "Filius");
		ChefBrico chefBrico6 = new ChefBrico("Lockhart", "Gilderoy");
		Ouvrier ouvrier1 = new Ouvrier("Abbot", "Hannah", PiecesMaison.CHAMBRE, 1);
		Ouvrier ouvrier2 = new Ouvrier("Bell", "Katie", PiecesMaison.CUISINE, 2);
		Ouvrier ouvrier3 = new Ouvrier("Brown", "Lavande", PiecesMaison.WC, 2);
		Ouvrier ouvrier4 = new Ouvrier("Chang", "Cho", PiecesMaison.SALON, 1);
		Ouvrier ouvrier5 = new Ouvrier("Delacour", "Fleur", PiecesMaison.SALLEdeBAIN, 3);
		Ouvrier ouvrier6 = new Ouvrier("Diggory", "Cédric", PiecesMaison.SALLEaMANGER, 4);
		Ouvrier ouvrier7 = new Ouvrier("Finnigan", "Seamus", PiecesMaison.CUISINE, 6);
		Ouvrier ouvrier8 = new Ouvrier("Granger", "Hermione", PiecesMaison.SALON, 6);
		Ouvrier ouvrier9 = new Ouvrier("Londubat", "Neville", PiecesMaison.SALLEdeBAIN, 3);
		Ouvrier ouvrier10 = new Ouvrier("Potter", "Harry", PiecesMaison.CHAMBRE, 6);
		Ouvrier ouvrier11 = new Ouvrier("Weasley", "Ron", PiecesMaison.CHAMBRE, 3);
		Ouvrier ouvrier12 = new Ouvrier("Thomas", "Dean", PiecesMaison.CUISINE, 6);

		// initialise dicoChef
		dicoChefUnderTest.put(1, chefStock1);
		dicoChefUnderTest.put(2, chefStock2);
		dicoChefUnderTest.put(3, chefStock3);
		dicoChefUnderTest.put(4, chefBrico4);
		dicoChefUnderTest.put(5, chefBrico5);
		dicoChefUnderTest.put(6, chefBrico6);

		// initialise dicoPersonnelUnderTest
		dicoPersonnelUnderTest.put(1, chefStock1);
		dicoPersonnelUnderTest.put(2, chefStock2);
		dicoPersonnelUnderTest.put(3, chefStock3);
		dicoPersonnelUnderTest.put(4, chefBrico4);
		dicoPersonnelUnderTest.put(5, chefBrico5);
		dicoPersonnelUnderTest.put(6, chefBrico6);
		dicoPersonnelUnderTest.put(7, ouvrier1);
		dicoPersonnelUnderTest.put(8, ouvrier2);
		dicoPersonnelUnderTest.put(9, ouvrier3);
		dicoPersonnelUnderTest.put(10, ouvrier4);
		dicoPersonnelUnderTest.put(11, ouvrier5);
		dicoPersonnelUnderTest.put(12, ouvrier6);
		dicoPersonnelUnderTest.put(13, ouvrier7);
		dicoPersonnelUnderTest.put(14, ouvrier8);
		dicoPersonnelUnderTest.put(15, ouvrier9);
		dicoPersonnelUnderTest.put(16, ouvrier10);
		dicoPersonnelUnderTest.put(17, ouvrier11);
		dicoPersonnelUnderTest.put(18, ouvrier12);

		// initialise les tableaux des ouvriers des chefs
		ArrayList<Ouvrier> tabOuvriers1 = chefStock1.getTabOuvrier();
		ArrayList<Ouvrier> tabOuvriers2 = chefStock2.getTabOuvrier();
		ArrayList<Ouvrier> tabOuvriers3 = chefStock3.getTabOuvrier();
		ArrayList<Ouvrier> tabOuvriers4 = chefBrico4.getTabOuvrier();
		ArrayList<Ouvrier> tabOuvriers5 = chefBrico5.getTabOuvrier();
		ArrayList<Ouvrier> tabOuvriers6 = chefBrico6.getTabOuvrier();

		tabOuvriers1.addAll(Arrays.asList(ouvrier1, ouvrier4));
		tabOuvriers2.addAll(Arrays.asList(ouvrier2, ouvrier3));
		tabOuvriers3.addAll(Arrays.asList(ouvrier5, ouvrier9, ouvrier11));
		tabOuvriers4.add(ouvrier6);
		tabOuvriers6.addAll(Arrays.asList(ouvrier7, ouvrier8, ouvrier10, ouvrier12));

		// initialise les lots
		LotDePiecesDetachees lot1 = new LotDePiecesDetachees("Vis", 0.8, 10, 3);
		LotDePiecesDetachees lot2 = new LotDePiecesDetachees("Vis", 0.8, 11, 2);
		LotDePiecesDetachees lot3 = new LotDePiecesDetachees("Vis", 2.3, 25.33, 2);
		LotDePiecesDetachees lot4 = new LotDePiecesDetachees("Vis", 0.8, 3.2, 4);
		LotDePiecesDetachees lot5 = new LotDePiecesDetachees("Planche", 4.5, 10.79, 3);
		LotDePiecesDetachees lot6 = new LotDePiecesDetachees("Planche", 8, 15.20, 6);
		LotDePiecesDetachees lot7 = new LotDePiecesDetachees("Planche", 4.5, 10.79, 3);
		LotDePiecesDetachees lot8 = new LotDePiecesDetachees("Poignée", 0.5, 3.99, 1);
		LotDePiecesDetachees lot9 = new LotDePiecesDetachees("Poignée", 0.7, 4.05, 1);
		LotDePiecesDetachees lot10 = new LotDePiecesDetachees("Clou", 0.2, 2.99, 1);
		LotDePiecesDetachees lot11 = new LotDePiecesDetachees("Porte", 8, 46, 5);
		LotDePiecesDetachees lot12 = new LotDePiecesDetachees("PiedChaise", 1.2, 15.2, 2);
		LotDePiecesDetachees lot13 = new LotDePiecesDetachees("PiedChaise", 1.2, 15.2, 2);

		// initialise dicoLotUnderTest
		dicoLotUnderTest.put(1, lot1);
		dicoLotUnderTest.put(2, lot2);
		dicoLotUnderTest.put(3, lot3);
		dicoLotUnderTest.put(4, lot4);
		dicoLotUnderTest.put(6, lot6);
		// dicoLotUnderTest.put(7, lot7);
		// dicoLotUnderTest.put(8, lot8);
		// dicoLotUnderTest.put(9, lot9);
		dicoLotUnderTest.put(11, lot11);
		// dicoLotUnderTest.put(13, lot13);

		// tabEntrepotInitial
		Tuple<Integer, Integer> coord6 = new Tuple<Integer, Integer>(0, 0);
		lot6.setCoordonnees(coord6);
		chefStock1.ajouterLot(lot6, tabEntrepot);

		Tuple<Integer, Integer> coord1 = new Tuple<Integer, Integer>(1, 0);
		lot1.setCoordonnees(coord1);
		chefStock1.ajouterLot(lot1, tabEntrepot);

		Tuple<Integer, Integer> coord4 = new Tuple<Integer, Integer>(1, 3);
		lot4.setCoordonnees(coord4);
		chefStock1.ajouterLot(lot4, tabEntrepot);

		Tuple<Integer, Integer> coord2 = new Tuple<Integer, Integer>(2, 0);
		lot2.setCoordonnees(coord2);
		chefStock1.ajouterLot(lot2, tabEntrepot);

		Tuple<Integer, Integer> coord3 = new Tuple<Integer, Integer>(2, 2);
		lot3.setCoordonnees(coord3);
		chefStock1.ajouterLot(lot3, tabEntrepot);

		Tuple<Integer, Integer> coord11 = new Tuple<Integer, Integer>(3, 0);
		lot11.setCoordonnees(coord11);
		chefStock1.ajouterLot(lot11, tabEntrepot);

		/*
		 * System.out.println("-------------------------------------------DEBUT"); for
		 * (int i = 0; i < 8; i++) { String row = ""; for (int j = 0; j < 7; j++) { if
		 * (e.tabEntrepot[i][j] > 9) { row += e.tabEntrepot[i][j] + " "; } else row +=
		 * e.tabEntrepot[i][j] + "  "; } System.out.println(row); }
		 * System.out.println("-------------------------------------------");
		 */

		ArrayList<LotDePiecesDetachees> listeLotsARegrouper = new ArrayList<>(dicoLotUnderTest.values()); // les
																											// nouveaux
																											// lots
																											// ajoutés
																											// et ceux
																											// supprimés
																											// de
																											// dicoLot
																											// sont donc
																											// directement
																											// bougés
																											// dans/hors
																											// listeLotsARegrouper
		Collections.sort(listeLotsARegrouper, Comparator.comparing(LotDePiecesDetachees::getVolume));
		Collections.reverse(listeLotsARegrouper);
		boolean listeLotsARegrouperBienRemplie1 = (listeLotsARegrouper.get(0) == lot6)
				&& (listeLotsARegrouper.get(1) == lot11) && (listeLotsARegrouper.get(2) == lot4)
				&& (listeLotsARegrouper.get(3) == lot1) && (listeLotsARegrouper.get(4) == lot3)
				&& (listeLotsARegrouper.get(5) == lot2);

		chefStock1.regrouperLots(e, listeLotsARegrouper);

		boolean check0 = Arrays.equals(tabEntrepot[0], new int[] { 0, 0, 0, 0, 0, 0, 0 });
		boolean check1 = Arrays.equals(tabEntrepot[1], new int[] { 1, 1, 1, 4, 4, 4, 4 });
		boolean check2 = Arrays.equals(tabEntrepot[2], new int[] { 0, 0, 0, 0, 0, 0, 0 });
		boolean check3 = Arrays.equals(tabEntrepot[3], new int[] { 0, 0, 0, 0, 0, 0, 0 });
		boolean check4 = Arrays.equals(tabEntrepot[4], new int[] { 0, 0, 0, 0, 0, 0, 0 });
		boolean check5 = Arrays.equals(tabEntrepot[5], new int[] { 0, 0, 0, 0, 0, 2, 2 });
		boolean check6 = Arrays.equals(tabEntrepot[6], new int[] { 3, 3, 11, 11, 11, 11, 11 });
		boolean check7 = Arrays.equals(tabEntrepot[7], new int[] { 0, 6, 6, 6, 6, 6, 6 });
		boolean tabEntrepotBienRempli1 = check0 && check1 && check2 && check3 && check4 && check5 && check6 && check7;

		dicoLotUnderTest.put(5, lot5);
		dicoLotUnderTest.put(12, lot12);
		dicoLotUnderTest.put(10, lot10);

		Tuple<Integer, Integer> coord5 = new Tuple<Integer, Integer>(0, 0);
		lot5.setCoordonnees(coord5);
		chefStock1.ajouterLot(lot5, tabEntrepot);

		Tuple<Integer, Integer> coord12 = new Tuple<Integer, Integer>(0, 3);
		lot12.setCoordonnees(coord12);
		chefStock1.ajouterLot(lot12, tabEntrepot);

		Tuple<Integer, Integer> coord10 = new Tuple<Integer, Integer>(0, 5);
		lot10.setCoordonnees(coord10);
		chefStock1.ajouterLot(lot10, tabEntrepot);

		listeLotsARegrouper = new ArrayList<>(dicoLotUnderTest.values()); // les nouveaux lots ajoutés et ceux supprimés
																			// de dicoLot sont donc directement bougés
																			// dans/hors listeLotsARegrouper
		Collections.sort(listeLotsARegrouper, Comparator.comparing(LotDePiecesDetachees::getVolume));
		Collections.reverse(listeLotsARegrouper);

		boolean listeLotsARegrouperBienRemplie2 = (listeLotsARegrouper.get(0) == lot6)
				&& (listeLotsARegrouper.get(1) == lot11) && (listeLotsARegrouper.get(2) == lot4)
				&& (listeLotsARegrouper.get(3) == lot5) && (listeLotsARegrouper.get(4) == lot1)
				&& (listeLotsARegrouper.get(5) == lot12) && (listeLotsARegrouper.get(6) == lot3)
				&& (listeLotsARegrouper.get(7) == lot2) && (listeLotsARegrouper.get(8) == lot10);

		chefStock1.regrouperLots(e, listeLotsARegrouper);

		boolean check0bis = Arrays.equals(tabEntrepot[0], new int[] { 0, 0, 0, 0, 0, 0, 0 });
		boolean check1bis = Arrays.equals(tabEntrepot[1], new int[] { 1, 1, 1, 4, 4, 4, 4 });
		boolean check2bis = Arrays.equals(tabEntrepot[2], new int[] { 0, 0, 0, 0, 0, 0, 0 });
		boolean check3bis = Arrays.equals(tabEntrepot[3], new int[] { 0, 0, 0, 0, 0, 0, 0 });
		boolean check4bis = Arrays.equals(tabEntrepot[4], new int[] { 0, 0, 0, 0, 0, 0, 0 });
		boolean check5bis = Arrays.equals(tabEntrepot[5], new int[] { 2, 2, 12, 12, 5, 5, 5 });
		boolean check6bis = Arrays.equals(tabEntrepot[6], new int[] { 3, 3, 11, 11, 11, 11, 11 });
		boolean check7bis = Arrays.equals(tabEntrepot[7], new int[] { 10, 6, 6, 6, 6, 6, 6 });
		boolean tabEntrepotBienRempli2 = check0bis && check1bis && check2bis && check3bis && check4bis && check5bis
				&& check6bis && check7bis;

		dicoChefUnderTest.clear();
		dicoPersonnelUnderTest.clear();
		dicoMeubleUnderTest.clear();
		dicoLotUnderTest.clear();
		tabOuvriers1.clear();
		tabOuvriers2.clear();
		tabOuvriers3.clear();
		tabOuvriers4.clear();
		tabOuvriers5.clear();
		tabOuvriers6.clear();
		e = null;
		Personnel.setCptHistorique();
		Meuble.setNbMeublesEnConstruction();
		LotDePiecesDetachees.setNbLotsAjoutes();
		Ouvrier.setCptOuvrier();
		ChefEquipe.setNbChefs();

		assertTrue(listeLotsARegrouperBienRemplie1 && tabEntrepotBienRempli1 && listeLotsARegrouperBienRemplie2
				&& tabEntrepotBienRempli2);
	}
	
	
	
	
	
	
	
	

	@Test
	public void testRetirerLotStockPersonnelDispo() {
		hmPersonnel.put(chefStock.getID(), chefStock);

		boolean b = GestionStock.retirerLotStock(hmPersonnel);

		assertTrue(b);
	}

	@Test
	public void testRetirerLotStockPasDispo() {
		hmPersonnel.put(chefStock.getID(), chefStock);
		chefStock.setActif(true);

		boolean b = GestionStock.retirerLotStock(hmPersonnel);

		assertFalse(b);
	}

	@Test
	public void testAjouterLotEmplacementVide() {
		tabEntrepot = new int[1][4];
		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(0, 2);
		lot.setCoordonnees(coord);

		chefStock.ajouterLot(lot, tabEntrepot);

		assertEquals(0, tabEntrepot[0][0]);
		assertEquals(0, tabEntrepot[0][1]);
		assertEquals(lot.getID(), tabEntrepot[0][2]);
		assertEquals(lot.getID(), tabEntrepot[0][3]);

	}

	@Test
	public void testAjouterLotCoordonneesNegatives() throws Exception {
		tabEntrepot = new int[1][3];
		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(-1, -1);
		lot.setCoordonnees(coord);

		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> chefStock.ajouterLot(lot, tabEntrepot));

	}

	@Test
	public void testAjouterLotCoordHorsTableau() {
		tabEntrepot = new int[1][3];
		Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(2, 1);
		lot.setCoordonnees(coord);

		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> chefStock.ajouterLot(lot, tabEntrepot));
	}

	@Test
	public void testAjouterLotCoordPasSet() {
		tabEntrepot = new int[1][3];

		Assertions.assertThrows(NullPointerException.class, () -> chefStock.ajouterLot(lot, tabEntrepot));
	}

}