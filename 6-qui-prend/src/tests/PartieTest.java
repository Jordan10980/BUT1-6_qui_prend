package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Joueur;
import game.Paquet;
import game.Partie;
import game.Serie;

public class PartieTest {
	
	public PartieTest() {
	}
	
	@Test
	public void TestNbJoueur() {
		
		// test avec plus de joueur que possible
		try {
			// Cr�ation d'une partie
			Partie partie = new Partie("src/tests/config_12joueurs.txt");			
			assert false : "Le nombre de joueur est sup�rieur � 10 !";
			
		} catch (Exception e) {
			//System.out.println("test avec plus de joueur que possible : OK");
		}
		
		// test avec trop peu de joueur 
		try {
			// Cr�ation d'une partie
			Partie partie = new Partie("src/tests/config_1joueur.txt");
			assert false : "Le nombre de joueur est inf�rieur � 2 !";
			
		} catch (Exception e) {
			//System.out.println("test avec trop peu de joueur : OK");
		}

		// test le nombre de joueur de la partie
		try {
			// Cr�ation d'une partie
			Partie partie = new Partie("src/tests/config_4joueurs.txt");
			assertTrue("Le nombre de joueur de la partie ne correspond pas au nombre de joueur dans le fichier !",partie.nbJoueur() == 4);
			
		} catch (Exception e) {
			assert false : "Erreur inattendue pendant les tests";
		}

	}
	

	@Test
	public void TestJoueur() {
		
		// test la bonne initialitation des joueur
		try {
			// Cr�ation d'une partie
			Partie partie = new Partie("src/tests/config_4joueurs.txt");

			for(int i=0 ; i < partie.nbJoueur(); i++) {
				Joueur joueur = partie.getJoueurs(i);

				//  teste le nombre de carte des joueurs avant le d�marrage de la partie
				assertTrue("L'un des joueurs n'a pas ses " + Partie.NB_CARTES_JOUEUR + " cartes en main avant le d�marrage de la partier",joueur.getNbCarteJoueur() ==  Partie.NB_CARTES_JOUEUR);
			}
		} catch (Exception e) {
			assert false : "Erreur inattendue pendant les tests";
		}
		
	}
	
	@Test
	public void TestSerie() {
		
		// test la bonne initialitation des series
		try {
			// Cr�ation d'une partie
			Partie partie = new Partie("src/tests/config_4joueurs.txt");
			
			Paquet paquet = partie.getPaquet();
			
			for(int i=0 ; i < Partie.NB_SERIE; i++) {
				Serie serie = partie.getSeries(i);
				
				//  v�rifie que 2 series n'ont pas le m�me num�ro
				for(int j = i+1 ; j < Partie.NB_SERIE; j++) {
					Serie autreSerie = partie.getSeries(j);
					assertTrue("Deux serie ont le m�me num�ro !", serie.getNumero() != autreSerie.getNumero());
				}

				//  teste si la s�rie a un bien une carte avant le d�marrage de la partie
				assertTrue("Une serie avant d�marrage de la partie poss�de d�j� des t�te !", serie.nbCarte() == 1 && serie.derniereCarte() != null);
				assertTrue("Une serie avant d�marrage de la partie poss�de d�j� des t�te !", serie.nbTete() > 0);
				
			}
		} catch (Exception e) {
			assert false : "Erreur inattendue pendant les tests";
		}
	}
}
