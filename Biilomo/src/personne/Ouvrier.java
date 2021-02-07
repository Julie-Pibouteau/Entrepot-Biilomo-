package personne;

/**
 * Les ouvriers peuvent gérer les stocks et monter un meuble correspondant à
 * leur spécialité
 * 
 * @author Lauréline
 *
 */

public class Ouvrier extends Personnel implements GestionStock {
	/**
	 * compte le nombre d'ouvriers
	 */
	public static int cptOuvriers;
	private int IDchef;

	/**
	 * constructeur d'Ouvrier
	 * 
	 * @param nom
	 * @param prenom
	 * @param domaine
	 * @param IDchef
	 */

	public Ouvrier(String nom, String prenom, PiecesMaison domaine, int IDchef) {
		super(nom, prenom);
		super.peutStocker = true;
		super.peutMonterMeuble = true;
		super.secteur = domaine;
		super.metier = Metier.OUVRIER;
		this.IDchef = IDchef;
		cptOuvriers++;

	}

	public int getIDchef() {
		return IDchef;
	}

	public void setIDchef(int nvID) {
		IDchef = nvID;
	}

	public static void setCptOuvrier() {
		cptOuvriers = 0;
	}

}
