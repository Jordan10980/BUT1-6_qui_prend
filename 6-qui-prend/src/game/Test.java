package game;
import java.util.HashMap;
import java.util.Map;

public class Test {
	Map<Integer, Integer> carte = new HashMap<>();
	private final int nb_cartes_joueurs = 10;
	private final int nb_cartes = 104;
	
	
	public Test() {
		//this.carte = carte;
	}
	
	public void initialiserValeur() {
		
		for(int i = 1; i <= nb_cartes; i++) {
			carte.put(nb_cartes-nb_cartes + i, 0);
		}
	}
	
	public void initialiserPenalite() {
		
	}

	@Override
	public String toString() {
		return "Test [carte=" + carte+ "]";
	}
	
	
}
