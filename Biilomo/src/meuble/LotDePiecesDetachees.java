package meuble;

import personne.Tuple;

/**
 * La classe LotDePiecesDetachees décrit un lot de pièces détachées, le lot ne
 * contenant que des pièces de même type
 * 
 * @author Julie
 */
public class LotDePiecesDetachees {
	private String nom;
	private double poids;
	private double prix; // prix par unité de volume
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
		this.aSupprimerOuAModifier = true; // valeur par défaut : à modifier
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
	 * permet d'obtenir l'emplacement de l'entrepôt où le lot est stocké
	 * 
	 * @return l'emplacement coordonnees de l'entrepôt où le lot est stocké
	 */
	public Tuple<Integer, Integer> getCoordonnees() {
		try {
			return coordonnees;
		} catch (NullPointerException e) {
			System.out.println("NullPointerException : coordonnées du lot non définies");
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
	 * modifier le statut du lot de pièces détachées
	 * 
	 * @param b un booléen pour indiquer si on doit supprimer le lot (dans le cas où
	 *          tout son contenu a été utilisé) ou au contraire modifier le lot
	 */
	public void setASupprimerOuAModifier(boolean b) {
		aSupprimerOuAModifier = b;
	}

	public int getVolumeALaisserDansLaBoite() {
		return volumeALaisserDansLaBoite;
	}

	/**
	 * Mettre à jour le volume d'un lot lorsqu'on l'a modifié en ne prenant qu'une
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