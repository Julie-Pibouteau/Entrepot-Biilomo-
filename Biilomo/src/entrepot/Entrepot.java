package entrepot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
 * La classe Entrepot décrit un entrepot : son personnel, son contenu, sa
 * trésorerie
 * 
 * @author Julie
 */

public class Entrepot {

	private static final int NB_OUVRIERS_PAR_CHEF = 4;
	private static final int SALAIRE_OUVRIER = 5;
	private static final int SALAIRE_CHEF = 10;
	private double tresorerie;
	private Map<Integer, ChefEquipe> dicoChef = new HashMap<>();
	private Map<Integer, Personnel> dicoPersonnel = new HashMap<>();
	private Map<Integer, Meuble> dicoMeuble = new HashMap<>();
	private Map<Integer, LotDePiecesDetachees> dicoLot = new HashMap<>();

	public int[][] tabEntrepot;

	public Entrepot(double tresorerie, Map<Integer, ChefEquipe> dicoChef, Map<Integer, Personnel> dicoPersonnel,
			Map<Integer, Meuble> dicoMeuble, Map<Integer, LotDePiecesDetachees> dicoLot) {
		this.tresorerie = tresorerie;
		this.dicoChef = dicoChef;
		this.dicoPersonnel = dicoPersonnel;
		this.dicoMeuble = dicoMeuble;
		this.dicoLot = dicoLot;
	}

	public Map<Integer, ChefEquipe> getDicoChef() {
		return dicoChef;
	}

	public void afficheDicoChef() {
		if (dicoChef.isEmpty()) {
			System.out.println("Il n'y a aucun chef d'équipe.");
		} else {
			Collection<ChefEquipe> chefs = dicoChef.values();
			Iterator<ChefEquipe> it = chefs.iterator();
			while (it.hasNext()) {
				ChefEquipe c = it.next();
				System.out.println(c.getID() + ":" + c.getNom());
			}
		}

	}

	public Map<Integer, Personnel> getDicoPersonnel() {
		return dicoPersonnel;
	}

	public void afficheDicoPersonnel() {
		if (dicoPersonnel.isEmpty()) {
			System.out.println("Il n'y a aucun personnel.");
		} else {
			Collection<Personnel> personnels = dicoPersonnel.values();
			Iterator<Personnel> it = personnels.iterator();
			while (it.hasNext()) {
				Personnel p = it.next();
				System.out.println(p.getID() + ":" + p.getNom() + " " + p.getPrenom());
			}
		}
	}

	public Map<Integer, Meuble> getDicoMeuble() {
		return dicoMeuble;
	}

	public void afficheDicoMeuble() {
		if (dicoMeuble.isEmpty()) {
			System.out.println("Il n'y a aucun meuble.");
		} else {
			Collection<Meuble> meubles = dicoMeuble.values();
			Iterator<Meuble> it = meubles.iterator();
			while (it.hasNext()) {
				Meuble m = it.next();
				System.out.println(m.getID() + ":" + m.getNom());

			}
		}
	}

	public Map<Integer, LotDePiecesDetachees> getDicoLot() {
		return dicoLot;
	}

	public void afficheDicoLot() {
		if (dicoLot.isEmpty()) {
			System.out.println("Il n'y a aucun lot de pièces détachées.");
		} else {
			Collection<LotDePiecesDetachees> lots = dicoLot.values();
			Iterator<LotDePiecesDetachees> it = lots.iterator();
			while (it.hasNext()) {
				LotDePiecesDetachees l = it.next();
				System.out.println(l.getID() + ":" + l.getNom());
			}
		}

	}

	/**
	 * vérifie qu'un chef n'a pas déjà sous ses ordres le nombre maximum d'ouvriers,
	 * à savoir 4 ouvriers
	 * 
	 * @param c le chef dont on étudie l'équipe
	 * @return true si le chef peut encore accueillir un ou plusieurs ouvriers,
	 *         false sinon
	 */
	public boolean verifMaxOuvrier(ChefEquipe c) {
		return c.getTabOuvrier().size() < NB_OUVRIERS_PAR_CHEF;
	}

	/**
	 * Savoir s'il reste de la place dans l'équipe d'un chef
	 *
	 * @return le chef dont il reste de la place
	 */
	public ChefEquipe possibleRecruterOuvrier() {
		Collection<ChefEquipe> chefs = dicoChef.values();
		Iterator<ChefEquipe> it = chefs.iterator();
		ChefEquipe cLibre = null;
		while (it.hasNext() && cLibre == null) {
			ChefEquipe c = it.next();
			if (verifMaxOuvrier(c)) {
				// ce chef a encore la place d'accueillir un ouvrier au moins
				cLibre = c;
			}
		}
		return cLibre;
	}

	/**
	 * recruter un nouveau membre du personnel, qui peut être soit un ouvrier, soit
	 * un chefBrico, soit un chefStock
	 * 
	 * @param nom     le nom de la personne embauchée
	 * 
	 * @param prenom  le prénom de la personne embauchée
	 * 
	 * @param metier  le métier de la personne embauchée (ouvrier, chefBrico,
	 *                chefStock)
	 * 
	 * @param secteur la pièce de la maison dans laquelle la personne embauchée est
	 *                spécialisée
	 *
	 * @param cLibre  le chef qui va accueillir l'ouvrier, dans le cas où le
	 *                personnel recruté est un ouvrier
	 */

	public void recrutementPersonnel(String nom, String prenom, Metier metier, PiecesMaison secteur,
			ChefEquipe cLibre) {
		if (metier.equals(Metier.OUVRIER)) {
			// Vérifier avant l’appel de la méthode recrutement si on a la place
			// d’accueillir un nouvel ouvrier (ie un chef avec moins de NB_OUVRIERS_PAR_CHEF
			// ouvriers).
			/*
			 * Collection<ChefEquipe> chefs = dicoChef.values(); Iterator<ChefEquipe> it =
			 * chefs.iterator(); ChefEquipe cLibre = null; while (it.hasNext() && cLibre ==
			 * null) { ChefEquipe c = it.next(); if (verifMaxOuvrier(c)) { // ce chef a
			 * encore la place d'accueillir un ouvrier au moins cLibre = c; } }
			 */
			if (cLibre != null) {
				int IDchef = cLibre.getID();
				Ouvrier nouvelOuvrier = new Ouvrier(nom, prenom, secteur, IDchef);
				dicoPersonnel.put(nouvelOuvrier.getID(), nouvelOuvrier);
				if (verifMaxOuvrier(cLibre)) {
					cLibre.getTabOuvrier().add(nouvelOuvrier);
				}
			}

		}

		else if (metier.equals(Metier.CHEFSTOCK)) {
			ChefStock nouveauChefStock = new ChefStock(nom, prenom);
			dicoPersonnel.put(nouveauChefStock.getID(), nouveauChefStock);
			dicoChef.put(nouveauChefStock.getID(), nouveauChefStock);
		}

		else if (metier.equals(Metier.CHEFBRICO)) {
			ChefBrico nouveauChefBrico = new ChefBrico(nom, prenom);
			dicoPersonnel.put(nouveauChefBrico.getID(), nouveauChefBrico);
			dicoChef.put(nouveauChefBrico.getID(), nouveauChefBrico);
		}
	}

	/**
	 * Réaffecter les ouvriers d'un chef dans les autres équipes
	 * 
	 * @param chefDisposReaffectationOuvriers le tableau des chefs qui accueillent
	 *                                        les ouvriers
	 * @param nbOuvriersReaffectes            le nombre d'ouvriers de ce chef
	 * @param c                               le chef dont on cherche à répartir
	 *                                        l'équipe
	 */
	public void reaffectationOuvriers(ArrayList<ChefEquipe> chefDisposReaffectationOuvriers, int nbOuvriersReaffectes,
			ChefEquipe c) {
		try {
			int ouvriersEncoreNonReaffectes = nbOuvriersReaffectes;
			int indiceTableauChefReaffectation = 0;
			while (ouvriersEncoreNonReaffectes != 0) {
				ChefEquipe cDispo = chefDisposReaffectationOuvriers.get(indiceTableauChefReaffectation);
				ArrayList<Ouvrier> tabOuvrier = cDispo.getTabOuvrier();
				while (tabOuvrier.size() < NB_OUVRIERS_PAR_CHEF && ouvriersEncoreNonReaffectes > 0) {
					tabOuvrier.add(c.getTabOuvrier().get(nbOuvriersReaffectes - ouvriersEncoreNonReaffectes));
					// maj du nouvel ID du nouveau chef de l'ouvrier
					c.getTabOuvrier().get(nbOuvriersReaffectes - ouvriersEncoreNonReaffectes).setIDchef(cDispo.getID());
					ouvriersEncoreNonReaffectes--;
				}
				indiceTableauChefReaffectation++;
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException : Nombres erronné d'ouvriers réaffectés");
		}
	}

	/**
	 * Déterminer si on est capable de répartir les ouvriers d'un chef
	 * 
	 * @param c le chef dont on cherche à répartir l'équipe
	 * @return true si on parvient à répartir tous les ouvriers dans des équipes
	 *         existantes, false sinon
	 */
	public boolean repartitionOuvriersPossible(ChefEquipe c) {
		boolean repartitionOuvriersPossible = false;
		// trouver le nombre d'ouvriers de c
		int nbOuvriersReaffectes = c.getTabOuvrier().size();
		// trouver les chefs qui pourraient accueillir les ouvriers
		if (nbOuvriersReaffectes == 0) {
			repartitionOuvriersPossible = true;
		} else /* if (nbOuvriersReaffectes > 0) */ {
			int cptPlacesDispo = 0;
			Collection<ChefEquipe> chefs = dicoChef.values();
			Iterator<ChefEquipe> it = chefs.iterator();
			ArrayList<ChefEquipe> chefDisposReaffectationOuvriers = new ArrayList<>();
			while (it.hasNext() && cptPlacesDispo < nbOuvriersReaffectes) {
				ChefEquipe ce = it.next();
				if (ce.getID() != c.getID()) {
					int nbPlacesLibres = 4 - ce.getTabOuvrier().size();
					if (nbPlacesLibres > 0 && nbPlacesLibres <= NB_OUVRIERS_PAR_CHEF) {
						chefDisposReaffectationOuvriers.add(ce);
						cptPlacesDispo += nbPlacesLibres;
					}

				}
			}
			if (cptPlacesDispo >= nbOuvriersReaffectes) {
				repartitionOuvriersPossible = true;
			}
			// réaffecter les ouvriers
			if (!chefDisposReaffectationOuvriers.isEmpty()) {
				reaffectationOuvriers(chefDisposReaffectationOuvriers, nbOuvriersReaffectes, c);
			}
		}

		return repartitionOuvriersPossible;
	}

	/**
	 * Licencier un chef ou un ouvrier. Si on licencie un chef, il faut vérifier
	 * auparavant qu’on a la possibilité de placer ses ouvriers dans d’autres
	 * équipes
	 * 
	 * @param p le personnel qu'on souhaite licencier
	 */
	public void licenciementPersonnel(Personnel p) {
		/*
		 * Si on licencie un chef, il faut vérifier auparavant qu’on a la possibilité de
		 * placer ses ouvriers dans d’autres équipes. A ce moment là, répartir les
		 * ouvriers, et supprimer le chef de la liste de Personnel et de la liste des
		 * chefs
		 */
		boolean personnelPeutEtreSupprime = true;
		if (p.getMetier() == Metier.CHEFSTOCK || p.getMetier() == Metier.CHEFBRICO) {
			// si on a pu répartir les ouvriers seulement
			if (repartitionOuvriersPossible((ChefEquipe) (p))) {
				dicoChef.remove(p.getID());
				ChefEquipe.nbChefs--;
			} else {
				personnelPeutEtreSupprime = false;
				System.out.println(
						"Impossible de licencier ce chef, on ne peut pas réaffecter les ouvriers sous sa responsabilité.");
			}
		} else { // si c'est un ouvrier
			Ouvrier o = (Ouvrier) p;
			ArrayList<Ouvrier> tabOuvrier = dicoChef.get(o.getIDchef()).getTabOuvrier();
			tabOuvrier.remove(o);
			Ouvrier.cptOuvriers--;
		}
		// dans tous les cas on supprime la personne de la liste du Personnel
		if (personnelPeutEtreSupprime) {
			dicoPersonnel.remove(p.getID());
		}

	}

	/**
	 * Mettre à jour la trésorerie, soit pour payer les employés, soit pour recevoir
	 * le paiement pour la construction d'un meuble
	 * 
	 * @param montant montant nul si on paie les employés, positif si un meuble est
	 *                fini d'être construit
	 */
	public void majTresorerie(double montant) {
		if (montant == 0) { // on paie les employés
			// tresorerie -= (Ouvrier.cptOuvriers * 5 + ChefEquipe.nbChefs * 10);
			tresorerie -= (Ouvrier.cptOuvriers * SALAIRE_OUVRIER + ChefEquipe.nbChefs * SALAIRE_CHEF);
		} else { // l'entrepôt est payé car un meuble de prix = montant est terminé
			tresorerie += montant;
		}
	}

	/**
	 * Appliquer la mise à jour sur l'état d’avancement de chaque meuble du
	 * dictionnaire. Si on a fini la construction du meuble, l'entrepôt est payé
	 * Dans le cas où tous les lots nécessaires à la construction du meuble n'ont
	 * pas encore été amenés, on cherche un personnel inactif pour le faire
	 */
	public void majAvancementDicoMeuble(int strategie) {
		// appliquer la mise à jour sur l'état d’avancement de chaque meuble du
		// dictionnaire
		Collection<Meuble> meubles = dicoMeuble.values();
		Iterator<Meuble> it = meubles.iterator();
		ArrayList<Integer> toRemove = new ArrayList<>();
		while (it.hasNext()) {
			Meuble m = it.next();
			// tous les lots nécessaires à la construction ont été amenés
			if (m.getNbLotsARecuperer() == 0) {
				m.majEtatAvancement();

				if (m.getEtatAvancement() == m.getNbPasConstruction()) { // le meuble est terminé

					majTresorerie(m.getPrix()); // on paie l'entrepôt

					// on retrouve l'employé qui a construit ce meuble et on le repasse en inactif
					m.getconstructeurMeuble().setActif(false);

					toRemove.add(m.getID());
					// dicoMeuble.remove(m.getID()); // enfin on le retire du dictionnaire
					// mettre un message indiquant la fin de la construction ?

				}
			}

			else {
				// il reste personnel inactif capable de s'occuper du stock et du stock à recup
				ArrayList<Personnel> personnelARemettreInactif = new ArrayList<>();
				Personnel personnelDispo = Personnel.trouverPersonnelInactif(dicoPersonnel, true, PiecesMaison.RIEN);
				while ((personnelDispo != null) && (m.getNbLotsARecuperer() != 0)) {
					personnelDispo.setActif(true);
					if (strategie == 2) {// si stratégie 2
						personnelDispo.setNiveauActivite();
					}
					m.setNbLotsARecuperer();// diminue de 1
					personnelARemettreInactif.add(personnelDispo);
					personnelDispo = Personnel.trouverPersonnelInactif(dicoPersonnel, true, PiecesMaison.RIEN);
				}
				for (int i = 0; i < personnelARemettreInactif.size(); i++)
					personnelARemettreInactif.get(i).setActif(false);
			}

		}
		for (int toRemoveIndex = 0; toRemoveIndex < toRemove.size(); toRemoveIndex++)
			dicoMeuble.remove(toRemove.get(toRemoveIndex));
	}

	public double getTresorerie() {
		return tresorerie;
	}

	/**
	 * Stocker un nouveau lot dans l'entrepôt, à condition de trouver une personne
	 * apte à stocker le lot et un emplacement libre
	 * 
	 * @param lot le lot de pièces détachées
	 * @return l'identifiant de la personne qui va stocker le lot si les conditions
	 *         ont été remplies, -1 si on n'a pas trouvé de personne pour stocker le
	 *         lot, -2 si on n'a pas trouvé d'emplacement libre
	 */
	public int stockageLot(LotDePiecesDetachees lot) {
		/*
		 * . NB : instancier le lot dans le Main et le rajouter à la listeLot dans
		 * Entrepot
		 */

		// D’abord on vérifie si qqn est dispo pour stocker le lot.
		Personnel personnelDispoStockage = Personnel.trouverPersonnelInactif(dicoPersonnel, true, PiecesMaison.RIEN);
		if (personnelDispoStockage == null) { // personne ne peut stocker ce lot
			System.out.println("Aucune personne n'est disponible pour stocker ce lot.");
			return -1;
		}
		/*
		 * Puis on recherche un emplacement libre pour stocker le lot, et l'ajouter à
		 * l'entrepot. Si les deux tests ont réussi, on modifie le booléen actif du
		 * premier personnel inactif trouvé et on renvoie l’ID de ce personnel qui va
		 * stocker le lot
		 */
		if (personnelDispoStockage.getMetier() == Metier.OUVRIER) {
			Ouvrier o = (Ouvrier) personnelDispoStockage;
			Tuple<Integer, Integer> emplacementLibreStockage = o.rechercherEmplacementLibre(lot.getVolume(),
					tabEntrepot);
			if (emplacementLibreStockage == null) {
				System.out.println("Aucun emplacement n'est disponible pour stocker ce lot.");
				return -2;
			}
			lot.setCoordonnees(emplacementLibreStockage);
			o.ajouterLot(lot, tabEntrepot);
			o.setActif(true);
			return o.getID();
		}

		else { // personnelDispoStockage.getMetier() == Metier.CHEFSTOCK
			ChefStock c = (ChefStock) personnelDispoStockage;
			Tuple<Integer, Integer> emplacementLibreStockage = c.rechercherEmplacementLibre(lot.getVolume(),
					tabEntrepot);
			if (emplacementLibreStockage == null) {
				System.out.println("Aucun emplacement n'est disponible pour stocker ce lot.");
				return -2;
			}
			lot.setCoordonnees(emplacementLibreStockage);
			c.ajouterLot(lot, tabEntrepot);
			c.setActif(true);
			return c.getID();
		}
	}

	/**
	 * Utilisé dans la stratégie 1 Trouver les lots de l'entrepôt permettant
	 * d'obtenir le volume demandé du type de lot recherché
	 * 
	 * @param lot                le lot de pièces détachées recherché
	 * @param tabPiecesDetachees le tableau qui va contenir tous les lots dont on a
	 *                           besoin pour construire le futur meuble
	 * @return true si l'entrepôt a les ressources nécessaires pour ce type de lot,
	 *         false sinon
	 */
	public boolean lotEnStock(LotDePiecesDetachees lot, ArrayList<LotDePiecesDetachees> tabPiecesDetachees) {
		int volumeTrouve = 0;
		int volumeSouhaite = lot.getVolume();
		String nomLot = lot.getNom();
		Collection<LotDePiecesDetachees> lotsStock = dicoLot.values();
		Iterator<LotDePiecesDetachees> it = lotsStock.iterator();
		while (it.hasNext() && volumeTrouve < volumeSouhaite) {
			LotDePiecesDetachees l = it.next();
			if (l.getNom().equals(nomLot)) {
				tabPiecesDetachees.add(l);

				if (volumeTrouve + l.getVolume() > volumeSouhaite) {
					l.setASupprimerOuAModifier(true); // on voudra modifier ce lot car on n'a pas besoin de tout son
														// contenu
					l.setVolumeALaisserDansLaBoite(volumeTrouve + l.getVolume() - volumeSouhaite);
					volumeTrouve = volumeSouhaite;
					return true;

				} else {
					l.setASupprimerOuAModifier(false); // on voudra supprimer ce lot
					if (volumeTrouve + l.getVolume() == volumeSouhaite) {
						return true;
					}
					volumeTrouve += l.getVolume();
				}
			}
		}
		return false; // on n'a pas réussi à trouver tous les lots nécessaires
	}

	/**
	 * Trouve le lot de pièces détachées ayant le meilleur prix par unité de volume
	 * 
	 * @param tabLot les lots de la catégorie recherchée
	 * @return le lot de pièces détachées ayant le meilleur prix par unité de volume
	 */
	public LotDePiecesDetachees lotMeilleurPrixVolume(ArrayList<LotDePiecesDetachees> tabLot) {
		double meilleurPrixVolume = 0;
		LotDePiecesDetachees meilleurLot = null;
		for (int i = 0; i < tabLot.size(); i++) {
			LotDePiecesDetachees l = tabLot.get(i);
			double prixVolume = l.getPrix() / l.getVolume();
			if (prixVolume > meilleurPrixVolume) {
				meilleurPrixVolume = prixVolume;
				meilleurLot = l;
			}
		}
		return meilleurLot;
	}

	/**
	 * Utilisé dans la stratégie 2 Trouver les lots de l'entrepôt permettant
	 * d'obtenir le volume demandé du type de lot recherché, tout en maximisant le
	 * prix
	 * 
	 * @param lot                le lot de pièces détachées
	 * @param tabPiecesDetachees le tableau qui va contenir tous les lots dont on a
	 *                           besoin pour construire le futur meuble
	 * @return true si l'entrepôt a les ressources nécessaires pour ce type de lot,
	 *         false sinon
	 */
	public boolean lotEnStockMaxPrix(LotDePiecesDetachees lot, ArrayList<LotDePiecesDetachees> tabPiecesDetachees) {
		int volumeTrouve = 0;
		int volumeSouhaite = lot.getVolume();
		String nomLot = lot.getNom();
		ArrayList<LotDePiecesDetachees> tabLotCategorie = new ArrayList<>();
		Collection<LotDePiecesDetachees> lotsStock = dicoLot.values();
		Iterator<LotDePiecesDetachees> it = lotsStock.iterator();
		// stocker tous les lots de la bonne catégorie
		while (it.hasNext()) {
			LotDePiecesDetachees l = it.next();
			if (l.getNom().equals(nomLot)) {
				tabLotCategorie.add(l);
			}
		}

		if (tabLotCategorie.isEmpty()) { // lot n'existe pas dans notre entrepot
			return false;
		}

		// chercher les lots de prix maximal
		while (volumeTrouve < volumeSouhaite) {
			LotDePiecesDetachees lotMeilleurPrix = lotMeilleurPrixVolume(tabLotCategorie);
			tabPiecesDetachees.add(lotMeilleurPrix);
			tabLotCategorie.remove(lotMeilleurPrix);
			if (volumeTrouve + lotMeilleurPrix.getVolume() > volumeSouhaite) {
				lotMeilleurPrix.setASupprimerOuAModifier(true); // on voudra modifier ce lot car on n'a pas besoin de
																// tout son
				// contenu
				lotMeilleurPrix
						.setVolumeALaisserDansLaBoite(volumeTrouve + lotMeilleurPrix.getVolume() - volumeSouhaite);
				volumeTrouve = volumeSouhaite;
				return true;

			} else {
				lotMeilleurPrix.setASupprimerOuAModifier(false); // on voudra supprimer ce lot
				if (volumeTrouve + lotMeilleurPrix.getVolume() == volumeSouhaite) {
					return true;
				}
				volumeTrouve += lotMeilleurPrix.getVolume();
			}
		}
		return false; // on n'a pas réussi à trouver tous les lots nécessaires
	}

	/**
	 * Vérifier que l'entrepôt dispose des ressources et du personnel nécessaires à
	 * la construction d'un nouveau meuble
	 * 
	 * @param strategie              1 ou 2 selon la stratégie qu'on choisit
	 *
	 * @param dicoLotsMeuble         les types de lot et leurs volumes respectifs
	 *                               nécessaires à la construction du meuble
	 * @param nomMeuble              le nom du meuble à monter
	 * @param specialiteMonterMeuble la pièce de la maison à laquelle ce meuble
	 *                               appartient
	 * @param dureeConstruction      le nombre de pas de temps dont le meuble a
	 *                               besoin pour être construit
	 * @return le nouveau meuble si on a trouvé les ressources et le personnel
	 *         nécessaires, null sinon
	 */
	public Meuble piecesEnStock(int strategie, Map<Integer, LotDePiecesDetachees> dicoLotsMeuble, String nomMeuble,
			PiecesMaison specialiteMonterMeuble, int dureeConstruction) {
		// savoir si qqn de dispo pour monter le meuble
		Personnel personnelDispoMontage = Personnel.trouverPersonnelInactif(dicoPersonnel, false,
				specialiteMonterMeuble);
		if (personnelDispoMontage == null) { // personne ne peut monter ce meuble
			System.out.println("Aucune personne n'est disponible pour monter ce meuble.");
			return null;
		}

		// puis vérifier qu'on a les ressources et le personnel nécessaires
		if (dicoLot.isEmpty()) {
			return null;
		}

		ArrayList<LotDePiecesDetachees> tabPiecesDetachees = new ArrayList<LotDePiecesDetachees>();
		Collection<LotDePiecesDetachees> lotsMeubles = dicoLotsMeuble.values();
		if (strategie == 1) {
			for (LotDePiecesDetachees l : lotsMeubles) {
				if (!lotEnStock(l, tabPiecesDetachees)) {
					return null; // il manque des pièces
				}
			}
		}

		if (strategie == 2) {
			for (LotDePiecesDetachees l : lotsMeubles) {
				if (!lotEnStockMaxPrix(l, tabPiecesDetachees)) {
					return null; // il manque des pièces
				}
			}
		}
		// si on a toutes les pièces en stock, on va supprimer de l'entrepôt les lots
		// qu'on utilise complètement et modifier ceux qu'on utilise partiellement
		for (int i = 0; i < tabPiecesDetachees.size(); i++) {
			LotDePiecesDetachees l = tabPiecesDetachees.get(i);
			boolean aSupprimerOuAModifier = l.getASupprimerOuAModifier();
			if (aSupprimerOuAModifier) { // à modifier
				modifierLot(l);
			} else { // à supprimer
				retirerLot(l);
			}
		}

		// On modifie le booléen actif du premier personnel inactif trouvé
		personnelDispoMontage.setActif(true);

		// puis on instancie le nouveau meuble
		Meuble nouveauMeuble = new Meuble(nomMeuble, specialiteMonterMeuble, dureeConstruction, dicoLotsMeuble,
				tabPiecesDetachees);
		nouveauMeuble.setConstructeurMeuble(personnelDispoMontage);
		return nouveauMeuble;
	}

	/**
	 * Retirer le lot utilisé du dictionnaire des lots et du tableau entrepot
	 * 
	 * @param lot le lot à retirer
	 */
	public void retirerLot(LotDePiecesDetachees lot) {
		// retire le lot utilisé du tableau entrepot
		Tuple<Integer, Integer> coordonnees = lot.getCoordonnees();
		int x = coordonnees.getX(), y = coordonnees.getY();
		for (int i = 0; i < lot.getVolume(); i++) {
			tabEntrepot[x][y + i] = 0;
		}
		// retire le lot utilisé du dictionnaire des lots
		dicoLot.remove(lot.getID());
	}

	/**
	 * Modifier le volume et la place occupée dans l'entrepot d’un lot ouvert mais
	 * pas complètement consommé.
	 * 
	 * @param lot le lot de pièces détachées
	 */
	public void modifierLot(LotDePiecesDetachees lot) {
		Tuple<Integer, Integer> coordonnees = lot.getCoordonnees();
		int x = coordonnees.getX(), y = coordonnees.getY();
		for (int i = lot.getVolumeALaisserDansLaBoite(); i < lot.getVolume(); i++) {
			tabEntrepot[x][y + i] = 0;
		}
		// modifie la valeur du volume de ce lot
		lot.setVolume(lot.getVolumeALaisserDansLaBoite());
	}

}