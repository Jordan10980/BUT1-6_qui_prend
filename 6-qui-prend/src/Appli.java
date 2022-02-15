import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Appli {
	
	private static int nb_joueur = 0;
	static ArrayList<String> liste_joueurs = new ArrayList();
	
	public static void lecture_joueur (String nom) {
		
		try {
			
			Scanner in = new Scanner(new FileInputStream(nom));
			
			while (in.hasNextLine()) {
				liste_joueurs.add(in.nextLine());
				nb_joueur++;
			}
			in.close();
		}catch(FileNotFoundException e) {
			System.out.println("Impossible d'ouvrir le fichier");
		}
		
	}

	public static void main(String[] args) {
		
			lecture_joueur("config.txt");
		    System.out.println(liste_joueurs); // Liste des joueurs
			System.out.println(liste_joueurs.get(0));
			System.out.println("Il y a "+ nb_joueur +" joueurs"); // Nombre de joueurs
			
			
			Joueur[] joueurs = new Joueur[nb_joueur];
			for(int i = 0; i < nb_joueur; i++) {
				joueurs[i] = new Joueur(liste_joueurs.get(i));
//				System.out.println(joueurs[i]); Pour vérifier que les Joueuers ont des numéros et des noms différents
			}
			
			System.out.print("Les " + nb_joueur +" joueurs sont ");
			for(int i = 0; i < nb_joueur -1; i++) {
				System.out.print(joueurs[i].getNom() +", ");
			}
			System.out.println("et "+ joueurs[nb_joueur-1].getNom() +". Merci de jouer à 6 qui prend !");
			
			System.out.println("CARTES");
			
			Carte cartes = new Carte();
			
			
			
			cartes.initialiserValeur();
			cartes.melangerCartes();
			cartes.afficherCartes();
			System.out.println("\n");
					
			
		for(int i = 0; i < nb_joueur; i++) {
			cartes.distributionCartes(joueurs[i]);
			System.out.println(joueurs[i]);
//			cartes.afficherCartes();
			System.out.println("\n");
		}
			
	}

}
