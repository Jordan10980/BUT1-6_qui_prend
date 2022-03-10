package game;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Paquet {
	
	private List<Carte> cartes = new ArrayList<Carte>();
	public final static int NB_CARTE_PAQUET = 104;
	public static int NB_CARTES_JOUEUR = 10;
	
	public Paquet() {
		for(int i = 1; i <= NB_CARTE_PAQUET; i++) {
			Carte c = new Carte();
			cartes.add(c);
		}
	}
	
	
	public int nbCarte() {
		return cartes.size();
	}
	
	public void melangerCartes() {
		Collections.shuffle(cartes);
	}
	
	public void distributionCartes(Joueur joueur) {
		for(int i = 0; i < NB_CARTES_JOUEUR; i++) {
			Carte carte = cartes.remove(0);
			joueur.prendCarte(carte);
		}
	}
	
	public void distributionSeries(Serie serie) {
		if(!serie.estPleine()) {
			serie.ajouterCarte(cartes.remove(0));
		}
	}

	public void afficherCartes() {
		System.out.println("Contenu du paquet de carte:\n" + this.cartes + "\n");

	}
	
}
