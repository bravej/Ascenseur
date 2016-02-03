package execution;

import java.util.ArrayList;

import traitement.Ascenseur;
import traitement.ChoixMouvementMinimum;
import traitement.ChoixRepartition;
import traitement.Constante;
import traitement.Controleur;
import traitement.EtatAscenseur;
import traitement.IAscenseur;
import traitement.OptionMusique;
import traitement.OptionVitesse;
import traitement.TriRequete;
import traitement.TriRequeteOptimise;
import affichage.Vue;
import affichage.VueEnCoupe;
import affichage.VueInteractiveExterne;
import affichage.VueInteractiveInterne;
import affichage.VueRequete;

public class JeuDeDonnee {

	// Donnees membres {
	private int nombreEtage = 6;
	private int nombreAscenseur = 6;
	private boolean bloque = false;
	private EtatAscenseur etatSauvegarde = new EtatAscenseur();
	private ArrayList<IAscenseur> ascenseurs = new ArrayList<IAscenseur>();
	private TriRequete triReq = new TriRequeteOptimise();
	private VueEnCoupe vueCoupe = new VueEnCoupe();

	// } Donnees membres

	public JeuDeDonnee(int choix) {
		switch (choix) {
		case 1:
			initialiserJeuDeDonnee1();
			break;
		case 2:
			initialiserJeuDeDonnee2();
			break;
		case 3:
			initialiserJeuDeDonnee3();
			break;
		case 4:

			break;

		}
	}

	/************************************************************************
	 * Le premier jeu de donnee on montre les options et la restauration de l
	 * etat, apres que * l ascenseur soit bloquer
	 ************************************************************************/
	private void initialiserJeuDeDonnee1() {

		// l'ascenseur ultime possede les 2 options
		IAscenseur ascenseurUltime = new Ascenseur(nombreEtage);
		ascenseurUltime = new OptionVitesse(new OptionMusique(ascenseurUltime));
		ascenseurs.add(ascenseurUltime);
		for (int i = 1; i < nombreAscenseur / 2; ++i) {
			ascenseurs.add(new OptionMusique(new Ascenseur(nombreEtage)));
			ascenseurs.get(i).enregisterObservateur(vueCoupe);
		}

		/**********************************************************************
		 * La moitie des ascenseurs ont l option musique et l autre la vitesse *
		 **********************************************************************/

		for (int i = nombreAscenseur / 2; i < nombreAscenseur; ++i) {
			ascenseurs.add(new OptionVitesse(new Ascenseur(nombreEtage)));
			ascenseurs.get(i).enregisterObservateur(vueCoupe);
		}

		/***************************************************************
		 * On prepare plusieurs requetes pour le jeu de donnee. Ici peut importe
		 * les requetes *
		 ***************************************************************/

		Controleur.getInstance().getAscenseurs().get(0).creerRequeteInterne(2);
		Controleur.getInstance().getAscenseurs().get(0).creerRequeteInterne(1);
		Controleur.getInstance().getAscenseurs().get(2).creerRequeteInterne(5);
		Controleur.getInstance().getAscenseurs().get(3).creerRequeteInterne(1);
		Controleur.getInstance().getAscenseurs().get(1).creerRequeteInterne(3);
		Controleur.getInstance().getAscenseurs().get(5).creerRequeteInterne(3);
		Controleur.getInstance().getAscenseurs().get(2).creerRequeteInterne(5);
		Controleur.getInstance().getAscenseurs().get(5).creerRequeteInterne(2);
		Controleur.getInstance().getAscenseurs().get(4).creerRequeteInterne(2);
		Controleur.getInstance().getAscenseurs().get(5).creerRequeteInterne(4);
		Controleur.getInstance().creerRequeteExterne(5, "haut");
		Controleur.getInstance().creerRequeteExterne(2, "bas");

		Controleur.getInstance().choisirAscenseur();

		for (IAscenseur a : ascenseurs) {
			triReq.trier(a);
		}

		bloque = true;
		iteration(13);
	}

	/*
	 * Le deuxieme jeu de donnees sert de demonstration du choix d'attribution
	 * des requetes externes par repartition equitable : l'ecart type ne sera
	 * jamais superieur a 1
	 */
	private void initialiserJeuDeDonnee2() {
		for (int i = 0; i < nombreAscenseur; ++i) {
			ascenseurs.add(new Ascenseur(nombreEtage));
			ascenseurs.get(i).enregisterObservateur(vueCoupe);
		}
		Controleur.getInstance().getAscenseurs().get(0).creerRequeteInterne(5);
		Controleur.getInstance().creerRequeteExterne(1, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(5, Constante.KBas());
		Controleur.getInstance().creerRequeteExterne(2, Constante.KBas());
		Controleur.getInstance().creerRequeteExterne(1, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(4, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(1, Constante.KBas());
		Controleur.getInstance().creerRequeteExterne(1, Constante.KBas());
		Controleur.getInstance().changerCritereChoix(new ChoixRepartition());

		Controleur.getInstance().choisirAscenseur();

		for (IAscenseur a : ascenseurs) {
			triReq.trier(a);
		}

		iteration(9);
		// iteration(13); //sans trier
	}

	/*
	 * Le troisieme jeu de donnees sert de demonstration du choix d'attribution
	 * des requetes externes pour des deplacements intelligents : si un
	 * ascenseur peut prendre au passage une requete, il le fait
	 */
	private void initialiserJeuDeDonnee3() {
		for (int i = 0; i < nombreAscenseur; ++i) {
			ascenseurs.add(new Ascenseur(nombreEtage));
			ascenseurs.get(i).enregisterObservateur(vueCoupe);
		}
		Controleur.getInstance().getAscenseurs().get(0).creerRequeteInterne(5);
		Controleur.getInstance().creerRequeteExterne(1, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(5, Constante.KBas());
		Controleur.getInstance().creerRequeteExterne(2, Constante.KBas());
		Controleur.getInstance().creerRequeteExterne(1, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(4, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(1, Constante.KBas());
		Controleur.getInstance().creerRequeteExterne(1, Constante.KBas());
		Controleur.getInstance().changerCritereChoix(
				new ChoixMouvementMinimum());

		Controleur.getInstance().choisirAscenseur();

		for (IAscenseur a : ascenseurs) {
			triReq.trier(a);
		}

		iteration(13);
	}

	/*******************************************************************
	 * partie commune a tout les jeux de donnees sauf pour le blocage *
	 *******************************************************************/
	private void iteration(int nombreIteration) {
		Vue vueInteractiveInterne = new VueInteractiveInterne();
		Vue vueInteractiveExterne = new VueInteractiveExterne();
		Vue vueRequete = new VueRequete();
		vueCoupe.actualiser();
		int cptIteration = 0;

		/********************************************
		 * on donne a nombreIteration le nombre * necessaire pour que toute les
		 * iterations * d un jeu de donnee soit afficher *
		 ********************************************/
		vueCoupe.affichage();// !!!!!!!!!!!!!!!!!!!!!!!!!!!

		while (cptIteration != nombreIteration) {
			vueInteractiveInterne.actualiser();
			vueInteractiveExterne.actualiser();
			vueRequete.actualiser();
			for (int i = 0; i < ascenseurs.size(); ++i) {
				ascenseurs.get(i).action();
				System.out.println();
			}

			/***************************************
			 * seulement pour le 1er jeu de donnee *
			 ***************************************/

			if (bloque) {
				if (cptIteration == 4) {
					etatSauvegarde.addMemento(Controleur.getInstance()
							.getAscenseurs().get(2).sauverMemento());
					Controleur.getInstance().getAscenseurs().get(2).bloquer();
				} else if (cptIteration == 7) {
					Controleur.getInstance().getAscenseurs().get(2)
							.debloquer(etatSauvegarde.getMemento());
				}
			}

			vueCoupe.affichage();// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			++cptIteration;
		}

	}
}
