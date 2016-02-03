package affichage;

import traitement.Ascenseur;
import traitement.Constante;
import traitement.Controleur;
import traitement.Requete;
import traitement.RequeteInterne;

public class VueEnCoupe implements Vue{

	private static String[][] grille;

	
	
	
	/******************************************
	 * La fonction actualiser est mise a jour * 
	 * apres chaque appele de action()        *
	 *****************************************/
	
	@Override
	public void actualiser() {
		int nbAscenseur = Controleur.getInstance().getAscenseurs().size();
		int etage = Controleur.getInstance().getAscenseurs().get(0).getNombreEtage();

		grille = new String[etage][nbAscenseur];
		for (int i = 0; i < etage; ++i)
		{
			for (int j = 0; j < nbAscenseur; ++j)
				grille[i][j] = "|";
		}
		int nbas = 0;
	
		/*****************************************
		 * On place les lettres correspondant aux*
		 * differents etat de l ascenseur, aux   *
		 * etages correspondants                 *
		 *****************************************/
		
		for (Ascenseur as : Controleur.getInstance().getAscenseurs()) {
			if (as.getEtat() == Constante.KFerme())
				grille[etage - 1 - as.getEtageCourant()][nbas] = "F";
			else if (as.getEtat() == Constante.KMonte())
				grille[etage - 1 - as.getEtageCourant()][nbas] = "M";
			else if (as.getEtat() == Constante.KOuvert())
				grille[etage - 1 - as.getEtageCourant()][nbas] = "O";
			else if (as.getEtat() == Constante.KDescend())
				grille[etage - 1 - as.getEtageCourant()][nbas] = "D";
			else
				grille[etage - 1  - as.getEtageCourant()][nbas] = "B";

			++nbas;
		}

	}

	
	public void affichage() {
			
		afficherGrilleCourante();
		afficherBoutonAllume ();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	private void afficherGrilleCourante()
	{
		int nbAscenseur = Controleur.getInstance().getAscenseurs().size();
		int etage = Controleur.getInstance().getAscenseurs().get(0).getNombreEtage();
		for (int i = 0; i < etage; ++i) {
			for (int j = 0; j < nbAscenseur; ++j)
				System.out.print(grille[i][j]);
			System.out.println();
		}
		System.out.println();
		System.out.println();
			
	}

	private void afficherBoutonAllume() {
		
		for(Ascenseur as : Controleur.getInstance().getAscenseurs())
		{
			for(Requete req : as.getRequetes())
			{
			

					System.out.print("Ascenseur numero : "); 
					System.out.print(as.getIndex()+1);
					System.out.println(" Bouton allume " + req);		
			}
		}
		System.out.println();
	}
}
