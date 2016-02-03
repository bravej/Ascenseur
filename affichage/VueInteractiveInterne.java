package affichage;

import traitement.Ascenseur;
import traitement.Controleur;

public class VueInteractiveInterne implements Vue{

	@Override
	public void actualiser() {
		
	} // on affiche les etages de l'ascenseur

	@Override
	public void affichage() {
		// TODO Auto-generated method stub
		for (Ascenseur as : Controleur.getInstance().getAscenseurs())
		{
			int numero = as.getIndex()+1;
			System.out.print("Bouton de l'asenceur numero : " + numero + "  ");
			for (int i = 0; i < as.getNombreEtage(); ++i)
			{
				/*****************************************
				 * Affiche 4 numeros d etage par ligne   *
				 *****************************************/
				if (i % 4 == 0)
					System.out.println();
				System.out.print(" " + i + " ");
				
			} 
			
			System.out.println();
			System.out.println("     B");
			System.out.println();
			
			/*****************************
			 * B correspond a la         *
			 * possibilitee de bloquer   *
			 * l'ascenseur               *
			 *****************************/
		}
	}
}