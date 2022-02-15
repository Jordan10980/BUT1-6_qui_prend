import java.util.ArrayList;
import java.util.Arrays;

public class Joueur {
	private String nom;
	private int numero;
	private static int numerocpt = 0;
	private static int[] cartes = new int [10];
	
	public Joueur() {
		this.nom = nom;
	    this.numero = numerocpt++;
	    this.cartes = cartes;
	}
	
	public Joueur(String nom_joueur) {
		this.nom = nom_joueur;
		this.numero = numerocpt++;
		this.cartes = cartes;
		
	}

	public String toString() {
		return "Joueur " + numero + "[nom=" + nom + "]" +" Cartes : "+ Arrays.toString(cartes);
	}
	
	public String getNom(){
		return nom;
	}
	
	public void setCartes(int valeurCarte, int indice) {
		cartes[indice] = valeurCarte;	
	}
	
	public int getCartes(int indiceCartes) {
		return cartes[indiceCartes];
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void afficherCartes() {
		System.out.println(cartes);
	}
}
