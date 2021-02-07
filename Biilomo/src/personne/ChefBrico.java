package personne;

/**
 * un ChefBrico est un ChefEquipe (et donc Personnel) qui peut monter des
 * meubles de toutes les pièces mais qui ne gère pas les stocks
 * 
 * @author Lauréline
 *
 */

public class ChefBrico extends ChefEquipe {

	public ChefBrico(String nom, String prenom) {
		super(nom, prenom);
		super.peutStocker = false;
		super.peutMonterMeuble = true;
		super.secteur = Enum.valueOf(PiecesMaison.class, "MAISON");
		super.metier = Enum.valueOf(Metier.class, "CHEFBRICO");
	}

}