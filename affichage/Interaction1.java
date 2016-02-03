package affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import traitement.Ascenseur;
import traitement.EtatAscenseur;

public class Interaction1 extends JFrame implements ActionListener{
	
	
	private JTextField etages = new JTextField("nombre d'etages");
	private JTextField ascenseurs = new JTextField("nombre d'ascenceurs");
	private JButton go = new JButton("go");

  public Interaction1(){                

    this.setTitle("IHM interface");
    this.setSize(400, 70);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(600, 400);
	//this.setLocationRelativeTo(null);

    
    JPanel l1 = new JPanel();
    l1.setLayout(new BoxLayout(l1, BoxLayout.LINE_AXIS));
    l1.add(etages);
    l1.add(ascenseurs);
    l1.add(go);
    go.addActionListener(this);
    
    Box box = Box.createVerticalBox();
    box.add(l1);

    
    this.getContentPane().add(box);
    this.setVisible(true);
  }

  	@Override
	public void actionPerformed(ActionEvent e) {
  		if(e.getSource() == go){
  			EtatAscenseur etatSauve = new EtatAscenseur();
			ArrayList<Ascenseur> mesAscenseurs = new ArrayList<Ascenseur>();
			try{
  			int nombreAscenseur = Integer.parseInt(ascenseurs.getText());
  			int nombreEtage= Integer.parseInt(etages.getText());
			
  			for (int i = 0; i <  nombreAscenseur; ++i){
				mesAscenseurs.add(new Ascenseur(nombreEtage));
			}
  			
  			Interaction2 inter = new Interaction2(nombreAscenseur, nombreEtage);
  			this.dispose();
			}catch(NumberFormatException ex ){
				PopUp msg = new PopUp("Entrez seulement des nombres");
			}

  		}  			
  	}
}