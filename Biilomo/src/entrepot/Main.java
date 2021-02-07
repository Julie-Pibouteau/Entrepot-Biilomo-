package entrepot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import meuble.LotDePiecesDetachees;
import meuble.Meuble;
import personne.ChefEquipe;
import personne.ChefStock;
import personne.Metier;
import personne.Ouvrier;
import personne.Personnel;
import personne.PiecesMaison;

/**
 * La classe Main effectue toute la simulation
 * 
 * @author Lauréline
 */

public class Main {
	private static Scanner scan = new Scanner(System.in);

	/**
	 * Méthode main
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		int m = 0; // nb de rangées
		int n = 0; // nb de colonnes par rangee
		double tresorerie = -1;
		int choixMode;
		int choixStrategie;
		Entrepot entrepot;
		Map<Integer, ChefEquipe> dicoChef = new HashMap<>();
		Map<Integer, Personnel> dicoPersonnel = new HashMap<>();
		Map<Integer, Meuble> dicoMeuble = new HashMap<>();
		Map<Integer, LotDePiecesDetachees> dicoLot = new HashMap<>();

		// initialisation de la simulation
		while (m < 1) {
			System.out.println("Veuillez entrer le nombre de rangées de l'entrepot (nombre entier)");
			m = scan.nextInt();
		}
		while (n < 1) {
			System.out.println("Veuillez entrer le nombre de colonnes de l'entrepot (nombre entier)");
			n = scan.nextInt();
		}
		while (tresorerie < 0) {
			System.out.println("Veuillez entrer le montant initial de la tresorerie");
			tresorerie = scan.nextDouble();
		}
		entrepot = new Entrepot(tresorerie, dicoChef, dicoPersonnel, dicoMeuble, dicoLot);
		entrepot.tabEntrepot = new int[m][n];

		// initialisation de l'entrepot :
		entrepot.recrutementPersonnel("Dumbledore", "Albus", Metier.CHEFSTOCK, PiecesMaison.RIEN, null);
		entrepot.recrutementPersonnel("Lupin", "Remus", Metier.CHEFBRICO, PiecesMaison.MAISON, null);
		entrepot.recrutementPersonnel("Abbot", "Hannah", Metier.OUVRIER, PiecesMaison.CHAMBRE, dicoChef.get(2));
		entrepot.recrutementPersonnel("Bell", "Katie", Metier.OUVRIER, PiecesMaison.CUISINE, dicoChef.get(1));
		entrepot.recrutementPersonnel("Brown", "Lavande", Metier.OUVRIER, PiecesMaison.WC, dicoChef.get(1));

		// tableau statique pour le recrutement
		String[] recrutement = new String[30];
		recrutement[0] = "OUVRIER Chang Cho SALON";
		recrutement[1] = "CHEFSTOCK McGonagall Minerva RIEN";
		recrutement[2] = "CHEFBRICO Rogue Severus MAISON";
		recrutement[3] = "CHEFSTOCK Black Sirius RIEN";
		recrutement[4] = "OUVRIER Black Elladora CHAMBRE";
		recrutement[5] = "CHEFSTOCK Black Regulus RIEN";
		recrutement[6] = "OUVRIER Chang Cho SALON";
		recrutement[7] = "CHEFSTOCK Burbage Charity RIEN";
		recrutement[8] = "CHEFBRICO Flitwick Filius MAISON";
		recrutement[9] = "CHEFBRICO Lockhart Gilderoy MAISON";
		recrutement[10] = "OUVRIER Granger Hermione SALON";
		recrutement[11] = "OUVRIER Londubat Neville SALLEdeBAIN";
		recrutement[12] = "OUVRIER Potter Harry CHAMBRE ";
		recrutement[13] = "OUVRIER Corner Michael SALLEaMANGER";
		recrutement[14] = "OUVRIER Deauclair Penelope SALLEdeBAIN";
		recrutement[15] = "CHEFBRICO Quirrell Professeur MAISON";
		recrutement[16] = "CHEFSTOCK Chourave Pomona RIEN";
		recrutement[17] = "CHEFBRICO Rosmerta Madame MAISON";
		recrutement[18] = "CHEFBRICO Serdaigle Rowena MAISON";
		recrutement[19] = "OUVRIER Lovegood Luna SALON";
		recrutement[20] = "OUVRIER Weasley Fred WC";
		recrutement[21] = "OUVRIER Thomas Dean CUISINE";
		recrutement[22] = "OUVRIER Goyle Gregory SALLEaMANGER";
		recrutement[23] = "CHEFSTOCK Hagrid Rubeus RIEN";
		recrutement[24] = "CHEFSTOCK Diggory Amos RIEN";
		recrutement[25] = "OUVRIER McLaggen Cormac SALLEdeBAIN";
		recrutement[26] = "OUVRIER Spinnet Alicia CUISINE";
		recrutement[27] = "CHEFSTOCK Karkaroff Igor RIEN";
		recrutement[28] = "OUVRIER Weasley Georges WC";
		recrutement[29] = "OUVRIER Bullstrode Milicent SALON";

		System.out.println("Quel mode de simulation voulez vous choisir ?");
		System.out.println("Tapez 0 pour le mode console");
		System.out.println("Tapez 1 pour le mode lecture de fichier");

		choixMode = scan.nextInt();

		// mode console
		if (choixMode == 0) {

			System.out.println("Quelle stratégie voulez vous choisir ?");
			System.out.println("Tapez 1 pour la 1e stratégie");
			System.out.println("Tapez 2 pour la 2e stratégie");

			choixStrategie = scan.nextInt();

			switch (choixStrategie) {
			case (1):
				consoleStrategie1(entrepot, recrutement);
				break;

			case (2):
				consoleStrategie2(entrepot, recrutement);
				break;

			default:
				System.out.println("Cette stratégie n'existe pas");
				break;
			}

		}

		// mode lecture de fichier
		else if (choixMode == 1) {

			System.out.println("Quelle stratégie voulez vous choisir ?");
			System.out.println("Tapez 1 pour la 1e stratégie");
			System.out.println("Tapez 2 pour la 2e stratégie");

			choixStrategie = scan.nextInt();

			switch (choixStrategie) {
			case (1):
				lectureFichierStrategie1(entrepot, recrutement);
				break;

			case (2):
				lectureFichierStrategie2(entrepot, recrutement);
				break;

			default:
				System.out.println("Cette stratégie n'existe pas");
				break;
			}

		}

		else {
			System.out.println("Cette valeur ne fait pas partie des possibilités.");
		}

		scan.close();

		// delete
		dicoChef.clear();
		dicoLot.clear();
		dicoMeuble.clear();
		dicoPersonnel.clear();

	}

	/**
	 * Afficher le menu des consignes
	 */
	public static void printMenu() {
		System.out.println("Tapez 0 pour recevoir un lot");
		System.out.println("Tapez 1 pour recevoir une commande");
		System.out.println("Tapez 2 pour ne rien faire");
		System.out.println("Tapez 3 pour quitter la simulation");
	}

	/**
	 * Simuler l'entrepôt en lisant les consignes indiquées par l'utilisateur sur la
	 * console en suivant la stratégie 1 simple
	 * 
	 * @param entrepot    l'entrepot
	 * @param recrutement le tableau contenant les employés que l'on peut recruter
	 */
	public static void consoleStrategie1(Entrepot entrepot, String[] recrutement) {
		boolean quitPasDeTemps = false;
		int choixConsigne;
		int personnelStockLotID;
		String nomLotAStocker;
		double poidsLotAStocker;
		double prixLotAStocker;
		int volumeLotAStocker;
		String line;
		LotDePiecesDetachees lotAStocker;
		Collection<Personnel> personnels;
		Iterator<Personnel> it;
		Personnel p;
		ChefEquipe c;
		int pasDeTemps = 1;

		do { // enchainement des pas de temps
			System.out.println("---------------------------------------- Pas de temps " + pasDeTemps);

			// Recrutement
			if (entrepot.getTresorerie() > 0) {
				// si tous les Personnels sont actifs on recrute
				boolean recruter = true;
				personnels = entrepot.getDicoPersonnel().values();
				it = personnels.iterator();
				while ((it.hasNext()) && (recruter)) {
					p = it.next();
					if (!p.getActif()) { // si un employé est inactif, on ne recrute pas
						recruter = false;
					}
				}
				if (recruter) { // tous les employés sont actifs, on va recruter qqu
					c = entrepot.possibleRecruterOuvrier();
					if (c != null) { // on recrute un ouvrier si possible

						// on utilise le tableau avec les employés potentiels
						int i = 0;
						boolean recrutementFait = false;
						while ((i < recrutement.length) && (!recrutementFait)) {
							line = recrutement[i];
							String[] infoRecrutement = line.split(" ");
							if (infoRecrutement[0].equals("OUVRIER")) { // on recrute un ouvrier
								entrepot.recrutementPersonnel(infoRecrutement[1], infoRecrutement[2],
										Enum.valueOf(Metier.class, infoRecrutement[0]),
										Enum.valueOf(PiecesMaison.class, infoRecrutement[3]), c);
								recrutement[i] = "rien";
								recrutementFait = true;
							}
							i++;
						}
					}

					else { // on recrute un chefbrico ou un chefstock

						// on utilise le tableau avec les employés potentiels
						int i = 0;
						boolean recrutementFait = false;
						while ((i < recrutement.length) && (!recrutementFait)) {
							line = recrutement[i];
							String[] infoRecrutement = line.split(" ");
							if ((infoRecrutement[0].equals("CHEFSTOCK")) || (infoRecrutement[0].equals("CHEFSTOCK"))) { // on
																														// recrute
																														// un
																														// chef
								entrepot.recrutementPersonnel(infoRecrutement[1], infoRecrutement[2],
										Enum.valueOf(Metier.class, infoRecrutement[0]),
										Enum.valueOf(PiecesMaison.class, infoRecrutement[3]), c);
								recrutement[i] = "rien";
								recrutementFait = true;
							}
							i++;
						}

					}

				}
			}
			// Licenciement
			// verif avant que le dicoPersonnel n'est pas vide
			if (!entrepot.getDicoPersonnel().isEmpty()) {

				// si la trésorerie est négative, on licencie le 1e personnel inactif trouvé
				if (entrepot.getTresorerie() < 0) {
					boolean licencie = false;
					personnels = entrepot.getDicoPersonnel().values();
					it = personnels.iterator();

					// on cherche le 1e Personnel inactif
					while ((it.hasNext()) && (!licencie)) {
						p = it.next();
						if (!p.getActif()) {
							entrepot.licenciementPersonnel(p);
							licencie = true;
						}
					}

				}

			}
			// choix entre reception lot/ reception commande/ rien/ arret simulation
			printMenu();
			choixConsigne = scan.nextInt();

			switch (choixConsigne) {

			case (0): // reception d'un lot

				System.out.println("Veuillez entrer le nom du lot a stocker");
				nomLotAStocker = scan.next();
				System.out.println("Veuillez entrer le poids du lot a stocker");
				poidsLotAStocker = scan.nextDouble();
				System.out.println("Veuillez entrer le prix du lot a stocker");
				prixLotAStocker = scan.nextDouble();
				System.out.println("Veuillez entrer le volume du lot a stocker");
				volumeLotAStocker = scan.nextInt();
				lotAStocker = new LotDePiecesDetachees(nomLotAStocker, poidsLotAStocker, prixLotAStocker,
						volumeLotAStocker);

				personnelStockLotID = entrepot.stockageLot(lotAStocker);
				if ((personnelStockLotID != -1) && (personnelStockLotID != -2)) {
					lotAStocker.setIDPStock(personnelStockLotID);
					// on ajoute lot au dico des lots présents dans l'entrepot
					entrepot.getDicoLot().put(lotAStocker.getID(), lotAStocker);
					// rendre personnel inactif
					entrepot.getDicoPersonnel().get(personnelStockLotID).setActif(false);
				} else {
					System.out.println("Stockage impossible");
				}

				break;

			case (1): // reception commande

				System.out.println("Veuillez entrer le nom du meuble a construire");
				String nomMeuble = scan.next();
				System.out.println("Veuillez entrer la specialite du meuble a construire (CHAMBRE, WC etc.)");
				PiecesMaison specialiteMeuble = Enum.valueOf(PiecesMaison.class, scan.next());
				System.out.println("Veuillez entrer la duree de construction du meuble a construire");
				int dureeConstructionMeuble = scan.nextInt();
				scan.nextLine();
				System.out.println(
						"Veuillez indiquer les types de lot et leur quatité nécessaire  a la construction du meuble");
				System.out.println("sous le format :<nom1> <volume1> <nom2> <volume2> etc.");
				line = scan.nextLine();
				String[] tabLotsMeuble = line.split(" "); // on sup que les noms de LotDePiecesDetachees en 1
															// mot ou alors pas d'espaces entre les mots
															// (trouver convention)
				Map<Integer, LotDePiecesDetachees> dicoLotsMeuble = new HashMap<>();
				ArrayList<LotDePiecesDetachees> listLotsMeuble = new ArrayList<>(); // pour pouvoir instancier
																					// tous les
																					// LotDePiecesDetachees
				int j = 0;
				for (int i = 0; i < tabLotsMeuble.length; i += 2) {
					// instanciation spéciale sinon c'est la galere car il faut poids, prix et crée
					// nvx ID ect. alors que pas dans la liste des lots de l'entrepot
					listLotsMeuble
							.add(new LotDePiecesDetachees(tabLotsMeuble[i], Integer.valueOf(tabLotsMeuble[i + 1])));
					dicoLotsMeuble.put(listLotsMeuble.get(j).getVolume(), listLotsMeuble.get(j));
					j++;
				}

				Meuble meuble = entrepot.piecesEnStock(1, dicoLotsMeuble, nomMeuble, specialiteMeuble,
						dureeConstructionMeuble);
				if (meuble == null) {
					System.out.println("impossible de construire le meuble");
				} else {
					entrepot.getDicoMeuble().put(meuble.getID(), meuble); // on ajoute le meuble au dico des meubles en
					// construction
				}
				// pb avec le fait que ce soit la meme var meuble pr tout le monde ?
				break;

			case (2): // rien
				break;

			case (3): // arret simulation
				quitPasDeTemps = true;
				break;

			default:
				System.out.println("Cette valeur ne fait pas partie de celle proposée");
			}

			if (!quitPasDeTemps) {// on ne le fait pas si l'utilisateur veut arrêter la simulation

				// maj de la liste des meubles en cours de construction
				entrepot.majAvancementDicoMeuble(1);

				// payement des employés
				entrepot.majTresorerie(0);
			}

			// print qqc à la fin de chaque pas de temps ?
			System.out.println(" --- DicoChef ");
			entrepot.afficheDicoChef();
			System.out.println(" ------------- DicoPersonnel ");
			entrepot.afficheDicoPersonnel();
			System.out.println(" ***** DicoMeuble ");
			entrepot.afficheDicoMeuble();
			System.out.println("///////// DicoLot ");
			entrepot.afficheDicoLot();
			System.out.println("treso :" + entrepot.getTresorerie());

			pasDeTemps++;
		} while (!quitPasDeTemps);
	}

	/**
	 * Simuler l'entrepôt en lisant les consignes indiquées par l'utilisateur sur la
	 * console en suivant la stratégie 2 d'optimisation
	 * 
	 * @param entrepot    l'entrepot
	 * @param recrutement le tableau contenant les employés que l'on peut recruter
	 */

	public static void consoleStrategie2(Entrepot entrepot, String[] recrutement) {
		int pasDeTemps = 1;
		int choixConsigne;
		int personnelStockLotID;
		String nomLotAStocker;
		double poidsLotAStocker;
		double prixLotAStocker;
		int volumeLotAStocker;
		String line;
		LotDePiecesDetachees lotAStocker;
		Personnel p;
		Personnel personnelLeMoinsUtile;
		boolean quitPasDeTemps = false;
		personnelLeMoinsUtile = Personnel.trouverPersonnelPeuUtile(entrepot.getDicoPersonnel());

		do { // enchainement des pas de temps
			System.out.println("---------------------------------------- Pas de temps " + pasDeTemps);

			// Licenciement
			licenciementStrategie2(entrepot, pasDeTemps, personnelLeMoinsUtile);
			personnelLeMoinsUtile = Personnel.trouverPersonnelPeuUtile(entrepot.getDicoPersonnel());

			// Recrutement
			recrutementStrategie2(entrepot, recrutement);

			// choix entre reception lot/ reception commande/ rien/ arret simulation
			printMenu();
			choixConsigne = scan.nextInt();

			switch (choixConsigne) {

			case (0): // reception d'un lot

				System.out.println("Veuillez entrer le nom du lot a stocker");
				nomLotAStocker = scan.next();
				System.out.println("Veuillez entrer le poids du lot a stocker");
				poidsLotAStocker = scan.nextDouble();
				System.out.println("Veuillez entrer le prix du lot a stocker");
				prixLotAStocker = scan.nextDouble();
				System.out.println("Veuillez entrer le volume du lot a stocker");
				volumeLotAStocker = scan.nextInt();
				lotAStocker = new LotDePiecesDetachees(nomLotAStocker, poidsLotAStocker, prixLotAStocker,
						volumeLotAStocker);

				personnelStockLotID = entrepot.stockageLot(lotAStocker);
				if ((personnelStockLotID != -1) && (personnelStockLotID != -2)) {
					lotAStocker.setIDPStock(personnelStockLotID);
					// on ajoute lot au dico des lots présents dans l'entrepot
					entrepot.getDicoLot().put(lotAStocker.getID(), lotAStocker);
					// rendre personnel inactif
					entrepot.getDicoPersonnel().get(personnelStockLotID).setActif(false);
				} else {
					System.out.println("Stockage impossible");
				}

				break;

			case (1): // reception commande

				System.out.println("Veuillez entrer le nom du meuble a construire");
				String nomMeuble = scan.next();
				System.out.println("Veuillez entrer la specialite du meuble a construire (CHAMBRE, WC etc.)");
				PiecesMaison specialiteMeuble = Enum.valueOf(PiecesMaison.class, scan.next());
				System.out.println("Veuillez entrer la duree de construction du meuble a construire");
				int dureeConstructionMeuble = scan.nextInt();
				scan.nextLine();
				System.out.println(
						"Veuillez indiquer les types de lot et leur quatité nécessaire  a la construction du meuble");
				System.out.println("sous le format :<nom1> <volume1> <nom2> <volume2> etc.");
				line = scan.nextLine();
				String[] tabLotsMeuble = line.split(" "); // on sup que les noms de LotDePiecesDetachees en 1
															// mot ou alors pas d'espaces entre les mots
															// (trouver convention)
				Map<Integer, LotDePiecesDetachees> dicoLotsMeuble = new HashMap<>();
				ArrayList<LotDePiecesDetachees> listLotsMeuble = new ArrayList<>(); // pour pouvoir instancier
																					// tous les
																					// LotDePiecesDetachees
				int j = 0;
				for (int i = 0; i < tabLotsMeuble.length; i += 2) {
					// instanciation spéciale sinon c'est la galere car il faut poids, prix et crée
					// nvx ID ect. alors que pas dans la liste des lots de l'entrepot
					listLotsMeuble
							.add(new LotDePiecesDetachees(tabLotsMeuble[i], Integer.valueOf(tabLotsMeuble[i + 1])));
					dicoLotsMeuble.put(listLotsMeuble.get(j).getVolume(), listLotsMeuble.get(j));
					j++;
				}

				Meuble meuble = entrepot.piecesEnStock(2, dicoLotsMeuble, nomMeuble, specialiteMeuble,
						dureeConstructionMeuble);
				if (meuble == null) {
					System.out.println("impossible de construire le meuble");
				} else {
					entrepot.getDicoMeuble().put(meuble.getID(), meuble); // on ajoute le meuble au dico des meubles en
					// construction
				}
				// pb avec le fait que ce soit la meme var meuble pr tout le monde ?
				break;

			case (2): // rien
				break;

			case (3): // arret simulation
				quitPasDeTemps = true;
				break;

			default:
				System.out.println("Cette valeur ne fait pas partie de celle proposée");
			}

			if (!quitPasDeTemps) { // on ne le fait pas si l'utilisateur veut arrêter la simulation

				// deplacer lot
				ArrayList<LotDePiecesDetachees> listeLotsARegrouper = new ArrayList<>(entrepot.getDicoLot().values());
				// les nouveaux lots ajoutés et ceux supprimés de dicoLot sont donc directement
				// bougés dans/hors listeLotsARegrouper
				Collections.sort(listeLotsARegrouper, Comparator.comparing(LotDePiecesDetachees::getVolume));
				Collections.reverse(listeLotsARegrouper);
				p = Personnel.trouverPersonnelInactif(entrepot.getDicoPersonnel(), true, PiecesMaison.RIEN);

				if (p.getMetier() == Metier.OUVRIER) {
					Ouvrier o = (Ouvrier) p;
					o.regrouperLots(entrepot, listeLotsARegrouper);
				}
				if (p.getMetier() == Metier.CHEFSTOCK) {
					ChefStock chefStock = (ChefStock) p;
					chefStock.regrouperLots(entrepot, listeLotsARegrouper);
				}

				// maj de la liste des meubles en cours de construction
				entrepot.majAvancementDicoMeuble(2);

				// payement des employés
				entrepot.majTresorerie(0);

			}

			// print qqc à la fin de chaque pas de temps ?
			System.out.println(" --- DicoChef ");
			entrepot.afficheDicoChef();
			System.out.println(" ------------- DicoPersonnel ");
			entrepot.afficheDicoPersonnel();
			System.out.println(" ***** DicoMeuble ");
			entrepot.afficheDicoMeuble();
			System.out.println("///////// DicoLot ");
			entrepot.afficheDicoLot();
			System.out.println("treso :" + entrepot.getTresorerie());

			pasDeTemps++;
		} while (!quitPasDeTemps);

	}

	/**
	 * Simuler l'entrepôt en lisant les consignes indiquées dans un fichier texte en
	 * suivant la stratégie 1 simple
	 * 
	 * @param entrepot    l'entrepot
	 * @param recrutement le tableau contenant les employés que l'on peut recruter
	 */
	public static void lectureFichierStrategie1(Entrepot entrepot, String[] recrutement) {
		String nomFichier;
		String line;
		String[] info;
		int personnelStockLotID;
		int pasDeTemps;
		LotDePiecesDetachees lotAStocker;
		Personnel p;
		ChefEquipe c;
		Collection<Personnel> personnels;
		Iterator<Personnel> it;

		try {
			System.out.println("Quel fichier voulez-vous lire ?");
			scan.nextLine();//
			nomFichier = scan.nextLine();
			BufferedReader reader = new BufferedReader(new FileReader(new File(nomFichier)));
			line = reader.readLine();
			pasDeTemps = 1;
			while (line != null) {
				System.out.println("----------------------------- Pas de temps " + pasDeTemps);
				// lecture identifiant de la consigne
				info = line.split(" ");

				// Recrutement
				if (entrepot.getTresorerie() > 0) {
					// si tous les Personnels sont actifs on recrute
					boolean recruter = true;
					personnels = entrepot.getDicoPersonnel().values();
					it = personnels.iterator();
					while ((it.hasNext()) && (recruter)) {
						p = it.next();
						if (!p.getActif()) { // si un employé est inactif, on ne recrute pas
							recruter = false;
						}
					}
					if (recruter) { // tous les employés sont actifs, on va recruter qqu
						c = entrepot.possibleRecruterOuvrier();
						if (c != null) { // on recrute un ouvrier si possible

							// on utilise le tableau avec les employés potentiels
							int i = 0;
							boolean recrutementFait = false;
							while ((i < recrutement.length) && (!recrutementFait)) {
								line = recrutement[i];
								String[] infoRecrutement = line.split(" ");
								if (infoRecrutement[0].equals("OUVRIER")) { // on recrute un ouvrier
									entrepot.recrutementPersonnel(infoRecrutement[1], infoRecrutement[2],
											Enum.valueOf(Metier.class, infoRecrutement[0]),
											Enum.valueOf(PiecesMaison.class, infoRecrutement[3]), c);
									recrutement[i] = "rien";
									recrutementFait = true;
								}
								i++;
							}
						}

						else { // on recrute un chefbrico ou un chefstock

							// on utilise le tableau avec les employés potentiels
							int i = 0;
							boolean recrutementFait = false;
							while ((i < recrutement.length) && (!recrutementFait)) {
								line = recrutement[i];
								String[] infoRecrutement = line.split(" ");
								if ((infoRecrutement[0].equals("CHEFSTOCK"))
										|| (infoRecrutement[0].equals("CHEFSTOCK"))) { // on recrute un chef
									entrepot.recrutementPersonnel(infoRecrutement[1], infoRecrutement[2],
											Enum.valueOf(Metier.class, infoRecrutement[0]),
											Enum.valueOf(PiecesMaison.class, infoRecrutement[3]), c);
									recrutement[i] = "rien";
									recrutementFait = true;
								}
								i++;
							}

						}

					}
				}

				// Licenciement
				// verif avant que le dicoPersonnel n'est pas vide
				if (!entrepot.getDicoPersonnel().isEmpty()) {

					// si la trésorerie est négative, on licencie le 1e personnel inactif trouvé
					if (entrepot.getTresorerie() < 0) {
						boolean licencie = false;

						personnels = entrepot.getDicoPersonnel().values();
						it = personnels.iterator();

						// on cherche le 1e Personnel inactif
						while ((it.hasNext()) && (!licencie)) {
							p = it.next();
							if (p.getActif() == false) {
								entrepot.licenciementPersonnel(p);
								licencie = true;
							}
						}

					}

				}

				// execution reception lot/ reception commande/ rien
				switch (info[1]) {
				// reception lot
				case "lot":
					try {
						lotAStocker = new LotDePiecesDetachees(info[2], Double.valueOf(info[3]),
								Double.valueOf(info[4]), Integer.valueOf(info[5]));

						personnelStockLotID = entrepot.stockageLot(lotAStocker);
						if ((personnelStockLotID != -1) && (personnelStockLotID != -2)) {
							lotAStocker.setIDPStock(personnelStockLotID);
							// on ajoute lot au dico des lots présents dans l'entrepot
							entrepot.getDicoLot().put(lotAStocker.getID(), lotAStocker);
							// rendre personnel inactif
							entrepot.getDicoPersonnel().get(personnelStockLotID).setActif(false);
						}

						else {
							System.out.println("Stockage impossible");
						}
					} catch (IndexOutOfBoundsException e) {
						System.out.println("IndexOutOfBoundsException : problème pour le stock du lot à la ligne "
								+ (pasDeTemps + 1));
						// car pasDeTemps commence à 0 alors que les lignes à 1

					}

					break;

				// reception d'une commande
				case "meuble":
					try {
						Map<Integer, LotDePiecesDetachees> dicoLotsMeuble = new HashMap<>();
						ArrayList<LotDePiecesDetachees> listLotsMeuble = new ArrayList<>(); // pour pouvoir instancier
																							// tous les
																							// LotDePiecesDetachees
						int j = 0;
						for (int i = 5; i < info.length; i += 2) {
							// instanciation spéciale sinon c'est la galere car il faut poids, prix et crée
							// nvx ID ect. alors que pas dans la liste des lots de l'entrepot
							listLotsMeuble.add(new LotDePiecesDetachees(info[i], Integer.valueOf(info[i + 1])));
							dicoLotsMeuble.put(listLotsMeuble.get(j).getVolume(), listLotsMeuble.get(j));
							j++;
						}

						Meuble meuble = entrepot.piecesEnStock(1, dicoLotsMeuble, info[2],
								Enum.valueOf(PiecesMaison.class, info[3]), Integer.valueOf(info[4]));
						if (meuble == null) {
							System.out.println("impossible de construire le meuble");
						} else {
							entrepot.getDicoMeuble().put(meuble.getID(), meuble); // on ajoute le meuble au dico des
																					// meubles
																					// en construction

						}
					} catch (IndexOutOfBoundsException e) {
						System.out.println(
								"IndexOutOfBoundsException : problème pour la commande à la ligne " + (pasDeTemps + 1));
					}
					break;

				// rien
				case "rien":
					System.out.println("on ne fait rien");
					break;

				default:
					System.out.println("Problème identifiant : cette consigne n'existe pas");
				}

				// maj de la liste des meubles en cours de construction
				entrepot.majAvancementDicoMeuble(1);

				// payement des employés
				entrepot.majTresorerie(0);

				// print dico à chaque pas de temps ?
				System.out.println(" --- DicoChef ");
				entrepot.afficheDicoChef();
				System.out.println(" ------------- DicoPersonnel ");
				entrepot.afficheDicoPersonnel();
				System.out.println(" ***** DicoMeuble ");
				entrepot.afficheDicoMeuble();
				System.out.println("///////// DicoLot ");
				entrepot.afficheDicoLot();
				System.out.println("treso :" + entrepot.getTresorerie());

				pasDeTemps++;

				// on lit la ligne suivante
				line = reader.readLine();
			}
			reader.close();
		}

		catch (Exception e) {
			System.out.println("ce fichier n'existe pas");
			e.printStackTrace();
		}
	}

	/**
	 * Simuler l'entrepôt en lisant les consignes indiquées dans un fichier texte en
	 * suivant la stratégie 2 d'optimisation
	 * 
	 * @param entrepot    l'entrepot
	 * @param recrutement le tableau contenant les employés que l'on peut recruter
	 */
	public static void lectureFichierStrategie2(Entrepot entrepot, String[] recrutement) {
		String nomFichier;
		String line;
		String[] info;
		int personnelStockLotID;
		int pasDeTemps;
		LotDePiecesDetachees lotAStocker;
		Personnel p;

		try {
			System.out.println("Quel fichier voulez-vous lire ?");
			scan.nextLine();//
			nomFichier = scan.nextLine();
			BufferedReader reader = new BufferedReader(new FileReader(new File(nomFichier)));
			line = reader.readLine();
			pasDeTemps = 1;
			Personnel personnelLeMoinsUtile = Personnel.trouverPersonnelPeuUtile(entrepot.getDicoPersonnel());

			while (line != null) {
				System.out.println("----------------------------- Pas de temps " + pasDeTemps);
				// lecture identifiant de la consigne
				info = line.split(" ");

				// Recrutement
				recrutementStrategie2(entrepot, recrutement);

				// Licenciement
				licenciementStrategie2(entrepot, pasDeTemps, personnelLeMoinsUtile);
				personnelLeMoinsUtile = Personnel.trouverPersonnelPeuUtile(entrepot.getDicoPersonnel());

				// execution reception lot/ reception commande/ rien
				switch (info[1]) {
				// reception lot
				case "lot":
					try {
						lotAStocker = new LotDePiecesDetachees(info[2], Double.valueOf(info[3]),
								Double.valueOf(info[4]), Integer.valueOf(info[5]));

						personnelStockLotID = entrepot.stockageLot(lotAStocker);
						if ((personnelStockLotID != -1) && (personnelStockLotID != -2)) {
							lotAStocker.setIDPStock(personnelStockLotID);
							// on ajoute lot au dico des lots présents dans l'entrepot
							entrepot.getDicoLot().put(lotAStocker.getID(), lotAStocker);
							// rendre personnel inactif
							entrepot.getDicoPersonnel().get(personnelStockLotID).setActif(false);
						}

						else {
							System.out.println("Stockage impossible");
						}
					} catch (IndexOutOfBoundsException e) {
						System.out.println("IndexOutOfBoundsException : problème pour le stock du lot à la ligne "
								+ (pasDeTemps + 1));
						// car pasDeTemps commence à 0 alors que les lignes à 1

					}

					break;

				// reception d'une commande
				case "meuble":
					try {
						Map<Integer, LotDePiecesDetachees> dicoLotsMeuble = new HashMap<>();
						ArrayList<LotDePiecesDetachees> listLotsMeuble = new ArrayList<>(); // pour pouvoir instancier
																							// tous les
																							// LotDePiecesDetachees
						int j = 0;
						for (int i = 5; i < info.length; i += 2) {
							// instanciation spéciale sinon c'est la galere car il faut poids, prix et crée
							// nvx ID ect. alors que pas dans la liste des lots de l'entrepot
							listLotsMeuble.add(new LotDePiecesDetachees(info[i], Integer.valueOf(info[i + 1])));
							dicoLotsMeuble.put(listLotsMeuble.get(j).getVolume(), listLotsMeuble.get(j));
							j++;
						}

						Meuble meuble = entrepot.piecesEnStock(2, dicoLotsMeuble, info[2],
								Enum.valueOf(PiecesMaison.class, info[3]), Integer.valueOf(info[4]));
						if (meuble == null) {
							System.out.println("impossible de construire le meuble");
						} else {
							entrepot.getDicoMeuble().put(meuble.getID(), meuble); // on ajoute le meuble au dico des
																					// meubles
																					// en construction

						}
					} catch (IndexOutOfBoundsException e) {
						System.out.println(
								"IndexOutOfBoundsException : problème pour la commande à la ligne " + (pasDeTemps + 1));
					}
					break;

				// rien
				case "rien":
					break;

				default:
					System.out.println("Problème identifiant : cette consigne n'existe pas");
				}

				// deplacer les lots pour améliorer
				ArrayList<LotDePiecesDetachees> listeLotsARegrouper = new ArrayList<>(entrepot.getDicoLot().values());
				// les nouveaux lots ajoutés et ceux supprimés de dicoLot sont donc directement
				// bougés dans/hors listeLotsARegrouper
				Collections.sort(listeLotsARegrouper, Comparator.comparing(LotDePiecesDetachees::getVolume));
				Collections.reverse(listeLotsARegrouper);
				p = Personnel.trouverPersonnelInactif(entrepot.getDicoPersonnel(), true, PiecesMaison.RIEN);
				if (p != null) {
					if (p.getMetier() == Metier.OUVRIER) {
						Ouvrier o = (Ouvrier) p;
						o.regrouperLots(entrepot, listeLotsARegrouper);
					}
					if (p.getMetier() == Metier.CHEFSTOCK) {
						ChefStock chefStock = (ChefStock) p;
						chefStock.regrouperLots(entrepot, listeLotsARegrouper);
					}
				}

				// maj de la liste des meubles en cours de construction
				entrepot.majAvancementDicoMeuble(2);

				// payement des employés
				entrepot.majTresorerie(0);

				// print dico à chaque pas de temps ?
				System.out.println(" --- DicoChef ");
				entrepot.afficheDicoChef();
				System.out.println(" ------------- DicoPersonnel ");
				entrepot.afficheDicoPersonnel();
				System.out.println(" ***** DicoMeuble ");
				entrepot.afficheDicoMeuble();
				System.out.println("///////// DicoLot ");
				entrepot.afficheDicoLot();
				System.out.println("treso :" + entrepot.getTresorerie());

				pasDeTemps++;

				// on lit la ligne suivante
				line = reader.readLine();
			}
			reader.close();

		}

		catch (Exception e) {
			// System.out.println("ce fichier n'existe pas");
			e.printStackTrace();
		}
	}

	/**
	 * Si personne n'est disponible pour stocker de lot, recruter un ouvrier si
	 * possible, un chefStock sinon Sinon, si personne n'est disponible pour
	 * construire un meuble, recrute un ouvrier si possible, un chefBrico sinon
	 * 
	 * @param entrepot    l'entrepot
	 * @param recrutement le tableau contenant les employés que l'on peut recruter
	 */
	public static void recrutementStrategie2(Entrepot entrepot, String[] recrutement) {
		if (entrepot.getTresorerie() > 0) {
			String line;
			String[] infoRecrutement;

			boolean recrute = true;
			Personnel p;
			ChefEquipe c = entrepot.possibleRecruterOuvrier();

			// si personne n'est disponible pour stocker

			if (Personnel.trouverPersonnelInactif(entrepot.getDicoPersonnel(), true, PiecesMaison.RIEN) == null) {

				// possible recruter ouvrier
				if (c != null) {

					// on utilise le tableau avec les employés potentiels
					int i = 0;
					boolean recrutementFait = false;
					while ((i < recrutement.length) && (!recrutementFait)) {
						line = recrutement[i];
						infoRecrutement = line.split(" ");
						if (infoRecrutement[0].equals("OUVRIER")) { // on recrute un ouvrier
							entrepot.recrutementPersonnel(infoRecrutement[1], infoRecrutement[2],
									Enum.valueOf(Metier.class, infoRecrutement[0]),
									Enum.valueOf(PiecesMaison.class, infoRecrutement[3]), c);
							recrutement[i] = "rien"; // on enleve l'employé recruté
							recrutementFait = true;
						}
						i++;
					}
				}
				// impossible de recruter un ouvrier donc on recrute chefstock
				else {
					// on utilise le tableau avec les employés potentiels
					int i = 0;
					boolean recrutementFait = false;
					while ((i < recrutement.length) && (!recrutementFait)) {
						line = recrutement[i];
						infoRecrutement = line.split(" ");
						if (infoRecrutement[0].equals("CHEFSTOCK")) { // on recrute un chefstock
							entrepot.recrutementPersonnel(infoRecrutement[1], infoRecrutement[2],
									Enum.valueOf(Metier.class, infoRecrutement[0]),
									Enum.valueOf(PiecesMaison.class, infoRecrutement[3]), c);
							recrutement[i] = "rien";
							recrutementFait = true;
						}
						i++;
					}
				}
			}

			else {
				// on regarde si qqu dispo pour construire meuble
				Collection<Personnel> personnel = entrepot.getDicoPersonnel().values();
				Iterator<Personnel> it = personnel.iterator();
				while ((it.hasNext()) && (recrute)) {
					p = it.next();
					if ((p.getPeutMonterMeuble()) && (!p.getActif())) {
						recrute = false;
					}
				}

				if (recrute && (c != null)) { // on recrute un ouvrier
					// on utilise le tableau avec les employés potentiels

					int i = 0;
					boolean recrutementFait = false;
					while ((i < recrutement.length) && (!recrutementFait)) {
						line = recrutement[i];
						infoRecrutement = line.split(" ");
						if (infoRecrutement[0].equals("OUVRIER")) { // on recrute un ouvrier
							entrepot.recrutementPersonnel(infoRecrutement[1], infoRecrutement[2],
									Enum.valueOf(Metier.class, infoRecrutement[0]),
									Enum.valueOf(PiecesMaison.class, infoRecrutement[3]), c);
							recrutement[i] = "rien";
							recrutementFait = true;
						}
						i++;
					}
				}

				if (recrute && c == null) {// on recrute un chefBrico
					// on utilise le tableau avec les employés potentiels

					int i = 0;
					boolean recrutementFait = false;
					while ((i < recrutement.length) && (!recrutementFait)) {
						line = recrutement[i];
						infoRecrutement = line.split(" ");
						if (infoRecrutement[0].equals("CHEFBRICO")) { // on recrute un ouvrier
							entrepot.recrutementPersonnel(infoRecrutement[1], infoRecrutement[2],
									Enum.valueOf(Metier.class, infoRecrutement[0]),
									Enum.valueOf(PiecesMaison.class, infoRecrutement[3]), c);
							recrutement[i] = "rien";
							recrutementFait = true;
						}
						i++;
					}
				}

			}
		}
	}

	/**
	 * Licencier l'employé le moins utile si la trésorerie est négative ou licencier
	 * tous les 5 pas de temps le personnel le moins utile si c'était le même au
	 * temps précédent
	 * 
	 * @param entrepot              l'entrepot
	 * @param pasDeTemps            le pas de temps auquel on se trouve
	 * @param personnelLeMoinsUtile le personnel donc le niveau d'activité est le
	 *                              plus bas
	 */

	public static void licenciementStrategie2(Entrepot entrepot, int pasDeTemps, Personnel personnelLeMoinsUtile) {

		if ((entrepot.getDicoPersonnel().size()) > 1) {
			// si la trésorerie est négative, on licencie le personnelLeMoinsUtile
			if (entrepot.getTresorerie() < 0) {
				entrepot.licenciementPersonnel(personnelLeMoinsUtile);
			}

			else if (pasDeTemps % 5 == 0) {
				// regarder si personneLaMoinsUtile au pas précédent est la même
				Personnel NvPersonnelLeMoinsUtile = Personnel.trouverPersonnelPeuUtile(entrepot.getDicoPersonnel());
				if ((NvPersonnelLeMoinsUtile.getID() == personnelLeMoinsUtile.getID())) {
					// on licencie cette personne
					entrepot.licenciementPersonnel(personnelLeMoinsUtile);
				}
			}

		}
	}

}