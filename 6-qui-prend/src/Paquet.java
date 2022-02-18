import java.util.ArrayList;
import java.util.List;

public class Paquet {
	
	private List<Carte> cartes = new ArrayList<Carte>();
	private final static int nbCartes = 104;
	
	public Paquet() {
		for(int i = 1; i <= nbCartes; i++) {
			Carte c = new Carte();
			cartes.add(c);
		}
	}
	
	public List<Carte> getCartes(){
		return cartes;
	}
	
}
