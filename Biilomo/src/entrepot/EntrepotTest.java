package entrepot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import meuble.LotDePiecesDetachees;
import meuble.Meuble;
import personne.ChefBrico;
import personne.ChefEquipe;
import personne.ChefStock;
import personne.Metier;
import personne.Ouvrier;
import personne.Personnel;
import personne.PiecesMaison;
import personne.Tuple;


/**
 * La classe EntrepotTest est la classe testant les méthodes de Entrepot
 * 
 * @author Julie
 */

class EntrepotTest {

	private Map<Integer, ChefEquipe> dicoChefUnderTest;
	private Map<Integer, Personnel> dicoPersonnelUnderTest;
	private Map<Integer, Meuble> dicoMeubleUnderTest;
	private Map<Integer, LotDePiecesDetachees> dicoLotUnderTest;
	private Entrepot e;

	private ChefStock chefStock1;
	private ChefStock chefStock2;
	private ChefStock chefStock3;
	private ChefBrico chefBrico4;
	private ChefBrico chefBrico5;
	private ChefBrico chefBrico6;
	private Ouvrier ouvrier1;
	private Ouvrier ouvrier2;
	private Ouvrier ouvrier3;
	private Ouvrier ouvrier4;
	private Ouvrier ouvrier5;
	private Ouvrier ouvrier6;
	private Ouvrier ouvrier7;
	private Ouvrier ouvrier8;
	private Ouvrier ouvrier9;
	private Ouvrier ouvrier10;
	private Ouvrier ouvrier11;
	private Ouvrier ouvrier12;

	private ArrayList<Ouvrier> tabOuvriers1;
	private ArrayList<Ouvrier> tabOuvriers2;
	private ArrayList<Ouvrier> tabOuvriers3;
	private ArrayList<Ouvrier> tabOuvriers4;
	private ArrayList<Ouvrier> tabOuvriers5;
	private ArrayList<Ouvrier> tabOuvriers6;

	private LotDePiecesDetachees lot1;
	private LotDePiecesDetachees lot2;
	private LotDePiecesDetachees lot3;
	private LotDePiecesDetachees lot4;
	private LotDePiecesDetachees lot5;
	private LotDePiecesDetachees lot6;
	private LotDePiecesDetachees lot7;
	private LotDePiecesDetachees lot8;
	private LotDePiecesDetachees lot9;
	private LotDePiecesDetachees lot10;
	private LotDePiecesDetachees lot11;
	private LotDePiecesDetachees lot12;
	private LotDePiecesDetachees lot13;
	private LotDePiecesDetachees lot14;
	private LotDePiecesDetachees lot15;
	private LotDePiecesDetachees lot16;
	private LotDePiecesDetachees lot17;
	private LotDePiecesDetachees lot18;
	private LotDePiecesDetachees lot19;
	private LotDePiecesDetachees lot20;

	private ArrayList<LotDePiecesDetachees> tabLot;

	private ArrayList<LotDePiecesDetachees> tabPiecesDetachees1;
	private ArrayList<LotDePiecesDetachees> tabPiecesDetachees2;
	private Map<Integer, LotDePiecesDetachees> dicoLotsMeuble1;
	private Map<Integer, LotDePiecesDetachees> dicoLotsMeuble2;
	private Meuble meuble1;
	private Meuble meuble2;

	public int[][] tabEntrepot = new int[10][20];

	@BeforeEach
	public void setUpTest() {
		// déclaration des dictionnaires et entrepôt
		dicoChefUnderTest = new HashMap<>();
		dicoPersonnelUnderTest = new HashMap<>();
		dicoMeubleUnderTest = new HashMap<>();
		dicoLotUnderTest = new HashMap<>();
		e = new Entrepot(100, dicoChefUnderTest, dicoPersonnelUnderTest, dicoMeubleUnderTest, dicoLotUnderTest);
		e.tabEntrepot = tabEntrepot;

		// initialise le personnel et les chefs
		chefStock1 = new ChefStock("Dumbledore", "Albus");
		chefStock2 = new ChefStock("McGonagall", "Minerva");
		chefStock3 = new ChefStock("Rogue", "Severus");
		chefBrico4 = new ChefBrico("Lupin", "Remus");
		chefBrico5 = new ChefBrico("Flitwick", "Filius");
		chefBrico6 = new ChefBrico("Lockhart", "Gilderoy");
		ouvrier1 = new Ouvrier("Abbot", "Hannah", PiecesMaison.CHAMBRE, 1);
		ouvrier2 = new Ouvrier("Bell", "Katie", PiecesMaison.CUISINE, 2);
		ouvrier3 = new Ouvrier("Brown", "Lavande", PiecesMaison.WC, 2);
		ouvrier4 = new Ouvrier("Chang", "Cho", PiecesMaison.SALON, 1);
		ouvrier5 = new Ouvrier("Delacour", "Fleur", PiecesMaison.SALLEdeBAIN, 3);
		ouvrier6 = new Ouvrier("Diggory", "Cédric", PiecesMaison.SALLEaMANGER, 4);
		ouvrier7 = new Ouvrier("Finnigan", "Seamus", PiecesMaison.CUISINE, 6);
		ouvrier8 = new Ouvrier("Granger", "Hermione", PiecesMaison.SALON, 6);
		ouvrier9 = new Ouvrier("Londubat", "Neville", PiecesMaison.SALLEdeBAIN, 3);
		ouvrier10 = new Ouvrier("Potter", "Harry", PiecesMaison.CHAMBRE, 6);
		ouvrier11 = new Ouvrier("Weasley", "Ron", PiecesMaison.CHAMBRE, 3);
		ouvrier12 = new Ouvrier("Thomas", "Dean", PiecesMaison.CUISINE, 6);

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
		tabOuvriers1 = chefStock1.getTabOuvrier();
		tabOuvriers2 = chefStock2.getTabOuvrier();
		tabOuvriers3 = chefStock3.getTabOuvrier();
		tabOuvriers4 = chefBrico4.getTabOuvrier();
		tabOuvriers5 = chefBrico5.getTabOuvrier();
		tabOuvriers6 = chefBrico6.getTabOuvrier();

		tabOuvriers1.addAll(Arrays.asList(ouvrier1, ouvrier4));
		tabOuvriers2.addAll(Arrays.asList(ouvrier2, ouvrier3));
		tabOuvriers3.addAll(Arrays.asList(ouvrier5, ouvrier9, ouvrier11));
		tabOuvriers4.add(ouvrier6);
		tabOuvriers6.addAll(Arrays.asList(ouvrier7, ouvrier8, ouvrier10, ouvrier12));

		// initialise les lots
		lot1 = new LotDePiecesDetachees("Vis", 0.8, 10, 3);
		lot2 = new LotDePiecesDetachees("Vis", 0.8, 11, 2);
		lot3 = new LotDePiecesDetachees("Vis", 2.3, 25.33, 2);
		lot4 = new LotDePiecesDetachees("Vis", 0.8, 3.2, 4);
		lot5 = new LotDePiecesDetachees("Planche", 4.5, 10.79, 3);
		lot6 = new LotDePiecesDetachees("Planche", 8, 15.20, 6);
		lot7 = new LotDePiecesDetachees("Planche", 4.5, 10.79, 3);
		lot8 = new LotDePiecesDetachees("Poignée", 0.5, 3.99, 1);
		lot9 = new LotDePiecesDetachees("Poignée", 0.7, 4.05, 1);
		lot10 = new LotDePiecesDetachees("Clou", 0.2, 2.99, 1);
		lot11 = new LotDePiecesDetachees("Porte", 8, 46, 5);
		lot12 = new LotDePiecesDetachees("PiedChaise", 1.2, 15.2, 2);
		lot13 = new LotDePiecesDetachees("PiedChaise", 1.2, 15.2, 2);

		// initialise dicoLotUnderTest
		dicoLotUnderTest.put(1, lot1);
		dicoLotUnderTest.put(2, lot2);
		dicoLotUnderTest.put(3, lot3);
		dicoLotUnderTest.put(4, lot4);
		dicoLotUnderTest.put(5, lot5);
		dicoLotUnderTest.put(6, lot6);
		dicoLotUnderTest.put(7, lot7);
		dicoLotUnderTest.put(8, lot8);
		dicoLotUnderTest.put(9, lot9);
		dicoLotUnderTest.put(10, lot10);
		dicoLotUnderTest.put(11, lot11);
		dicoLotUnderTest.put(12, lot12);
		dicoLotUnderTest.put(13, lot13);
		tabLot = new ArrayList<>(); // pour le test du lot au meilleur prix
		tabLot.addAll(Arrays.asList(lot1, lot2, lot3, lot4));

		// vont servir pour définir un meuble mais ne vont pas être rajoutés à
		// l'entrepôt
		// pour le meuble1 (la Chaise)
		lot14 = new LotDePiecesDetachees("Vis", 1.2, 15.2, 4);
		lot15 = new LotDePiecesDetachees("PiedChaise", 1.2, 15.2, 4);
		lot16 = new LotDePiecesDetachees("Planche", 1.2, 15.2, 2);
		dicoLotsMeuble1 = new HashMap<>();
		dicoLotsMeuble1.put(14, lot14);
		dicoLotsMeuble1.put(15, lot15);
		dicoLotsMeuble1.put(16, lot16);
		tabPiecesDetachees1 = new ArrayList<>();
		tabPiecesDetachees1.addAll(Arrays.asList(lot1, lot2, lot12, lot13, lot5));

		// pour le meuble2 (l'Armoire)
		lot17 = new LotDePiecesDetachees("Vis", 1.2, 15.2, 6);
		lot18 = new LotDePiecesDetachees("Planche", 1.2, 15.2, 7);
		lot19 = new LotDePiecesDetachees("Porte", 1.2, 15.2, 2);
		lot20 = new LotDePiecesDetachees("Poignée", 1.2, 15.2, 2);
		dicoLotsMeuble2 = new HashMap<>();
		dicoLotsMeuble2.put(17, lot17);
		dicoLotsMeuble2.put(18, lot18);
		dicoLotsMeuble2.put(19, lot19);
		dicoLotsMeuble2.put(20, lot20);
		tabPiecesDetachees2 = new ArrayList<>();

		// initialise les meubles
		meuble1 = new Meuble("Chaise", PiecesMaison.SALON, 1, dicoLotsMeuble1, tabPiecesDetachees1);
		meuble1.setConstructeurMeuble(ouvrier8);
		ouvrier8.setActif(true);
		meuble2 = new Meuble("Armoire", PiecesMaison.CHAMBRE, 5, dicoLotsMeuble2, tabPiecesDetachees2);
		meuble2.setConstructeurMeuble(ouvrier1);
		ouvrier1.setActif(true);

		// initialise dicoMeubleUnderTest
		dicoMeubleUnderTest.put(1, meuble1);
		dicoMeubleUnderTest.put(2, meuble2);

		// initialise tabEntrepot
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				tabEntrepot[i][j] = 0;
			}
		}
	}

	@AfterEach
	public void cleanupTest() {
		dicoChefUnderTest.clear();
		dicoPersonnelUnderTest.clear();
		dicoMeubleUnderTest.clear();
		dicoLotUnderTest.clear();
		dicoLotsMeuble1.clear();
		dicoLotsMeuble2.clear();
		tabOuvriers1.clear();
		tabOuvriers2.clear();
		tabOuvriers3.clear();
		tabOuvriers4.clear();
		tabOuvriers5.clear();
		tabOuvriers6.clear();
		tabPiecesDetachees1.clear();
		tabPiecesDetachees2.clear();
		e = null;
		Personnel.setCptHistorique();
		Meuble.setNbMeublesEnConstruction();
		LotDePiecesDetachees.setNbLotsAjoutes();
		Ouvrier.setCptOuvrier();
		ChefEquipe.setNbChefs();
	}

	// ATTENTION : les tests sont exécutés dans l'ordre ALPHABETIQUE

	@ParameterizedTest
	@CsvSource({ "1,true", "2,true", "3,true", "4,true", "5,true", "6,false" })
	public void testVerifMaxOuvrier(int chefID, boolean value) {
		ChefEquipe c = dicoChefUnderTest.get(chefID);
		boolean expected = value;
		boolean result = e.verifMaxOuvrier(c);
		assertEquals(expected, result);
	}

	@Test
	public void testRecrutementPersonnelOuvrier() {
		e.recrutementPersonnel("Spinnet", "Alicia", Metier.OUVRIER, PiecesMaison.CUISINE, chefStock1);
		boolean insereDansDicoPersonnel = dicoPersonnelUnderTest.get(19).getNom().equals("Spinnet")
				&& dicoPersonnelUnderTest.get(19).getID() == 19;
		boolean insereDansTabOuvriers = chefStock1.getTabOuvrier().get(2).getNom().equals("Spinnet")
				&& chefStock1.getTabOuvrier().get(2).getID() == 19;
		assertTrue(insereDansDicoPersonnel && insereDansTabOuvriers);
	}

	@Test
	public void testRecrutementPersonnelChefStock() {
		e.recrutementPersonnel("Jedusor", "Tom", Metier.CHEFSTOCK, PiecesMaison.RIEN, null);
		boolean insereDansDicoPersonnel = dicoPersonnelUnderTest.get(19).getNom().equals("Jedusor")
				&& dicoPersonnelUnderTest.get(19).getID() == 19;
		boolean insereDansDicoChef = dicoChefUnderTest.get(19).getNom().equals("Jedusor")
				&& dicoChefUnderTest.get(19).getID() == 19;
		assertTrue(insereDansDicoPersonnel && insereDansDicoChef);
	}

	@Test
	public void testRecrutementPersonnelChefBrico() {
		e.recrutementPersonnel("Pettigrow", "Peter", Metier.CHEFBRICO, PiecesMaison.MAISON, null);
		boolean insereDansDicoPersonnel = dicoPersonnelUnderTest.get(19).getNom().equals("Pettigrow")
				&& dicoPersonnelUnderTest.get(19).getID() == 19;
		boolean insereDansDicoChef = dicoChefUnderTest.get(19).getNom().equals("Pettigrow")
				&& dicoChefUnderTest.get(19).getID() == 19;
		assertTrue(insereDansDicoPersonnel && insereDansDicoChef);
	}

	@Test
	public void testReaffectationOuvriers() {
		ArrayList<ChefEquipe> chefDisposReaffectationOuvriers = new ArrayList<>();
		chefDisposReaffectationOuvriers.addAll(Arrays.asList(chefStock1, chefStock2));
		ChefEquipe c = chefStock3;
		int nbOuvriersReaffectes = 3;
		// on réaffecte les trois ouvriers de Rogue à Dumbledore et McGonagall
		e.reaffectationOuvriers(chefDisposReaffectationOuvriers, nbOuvriersReaffectes, c);
		ArrayList<Ouvrier> tabOuvriers1 = chefStock1.getTabOuvrier();
		ArrayList<Ouvrier> tabOuvriers2 = chefStock2.getTabOuvrier();
		boolean bienReaffectesChezDumbledore = tabOuvriers1
				.containsAll(Arrays.asList(ouvrier1, ouvrier4, ouvrier5, ouvrier9));
		boolean bienReaffectesChezMcGonagall = tabOuvriers2.containsAll(Arrays.asList(ouvrier2, ouvrier3, ouvrier11));
		assertTrue(bienReaffectesChezDumbledore && bienReaffectesChezMcGonagall);

	}

	@Test
	public void testRepartitionOuvriersPossibleQuandAucunOuvrier() {
		assertTrue(e.repartitionOuvriersPossible(chefBrico5));
	}

	@ParameterizedTest
	@CsvSource({ "1,true", "2,true", "3,true", "4,true", "6,true" })
	public void testRepartitionOuvrierPossible(int IDchef, boolean value) {
		ChefEquipe c = dicoChefUnderTest.get(IDchef);
		boolean result = e.repartitionOuvriersPossible(c);
		assertEquals(result, value);
	}

	@Test
	public void testLicenciementOuvrier() {
		Ouvrier o = ouvrier9;
		e.licenciementPersonnel(o);
		// ArrayList<Ouvrier> tabOuvrier3 =
		// dicoChefUnderTest.get(o.getIDchef()).getTabOuvrier();
		ArrayList<Ouvrier> tabOuvrier3 = chefStock3.getTabOuvrier();
		boolean estSupprimeDuTabOuvrier = !tabOuvrier3.contains(o);
		boolean estSupprimeDeDicoPersonnel = !dicoPersonnelUnderTest.containsKey(o.getID());
		assertTrue(estSupprimeDuTabOuvrier && estSupprimeDeDicoPersonnel);
	}

	@Test
	public void testLicenciementChefStock() {
		ChefStock cs = chefStock2;
		e.licenciementPersonnel(cs);
		boolean estSupprimeDeDicoChef = !dicoChefUnderTest.containsKey(cs.getID());
		boolean estSupprimeDeDicoPersonnel = !dicoPersonnelUnderTest.containsKey(cs.getID());
		assertTrue(estSupprimeDeDicoChef && estSupprimeDeDicoPersonnel);
	}

	@Test
	public void testLicenciementChefBrico() {
		ChefBrico cb = chefBrico4;
		e.licenciementPersonnel(cb);
		boolean estSupprimeDeDicoChef = !dicoChefUnderTest.containsKey(cb.getID());
		boolean estSupprimeDeDicoPersonnel = !dicoPersonnelUnderTest.containsKey(cb.getID());
		assertTrue(estSupprimeDeDicoChef && estSupprimeDeDicoPersonnel);
	}

	@Test
	public void testMajTresorerieSalaires() {
		double tresorerieInitiale = e.getTresorerie();
		double tresorerieFinaleAttendue = tresorerieInitiale - 120;
		e.majTresorerie(0);
		double tresorerieFinaleObtenue = e.getTresorerie();
		assertEquals(tresorerieFinaleAttendue, tresorerieFinaleObtenue);
	}

	@Test
	public void testMajAvancementDicoMeuble() {
		meuble1.setNbLotsARecupererReinit(); // on imagine qu'on a déjà récupéré les 5 lots et comme la construction se
												// fait en un pas de temps, on teste si l'entrepôt est bien payé
		double tresorerieInitiale = e.getTresorerie();
		e.majAvancementDicoMeuble(1);
		double tresorerieFinaleAttendue = tresorerieInitiale + 145.17000000000002;
		double tresorerieFinaleObtenue = e.getTresorerie();
		boolean tresorerieBienMAJ = (tresorerieFinaleAttendue == tresorerieFinaleObtenue);
		boolean avancementArmoireOK = (1 == meuble2.getEtatAvancement());
		Personnel constructeurChaise = meuble1.getconstructeurMeuble();
		boolean constructeurInactif = !constructeurChaise.getActif();
		boolean chaiseSupprimee = !dicoMeubleUnderTest.containsKey(1);
		boolean armoireConservee = dicoMeubleUnderTest.containsKey(2);
		assertTrue(
				tresorerieBienMAJ && avancementArmoireOK && constructeurInactif && chaiseSupprimee && armoireConservee);
	}

	@Test
	public void testStockageLotAucunPersonnelDispo() {
		// CODEAFFICHAGE
		// test affichage chefs
		/*
		 * System.out.println("affichage chefs et leurs tabOuvrier");
		 * Collection<ChefEquipe> chefs = dicoChefUnderTest.values();
		 * Iterator<ChefEquipe> it = chefs.iterator(); while(it.hasNext()) { ChefEquipe
		 * ce = it.next(); System.out.println(ce.getID() + ":" + ce.getNom());
		 * ArrayList<Ouvrier> a = ce.getTabOuvrier(); for (Ouvrier o : a) {
		 * System.out.println(o.getNom()); } }
		 * 
		 * System.out.println("avant modif\n"); //test affichage personnel
		 * System.out.println("affichage personnel"); Collection<Personnel>
		 * membrespersonnels = dicoPersonnelUnderTest.values(); Iterator<Personnel> itp
		 * = membrespersonnels.iterator(); while(itp.hasNext()) { Personnel p =
		 * itp.next(); System.out.println(p.getID() + ":" + p.getNom() +
		 * ", peut stocker =" + p.getPeutStocker() + ", actif =" + p.getActif()); }
		 * System.out.println("----------------------------------------------------");
		 * //FINCODEAFFICHAGE
		 */

		Collection<Personnel> personnels = dicoPersonnelUnderTest.values();
		Iterator<Personnel> it = personnels.iterator();
		while (it.hasNext()) {
			Personnel p = it.next();
			if (p.getPeutStocker())
				p.setActif(true);
		}

		int answer = e.stockageLot(lot14);
		assertEquals(answer, -1);
	}

	@Test
	public void testStockageLotAucunEmplacementDispo() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				tabEntrepot[i][j] = 1;
			}
		}
		tabEntrepot[2][3] = 0;
		tabEntrepot[2][4] = 0;
		tabEntrepot[2][5] = 0;

		tabEntrepot[8][5] = 0;
		tabEntrepot[8][6] = 0;

		int answer = e.stockageLot(lot14);
		assertEquals(answer, -2);

	}

	@Test
	public void testStockageLotEmplacementDispo() {
		chefStock1.setActif(true);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				tabEntrepot[i][j] = 1;
			}
		}
		tabEntrepot[2][3] = 0;
		tabEntrepot[2][4] = 0;
		tabEntrepot[2][5] = 0;
		tabEntrepot[2][6] = 0;

		tabEntrepot[8][15] = 0;
		tabEntrepot[8][16] = 0;
		tabEntrepot[8][17] = 0;
		tabEntrepot[8][18] = 0;

		int personnelID = e.stockageLot(lot14);
		boolean personnelOK = (personnelID == 2) && chefStock2.getActif() == true;
		int absLot = lot14.getCoordonnees().getX();
		int ordLot = lot14.getCoordonnees().getY();
		boolean coordonneesBienRemplies = (absLot == 2) && (ordLot == 3);
		boolean tabEntrepotBienRempli = true;
		for (int j = 3; j < 7; j++) {
			if (tabEntrepot[2][j] != 14) {
				tabEntrepotBienRempli = false;
			}
		}
		assertTrue(personnelOK && coordonneesBienRemplies && tabEntrepotBienRempli);
	}

	@Test
	public void testLotEnStockLotsAModifier() {
		ArrayList<LotDePiecesDetachees> tabPiecesDetachees = new ArrayList<>();
		boolean lotEnStock = e.lotEnStock(lot14, tabPiecesDetachees);
		boolean tabBienRempli = tabPiecesDetachees.contains(lot1) && tabPiecesDetachees.contains(lot2)
				&& tabPiecesDetachees.size() == 2;
		boolean lot1BienMAJ = lot1.getASupprimerOuAModifier() == false;
		boolean lot2BienMAJ = lot2.getASupprimerOuAModifier() == true && lot2.getVolumeALaisserDansLaBoite() == 1;
		assertTrue(lotEnStock && tabBienRempli && lot1BienMAJ && lot2BienMAJ);
	}

	@Test
	public void testLotEnStockLotsCompteBon() {
		ArrayList<LotDePiecesDetachees> tabPiecesDetachees = new ArrayList<>();
		boolean lotEnStock = e.lotEnStock(lot15, tabPiecesDetachees);
		boolean tabBienRempli = tabPiecesDetachees.contains(lot12) && tabPiecesDetachees.contains(lot13)
				&& tabPiecesDetachees.size() == 2;
		boolean lot1BienMAJ = lot12.getASupprimerOuAModifier() == false;
		boolean lot2BienMAJ = lot13.getASupprimerOuAModifier() == false;
		assertTrue(lotEnStock && tabBienRempli && lot1BienMAJ && lot2BienMAJ);
	}

	@Test
	public void testLotMeilleurPrixVolume() {
		LotDePiecesDetachees lotTrouve = e.lotMeilleurPrixVolume(tabLot);
		LotDePiecesDetachees lotAttendu = lot3;
		assertEquals(lotTrouve, lotAttendu);
	}

	public void testLotEnStockMaxPrix() {
		ArrayList<LotDePiecesDetachees> tabPiecesDetachees = new ArrayList<>();
		boolean executionMethode = e.lotEnStockMaxPrix(lot14, tabPiecesDetachees);
		boolean tabBienRempli = tabPiecesDetachees.contains(lot2) && tabPiecesDetachees.contains(lot3)
				&& tabPiecesDetachees.size() == 2;
		boolean lot2BienMAJ = lot2.getASupprimerOuAModifier() == false;
		boolean lot3BienMAJ = lot3.getASupprimerOuAModifier() == false;
		assertTrue(executionMethode && tabBienRempli && lot2BienMAJ && lot3BienMAJ);
	}

	@Test
	public void testPiecesEnStock() {
		chefBrico4.setActif(true);
		chefBrico5.setActif(true);
		chefBrico6.setActif(true);

		Tuple<Integer, Integer> coord1 = new Tuple<>(1, 6);
		Tuple<Integer, Integer> coord2 = new Tuple<>(8, 0);
		Tuple<Integer, Integer> coord3 = new Tuple<>(8, 2);
		Tuple<Integer, Integer> coord5 = new Tuple<>(3, 17);
		Tuple<Integer, Integer> coord6 = new Tuple<>(5, 13);
		Tuple<Integer, Integer> coord11 = new Tuple<>(7, 6);
		Tuple<Integer, Integer> coord8 = new Tuple<>(9, 5);
		Tuple<Integer, Integer> coord9 = new Tuple<>(9, 19);

		lot1.setCoordonnees(coord1);
		lot2.setCoordonnees(coord2);
		lot3.setCoordonnees(coord3);
		lot5.setCoordonnees(coord5);
		lot6.setCoordonnees(coord6);
		lot11.setCoordonnees(coord11);
		lot8.setCoordonnees(coord8);
		lot9.setCoordonnees(coord9);

		ouvrier1.ajouterLot(lot1, tabEntrepot);
		ouvrier1.ajouterLot(lot2, tabEntrepot);
		ouvrier1.ajouterLot(lot3, tabEntrepot);
		ouvrier1.ajouterLot(lot5, tabEntrepot);
		ouvrier1.ajouterLot(lot6, tabEntrepot);
		ouvrier1.ajouterLot(lot11, tabEntrepot);
		ouvrier1.ajouterLot(lot8, tabEntrepot);
		ouvrier1.ajouterLot(lot9, tabEntrepot);

		System.out.println("avec les lots entiers :\n");
		for (int i = 0; i < 10; i++) {
			String row = "";
			for (int j = 0; j < 20; j++) {
				if (tabEntrepot[i][j] > 9) {
					row += tabEntrepot[i][j] + " ";
				} else
					row += tabEntrepot[i][j] + "  ";
			}
			System.out.println(row);
		}

		Meuble m = e.piecesEnStock(1, dicoLotsMeuble2, "Armoire", PiecesMaison.CHAMBRE, 5);
		boolean meubleOK = m != null;
		boolean ouvrierOK = ouvrier1.getActif();
		tabPiecesDetachees2 = m.getTabPiecesDetachees();
		boolean tabComplet = tabPiecesDetachees2
				.containsAll(Arrays.asList(lot1, lot2, lot3, lot5, lot6, lot11, lot8, lot9));

		for (LotDePiecesDetachees l : tabPiecesDetachees2) {
			System.out.println(l.getID() + " : " + l.getNom());
		}

		System.out.println("tab complet = " + tabComplet);
		boolean lotSupprOK = !lot1.getASupprimerOuAModifier() && !lot2.getASupprimerOuAModifier()
				&& !lot5.getASupprimerOuAModifier() && !lot8.getASupprimerOuAModifier()
				&& !lot9.getASupprimerOuAModifier();
		System.out.println("lotSupprOK = " + lotSupprOK);
		boolean lotModifOK = lot3.getASupprimerOuAModifier() && lot6.getASupprimerOuAModifier()
				&& lot11.getASupprimerOuAModifier();
		System.out.println("lotModifOK = " + lotModifOK);

		System.out.println("avec le lot modifié :\n");
		for (int i = 0; i < 10; i++) {
			String row = "";
			for (int j = 0; j < 20; j++) {
				if (tabEntrepot[i][j] > 9) {
					row += tabEntrepot[i][j] + " ";
				} else
					row += tabEntrepot[i][j] + "  ";
			}
			System.out.println(row);
		}

		assertTrue(meubleOK && ouvrierOK && tabComplet && lotSupprOK && lotModifOK);
	}

	@Test
	public void testRetirerLot() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				tabEntrepot[i][j] = 1;
			}
		}
		tabEntrepot[8][14] = 6;
		tabEntrepot[8][15] = 6;
		tabEntrepot[8][16] = 6;
		tabEntrepot[8][17] = 6;
		tabEntrepot[8][18] = 6;
		tabEntrepot[8][19] = 6;
		Tuple<Integer, Integer> newCoordonnees = new Tuple<>(8, 14);
		lot6.setCoordonnees(newCoordonnees);
		e.retirerLot(lot6);

		boolean tabEntrepotBienVide = true;
		for (int j = 14; j < 20; j++) {
			if (tabEntrepot[8][j] == 6) {
				tabEntrepotBienVide = false;
			}
		}
		boolean dicoLotSansLot6 = !dicoLotUnderTest.containsKey(6);
		assertTrue(tabEntrepotBienVide && dicoLotSansLot6);
	}

	@Test
	public void testModifierLot() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				tabEntrepot[i][j] = 1;
			}
		}
		tabEntrepot[8][14] = 6;
		tabEntrepot[8][15] = 6;
		tabEntrepot[8][16] = 6;
		tabEntrepot[8][17] = 6;
		tabEntrepot[8][18] = 6;
		tabEntrepot[8][19] = 6;
		Tuple<Integer, Integer> newCoordonnees = new Tuple<>(8, 14);
		lot6.setCoordonnees(newCoordonnees);
		lot6.setVolumeALaisserDansLaBoite(4);

		e.modifierLot(lot6);

		boolean tabEntrepotBienModifie = true;
		for (int j = 14; j < 18; j++) {
			if (tabEntrepot[8][j] == 0) {
				tabEntrepotBienModifie = false;
			}
		}
		for (int j = 18; j < 20; j++) {
			if (tabEntrepot[8][j] == 6) {
				tabEntrepotBienModifie = false;
			}
		}

		boolean volumeLotBienModifie = lot6.getVolume() == 4;
		assertTrue(tabEntrepotBienModifie && volumeLotBienModifie);
	}

}