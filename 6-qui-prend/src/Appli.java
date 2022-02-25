

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author iPrisc & Jordan10980
 *
 */
public class Appli {
	

//	private static final int NB_SERIE = 4;

	/**
	 * Méthode principale permettant de lancer une partie de 6-qui-prend
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		Partie partie = new Partie();
		
		// Démarrage du jeu....
		
		
		
//		 permet aux joueurs de choisir leurs cartes
		for(int i = 0; i< partie.nbJoueur(); i++) {
			System.out.println("A " + partie.getJoueurs(i).getNom() + " de jouer.");
			Console.pause();
			for(int j = 0; j < Partie.NB_SERIE; j++) {
				System.out.println(partie.getSeries(j).toString1());
			}
			System.out.println(partie.getJoueurs(i).toString1());
			partie.getJoueurs(i).choisirCarte();
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
	 * Crée les séries de jeu en piochant des cartes du paquet de carte
	 * 
	 * @param p : le paquet de carte de la partie
	 * @return les séries créées
	 */
//	private static Serie[] creerSeries(Paquet p) {
//		Serie[] series = new Serie[NB_SERIE];
//		for (int i = 0; i < NB_SERIE; i++) {
//			series[i] = new Serie();
//			p.distributionSeries(series[i]);
////			System.out.println(series[i]);
//		}
//
//		return series;
//	}

	/**
	 * Récupère un nouveau paquet de carte complet pour jouer à 6-qui-prend<BR>
	 * Le paquet de carte récupéré est déjà mélangé et prêt à être distribué.
	 * 
	 * @return le paquet de carte prêt à être distribué
	 */
//	private static Paquet recupererNouveauPaquet() {
//
//		Paquet p = new Paquet();
//		p.melangerCartes();
//		
//		return p;
//	}

	/**
	 * Initialise un tableau de joueur pour la partie en fonction d'une liste de nom de joueur.
	 * 
	 * @return le tableau de joueur prêt à jouer à 6-qui-prend
	 */
//	private static Joueur[] initialiserJoueur() {
//
//		List<String> listeNomJoueurs = lireNomJoueur();
////--		System.out.println(listeNomJoueurs); // Liste des joueurs
////	--	System.out.println(listeNomJoueurs.get(0));
////	--	System.out.println("Il y a " + listeNomJoueurs.size() + " joueurs"); // Nombre de joueurs
//
//		Joueur[] joueurs = new Joueur[listeNomJoueurs.size() ];
//		for (int i = 0; i < listeNomJoueurs.size() ; i++) {
//			joueurs[i] = new Joueur(listeNomJoueurs.get(i));
////	--		System.out.println(joueurs[i]); Pour vérifier que les Joueuers ont des numéros et des noms différents
//		}
//
//		System.out.print("Les " + joueurs.length  + " joueurs sont ");
//		for (int i = 0; i < joueurs.length  - 1; i++) {
//			System.out.print(joueurs[i].getNom() + ", ");
//		}
//		System.out.println("et " + joueurs[joueurs.length  - 1].getNom() + ". Merci de jouer à 6 qui prend !");
//
//		return joueurs;
//	}

	
	/**
	 * Lit les noms des joueurs de la partie depuis le fichier de nom de joueur.
	 * 
	 * @return la liste des nom de joueur
	 */
//	public static List<String> lireNomJoueur() {
//
//		final String nomFichierJoueur = "config.txt";
//		List<String> listeNomJoueurs = new ArrayList<String>();
//		try {
//
//			Scanner in = new Scanner(new FileInputStream(nomFichierJoueur));
//			while (in.hasNextLine()) {
//				listeNomJoueurs.add(in.nextLine());
//			}
//			in.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("Impossible d'ouvrir le fichier " + nomFichierJoueur +  " contenant les noms des joueurs.");
//			System.out.println("La partie ne peux donc pas commencer...");
//			throw new RuntimeException(e);
//		}
//		
//		return listeNomJoueurs;
//	}
	


	
}
