import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public Joueur(String nomJoueur) {
		this.nom = nomJoueur;
		this.numero = numerocpt++;
	}
	

	@Override
	public String toString() {
		return "Joueur " + numero + "[nom=" + nom + "]" +" Cartes : "+ cartes;
	}

	public String toString1() { // Affichages des cartes du joueurs
		String chaine = "- Vos cartes : ";
		for(int i = 0; i < cartes.size()-1; i++) {
			chaine += cartes.get(i) +", ";
		}
		chaine += cartes.get(cartes.size());
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
	
//	public void afficherCartes() {
//		System.out.println(cartes);
//	}
	
	
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
}
