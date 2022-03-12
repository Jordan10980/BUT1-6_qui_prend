package game;
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
	private List<Carte> cartesRamassees = new ArrayList<Carte>();
	
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

	/**
	 * Affiche la main du joueur, c'est à dire l'ensemble des cartes qu'il possède dans sa main.
	 * 
	 */
	public void afficherMain() { 
		String chaine = "- Vos cartes : ";
		boolean first = true;
		for(Carte c : cartes) {
			if(!first)
				chaine += ", ";
			else 
				first = false;
			chaine += c ;
		}
		System.out.println(chaine);
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
	
	public void rammasserCarte(Carte carte) {
		cartesRamassees.add(carte);
	}

	/**
	 * Choix d'une carte par le joueur parmi les cartes de sa main.
	 * @return la carte choisie
	 */
	public Carte choisirCarte() {
		int indice = -1;
		System.out.print("Saisissez votre choix : ");
		
		
		Carte carteChoisie = null;
		do {
			Scanner sc = new Scanner(System.in);
			String rep = sc.next();
			if(isStringInteger(rep,10)== true) {
				indice = carteExiste(Integer.valueOf(rep));
				
				if(indice != -1) {
					carteChoisie = cartes.remove(indice);
					//Partie.cartes_posees.add(carteJoue);
				}
				
			}
			else{
				System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");	
			}
			
			
		} while(indice == -1);
		return carteChoisie;
	}
	
	
	public static boolean isStringInteger(String stringToCheck, int radix) {
        Scanner sc = new Scanner(stringToCheck.trim());
        if(!sc.hasNextInt(radix)) return false;
        sc.nextInt(radix);
        return !sc.hasNext();
    }
	
	private int carteExiste(int valeur) {
		for(int i = 0; i < cartes.size(); i++) {
			if(valeur == cartes.get(i).getNumero())
				return i;
		}
		
		return -1;
	}
	
	public boolean mainVide() {
		return cartes.size() == 0;
	}
	
	/**
	 * Calcule et renvoie le nombre de tête du joueur.<BR>
	 * Le calcul se base sur la liste des cartes rammasssées par le joueur depuis le début de la partie.
	 * 
	 * @return le nombre de tête du joueur
	 */
	public int getNbTete() {
		int nbTete = 0;
		for(Carte carte : cartesRamassees) {
			nbTete += carte.getNbTete();
		}
		return nbTete;
	}
}

	
