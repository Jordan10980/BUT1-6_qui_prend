import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partie {
	public static final int NB_SERIE = 4;
	//static List<Carte> cartes_posees = new ArrayList<Carte>();
	
	// Les joueurs participant à la partie
	Joueur[] joueurs;
	
	// Le paquet de carte utilisé durant la partie
	Paquet paquet;
	
	// Les différentes séries "en cours" utilisées durant la partie
	Serie[] series;
	

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

	/**
	 * La création/initialisation d'une Partie consiste en:
	 * <LI> La création des joueurs de la partie à partir du fichier config.txt
	 * <LI> La création d'un paquet de carte mélangé
	 * <LI> La distribution des cartes aux différents joueurs
	 * <LI> La création/initialisation des série avec une carte initiale
	 */
	public Partie() {
		// Initialise une liste de joueur pour la partie
		this.joueurs = initialiserJoueur();

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
	private Joueur[] initialiserJoueur() {

		List<String> listeNomJoueurs = lireNomJoueur();
//		System.out.println(listeNomJoueurs); // Liste des joueurs
//		System.out.println(listeNomJoueurs.get(0));
//		System.out.println("Il y a " + listeNomJoueurs.size() + " joueurs"); // Nombre de joueurs

		Joueur[] joueurs = new Joueur[listeNomJoueurs.size() ];
		for (int i = 0; i < listeNomJoueurs.size() ; i++) {
			joueurs[i] = new Joueur(listeNomJoueurs.get(i));
//			System.out.println(joueurs[i]); Pour vérifier que les Joueuers ont des numéros et des noms différents
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
	private List<String> lireNomJoueur() {

		final String nomFichierJoueur = "config.txt";
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
		
		return listeNomJoueurs;
	}

	
	/**
	 * Démarre une partie préalablement créée/initialisée avec l'ensemble des joueurs (et leur main) et des séries.
	 * 
	 */
	public void demarrer() {
//		 permet aux joueurs de choisir leurs cartes
		for(int i = 0; i< this.nbJoueur(); i++) {
			System.out.println("A " + this.getJoueurs(i).getNom() + " de jouer.");
			Console.pause();
			for(int j = 0; j < Partie.NB_SERIE; j++) {
				System.out.println(this.getSeries(j));
			}
			System.out.println(this.getJoueurs(i).toString1());
			Carte carteChoisie = this.getJoueurs(i).choisirCarte();
			
			if(!this.placerCarte(carteChoisie, this.getJoueurs(i))) {
				Serie serie = this.getJoueurs(i).choisirSerie();
			}
			Console.clearScreen();
		}
		
//		 A continuer
//		System.out.println(Partie.cartes_posees);
//		
//		System.out.println("Les cartes ");
//		for(int i = 0; i < joueurs.length; i++) {
//			System.out.println();
//		}
//	
//		
	}
	
	/**
	 * Place une carte sur l'une des séries selon les règles définies par le jeu 6-qui-prend.<BR>
	 * La carte est ajoutée à la liste de carte de la série.
	 * @param carteAPlacer : la carte à placer sur la bonne série
	 */
	public boolean placerCarte(Carte carteAPlacer, Joueur joueur) {
		int indiceSerieTrouvee = -1;
		for(int i = 0; i < Partie.NB_SERIE; i++) {
			Serie serie = series[i];
			Carte derniereCarte = serie.derniereCarte();
			if(carteAPlacer.getNumero() > derniereCarte.getNumero()) {
				if(indiceSerieTrouvee != -1) {
					if(derniereCarte.getNumero() > series[indiceSerieTrouvee].derniereCarte().getNumero()) {
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
			if(series[indiceSerieTrouvee].estPleine()) {
				int nbTete = series[indiceSerieTrouvee].nbTete();
				joueur.ajouterTete(nbTete);
				Serie serie = new Serie();
				series[indiceSerieTrouvee] = serie;
			}
			series[indiceSerieTrouvee].ajouterCarte(carteAPlacer);
			return true;	
		}
	}
}
