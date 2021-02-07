package personne;

import java.util.ArrayList;

/**
 * Un Chef d'Equipe est un Personnel qui dirige une equipe
 * 
 * @author Lauréline
 *
 */
public abstract class ChefEquipe extends Personnel {

	protected ArrayList<Ouvrier> tabOuvriers = new ArrayList<Ouvrier>();
	/**
	 * compte le nombre de Chefs
	 */
	public static int nbChefs;

	/**
	 * constructeur de ChefEquipe (on ne connait pas son equipe quand on l'instancie
	 * 
	 * @param nom
	 * @param prenom
	 */
	public ChefEquipe(String nom, String prenom) {
		super(nom, prenom);
		nbChefs++;
	}

	public ArrayList<Ouvrier> getTabOuvrier() {
		return tabOuvriers;
	}

	public static void setNbChefs() {
		nbChefs = 0;
	}

}