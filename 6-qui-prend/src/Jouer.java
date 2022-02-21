import java.util.ArrayList;
import java.util.List;

public class Jouer {
	private int numeroTour;
	private static int numeroJoueur = 0;
	private static int nbJoueur;
	private List<String> listeNomJoueurs = new ArrayList<String>();
	private List<Joueur> listeJoueurs = new ArrayList<Joueur>();
	Joueur[] joueur = new Joueur[nbJoueur];
	
	public Jouer (int nombreJoueur){
		this.numeroTour = numeroTour;
		this.nbJoueur = nombreJoueur;

	}
	
	public void setJoueur(Joueur joueurs) {
		
		joueur[0] = new Joueur(joueur[0].getNom());
		listeJoueurs.add(joueur[0]);
		
		System.out.println(joueur[0]);
		
	}
	
	public void setNomJoueurs (String nom) {
		listeNomJoueurs.add(nom);
	}
	
	public void choixCartes() {
		for(int i = 0; i < nbJoueur; i++) {
			System.out.println("A " + listeNomJoueurs.get(numeroJoueur) +" de jouer.");
			System.out.println("<pause>");
			numeroJoueur++;
		}
		
	}
	

	@Override
	public String toString() {
		return "Jouer [numeroTour=" + numeroTour + ", numeroJoueur=" + numeroJoueur + ", nombre de Joueurs="+nbJoueur+ ", Joueurs :"+listeNomJoueurs + "]";
	}
	
	
}
