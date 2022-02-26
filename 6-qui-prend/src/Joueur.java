import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class qui représente un joueur du jeu 6 qui prend avec :
 * <li> son nom
 * <li> son <b>numéro</b> 
 * <li>sa main (liste de cartes qu'il a en main).
 * <br><br>
 * @author florent & jordan
 *
 */
public class Joueur {
	private String nom;
	private int numero;
	private static int numerocpt = 1;
	private List<Carte> cartes = new ArrayList<Carte>(); // la main du joueur
	
	/**
	 * Constructeur pour initialiser un joueur. <br>
	 * Son numéro est calculé à partir d'un compteur de joueur unique.
	 * @param nomJoueur : le nom du joueur à créer
	 */
	public Joueur(String nom_joueur) {
		this.nom = nom_joueur;
		this.numero = numerocpt++;
	}
	
	@Override
	public String toString() {
		return "Joueur " + numero + "[nom=" + nom + "]" +" Cartes : "+ cartes;
	}

	public String toString1() { // Affichages des cartes du joueurs
		String chaine = "- Vos cartes : ";
		for(int i = 0; i < cartes.size()-1; i++) {
			chaine += cartes.get(i)+", ";
		}
		chaine += cartes.get(cartes.size()-1);
		return chaine;
	}

	/**
	 * Retourne le nom du joueur
	 * @return le nom
	 */
	public String getNom(){
		return nom;
	}
	
	/**
	 * retourne le numéro
	 * @return le numéro
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * Retourne le nombre de carte dans la main du joueur
	 * @return 
	 */
	public int getNbCarteJoueur() {
		return cartes.size();
	}
	
	/**
	 * Rajoute une carte dans la main du joueur
	 * @param carte : carte qu'il prend
	 */
	public void prendCarte(Carte carte) {
		cartes.add(carte);
	}

	/**
	 * Choix d'une carte par le joueur parmi les cartes de sa main.
	 * @return la carte choisie
	 */
	public Carte choisirCarte() {
		int indice = -1;
		System.out.print("Saisissez votre choix : ");
		
		Carte carteChoisie=null;
		do {
			Scanner sc = new Scanner(System.in);
			String rep = sc.next();
			indice = carteExiste(Integer.valueOf(rep));
			if(indice != -1) {
				carteChoisie = cartes.remove(indice);
				//Partie.cartes_posees.add(carteJoue);
			}
			else{
				System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");	
			}
		} while(indice == -1);
		return carteChoisie;
	}
	
	
	private int carteExiste(int valeur) {
		for(int i = 0; i < cartes.size(); i++) {
			if(valeur == cartes.get(i).getNumero())
				return i;
		}
		
		return -1;
	}
	
}
