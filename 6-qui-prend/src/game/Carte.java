package game;
public class Carte implements Comparable {
	private int numero;
	private int nbTete;
	private static int numerocpt = 1;
	
	public Carte() {
		this.numero = numerocpt++;
		this.nbTete = initNbTete(this.numero);
	}
	
	private int initNbTete(int numero) {
		
		if(taille(numero)> 1 && String.valueOf(numero).substring(taille(numero)-1).equals(String.valueOf(5)) && String.valueOf(numero).substring(taille(numero)-2,1).equals(String.valueOf(5)))
			return 7;
		
		if(String.valueOf(numero).substring(taille(numero)-1).equals(String.valueOf(5))) 
			return 2;
		
		if(String.valueOf(numero).substring(taille(numero)-1).equals(String.valueOf(0))) 
			return 3;
		
		if(taille(numero)> 1 && String.valueOf(numero).substring(taille(numero)-1).equals(String.valueOf(numero).substring(taille(numero)-2,1))) 
			return 5;
		else 
			return 1;
	}

	public int taille(int numero) {
		return Integer.toString(numero).length();
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getNbTete() {
		return nbTete;
	}

	@Override
	public String toString() {
		if (nbTete > 1) {
			return String.valueOf(numero) + " (" + nbTete +")";
		}
		else {
			return String.valueOf(numero) ;
		}
	}
	
	@Override
	public int compareTo(Object o) {
		return this.getNumero() - ((Carte) o).getNumero();
	}
}
