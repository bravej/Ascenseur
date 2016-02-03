package affichage;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

import traitement.Ascenseur;
import traitement.Controleur;
import traitement.Requete;

public class AffichageVR extends JFrame {

	private String message;
	private PanneauVR vueRequete = new PanneauVR();

  public AffichageVR() {

		this.setTitle("Requetes");
		this.setSize(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(1500, 400);
		//this.setLocationRelativeTo(null);
		
		this.setContentPane(vueRequete);	    
		this.setVisible(true);
  }
  
  public void refresh(){
	  vueRequete.repaint();	  
  }
}