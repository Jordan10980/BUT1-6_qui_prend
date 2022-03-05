import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Paquet {
	
	private List<Carte> cartes = new ArrayList<Carte>();
	private final static int nbCartes = 104;
	private static int nb_cartes_joueur = 10;
	
	public Paquet() {
		for(int i = 1; i <= nbCartes; i++) {
			Carte c = new Carte();
			cartes.add(c);
		}
	}
	
	public List<Carte> getCartes(){
		return cartes;
	}
	
	public void melangerCartes() {
		Collections.shuffle(cartes);
	}
	
	public void distributionCartes(Joueur joueur) {
		for(int i = 0; i < nb_cartes_joueur; i++) {
			Carte carte = cartes.remove(0);
			joueur.prendCarte(carte);
		}
	}
	
	public void distributionSeries(Serie serie) {
		serie.ajouterCarte(cartes.remove(0));
	}

	public void afficherCartes() {
		System.out.println("Contenu du paquet de carte:\n" + this.cartes + "\n");

	}
	
}
