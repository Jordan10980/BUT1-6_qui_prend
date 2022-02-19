import java.util.Arrays;

public class Serie {
	private static final int maxCartesSerie = 5;
	private static Carte[] cartesSerie = new Carte[5]; 
	private int numero;
	private static int numerocpt = 1;
	
	
	public Serie() {
		this.numero = numerocpt++;
	}
	
	public Serie(Carte c) {
		this.numero = numerocpt++;
		//this.cartesSerie = cartesSerie;
	}

	@Override
	public String toString() {
		return "Serie "+numero +" "+ Arrays.toString(cartesSerie);
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setCarte(Carte c, int indice) {
		cartesSerie[indice] = c;
	}
}
