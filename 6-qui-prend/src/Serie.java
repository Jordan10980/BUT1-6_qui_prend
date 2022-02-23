import java.util.ArrayList;
import java.util.List;

public class Serie {
	private static final int maxCartesSerie = 5;
	private int numero;
	private static int numerocpt = 1;
	private List<Carte> cartesSerie = new ArrayList<Carte>();
	
	
	public Serie() {
		this.numero = numerocpt++;
	}
	
//	public Serie(Carte c) {
//		this.numero = numerocpt++;
//	}

	@Override
	public String toString() {
		
		return "- série n° "+numero +" : "+ cartesSerie;
	}
	

	public String toString1() { // Affichages des cartes de la serie
		String chaine = "- série n° " + numero +" : ";
		int i = 0;
		for(Carte c : cartesSerie) {
			chaine += cartesSerie.get(i);
			i++;
			if(cartesSerie.size() > 2) {
				
			}
		}
		return chaine;
	}
	
	
	public int getNumero() {
		return numero;
	}
	
//	public void setCarte(Carte c, int indice) {
//		cartesSerie.add(c);
//	}

	public void ajouterCarte(Carte carte) {
		cartesSerie.add(carte);
	}
}
