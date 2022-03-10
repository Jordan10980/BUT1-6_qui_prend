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
	
	// Les joueurs participant � la partie
	private Joueur[] joueurs;
	
	// Le paquet de carte utilis� durant la partie
	private Paquet paquet;
	
	// Les diff�rentes s�ries "en cours" utilis�es durant la partie
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
	 * La cr�ation/initialisation d'une Partie consiste en:
	 * <LI> La cr�ation des joueurs de la partie � partir du fichier config.txt
	 * <LI> La cr�ation d'un paquet de carte m�lang�
	 * <LI> La distribution des cartes aux diff�rents joueurs
	 * <LI> La cr�ation/initialisation des s�rie avec une carte initiale
	 */
	public Partie(String nomFichierJoueur) throws Exception {
		// Initialise une liste de joueur pour la partie
		this.joueurs = initialiserJoueur(nomFichierJoueur);

		// Recup�re un paquet m�lang�
		this.paquet = recupererNouveauPaquet();
		
		// Distribue les cartes du paquet aux diff�rents joueurs
		for (int i = 0; i < joueurs.length; i++) {
			paquet.distributionCartes(joueurs[i]);
		}
		
		// Cr�e les s�ries pour le d�marrage du jeu
		this.series = creerSeries(paquet);
	}
	
	/**
	 * Cr�e les s�ries de jeu en piochant des cartes du paquet de carte
	 * 
	 * @param p : le paquet de carte de la partie
	 * @return les s�ries cr��es
	 */
	private Serie[] creerSeries(Paquet p) {
		Serie[] series = new Serie[NB_SERIE];
		for (int i = 0; i < NB_SERIE; i++) {
			series[i] = creerNouvelleSerie(p);
		}
		return series;
	}

	/**
	 * Cr�ation/initialisation d'une nouvelle serie
	 * @param paquet : le paquet n�c�ssaire � l'initilaition de la s�rie
	 * @return la nouvelle s�rie cr��e et initialis�e
	 */
	private Serie creerNouvelleSerie(Paquet paquet) {
		Serie serie  = new Serie();
		paquet.distributionSeries(serie);
		return serie;
	}

	/**
	 * R�cup�re un nouveau paquet de carte complet pour jouer � 6-qui-prend<BR>
	 * Le paquet de carte r�cup�r� est d�j� m�lang� et pr�t � �tre distribu�.
	 * 
	 * @return le paquet de carte pr�t � �tre distribu�
	 */
	private Paquet recupererNouveauPaquet() {
		Paquet p = new Paquet();
		p.melangerCartes();
		return p;
	}

	/**
	 * Initialise un tableau de joueur pour la partie en fonction d'une liste de nom de joueur.
	 * 
	 * @return le tableau de joueur pr�t � jouer � 6-qui-prend
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
		System.out.println("et " + joueurs[joueurs.length  - 1].getNom() + ". Merci de jouer � 6 qui prend !");

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
			throw new Exception("Le nombre de joueur doit �tre plus grand que 2 et inf�rieur � 10");
		}

		return listeNomJoueurs;
	}
	
	/**
	 * D�marre une partie pr�alablement cr��e/initialis�e avec l'ensemble des joueurs (et leur main) et des s�ries.
	 * 
	 */
	public void demarrer() {
		// Les cartes pos�es pendant le tour avec le joueur associ�
		Map<Carte, Joueur> carteAPoser = new TreeMap<Carte, Joueur>();

		// Les t�tes rammass�es pendant le tour avec le joueur associ�
		Map<Joueur, Integer> teteParJoueur = new HashMap<Joueur, Integer>();

		// On boucle tant que le jeu n'est pas termin�
		while(!finJeu()) {

			// On appelle les joueurs un par un pour qu'ils choisissent une carte � poser
			appelJoueur(carteAPoser);
			// On affiche les cartes � poser choisies par tous les joueurs
			affichageCarteAPoser(carteAPoser);
			// On pose les cartes dans les s�ries
			poserLesCartes(carteAPoser, teteParJoueur);
			// Une fois les cartes pos�es, on affiche les s�ries mises � jour
			affichageSerie();
			// On affiche les scores (nombre de t�te r�cup�r� par les joueurs) pour le tour en cours
			if(!affichageScoreTour(teteParJoueur)) {
	        	System.out.println("Aucun joueur ne ramasse de t�te de boeufs.");
	        }
			// On r�initialise les Map de cartes pos�es et de nombre de t�tes ramass�es pendant le tour
			teteParJoueur.clear();
			carteAPoser.clear();
		}
		
		// Une fois la partie finie, on affiche le score final de la partie
		affichageFinal();
	}
	
	/**
	 * Appel des joueurs un par un pour qu'ils choisissent une carte � poser
	 */
	public void appelJoueur(Map<Carte, Joueur> carteAPoser) {
		// On boucle sur tous les joueurs
		for(int i = 0; i< this.nbJoueur(); i++) {
			Joueur joueur = this.getJoueurs(i);

			// Le joueur joue le tour si sa main n'est pas vide
			if(!joueur.mainVide()) {
				System.out.println("A " + joueur.getNom() + " de jouer.");
				Console.pause();

				// On affiche les s�ries au joueur
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
		System.out.println(" vont �tre pos�es.");
	}
	
	/**
	 * Pose des cartes dans les s�ries en suivant les conditions du jeu � savoir:
	 * <LI> Condition 1: Les cartes d'une m�me s�rie sont toujours de valeurs croissante
	 * <LI> Condition 2: Une carte doit toujours �tre depos�e dans la s�rie o� la diff�rence entre sa valeur et celle de la derni�re carte de la s�rie est la plus faible.
	 * <LI> Condition 3: Lorsqu'une sixi�me carte doit �tre deposee dans une serie, le joueur ramasse les 5 cartes de la serie
	 * <LI> Condition 4: Un joueur ayant une carte trop faible pour �tre d�pos� dans un s�rie, choisit une s�rie et la rammasse. Sa carte d�bute une nouvelle s�rie 
	 * 
	 * @param teteParJoueur : les t�tes r�cup�r�es par les joueurs durant le tour
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
			System.out.println(nom + " a rammass� " + tete + " t�tes de boeufs");
			teteRamassee=true;
		}
		return teteRamassee;
	}
	
	
	/**
	 * Place une carte sur l'une des s�ries selon les r�gles d�finies par le jeu 6-qui-prend.<BR>
	 * La carte est ajout�e � la liste de carte de la s�rie.
	 * Le placement de la carte dans une s�rie peut engendr� le ramassage des cartes par le joeur (selon les conditions du jeu).
	 * Si la carte ne peut pas �tre automatiquement plac�e dans une s�rie, aucune s�rie n'est modifi�.
	 * 
	 * @param carteAPlacer : la carte � placer dans la bonne s�rie
	 * @param joueur : le joueur ayant poas� la carte 
	 * @param teteParJoueur : les t�tes r�cup�r�es par l'ensemble des joueurs durant le tour (�ventuellement mis � jour si le joueur rammasse des cartes d'une s�rie en posant sa carte)
	 * @return vrai si la carte a pu �tre pos�e dans une s�rie, faux sinon
	 */
	public boolean placerCarte(Carte carteAPlacer, Joueur joueur, Map<Joueur, Integer> teteParJoueur) {
		int indiceSerieTrouvee = -1;
		for(int i = 0; i < Partie.NB_SERIE; i++) {
			Serie serie = series[i];
			// On r�cup�re la derni�re carte d'une s�rie
			Carte derniereCarte = serie.derniereCarte();

			// Condition1 : On trouve une s�rie dont la valeur de la derni�re carte est inf�rieure � celle de la carte � poser
			if(carteAPlacer.getNumero() > derniereCarte.getNumero()) { 
				// Condition2 : Si on avait d�j� trouv� une s�rie compatible avec la condition1, on regarde si la s�rie actuelle est plus pertinente (derni�re carte plus proche de la carte � poser) que celle d�j� trouv�e
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
			//Condition3: Si la s�rie est pleine alors on la vide (transfert des cartes de la s�rie vers le tas de cartes rammass�es par le joueur)
			if(series[indiceSerieTrouvee].estPleine()) { // Condition 3
				// Au passage, on met � jour le nombre de t�te rammasse�s par le joueur durant le tour
				int nbTete = series[indiceSerieTrouvee].nbTete();
				series[indiceSerieTrouvee].vider(joueur);
				teteParJoueur.put(joueur, nbTete);
			}
			// Ajout de la carte � poser � la s�rie  
			series[indiceSerieTrouvee].ajouterCarte(carteAPlacer);
			return true;	
		}
		
	}
	
	/**
	 * Choix de la s�rie que le joueur va devoir rammaser.
	 * @param carte : la carte � poser pour le joueur qui n'a pas pu �tre poser automatiquement
	 * @param joueur : le joueur ayant jouer cette carte
	 * @return la s�rie choisie par le joueur et dont il va devoir rammaser les cartes
	 */
	public Serie choisirSerie(Carte carte, Joueur joueur) {
		System.out.println("Pour poser la carte " + carte +", " + joueur.getNom() + " doit choisir la s�rie qu'il va ramasser." );

		// Affichage des s�ries
		affichageSerie();
		
		// Choix de la s�rie par l'utilisateur
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
	 * V�rifie qu'une s�rie exsite en fonction d'un num�ro.
	 * @param valeur : le num�ro de s�rie � v�rifier
	 * @return vrai si la s�rie existe, faux si elle n'existe pas
	 */
	private int serieExiste(int valeur) {
		for(int i = 0; i < Partie.NB_SERIE; i++) {
			if(valeur == series[i].getNumero())
				return i;
		}
		return -1;
	}
	
	/**
	 * D�termine si la partie est finie ou pas.<BR>
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
	 * On affiche le nombre de t�tes rammass�es par le joueur
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
					System.out.println(joueurs[j].getNom() + " a rammass� " + joueurs[j].getNbTete()+ " t�tes de boeufs");
				}
			}
			
		}
		
		// si les joueurs ont le m�me nombre de t�tes de boeufs on les tries par odre alphab�tique
		
	
	}
	
}
