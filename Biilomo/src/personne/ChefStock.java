package personne;

/**
 * Un ChefStock est un ChefEquipe (et donc Personnel) qui peut g�rer les stocks
 * mais ne peut pas monter de meubles
 * 
 * @author Laur�line
 *
 */
public class ChefStock extends ChefEquipe implements GestionStock {

	public ChefStock(String nom, String prenom) {
		super(nom, prenom);
		super.peutMonterMeuble = false;
		super.peutStocker = true;
		super.secteur = Enum.valueOf(PiecesMaison.class, "RIEN");
		super.metier = Enum.valueOf(Metier.class, "CHEFSTOCK");
	}

}
