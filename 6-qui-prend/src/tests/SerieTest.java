package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import game.Carte;
import game.Paquet;
import game.Partie;
import game.Serie;

public class SerieTest {
	
	public SerieTest() {
	}
	
	@Test
	public void TestSeriePleine() {
		// Teste si une serie auquel ont a ajouté le nombre maximal de cartes est bien pleine
		Serie serie = new Serie();
		List<Carte> cartes = new ArrayList<Carte>();
		for(int i=0; i<Serie.MAX_SERIE_CARTE; i++) {
			Carte carte = new Carte();
			serie.ajouterCarte(carte);
			assertTrue("Le nombre de carte d'une serie ne correspond pas au nombre de carte ajoutée !", serie.nbCarte() == i+1);
		}
		assertTrue("Une serie auquel on a ajouter " + Serie.MAX_SERIE_CARTE + " n'est pas pleine !", serie.estPleine());
	}

	
	@Test
	public void TestDerniereCarte() {
		// Teste si  la dernière carte ajouté à la série est bien celle renvoyée par la méthode derniereCarte
		Serie serie = new Serie();
		Carte carte1 = new Carte();
		Carte carte2 = new Carte();
		serie.ajouterCarte(carte1);
		serie.ajouterCarte(carte2);
		assertTrue("La dernière carte de la serie est celle que l'on a jouter en premier ! " , ! carte1.equals(serie.derniereCarte()));
		assertTrue("La dernière carte de la serie n'est pas celle que l'on a jouter en dernier ! ", carte2.equals(serie.derniereCarte()));
	}
	

	@Test
	public void TestCarteEnTrop() {
		// Teste si il n'est véritablement pas possible d'ajouter une carte à une série déjà pleine
		Serie serie = new Serie();
		List<Carte> cartes = new ArrayList<Carte>();
		for(int i=0; i<Serie.MAX_SERIE_CARTE; i++) {
			Carte carte = new Carte();
			serie.ajouterCarte(carte);
		}
		int nbCarteserieAvant = serie.nbCarte();
		Carte carteEnTrop = new Carte();
		serie.ajouterCarte(carteEnTrop);
		int nbCarteserieApres = serie.nbCarte();
		
		assertTrue("Une serie déjà pleine ne peut pas recevoir de nouvelle carte !", nbCarteserieAvant == nbCarteserieApres);
	}

}
