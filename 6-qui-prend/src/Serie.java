import java.util.ArrayList;
import java.util.List;

public class Serie {
	private static final int maxCartesSerie = 5;
//	private static Carte[] cartesSerie = new Carte[5]; 
	private int numero;
	private static int numerocpt = 1;
	private List<Carte> cartesSerie = new ArrayList(5);
	
	
	public Serie() {
		this.numero = numerocpt++;
	}
	
	public Serie(Carte c) {
		this.numero = numerocpt++;
		//this.cartesSerie = cartesSerie;
	}
	


	@Override
	public String toString() { // Affichages des cartes de la serie
		String chaine = "- s�rie n� " + numero +" : ";
		boolean first = true;
		for(Carte c : cartesSerie) {
			if(!first)
				chaine += ", ";
			else 
				first = false;
			chaine += c ;
		}
		return chaine;
	}
	
	
	public int getNumero() {
		return numero;
	}
	
//	public void setCarte(Carte c, int indice) {
//		cartesSerie.add(c);
//	}
	
	public Carte derniereCarte() {
		int i = cartesSerie.size();
		return cartesSerie.get(i-1);
	}
	
	public void ajouterCarte(Carte c) {
		cartesSerie.add(c);
	}

	public boolean estPleine() {
		return (cartesSerie.size() == 5);
	}

	public int nbTete() {
		int somme = 0;
		for(int i = 0; i < cartesSerie.size(); i++) {
			somme += cartesSerie.get(i).getNbTete();
		}
		return somme;
	}
	
	public void vider(Joueur joueur) {
		while(cartesSerie.size() != 0) {
			Carte carte = cartesSerie.remove(0);
			joueur.prendSerie(carte);
		}
	}
	
}
