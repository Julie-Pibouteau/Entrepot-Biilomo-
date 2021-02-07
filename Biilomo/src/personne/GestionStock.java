package personne;

import java.util.ArrayList;
import java.util.HashMap;

import entrepot.Entrepot;
import meuble.LotDePiecesDetachees;

/**
 * L'interface GestionStock contient des m�thodes qui g�rent la gestion des
 * stocks de lots de l'entrep�t
 * 
 * @author Julie et Laur�line
 *
 */
public interface GestionStock {

	/**
	 * V�rifie si on peut d�placer le lot aux coordonn�es indiqu�es, et le d�place
	 * dans entrepot en cons�quence. On met aussi � jour les nouvelles coordonnees du
	 * lot
	 * 
	 * @param lot         le lot qu'on veut d�placer
	 * @param coord       les coordonn�es auxquelles on veut d�placer le lot
	 * @param tabEntrepot le tableau a 2 dimensions representant l'entrepot
	 * 
	 * @return true si le d�placement a �t� effectu�, false sinon
	 */
	// vraiment besoin de verif coord ? on a la fonction rechercherEmplacementLibre
	public default boolean deplacerLot(LotDePiecesDetachees lot, Tuple<Integer, Integer> coord, int[][] tabEntrepot) {
		try {
			int abs = coord.getX();
			int ord = coord.getY();
			int ancienne_ord = lot.getCoordonnees().getY();
			int taille = 0;

			// on sup de tabEntrepot les anciennes coordonn�es
			for (int i = 0; i < lot.getVolume(); i++) {
				tabEntrepot[lot.getCoordonnees().getX()][ancienne_ord] = 0;
				ancienne_ord++;
			}
			// nouvelles coordonnees
			lot.setCoordonnees(coord);

			while (taille < lot.getVolume()) {
				tabEntrepot[abs][ord] = lot.getID();
				ord++;
				taille++;
			}
			return true;

		} catch (NullPointerException e) {
			System.out.println("NullPointerException : lot non/mal initialis�");
			return false;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException : coordonn�es du lot incorrectes");
			return false;
		}
	}

	/**
	 * R�organiser les lots dans l'entrep�t pour obtenir un rangement plus compact
	 * 
	 * @param e                   l'entrep�t
	 * @param listeLotsARegrouper les lots qu'on doit r�organiser
	 */
	public default void regrouperLots(Entrepot e, ArrayList<LotDePiecesDetachees> listeLotsARegrouper) {
		/*
		 * //r�cup�rer la liste actuelle des lots et la trier par volume de lots
		 * d�croissant
		 * 
		 * DANS LE MAIN A CHAQUE PAS DE TEMPS ArrayList<LotDePiecesDetachees>
		 * listeLotsARegrouper = new ArrayList<>(dicoLot.values()); // les nouveaux lots
		 * ajout�s et ceux supprim�s de dicoLot sont donc directement boug�s dans/hors
		 * listeLotsARegrouper 
		 * Collections.sort(listeLotsARegrouper, Comparator.comparing(LotDePiecesDetachees::getVolume));
		 * Collections.reverse(listeLotsARegrouper);
		 */

		ArrayList<LotDePiecesDetachees> l = listeLotsARegrouper;
		ArrayList<Integer> idLotsANePasDeplacer = new ArrayList<>();
		// r�initialise intelligemment le tabEntrepot pour pouvoir y r�organiser les
		// lots
		for (int i = 0; i < e.tabEntrepot.length; i++) {
			boolean ligneComplete = true;
			for (int j = 0; j < e.tabEntrepot[0].length; j++) {
				if (e.tabEntrepot[i][j] == 0) {
					ligneComplete = false;
				}
			}
			// si la ligne n'est pas compl�te, on consid�re qu'elle n'est pas d�j� optimis�e
			// donc on la vide pour essayer de faire mieux.
			if (!ligneComplete) {
				for (int j = 0; j < e.tabEntrepot[0].length; j++) {
					e.tabEntrepot[i][j] = 0;
				}
			}
			// si la ligne est compl�te, on y laisse les ID de lots et on retient ces ID
			// pour ne pas les retraiter dans la r�organisation.
			else {
				for (int j = 0; j < e.tabEntrepot[0].length; j++) {
					int id = e.tabEntrepot[i][j];
					if (!idLotsANePasDeplacer.contains(id)) {
						idLotsANePasDeplacer.add(id);
					}
				}
			}
		}

		// ranger les lots du plus gros au plus petit en les regroupant au maximum

		int i = 0;
		Personnel p = Personnel.trouverPersonnelInactif(e.getDicoPersonnel(), true, PiecesMaison.RIEN);
		ArrayList<Personnel> personnelARemettreInactif = new ArrayList<>();
		while (i < l.size() && p != null) {

			LotDePiecesDetachees lot = l.get(i);
			int idLot = lot.getID();
			if (!idLotsANePasDeplacer.contains(idLot)) {
				Tuple<Integer, Integer> nouvellesCoord = rechercherDernierEmplacementLibre(lot.getVolume(),
						e.tabEntrepot);
				lot.setCoordonnees(nouvellesCoord);
				ajouterLot(lot, e.tabEntrepot);
				if (!nouvellesCoord.equals(lot.getCoordonnees())) {
					p.setActif(true);
					personnelARemettreInactif.add(p);
					p = Personnel.trouverPersonnelInactif(e.getDicoPersonnel(), true, PiecesMaison.RIEN);
				}

			}
			i++;
		}
		// remettre le personnel en inactif
		for (int index = 0; index < personnelARemettreInactif.size(); index++) {
			personnelARemettreInactif.get(index).setNiveauActivite();
			personnelARemettreInactif.get(index).setActif(false);
		}
	}

	/**
	 * Renvoyer le 1e emplacement libre trouv� dans l'entrepot pouvant contenir le
	 * lot
	 * 
	 * @param volumeLot   le volume du lot
	 * @param tabEntrepot le tableau repr�sentant l'entrep�t
	 * @return les coordonn�es de l'emplacement libre
	 */

	// public default Tuple<Integer,Integer> rechercherEmplacementLibre(int
	// volumeLot){
	public default Tuple<Integer, Integer> rechercherEmplacementLibre(int volumeLot, int[][] tabEntrepot) {
		if (tabEntrepot == null) {
			return null;
		}
		for (int i = 0; i < tabEntrepot.length; i++) { // boucle for sur les lignes
			int cpt = 0;
			for (int j = 0; j < tabEntrepot[0].length; j++) { // boucle for sur les colonnes
				if (tabEntrepot[i][j] != 0) { // on sup que si une case est vide alors elle est �gale � 0
					cpt = 0; // on r�initialise le compteur car la case est occup�e
				} else {
					cpt++;
					if (cpt == volumeLot) { // on a trouve suffisamment de place
						Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(i, j - cpt + 1);
						return coord;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Trouve le dernier emplacement libre possible pour stocker le lot
	 * 
	 * @param volumeLot
	 * @param tabEntrepot
	 * @return les coordonn�es de cet emplacement
	 */
	public default Tuple<Integer, Integer> rechercherDernierEmplacementLibre(int volumeLot, int[][] tabEntrepot) {
		if (tabEntrepot == null) {
			return null;
		}
		for (int i = tabEntrepot.length - 1; i > -1; i--) { // boucle for sur les lignes
			int cpt = 0;
			for (int j = tabEntrepot[0].length - 1; j > -1; j--) { // boucle for sur les colonnes
				if (tabEntrepot[i][j] != 0) { // on sup que si une case est vide alors elle est �gale � 0
					cpt = 0; // on r�initialise le compteur car la case est occup�e
				} else {
					cpt++;
					if (cpt == volumeLot) { // on a trouve suffisamment de place
						Tuple<Integer, Integer> coord = new Tuple<Integer, Integer>(i, j);
						return coord;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Affecter la valeur de l'ID du LotDePiecesDetachees dans les cases de
	 * l'entrepot occup�es par le lot
	 * 
	 * @param lot   le lot
	 * @param coord les coordonn�es du lot
	 */
	public default void ajouterLot(LotDePiecesDetachees lot, int[][] tabEntrepot) {
		try {
			int y = lot.getCoordonnees().getY();
			int x = lot.getCoordonnees().getX();
			// verif coord ok ? besoin ?

			for (int i = 0; i < lot.getVolume(); i++) {
				tabEntrepot[x][y] = lot.getID();
				y++;
			}

		} catch (NullPointerException e) {
			System.out.println("NullPointerException : lot non/mal initialis�");
			throw e;
		}

		catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException : coordonn�es du lot incorrectes");
			throw e;
		}

	}

	/**
	 * Chercher un personnel inactif pour retirer un lot du stock. Le rendre actif
	 * 
	 * @param hmPersonnel le dictionnaire du personnel de l'entrep�t
	 * @return true si on a trouv� un personnel inactif apte � g�rer le stock false
	 *         si personne n'est disponible
	 */

	public static boolean retirerLotStock(HashMap<Integer, Personnel> hmPersonnel) {
		Personnel p = Personnel.trouverPersonnelInactif(hmPersonnel, true, null);
		if (p == null) {
			System.out.println("Personne n'est disponible");
			return false;
		}

		p.setActif(true);
		return true;

	}

}