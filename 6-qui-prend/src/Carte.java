import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Carte {
	
	private static int nb_cartes = 104;
	static ArrayList<Integer> valeur = new ArrayList();
	static ArrayList<Integer> penalite = new ArrayList();
	private final int nb_cartes_joueurs = 10;
	
	public Carte() {
		this.valeur = valeur;
	}
	
	public void initialiserValeur() {
		
		for(int i = 1; i <= nb_cartes; i++) {
			valeur.add(nb_cartes-nb_cartes + i);
		}
	}
	

	public void melangerCartes() {
		Collections.shuffle(valeur);
		
	}
	
	public void afficherCartes() {
		System.out.println(valeur);
	}
	
	public void initialiserPenalite() {
		
	}
	
	public void retirerValList(int indice) {
	
	}
	
	public void distributionCartes(Joueur joueurs) {
		for(int i = 0; i < nb_cartes_joueurs; i++) {
			joueurs.setCartes(valeur.get(i), i);
		}
		SupprimerCartes();
	
			
	}
	
	public void SupprimerCartes() {
		for(int i = 0; i < nb_cartes_joueurs; i++) {
			valeur.remove(0);
		}
	}

			
	
	

	
}
