package personne;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * La classe Personnel regroupe tous les employés : les chefs d'équipe et les
 * ouvriers
 *
 * @author Lauréline et Julie
 */

public abstract class Personnel {
	private String nom;
	private String prenom;
	protected int ID;
	protected boolean peutStocker;
	protected boolean peutMonterMeuble;
	protected Metier metier;
	protected PiecesMaison secteur;
	/**
	 * permet d'initialiser l'ID de chaque nouvelle recrue
	 */
	public static int cptHistorique;
	/**
	 * quand actif est true alors l'employé est actif, quand false alors il est
	 * inactif
	 */
	private boolean actif;
	private int niveauActivite = 0; // donne le nombre de fois où le personnel a été mis actif

	/**
	 * Constructeur de Personnel
	 * 
	 * @param nom
	 * @param prenom
	 */
	public Personnel(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.ID = ++cptHistorique;
		this.actif = false;
		// pour les booleens, dépend ouvrier ou quel chef d'équipe, init à false ?
		this.peutMonterMeuble = false;
		this.peutStocker = false;

	}

	public void setNiveauActivite() {
		niveauActivite++;
	}

	public Metier getMetier() {
		return this.metier;
	}

	public boolean getPeutStocker() {
		return peutStocker;
	}

	public boolean getPeutMonterMeuble() {
		return peutMonterMeuble;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public int getID() {
		return this.ID;
	}

	public boolean getActif() {
		return this.actif;
	}

	public void setActif(boolean b) {
		actif = b;
		niveauActivite++;
	}

	public static void setCptHistorique() {
		cptHistorique = 0;
	}

	public int getNiveauActivite() {
		return niveauActivite;
	}

	/**
	 * Dans cette méthode, on parcourt la liste du personnel et on s’arrête dès
	 * qu’on trouve une personne correspondant au booléen passé en argument et
	 * inactif. Si le booléen est true on veut stocker, si c’est false, on veut
	 * monter un meuble si on veut monter un meuble, on précise de quel type de
	 * meuble il s'agit grace a specialiteMonterMeuble
	 * 
	 * @param hmPersonnel         le dictionnaire du personnel
	 * @param stockerOuConstruire true = on veut stocker false = on veut monter un
	 *                            meuble
	 * @param specialiteMonterMeuble   spécialité du meuble qu'on souhaite construire
	 * @return le Personnel p si on a trouvé un Personnel inactif, null sinon
	 */
	public static Personnel trouverPersonnelInactif(Map<Integer, Personnel> hmPersonnel, boolean stockerOuConstruire,
			PiecesMaison specialiteMonterMeuble) {
		try {
			if (hmPersonnel.isEmpty()) {
				return null;
			}
			// on veut stocker
			if (stockerOuConstruire) {
				for (HashMap.Entry<Integer, Personnel> paire : hmPersonnel.entrySet()) {
					Personnel p = paire.getValue();

					if ((p.peutStocker) && (!p.actif)) {

						return p;
					}
				}
			}
			// on veut construire
			if (!stockerOuConstruire) {
				if ((specialiteMonterMeuble == null) || (specialiteMonterMeuble == PiecesMaison.RIEN)) {
					return null;
				}
				for (Map.Entry<Integer, Personnel> paire : hmPersonnel.entrySet()) {
					Personnel p = paire.getValue();
					if ((p.peutMonterMeuble) && (!p.actif)
							&& (p.secteur == specialiteMonterMeuble || p.secteur == PiecesMaison.MAISON)) {
						return p;
					}
				}
			}
			return null;
		} catch (NullPointerException e) {
			System.out.println("NullPointerException : dictionnaire du personnel vide");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Trouve le personnel qui a été le moins utile au vu de son niveau d'activité
	 * 
	 * @param hmPersonnel le dictionnaire de l'ensemble du personnel
	 * @return le personnel qui a été le moins utile
	 */
	public static Personnel trouverPersonnelPeuUtile(Map<Integer, Personnel> hmPersonnel) {
		if (hmPersonnel.isEmpty()) {
			return null;
		}
		Collection<Personnel> personnels = hmPersonnel.values();
		Iterator<Personnel> it = personnels.iterator();
		Personnel pPeuUtile = it.next();
		while (it.hasNext()) {
			Personnel p = it.next();
			if (p.getNiveauActivite() < pPeuUtile.getNiveauActivite()) {
				pPeuUtile = p;
			}
		}
		return pPeuUtile;
	}

}