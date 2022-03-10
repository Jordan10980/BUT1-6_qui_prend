package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import game.Carte;
import game.Joueur;
import game.Serie;

public class JoueurTest {
	
	
	public JoueurTest() {
	}
	
	@Test
	public void TestJoueur() {
		// Teste les nom et nombre de t�te d'un joueur cr��
		final String JOHN = "John";
		Joueur john= new Joueur(JOHN);
		assertTrue("Le joueur cr�� n'a pas le nom attendu !", john.getNom().equals(JOHN));
		assertTrue("Le joueur cr�� sans avoir jouer a d�j� des t�tes de p�nalit� !", john.getNbTete() == 0);

		//  V�rifie que 2 joueurs n'ont pas le m�me num�ro
		List<Joueur> joueurs = new ArrayList<Joueur>();
		for(int i=0 ; i < 10; i++) {
			Joueur joueur= new Joueur("Joueur" + i);
			joueurs.add(joueur);
		}
		for(int i=0 ; i < 10; i++) {
			Joueur joueur= joueurs.get(i);
			
			for(int j = i+1 ; j < 10; j++) {
				Joueur autreJoueur = joueurs.get(j);
				assertTrue("Deux joueurs ont le m�me num�ro !", joueur.getNumero() != autreJoueur.getNumero());
			}
		}
			
	}

	@Test
	public void TestCarteJoueur() {
		// Teste si un joueur cr�e (avant qu'on lui distribue les cartes) n'a pas de carte cach�e "dans sa manche"
		Joueur joueuse= new Joueur("Yoko");
		assertTrue("Le joueur cr�� a d�j� des carte dans sa main avant qu'on lui distribue des cartes !", joueuse.mainVide());
		assertTrue("Le joueur cr�� a d�j� des carte cach� dans sa manche avant qu'on lui distribue des cartes !", joueuse.getNbCarteJoueur() == 0);

		// Teste si un joueur � qui ont distribue une carte la place bien dans sa main"
		Carte carte= new Carte();
		joueuse.prendCarte(carte);
		assertTrue("Le joueur ayant pris une carte a toujours sa main vide !", ! joueuse.mainVide());
		assertTrue("Le joueur cr�� ayant pris un carte n'a pas le bon nombre de carte !", joueuse.getNbCarteJoueur() == 1);
		
		// Teste si un joueur qui rammasse une carte provenant d'une s�rie a bien son nombre de t�te qui augmente"
		Carte autreCarte= new Carte();
		int nbTetejoueuseAvant = joueuse.getNbTete();
		joueuse.prendSerie(autreCarte);
		int nbTetejoueuseApres = joueuse.getNbTete();
		int nbTeteCarte = autreCarte.getNbTete();
		assertTrue("Le joueur qui ramasse une carte n'a pas son nombre de t�te qui augmente en cons�quence !", nbTetejoueuseApres ==  nbTetejoueuseAvant + nbTeteCarte);
		
	}

}
