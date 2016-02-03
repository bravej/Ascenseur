package affichage;

import traitement.Ascenseur;
import traitement.Controleur;

public class VueInteractiveExterne implements Vue {

	@Override
	public void actualiser() {
		
	}

	@Override
	public void affichage() {
		for (Ascenseur as : Controleur.getInstance().getAscenseurs())
		{
			System.out.println("haut :      -");
			System.out.println("	   ---");
			System.out.println("	 --- ---");
			System.out.println("           ---");
			System.out.println("	   ---");
			System.out.println("           ---");
			// ascii art fleche haut
			System.out.println();
			
			System.out.println("           ---");
			System.out.println("	   ---");
			System.out.println("           ---");
			System.out.println("	 --- ---");
			System.out.println("	   ---");
			System.out.println("bas  :      -");
			// ascii art fleche bas
			System.out.println();
			
			
		}
			System.out.println();
		
	}

}
