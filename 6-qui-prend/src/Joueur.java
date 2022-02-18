import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Joueur {
	private String nom;
	private int numero;
	private static int numerocpt = 1;
	private Carte[] cartes = new Carte[10];
	private static int nb_cartes_joueur = 10;
	
	public Joueur() {
		this.nom = nom;
	    this.numero = numerocpt++;
	    this.cartes = cartes;
	    this.nb_cartes_joueur = nb_cartes_joueur;
	}
	
	public Joueur(String nom_joueur) {
		this.nom = nom_joueur;
		this.numero = numerocpt++;
		this.cartes = cartes;
		this.nb_cartes_joueur = nb_cartes_joueur;
		
	}

	public String toString() {
		return "Joueur " + numero + "[nom=" + nom + "]" +" Cartes : "+ Arrays.toString(cartes);
	}
	
	public String getNom(){
		return nom;
	}
	
	public void setCarte(Carte c, int indice) {
		cartes[indice] = c;
	}
	
//	public int getCarte(int indiceCartes) {
//		return cartes[indiceCartes];
//	}
	
	public int getNumero() {
		return numero;
	}
	
	public void afficherCartes() {
		System.out.println(cartes);
	}
	
	public int getNbCarteJoueur() {
		return nb_cartes_joueur;
	}
}
