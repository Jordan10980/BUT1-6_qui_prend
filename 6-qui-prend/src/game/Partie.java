package game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Partie {
	public static final int NB_SERIE = 4;
	
	private static final String NOM_FICHIER_JOUEUR = "config.txt";
	
	// Les joueurs participant à la partie
	private Joueur[] joueurs;
	
	// Le paquet de carte utilisé durant la partie
	private Paquet paquet;
	
	// Les différentes séries "en cours" utilisées durant la partie
	private Serie[] series;

	public Joueur getJoueurs(int indice) {
		return joueurs[indice];
	}

	public Paquet getPaquet() {
		return paquet;
	}

	public Serie getSeries(int indice) {
		return series[indice];
	}
	

	public int nbJoueur() {
		return joueurs.length;
	}

	public Partie() throws Exception {
		this(NOM_FICHIER_JOUEUR);
	}

	/**
	 * La création/initialisation d'une Partie consiste en:
	 * <LI> La création des joueurs de la partie à partir du fichier config.txt
	 * <LI> La création d'un paquet de carte mélangé
	 * <LI> La distribution des cartes aux différents joueurs
	 * <LI> La création/initialisation des série avec une carte initiale
	 */
	public Partie(String nomFichierJoueur) throws Exception {
		// Initialise une liste de joueur pour la partie
		this.joueurs = initialiserJoueur(nomFichierJoueur);

		// Recupère un paquet mélangé
		this.paquet = recupererNouveauPaquet();
		
		// Distribue les cartes du paquet aux différents joueurs
		for (int i = 0; i < joueurs.length; i++) {
			paquet.distributionCartes(joueurs[i]);
		}
		
		// Crée les séries pour le démarrage du jeu
		this.series = creerSeries(paquet);
	}
	
	/**
	 * Crée les séries de jeu en piochant des cartes du paquet de carte
	 * 
	 * @param p : le paquet de carte de la partie
	 * @return les séries créées
	 */
	private Serie[] creerSeries(Paquet p) {
		Serie[] series = new Serie[NB_SERIE];
		for (int i = 0; i < NB_SERIE; i++) {
			series[i] = creerNouvelleSerie(p);
		}
		return series;
	}

	/**
	 * Création/initialisation d'une nouvelle serie
	 * @param paquet : le paquet nécéssaire à l'initilaition de la série
	 * @return la nouvelle série créée et initialisée
	 */
	private Serie creerNouvelleSerie(Paquet paquet) {
		Serie serie  = new Serie();
		paquet.distributionSeries(serie);
		return serie;
	}

	/**
	 * Récupère un nouveau paquet de carte complet pour jouer à 6-qui-prend<BR>
	 * Le paquet de carte récupéré est déjà mélangé et prêt à être distribué.
	 * 
	 * @return le paquet de carte prêt à être distribué
	 */
	private Paquet recupererNouveauPaquet() {
		Paquet p = new Paquet();
		p.melangerCartes();
		return p;
	}

	/**
	 * Initialise un tableau de joueur pour la partie en fonction d'une liste de nom de joueur.
	 * 
	 * @return le tableau de joueur prêt à jouer à 6-qui-prend
	 */
	private Joueur[] initialiserJoueur(String nomFichierJoueur) throws Exception  {

		List<String> listeNomJoueurs = lireNomJoueur(nomFichierJoueur);

		Joueur[] joueurs = new Joueur[listeNomJoueurs.size() ];
		for (int i = 0; i < listeNomJoueurs.size() ; i++) {
			joueurs[i] = new Joueur(listeNomJoueurs.get(i));
		}

		System.out.print("Les " + joueurs.length  + " joueurs sont ");
		for (int i = 0; i < joueurs.length  - 1; i++) {
			System.out.print(joueurs[i].getNom() + ", ");
		}
		System.out.println("et " + joueurs[joueurs.length  - 1].getNom() + ". Merci de jouer à 6 qui prend !");

		return joueurs;
	}

	
	/**
	 * Lit les noms des joueurs de la partie depuis le fichier de nom de joueur.
	 * 
	 * @return la liste des nom de joueur
	 */
	private List<String> lireNomJoueur(String nomFichierJoueur) throws Exception {
		
		List<String> listeNomJoueurs = new ArrayList<String>();
		try {

			Scanner in = new Scanner(new FileInputStream(nomFichierJoueur));
			while (in.hasNextLine()) {
				listeNomJoueurs.add(in.nextLine());
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Impossible d'ouvrir le fichier " + nomFichierJoueur +  " contenant les noms des joueurs.");
			System.out.println("La partie ne peux donc pas commencer...");
			throw new RuntimeException(e);
		}
		
		if(listeNomJoueurs.size() < 2  || listeNomJoueurs.size() > 10) {
			throw new Exception("Le nombre de joueur doit être plus grand que 2 et inférieur à 10");
		}

		return listeNomJoueurs;
	}
	
	/**
	 * Démarre une partie préalablement créée/initialisée avec l'ensemble des joueurs (et leur main) et des séries.
	 * 
	 */
	public void demarrer() {
		// Les cartes posées pendant le tour avec le joueur associé
		Map<Carte, Joueur> carteAPoser = new TreeMap<Carte, Joueur>();

		// Les têtes rammassées pendant le tour avec le joueur associé
		Map<Joueur, Integer> teteParJoueur = new HashMap<Joueur, Integer>();

		// On boucle tant que le jeu n'est pas terminé
		while(!finJeu()) {

			// On appelle les joueurs un par un pour qu'ils choisissent une carte à poser
			appelJoueur(carteAPoser);
			// On affiche les cartes à poser choisies par tous les joueurs
			affichageCarteAPoser(carteAPoser);
			// On pose les cartes dans les séries
			poserLesCartes(carteAPoser, teteParJoueur);
			// Une fois les cartes posées, on affiche les séries mises à jour
			affichageSerie();
			// On affiche les scores (nombre de tête récupéré par les joueurs) pour le tour en cours
			if(!affichageScoreTour(teteParJoueur)) {
	        	System.out.println("Aucun joueur ne ramasse de tête de boeufs.");
	        }
			// On réinitialise les Map de cartes posées et de nombre de têtes ramassées pendant le tour
			teteParJoueur.clear();
			carteAPoser.clear();
		}
		
		// Une fois la partie finie, on affiche le score final de la partie
		affichageFinal();
	}
	
	/**
	 * Appel des joueurs un par un pour qu'ils choisissent une carte à poser
	 */
	public void appelJoueur(Map<Carte, Joueur> carteAPoser) {
		// On boucle sur tous les joueurs
		for(int i = 0; i< this.nbJoueur(); i++) {
			Joueur joueur = this.getJoueurs(i);

			// Le joueur joue le tour si sa main n'est pas vide
			if(!joueur.mainVide()) {
				System.out.println("A " + joueur.getNom() + " de jouer.");
				Console.pause();

				// On affiche les séries au joueur
				affichageSerie();
				// On affiche sa main
				joueur.afficherMain();
				// On lui demande de choisir ue carte de sa main
				Carte carteChoisie = joueur.choisirCarte();
				// On sauvegarde la carte choisie pour le tour en cours
				carteAPoser.put(carteChoisie, joueur);

				Console.clearScreen();
			}
		}
	}
		
	/**
	 * affichage des cartes a poser choisie par tous les joueurs durant le tour en cours.
	 */
	public void affichageCarteAPoser(Map<Carte, Joueur> carteAPoser) {
		System.out.print("Les cartes ");
		int i=0;
        for (Map.Entry mapentry : carteAPoser.entrySet()) {
        	int num = ((Carte) mapentry.getKey()).getNumero();
        	String nom = ((Joueur)mapentry.getValue()).getNom();
			if(i > 0){
				if(i < carteAPoser.size()-1) {
					System.out.print(", ");
				} 
				else {
					System.out.print(" et ");
				}
			}
        	System.out.print(num + " (" + nom + ")");
			i++;	
        }
		System.out.println(" vont être posées.");
	}
	
	/**
	 * Pose des cartes dans les séries en suivant les conditions du jeu à savoir:
	 * <LI> Condition 1: Les cartes d'une même série sont toujours de valeurs croissante
	 * <LI> Condition 2: Une carte doit toujours être deposée dans la série où la différence entre sa valeur et celle de la dernière carte de la série est la plus faible.
	 * <LI> Condition 3: Lorsqu'une sixième carte doit être deposee dans une serie, le joueur ramasse les 5 cartes de la serie
	 * <LI> Condition 4: Un joueur ayant une carte trop faible pour être déposé dans un série, choisit une série et la rammasse. Sa carte débute une nouvelle série 
	 * 
	 * @param teteParJoueur : les têtes récupérées par les joueurs durant le tour
	 */
	public void poserLesCartes(Map<Carte, Joueur> carteAPoser, Map<Joueur, Integer> teteParJoueur) {
	   for (Map.Entry mapentry : carteAPoser.entrySet()) {
        	Carte carte = ((Carte) mapentry.getKey());
        	Joueur joueur = ((Joueur)mapentry.getValue());
        	if(!placerCarte(carte, joueur, teteParJoueur)) {
        		Serie serie = choisirSerie(carte, joueur);  // condition 4
        		int nbTete = serie.nbTete();
				serie.vider(joueur);
				serie.ajouterCarte(carte);
		        teteParJoueur.put(joueur, nbTete);
		        affichageCarteAPoser(carteAPoser);
        	}
        }
	   
	}
	
	public void affichageSerie() {	
		for(int j = 0; j < Partie.NB_SERIE; j++) {
			System.out.println(this.getSeries(j));
		}
	}
	
	public boolean affichageScoreTour(Map<Joueur, Integer> teteParJoueur) {
		boolean teteRamassee=false;
		for (Map.Entry mapentry : teteParJoueur.entrySet()) {
			String nom = ((Joueur) mapentry.getKey()).getNom();
			int tete = ((Integer) mapentry.getValue());
			System.out.println(nom + " a rammassé " + tete + " têtes de boeufs");
			teteRamassee=true;
		}
		return teteRamassee;
	}
	
	
	/**
	 * Place une carte sur l'une des séries selon les règles définies par le jeu 6-qui-prend.<BR>
	 * La carte est ajoutée à la liste de carte de la série.
	 * Le placement de la carte dans une série peut engendré le ramassage des cartes par le joeur (selon les conditions du jeu).
	 * Si la carte ne peut pas être automatiquement placée dans une série, aucune série n'est modifié.
	 * 
	 * @param carteAPlacer : la carte à placer dans la bonne série
	 * @param joueur : le joueur ayant poasé la carte 
	 * @param teteParJoueur : les têtes récupérées par l'ensemble des joueurs durant le tour (éventuellement mis à jour si le joueur rammasse des cartes d'une série en posant sa carte)
	 * @return vrai si la carte a pu être posée dans une série, faux sinon
	 */
	public boolean placerCarte(Carte carteAPlacer, Joueur joueur, Map<Joueur, Integer> teteParJoueur) {
		int indiceSerieTrouvee = -1;
		for(int i = 0; i < Partie.NB_SERIE; i++) {
			Serie serie = series[i];
			// On récupère la dernière carte d'une série
			Carte derniereCarte = serie.derniereCarte();

			// Condition1 : On trouve une série dont la valeur de la dernière carte est inférieure à celle de la carte à poser
			if(carteAPlacer.getNumero() > derniereCarte.getNumero()) { 
				// Condition2 : Si on avait déjà trouvé une série compatible avec la condition1, on regarde si la série actuelle est plus pertinente (dernière carte plus proche de la carte à poser) que celle déjà trouvée
				if(indiceSerieTrouvee != -1) {
					if(derniereCarte.getNumero() > series[indiceSerieTrouvee].derniereCarte().getNumero()) { //condition 2
						indiceSerieTrouvee = i;
					}
				}
				else {
					indiceSerieTrouvee = i;
				}
			}			
		}
		
		if(indiceSerieTrouvee == -1) {
			return false;
		}
		else {
			//Condition3: Si la série est pleine alors on la vide (transfert des cartes de la série vers le tas de cartes rammassées par le joueur)
			if(series[indiceSerieTrouvee].estPleine()) { // Condition 3
				// Au passage, on met à jour le nombre de tête rammasseés par le joueur durant le tour
				int nbTete = series[indiceSerieTrouvee].nbTete();
				series[indiceSerieTrouvee].vider(joueur);
				teteParJoueur.put(joueur, nbTete);
			}
			// Ajout de la carte à poser à la série  
			series[indiceSerieTrouvee].ajouterCarte(carteAPlacer);
			return true;	
		}
		
	}
	
	/**
	 * Choix de la série que le joueur va devoir rammaser.
	 * @param carte : la carte à poser pour le joueur qui n'a pas pu être poser automatiquement
	 * @param joueur : le joueur ayant jouer cette carte
	 * @return la série choisie par le joueur et dont il va devoir rammaser les cartes
	 */
	public Serie choisirSerie(Carte carte, Joueur joueur) {
		System.out.println("Pour poser la carte " + carte +", " + joueur.getNom() + " doit choisir la série qu'il va ramasser." );

		// Affichage des séries
		affichageSerie();
		
		// Choix de la série par l'utilisateur
		System.out.print("Saisissez votre choix : ");
		int indice = -1;
		Serie serieChoisie = null;
		do {
			Scanner sc = new Scanner(System.in);
			String rep = sc.next();
			indice = serieExiste(Integer.valueOf(rep));
			if(indice != -1) {
				serieChoisie = series[indice];
			}
			else{
				System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");	
			}
		} while(indice == -1);
		//affichageCarteAPoser();
		return serieChoisie;
		
	}
	
	/**
	 * Vérifie qu'une série exsite en fonction d'un numéro.
	 * @param valeur : le numéro de série à vérifier
	 * @return vrai si la série existe, faux si elle n'existe pas
	 */
	private int serieExiste(int valeur) {
		for(int i = 0; i < Partie.NB_SERIE; i++) {
			if(valeur == series[i].getNumero())
				return i;
		}
		return -1;
	}
	
	/**
	 * Détermine si la partie est finie ou pas.<BR>
	 * La partie est finie lorsque plus aucun joueur n'a de carte dans sa main.
	 * @return vrai si la partie est finie. Faux sinon
	 */
	public boolean finJeu() {
		for(int i = 0; i < this.nbJoueur(); i++) {
			if(! joueurs[i].mainVide()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Affichage  des scores finaux de tous les joueurs.<BR>
	 * On affiche le nombre de têtes rammassées par le joueur
	 */
	public void affichageFinal() { 
		ArrayList<Integer> teteTmp = new ArrayList();
		
		System.out.println("** Score final");
		
		for(int i = 0; i < this.nbJoueur(); i++) {
			teteTmp.add(joueurs[i].getNbTete());
		}
		Collections.sort(teteTmp);
		
		for(int i = 0; i < this.nbJoueur(); i++) {
			for(int j = 0; j < this.nbJoueur(); j++) {
				if(teteTmp.get(i) == joueurs[j].getNbTete()) {
					System.out.println(joueurs[j].getNom() + " a rammassé " + joueurs[j].getNbTete()+ " têtes de boeufs");
				}
			}
			
		}
		
		// si les joueurs ont le même nombre de têtes de boeufs on les tries par odre alphabétique
		
	
	}
	
}
