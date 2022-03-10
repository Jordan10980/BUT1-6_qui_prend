package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Paquet;

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
	public void TestPioche() {
		// Teste si la pioche enleve bien une carte au paquet
		Paquet  paquet = new Paquet();

		int nbCartePaquetAvant = paquet.nbCarte();
		paquet.pioche();
		int nbCartePaquetApres = paquet.nbCarte();
		assertTrue("Le nombre de cartes restantes dans le paquet après distribution d'une série n'est pas correct !", nbCartePaquetAvant == nbCartePaquetApres +1);
	}
	
}
