

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author iPrisc & Jordan10980
 *
 */
public class Appli {

	/**
	 * M�thode principale permettant de cr�er et  lancer une partie de 6-qui-prend
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		// Cr�ation d'une partie
		Partie partie = new Partie();
		
		// D�marrage du jeu....
		partie.demarrer();
		
		
	}

}
