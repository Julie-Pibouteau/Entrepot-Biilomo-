package meuble;

import personne.Tuple;

/**
 * La classe LotDePiecesDetachees d�crit un lot de pi�ces d�tach�es, le lot ne
 * contenant que des pi�ces de m�me type
 * 
 * @author Julie
 */
public class LotDePiecesDetachees {
	private String nom;
	private double poids;
	private double prix; // prix par unit� de volume
	private int volume;
	private int ID;
	private int IDPStock; // ID du personnel qui stocke le lot
	private Tuple<Integer, Integer> coordonnees;
	private boolean aSupprimerOuAModifier;
	private int volumeALaisserDansLaBoite;
	public static int nbLotsAjoutes = 0;

	public LotDePiecesDetachees(String nom, double poids, double prix, int volume) {
		this.ID = ++nbLotsAjoutes;
		this.nom = nom;
		this.poids = poids;
		this.prix = prix;
		this.volume = volume;
		this.volumeALaisserDansLaBoite = volume;
		this.aSupprimerOuAModifier = true; // valeur par d�faut : � modifier
	}

	// constructeur special pour la commande meuble
	public LotDePiecesDetachees(String nom, int volumeSouhaite) {
		this.nom = nom;
		this.volume = volumeSouhaite;
	}

	public static void setNbLotsAjoutes() {
		nbLotsAjoutes = 0;
	}

	public double getPrix() {
		return prix;
	}

	public int getID() {
		return ID;
	}

	public int getIDPStock() {
		return IDPStock;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int newVolume) {
		volume = newVolume;
	}

	public void setIDPStock(int IDP) {
		IDPStock = IDP;
	}

	/**
	 * permet d'obtenir l'emplacement de l'entrep�t o� le lot est stock�
	 * 
	 * @return l'emplacement coordonnees de l'entrep�t o� le lot est stock�
	 */
	public Tuple<Integer, Integer> getCoordonnees() {
		try {
			return coordonnees;
		} catch (NullPointerException e) {
			System.out.println("NullPointerException : coordonn�es du lot non d�finies");
			return null;
		}
	}

	public void setCoordonnees(Tuple<Integer, Integer> newCoordonnees) {
		coordonnees = newCoordonnees;
	}

	public boolean getASupprimerOuAModifier() {
		return aSupprimerOuAModifier;
	}

	/**
	 * modifier le statut du lot de pi�ces d�tach�es
	 * 
	 * @param b un bool�en pour indiquer si on doit supprimer le lot (dans le cas o�
	 *          tout son contenu a �t� utilis�) ou au contraire modifier le lot
	 */
	public void setASupprimerOuAModifier(boolean b) {
		aSupprimerOuAModifier = b;
	}

	public int getVolumeALaisserDansLaBoite() {
		return volumeALaisserDansLaBoite;
	}

	/**
	 * Mettre � jour le volume d'un lot lorsqu'on l'a modifi� en ne prenant qu'une
	 * partie de son contenu
	 * 
	 * @param newVolume le nouveau volume du lot
	 */
	public void setVolumeALaisserDansLaBoite(int newVolume) {
		volumeALaisserDansLaBoite = newVolume;
	}

	public String getNom() {
		return nom;
	}

}