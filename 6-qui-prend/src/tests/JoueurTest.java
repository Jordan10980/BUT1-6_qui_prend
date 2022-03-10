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
		// Teste les nom et nombre de tête d'un joueur créé
		final String JOHN = "John";
		Joueur john= new Joueur(JOHN);
		assertTrue("Le joueur créé n'a pas le nom attendu !", john.getNom().equals(JOHN));
		assertTrue("Le joueur créé sans avoir jouer a déjà des têtes de pénalité !", john.getNbTete() == 0);

		//  Vérifie que 2 joueurs n'ont pas le même numéro
		List<Joueur> joueurs = new ArrayList<Joueur>();
		for(int i=0 ; i < 10; i++) {
			Joueur joueur= new Joueur("Joueur" + i);
			joueurs.add(joueur);
		}
		for(int i=0 ; i < 10; i++) {
			Joueur joueur= joueurs.get(i);
			
			for(int j = i+1 ; j < 10; j++) {
				Joueur autreJoueur = joueurs.get(j);
				assertTrue("Deux joueurs ont le même numéro !", joueur.getNumero() != autreJoueur.getNumero());
			}
		}
			
	}

	@Test
	public void TestCarteJoueur() {
		// Teste si un joueur crée (avant qu'on lui distribue les cartes) n'a pas de carte cachée "dans sa manche"
		Joueur joueuse= new Joueur("Yoko");
		assertTrue("Le joueur créé a déjà des carte dans sa main avant qu'on lui distribue des cartes !", joueuse.mainVide());
		assertTrue("Le joueur créé a déjà des carte caché dans sa manche avant qu'on lui distribue des cartes !", joueuse.getNbCarteJoueur() == 0);

		// Teste si un joueur à qui ont distribue une carte la place bien dans sa main"
		Carte carte= new Carte();
		joueuse.prendCarte(carte);
		assertTrue("Le joueur ayant pris une carte a toujours sa main vide !", ! joueuse.mainVide());
		assertTrue("Le joueur créé ayant pris un carte n'a pas le bon nombre de carte !", joueuse.getNbCarteJoueur() == 1);
		
		// Teste si un joueur qui rammasse une carte provenant d'une série a bien son nombre de tête qui augmente"
		Carte autreCarte= new Carte();
		int nbTetejoueuseAvant = joueuse.getNbTete();
		joueuse.prendSerie(autreCarte);
		int nbTetejoueuseApres = joueuse.getNbTete();
		int nbTeteCarte = autreCarte.getNbTete();
		assertTrue("Le joueur qui ramasse une carte n'a pas son nombre de tête qui augmente en conséquence !", nbTetejoueuseApres ==  nbTetejoueuseAvant + nbTeteCarte);
		
	}

}
