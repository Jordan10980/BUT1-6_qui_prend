package appli;
import game.Partie;

/**
 * 
 * @author iPrisc & Jordan10980
 *
 */
public class Application {

	/**
	 * Méthode principale permettant de créer et  lancer une partie de 6-qui-prend
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		try {
			// Création d'une partie
			Partie partie = new Partie();
			
			// Démarrage du jeu....
			partie.demarrer();
			
		} catch (Exception e) {
			System.out.println("La partie a du se terminer prématurément du à l'erreur suivante : " + e.getMessage());
		}

		// possibilité de Jouer plusieurs manches en accumulant les pts de chaque manche.
		
		
	}

}
