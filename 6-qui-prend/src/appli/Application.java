package appli;
import game.Partie;

/**
 * 
 * @author iPrisc & Jordan10980
 *
 */
public class Application {

	/**
	 * M�thode principale permettant de cr�er et  lancer une partie de 6-qui-prend
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		try {
			// Cr�ation d'une partie
			Partie partie = new Partie();
			
			// D�marrage du jeu....
			partie.demarrer();
			
		} catch (Exception e) {
			System.out.println("La partie a du se terminer pr�matur�ment du � l'erreur suivante : " + e.getMessage());
		}

		// possibilit� de Jouer plusieurs manches en accumulant les pts de chaque manche.
		
		
	}

}
