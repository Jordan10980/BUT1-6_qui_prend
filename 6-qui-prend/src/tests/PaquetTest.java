package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Joueur;
import game.Paquet;
import game.Serie;

public class PaquetTest {
	
	public PaquetTest() {
	}
	
	@Test
	public void TestPaquetComplet() {
		// Teste si un paquet créé contient bien le bon nombre de carte initialement
		Paquet  paquet = new Paquet();
		assertTrue("Le nombre de carte intiale dans le paquet n'est pas correct !", paquet.nbCarte() == Paquet.NB_CARTE_PAQUET);
	}

	@Test
	public void TestDistributionCarteJoueur() {
		// Teste si la distribution des cartes à un joueur se fait correctement
		Paquet  paquet = new Paquet();
		Joueur joueur = new Joueur("Ringo");

		int nbCartePaquetAvant = paquet.nbCarte();
		paquet.distributionCartes(joueur);
		int nbCartePaquetApres = paquet.nbCarte();
		assertTrue("Le nombre de cartes restantes dans le paquet après distribution d'un joueur n'est pas correct !", nbCartePaquetAvant == nbCartePaquetApres + paquet.NB_CARTES_JOUEUR);
	}

	@Test
	public void TestDistributionCarteSerie() {
		// Teste si la distribution des cartes à une série se fait correctement
		Paquet  paquet = new Paquet();
		Serie serie = new Serie();

		int nbCartePaquetAvant = paquet.nbCarte();
		paquet.distributionSeries(serie);
		int nbCartePaquetApres = paquet.nbCarte();
		assertTrue("Le nombre de cartes restantes dans le paquet après distribution d'une série n'est pas correct !", nbCartePaquetAvant == nbCartePaquetApres +1);
	}
	
}
