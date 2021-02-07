Cette archive contient le projet Biilomo, implémenté en Java par Lauréline Charret et Julie Pibouteau. 

1. CONTENU DE L'ARCHIVE ---------------------------------------------------------------------------------------------------------------

Pour accéder au code, vous devez suivre l'arborescence suivante : Biilomo puis src.
Vous trouverez 3 packages :
entrepot
personne
meuble

entrepot contient :
	la classe Entrepot.java et sa classe de tests EntrepotTest.java
	la classe Main.java

personne contient : 
	l'interface GestionStock.java et sa classe de tests GestionStockTest.java
	la classe Personnel.java et sa classe de tests PersonnelTest.java
	la classe ChefEquipe.java 
	la classe ChefStock.java
	la classe ChefBrico.java
	la classe Ouvrier.java
	la classe Tuple.java
	l'énumération Metier.java
	l'énumération PiecesMaison.java
	
meuble contient :
	la classe LotDePiecesDetachees.java
	la classe Meuble.java

Ainsi que 2 fichiers tests:
	test1.txt
	test2.txt


2. LANCER LE PROJET BIILOMO -----------------------------------------------------------------------------------------------------------

Pour lancer le projet, vous pouvez soit l'ouvrir dans Eclipse, soit compiler chaque fichier java. Pour cela, ouvrez un terminal et compilez chaque fichier .java mentionné plus haut avec la commande : javac nomFichier.java. Puis exécutez : java Main

Vous n'avez plus qu'à suivre les instructions qui s'affichent à l'écran.

Les dimensions que vous donnez à l'entrepôt doivent être des entiers strictement positifs.
Vous pouvez fixer librement le montant de la trésorerie.
!! IMPORTANT : tous les nombres décimaux doivent être écrits dans la console avec une virgule, pas un point. On écrira par exemple 21,6 et non pas 21.6

Par la suite, vous avez le choix entre deux modes pour donner vos instructions à l'entrepôt : la console (tapez 0) ou la lecture d'un fichier (tapez 1).
Si vous choisissez le fichier texte, chacune de ses lignes suivra l’une des formes suivantes : 

id rien
id lot nom poids prix volume
id meuble nom pieceMaison duréeConstruction typeLot1 volumeLot1 typeLot2 volumeLot2 ...

où id est un int qui représente l’identifiant de la consigne;
nom est un String qui identifie le nom de la pièce (porte, tiroir, poignee, cheville, vis, planche...) ou du meuble (commode, lit, etagere, placard...); 
!!! ATTENTION S'il s'agit d'un nom composé de plusieurs mots, il faut l'écrire avec des tirets bas au lieu d'espaces (ex : pied_de_table)
pieceMaison est un String qui donne la pièce de la maison associée au meuble;
!! IMPORTANT Quel que soit le mode, le nom de la pièce de la maison doit être écrit sous la forme suivante et doit appartenir à cette liste :
{CUISINE, CHAMBRE, SALLEaMANGER, SALON, SALLEdeBAIN, WC, MAISON, RIEN}
duréeConstruction est un int qui précise la durée de construction d’un meuble;
poids et prix sont des double et volume est un int.

Par exemple :
1 rien
2 lot vis 3.2 2.4 3
3 lot vis 2.3 2.6 5
4 lot clou 2.2 3.7 3
5 lot clou 2.1 1.3 2
6 meuble table CHAMBRE 3 vis 1 clou 2

Après avoir choisi votre mode, vous devez choisir entre deux stratégie :
Tapez 1 pour la stratégie 1, dont l'objectif est la simplicité.
Tapez 2 pour la stratégie 2 dont l'objectif est l'optimisation.


3. UTILISER LE PROJET BIILOMO ---------------------------------------------------------------------------------------------------------

A chaque pas de temps, vous avez le choix entre :
	Taper 0 pour recevoir un lot
	Taper 1 pour recevoir une commande
	Taper 2 pour ne rien faire
	Taper 3 pour quitter la simulation
	
Veuillez noter qu'au début de la simulation, l'entrepôt possède déjà un chef "brico", un chef "stock" et trois ouvriers afin de pouvoir démarrer la simulation dans de bonnes conditions avec de la diversité dans le personnel.
