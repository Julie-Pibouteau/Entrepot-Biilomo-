package personne;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * La classe Personnel regroupe tous les employ�s : les chefs d'�quipe et les
 * ouvriers
 *
 * @author Laur�line et Julie
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
	 * quand actif est true alors l'employ� est actif, quand false alors il est
	 * inactif
	 */
	private boolean actif;
	private int niveauActivite = 0; // donne le nombre de fois o� le personnel a �t� mis actif

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
		// pour les booleens, d�pend ouvrier ou quel chef d'�quipe, init � false ?
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
	 * Dans cette m�thode, on parcourt la liste du personnel et on s�arr�te d�s
	 * qu�on trouve une personne correspondant au bool�en pass� en argument et
	 * inactif. Si le bool�en est true on veut stocker, si c�est false, on veut
	 * monter un meuble si on veut monter un meuble, on pr�cise de quel type de
	 * meuble il s'agit grace a specialiteMonterMeuble
	 * 
	 * @param hmPersonnel         le dictionnaire du personnel
	 * @param stockerOuConstruire true = on veut stocker false = on veut monter un
	 *                            meuble
	 * @param specialiteMonterMeuble   sp�cialit� du meuble qu'on souhaite construire
	 * @return le Personnel p si on a trouv� un Personnel inactif, null sinon
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
	 * Trouve le personnel qui a �t� le moins utile au vu de son niveau d'activit�
	 * 
	 * @param hmPersonnel le dictionnaire de l'ensemble du personnel
	 * @return le personnel qui a �t� le moins utile
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