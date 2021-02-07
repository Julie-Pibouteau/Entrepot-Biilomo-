package personne;

/**
 * une PiecesMaison correspond à la spécialité de l'ouvrier; MAISON signifie
 * qu'il peut s'occuper de toutes les pièces, ie chef brico; RIEN signifie qu'il
 * ne s'occupe d'aucune pièce (ne peut pas monter de meubles), ie chef stock.
 * 
 * @author Lauréline
 *
 */
public enum PiecesMaison {
	CUISINE, CHAMBRE, SALLEaMANGER, SALON, SALLEdeBAIN, WC, MAISON, RIEN
};