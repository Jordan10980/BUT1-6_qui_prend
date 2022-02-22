import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Joueur {
	private String nom;
	private int numero;
	private static int numerocpt = 1;
	private Carte[] cartes = new Carte[10];
	private static int nb_cartes_joueur = 10;
	
//	public Joueur() {
//		this.nom = nom;
//	    this.numero = numerocpt++;
//	    this.cartes = cartes;
//	    this.nb_cartes_joueur = nb_cartes_joueur;
//	}
	
	public Joueur(String nom_joueur) {
		this.nom = nom_joueur;
		this.numero = numerocpt++;
		//this.cartes = cartes;
		//this.nb_cartes_joueur = nb_cartes_joueur;
		
	}
	
	public String toString() {
		return "Joueur " + numero + "[nom=" + nom + "]" +" Cartes : "+ Arrays.toString(cartes);
	}

	public String toString1() { // Affichages des cartes du joueurs
		String chaine = "- Vos cartes : ";
		for(int i = 0; i < 9; i++) {
			chaine += cartes[i]+", ";
		}
		chaine += cartes[9];
		return chaine;
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
	
//	public void choisirCarte() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Saissisez votre choix : ");
//		String rep = sc.next();
//		int indice = carteExiste(Integer.valueOf(rep));
//		if(indice != -1) {
//			System.out.println("\n"+ cartes[indice]);
//			System.out.println(Arrays.toString(cartes));
//			supprimerCarteJoueur(indice);
//			System.out.println(Arrays.toString(cartes));
//			
//		}
//		else{
//			choisirCarte();
//		}
//
//	}
	
	public void choisirCarte() {
		int indice = -1;
		System.out.println("Saisissez votre choix : ");
		
		do {
			Scanner sc = new Scanner(System.in);
			String rep = sc.next();
			indice = carteExiste(Integer.valueOf(rep));
			if(indice != -1) {
//				System.out.println(Arrays.toString(cartes)); //Avant suppression de la carte
				supprimerCarteJoueur(indice);
//				System.out.println(Arrays.toString(cartes)); //Après suppression de la carte
				nb_cartes_joueur--;
//				System.out.println("Le joueur a maintenant " + nb_cartes_joueur + "cartes"); // nombre cartes Joueur
			}
			else{
				System.out.println("Vous n'avez pas cette carte, saisissez votre choix :  ");	
			}

			
		} while(indice == -1);
	}
	
	
	public int carteExiste(int valeur) {
		for(int i = 0; i < cartes.length; i++) {
			if(valeur == cartes[i].getNumero())
				return i;
		}
		
		return -1;
	}
	
	public void supprimerCarteJoueur(int indice) {
		cartes[indice] = null;
	}
	
}
