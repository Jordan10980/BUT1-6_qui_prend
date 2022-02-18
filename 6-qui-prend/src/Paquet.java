import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Paquet {
	
	private ArrayList<Carte> cartes = new ArrayList<Carte>();
	private final static int nbCartes = 104;
	private static int nb_cartes_joueur = 10;
	
	public Paquet() {
		for(int i = 1; i <= nbCartes; i++) {
			Carte c = new Carte();
			cartes.add(c);
		}
	}
	
	public ArrayList<Carte> getCartes(){
		return cartes;
	}
	
	public void melangerCartes() {
		Collections.shuffle(cartes);
	}
	
	public void distributionCartes(Joueur joueur) {
		for(int i = 0; i < nb_cartes_joueur; i++) {
			joueur.setCarte(cartes.get(i), i);
			
		}
		SupprimerCartes();
		
	}
	
	public void SupprimerCartes() {
		for(int i = 0; i < nb_cartes_joueur; i++) {
			cartes.remove(0);
		}
	}
	
	public void distributionSeries(Serie serie) {
		serie.setCarte(cartes.get(0), 0);
		supprimerCarteSerie();
	}
	
	public void supprimerCarteSerie() {
		cartes.remove(0);
	}
	

	
}
