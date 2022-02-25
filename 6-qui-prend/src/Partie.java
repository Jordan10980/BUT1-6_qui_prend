import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partie {
	public static final int NB_SERIE = 4;
	static List<Carte> cartes_posees = new ArrayList<Carte>();
	
	Joueur[] joueurs;
	Paquet paquet;
	Serie[] series;
	
	public Partie() {
		// Initialise une liste de joueur pour la partie
		this.joueurs = initialiserJoueur();

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
			series[i] = new Serie();
			p.distributionSeries(series[i]);
//			System.out.println(series[i]);
		}

		return series;
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
	private Joueur[] initialiserJoueur() {

		List<String> listeNomJoueurs = lireNomJoueur();
//		System.out.println(listeNomJoueurs); // Liste des joueurs
//		System.out.println(listeNomJoueurs.get(0));
//		System.out.println("Il y a " + listeNomJoueurs.size() + " joueurs"); // Nombre de joueurs

		Joueur[] joueurs = new Joueur[listeNomJoueurs.size() ];
		for (int i = 0; i < listeNomJoueurs.size() ; i++) {
			joueurs[i] = new Joueur(listeNomJoueurs.get(i));
//			System.out.println(joueurs[i]); Pour v�rifier que les Joueuers ont des num�ros et des noms diff�rents
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
	
	
//	public static void Tour0() {
//		for(int i = 0; i< joueurs.length; i++) {
//			System.out.println("A " + joueurs[i].getNom() + " de jouer.");
//			Console.pause();
//			for(int j = 0; j < Partie.NB_SERIE; j++) {
//				System.out.println(series[j].toString1());
//			}
//			System.out.println(joueurs[i].toString1());
//			joueurs[i].choisirCarte();
//			Console.clearScreen();
//			
//		}
//		
//	}

}
