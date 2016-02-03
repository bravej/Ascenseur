package affichage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import traitement.Ascenseur;
import traitement.Controleur;
import traitement.Requete;

public class PanneauVR extends JPanel{
	
	private String requete;
	
	 public void paintComponent(Graphics g) {
		 
		 g.setColor(Color.white);
		 g.fillRect(0, 0, this.getWidth(), this.getHeight());
		 g.setColor(Color.black);
		    
		 ArrayList<ArrayList<Requete>> requeteParEtage = new ArrayList<ArrayList<Requete>>();
			for (int i = 0; i < Controleur.getInstance().getAscenseurs().get(0).getNombreEtage(); ++i)
			{
				requeteParEtage.add(new ArrayList<Requete>());
			}
			for (Ascenseur as : Controleur.getInstance().getAscenseurs())
			{		
				for (Requete req : as.getRequetes())
				{
					try{
						requeteParEtage.get(req.getEtageDeLaRequete()).add(req);
					}catch(java.lang.IndexOutOfBoundsException e){
						PopUp msg = new PopUp("Etage inexistant. Attention, on commence à l'étage 0 !");
					}
					
				}
			}
			int cpt = 10;
			
			for (int i = 0; i < requeteParEtage.size(); ++i)
			{
				for (int j = 0; j < requeteParEtage.get(i).size(); ++j)
				{
					cpt += 20;
					g.drawString("Etage : " + i + " " + requeteParEtage.get(i).get(j).toString() + "; ",30, cpt);
				}
			}
	 }

}
