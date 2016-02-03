package affichage;

import java.util.ArrayList;

import traitement.Ascenseur;
import traitement.Controleur;
import traitement.Requete;

public class VueRequete implements Vue{

	ArrayList<ArrayList<Requete>> requeteParEtage = new ArrayList<ArrayList<Requete>>();

	//Vue{
	
	@Override
	public void actualiser()
	{
	}
	
	public void affichage ()
	{
		for (int i = 0; i < Controleur.getInstance().getAscenseurs().get(0).getNombreEtage(); ++i)
		{
			requeteParEtage.add(new ArrayList<Requete>());
		} // on initialise le tableau de tableau de requete
		for (Ascenseur as : Controleur.getInstance().getAscenseurs())
		{		
			for (Requete req : as.getRequetes())
			{
				requeteParEtage.get(req.getEtageDeLaRequete()).add(req);
				
			}// on ajoute la requete a l indice corespondant 
		}
		
		for (int i = 0; i < requeteParEtage.size(); ++i)
		{
			for (int j = 0; j < requeteParEtage.get(i).size(); ++j)
			{
				System.out.print("Etage : " + i + " ");
				System.out.println(requeteParEtage.get(i).get(j).toString() + "; ");
			}// on affiche les etages suivie des types de requete
			
		}
		System.out.println();
	}
	
	// }Vue	

}
