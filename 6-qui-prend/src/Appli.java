import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author iPrisc & Jordan10980
 *
 */
public class Appli {

//	private static int nb_joueur = 0;
	private static final int NB_SERIE = 4;

	/**
	 * M�thode principale permettant de lancer une partie de 6-qui-prend
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	

		// Initialise une liste de joueur pour la partie
		Joueur[] joueurs = initialiserJoueur();

		// Recup�re un paquet m�lang�
		Paquet p = recupererNouveauPaquet();
		
		// Distribue les cartes du paquet aux diff�rents joueurs
		for (int i = 0; i < joueurs.length; i++) {
			p.distributionCartes(joueurs[i]);
//			System.out.println(joueurs[i]);
//			p.afficherCartes();
		}
		
		// Cr�e les s�ries pour le d�marrage du jeu
		Serie[] series = creerSeries(p);
//		p.afficherCartes();
		
		// D�marrage du jeu....
		
		Jouer jeu = new Jouer(joueurs.length);
		
//		jeu.setJoueur(joueurs[0]);
		
		
//		for (int i = 0; i < joueurs.length; i++) {
//			jeu.setNomJoueurs(joueurs[i].getNom()); // r�cup�re le nom des joueurs
//		}
//		
//		jeu.choixCartes();
//		System.out.println(jeu);
//		
		
		System.out.println("A " + joueurs[0].getNom() + " de jouer.");
		Console.pause();
		for(int i = 0; i < NB_SERIE; i++) {
			System.out.println(series[i].toString1());
		}
		System.out.println(joueurs[0].toString1());

	}
	/**
	 * Cr�e les s�ries de jeu en piochant des cartes du paquet de carte
	 * 
	 * @param p : le paquet de carte de la partie
	 * @return les s�ries cr��es
	 */
	private static Serie[] creerSeries(Paquet p) {
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
	private static Paquet recupererNouveauPaquet() {

		Paquet p = new Paquet();

//		System.out.println("Avant m�lange");
//		p.afficherCartes();

		p.melangerCartes();

//		System.out.println("Apr�s m�lange");
//		p.afficherCartes();

		return p;
	}

	/**
	 * Initialise un tableau de joueur pour la partie en fonction d'une liste de nom de joueur.
	 * 
	 * @return le tableau de joueur pr�t � jouer � 6-qui-prend
	 */
	private static Joueur[] initialiserJoueur() {

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
	public static List<String> lireNomJoueur() {

		final String nomFichierJoueur = "config.txt";
		List<String> listeNomJoueurs = new ArrayList<String>();
		try {

			Scanner in = new Scanner(new FileInputStream(nomFichierJoueur));
			while (in.hasNextLine()) {
				listeNomJoueurs.add(in.nextLine());
//				nb_joueur++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Impossible d'ouvrir le fichier " + nomFichierJoueur +  " contenant les noms des joueurs.");
			System.out.println("La partie ne peux donc pas commencer...");
			throw new RuntimeException(e);
		}
		
		return listeNomJoueurs;
	}

	
}
