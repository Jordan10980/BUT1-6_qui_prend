import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Carte {
	private int numero;
	private int nbTete;
	private static int numerocpt = 1;
	
	
	
	public Carte() {
		this.numero = numerocpt++;
		this.nbTete = initNbTete(this.numero);
	}
	
	
	public int initNbTete(int numero) {
		
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
		return "Carte [numero=" + numero + ", nbTete=" + nbTete + "]";
	}
	
	
	
}
