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
import traitement.Controleur;
import traitement.Requete;
import traitement.RequeteInterne;
import traitement.TriRequete;
import traitement.TriRequeteOptimise;

public class Interaction2 extends JFrame implements ActionListener {

	private int nombreAscenseur;
	private int nombreEtage;

	private AffichageVC affich;
	private AffichageVR requetes = new AffichageVR();

	private JButton iteration = new JButton("Iteration");

	private JTextField externeEtage = new JTextField("etage");
	private JTextField externeDir = new JTextField("direction");
	private JButton externe = new JButton("Creer Requete Externe");

	private JTextField interneEtage = new JTextField("etage");
	private JTextField interneAsc = new JTextField("ascenseur");
	private JButton interne = new JButton("Creer Requete Interne");

	private static TriRequete triReq = new TriRequeteOptimise();

	public Interaction2(int nombreAscenseur, int nombreEtage) {

		this.nombreAscenseur = nombreAscenseur;
		this.nombreEtage = nombreEtage;

		this.affich = new AffichageVC(nombreAscenseur * 60, nombreEtage * 70);

		this.setTitle("IHM interface");
		this.setSize(400, 125);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(50, 400);
		// this.setLocationRelativeTo(null);

		JPanel l1 = new JPanel();
		l1.setLayout(new BoxLayout(l1, BoxLayout.LINE_AXIS));
		l1.add(iteration);
		iteration.addActionListener(this);

		JPanel l2 = new JPanel();
		l2.setLayout(new BoxLayout(l2, BoxLayout.LINE_AXIS));
		l2.add(externeEtage);
		l2.add(externeDir);
		l2.add(externe);
		externe.addActionListener(this);

		JPanel l3 = new JPanel();
		l3.setLayout(new BoxLayout(l3, BoxLayout.LINE_AXIS));
		l3.add(interneEtage);
		l3.add(interneAsc);
		l3.add(interne);
		interne.addActionListener(this);

		Box box = Box.createVerticalBox();
		box.add(l1);
		box.add(l2);
		box.add(l3);

		this.getContentPane().add(box);
		this.setVisible(true);
	}

	public static void trier(Ascenseur ascenseur) {
		triReq.trier(ascenseur); // appel de l'algo de sélection d'ascenseur
	}

	public static void changerTri(TriRequete typeTri) {
		triReq = typeTri;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == iteration) {
			affich.refresh();
			requetes.refresh();
		}

		if (e.getSource() == externe) {
			try {
				int etageRequeteExterne = Integer.parseInt(externeEtage
						.getText());
				String direction = externeDir.getText();

				if (etageRequeteExterne <= Controleur.getInstance()
						.getAscenseurs().get(0).getNombreEtage()
						&& etageRequeteExterne >= 0) {
					if (direction.equals("haut") || direction.equals("bas")) {
						Controleur.getInstance().creerRequeteExterne(
								etageRequeteExterne, direction);
						Controleur.getInstance().choisirAscenseur();
						for (Ascenseur as : Controleur.getInstance()
								.getAscenseurs()) {
							trier(as);
						}// on effectue un nouveau tri
					} else {
						PopUp msg = new PopUp("Direction errone");
					}
				} else {
					PopUp msg = new PopUp("Etage inconnu");
				}
			} catch (NumberFormatException ex) {
				PopUp msg = new PopUp("Entrez seulement des nombres");
			}
		}

		if (e.getSource() == interne) {
			try {
				int etageRequeteInterne = Integer.parseInt(interneEtage
						.getText());
				int numeroAscenseur = Integer.parseInt(interneAsc.getText());

				if (etageRequeteInterne <= Controleur.getInstance()
						.getAscenseurs().get(0).getNombreEtage()
						&& etageRequeteInterne >= 0) {
					if (numeroAscenseur <= nombreAscenseur) {
						Controleur
								.getInstance()
								.getAscenseurs()
								.get(numeroAscenseur - 1)
								.ajouterRequete(
										new RequeteInterne(etageRequeteInterne));
						trier(Controleur.getInstance().getAscenseurs()
								.get(numeroAscenseur - 1));

					} else {
						PopUp msg = new PopUp("Numero ascenseur inconnu");
					}
				} else {
					PopUp msg = new PopUp("Etage errone");
				}
			} catch (NumberFormatException ex) {
				PopUp msg = new PopUp("Entrez seulement des nombres");
			}

		}
	}
}