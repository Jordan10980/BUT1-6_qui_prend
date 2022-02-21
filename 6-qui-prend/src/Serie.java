import java.util.ArrayList;
import java.util.Arrays;

public class Serie {
	private static final int maxCartesSerie = 5;
//	private static Carte[] cartesSerie = new Carte[5]; 
	private int numero;
	private static int numerocpt = 1;
	private ArrayList<Carte> cartesSerie = new ArrayList(5);
	
	
	public Serie() {
		this.numero = numerocpt++;
	}
	
	public Serie(Carte c) {
		this.numero = numerocpt++;
		//this.cartesSerie = cartesSerie;
	}
	


	@Override
	public String toString() {
		
		return "- série n° "+numero +" : "+ cartesSerie;
	}
	

	public String toString1() { // Affichages des cartes du joueurs
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
	
	public void setCarte(Carte c, int indice) {
		cartesSerie.add(c);
	}
}
