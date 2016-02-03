package affichage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import traitement.Ascenseur;
import traitement.Constante;
import traitement.Controleur;

public class PanneauVC extends JPanel {

  private int posX = -50;
  private int posY = -50;

  public void paintComponent(Graphics g) {

    g.setColor(Color.white);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    g.setColor(Color.black);

    int nbAscenseur = Controleur.getInstance().getAscenseurs().size();
	int etage = Controleur.getInstance().getAscenseurs().get(0).getNombreEtage();
	Image[][] grille = new Image[etage][nbAscenseur];
	for (int i = 0; i < etage; ++i)
		for (int j = 0; j < nbAscenseur; ++j)
			try {
				grille[i][j] = ImageIO.read(new File("Nasc.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}

	int nbas = 0;
	for (Ascenseur as : Controleur.getInstance().getAscenseurs()) {
		if (as.getEtat() == Constante.KFerme())
			try {
				grille[etage - 1 - as.getEtageCourant()][nbas] = ImageIO.read(new File("ascClose.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (as.getEtat() == Constante.KMonte())
			try {
				grille[etage - 1 - as.getEtageCourant()][nbas] = ImageIO.read(new File("ascClose.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (as.getEtat() == Constante.KOuvert())
			try {
				grille[etage - 1 - as.getEtageCourant()][nbas] = ImageIO.read(new File("ascOpen.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (as.getEtat() == Constante.KDescend())
			try {
				grille[etage - 1 - as.getEtageCourant()][nbas] = ImageIO.read(new File("ascClose.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			try {
				grille[etage - 1  - as.getEtageCourant()][nbas] = ImageIO.read(new File("ascClose.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		++nbas;
	}
	for (int i = 0; i < etage; ++i) {
		for (int j = 0; j < nbAscenseur; ++j){
			g.drawImage(grille[i][j], 50*j+20, 50*i+20, this);

		}

	}
  }

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }
}