package meuble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import personne.Personnel;
import personne.PiecesMaison;

/**
 * La classe Meuble décrit un meuble, un meuble étant composé de plusieurs lots
 * de pièces détachées et pouvant être monté en plusieurs pas de temps
 * 
 * @author Julie
 */

public class Meuble {

	private int ID;
	private String nom;
	private Map<Integer, LotDePiecesDetachees> dicoLotsMeuble = new HashMap<>();
	private ArrayList<LotDePiecesDetachees> tabPiecesDetachees = new ArrayList<LotDePiecesDetachees>();
	private int nbLotsARecuperer;
	private PiecesMaison pieceMaison;
	private int nbPasConstruction; // nombre de pas de temps nécessaires à la construction
	private double prix;
	private int nbLots;
	public static int nbMeublesEnConstruction;
	private int etatAvancement = 0;
	private Personnel constructeurMeuble;

	public Meuble(String nom, PiecesMaison pieceMaison, int dureeConstruction,
			Map<Integer, LotDePiecesDetachees> dicoLotsMeuble, ArrayList<LotDePiecesDetachees> tabPiecesDetachees) {
		this.ID = ++nbMeublesEnConstruction;
		this.nom = nom;
		this.pieceMaison = pieceMaison;
		this.nbPasConstruction = dureeConstruction;
		this.dicoLotsMeuble = dicoLotsMeuble;
		this.tabPiecesDetachees = tabPiecesDetachees;
		this.nbLotsARecuperer = tabPiecesDetachees.size();
	}

	public static void setNbMeublesEnConstruction() {
		nbMeublesEnConstruction = 0;
	}

	public void majEtatAvancement() {
		etatAvancement++;
	}

	/**
	 * calcule le prix d'un meuble comme étant la somme des prix des lots de pièces
	 * détachées servant à le construire
	 */
	public void calculPrixMeuble() {
		prix = 0;
		for (LotDePiecesDetachees lot : tabPiecesDetachees) {
			prix += lot.getPrix() * lot.getVolume();
		}
	}

	public String getNom() {
		return this.nom;
	}

	public double getPrix() {
		calculPrixMeuble();
		return prix;
	}

	public int getID() {
		return ID;
	}

	public int getEtatAvancement() {
		return etatAvancement;
	}

	public Personnel getconstructeurMeuble() {
		try {
			return constructeurMeuble;
		} catch (NullPointerException e) {
			System.out.println("NullPointerException : le personnel construisant ce meuble n'a pas été défini");
			return null;
		}
	}

	public void setConstructeurMeuble(Personnel p) {
		constructeurMeuble = p;
	}

	public int getNbPasConstruction() {
		return nbPasConstruction;
	}

	/**
	 * connaître le nombre de lots restant à récupérer dans l'entrepôt pour
	 * construire le meuble
	 * 
	 * @return nbLotsARecuperer, le nombre de lots restant à récupérer dans
	 *         l'entrepôt pour construire le meuble
	 */
	public int getNbLotsARecuperer() {
		return nbLotsARecuperer;
	}

	public void setNbLotsARecuperer() { // on recup les lots 1 par 1 donc ne diminue que de 1
		--nbLotsARecuperer;
	}

	public void setNbLotsARecupererReinit() {
		nbLotsARecuperer = 0;
	}

	public ArrayList<LotDePiecesDetachees> getTabPiecesDetachees() {
		return tabPiecesDetachees;
	}
}
