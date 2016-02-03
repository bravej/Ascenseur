package execution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import traitement.Ascenseur;
import traitement.ChoixMouvementMinimum;
import traitement.Constante;
import traitement.Controleur;
import traitement.EtatAscenseur;
import traitement.IAscenseur;
import traitement.OptionMusique;
import traitement.OptionVitesse;
import traitement.RequeteInterne;
import traitement.TriRequete;
import traitement.TriRequeteOptimise;
import affichage.Interaction1;
import affichage.Vue;
import affichage.VueEnCoupe;
import affichage.VueInteractiveExterne;
import affichage.VueInteractiveInterne;
import affichage.VueRequete;

public class Libre {

	private Vue vueCoupe = new VueEnCoupe();
	static Vue vueInteractive;
	static Vue vueRequete = new VueRequete();
	static int nombreAscenseur;
	static BufferedReader bufferReader = new BufferedReader(
			new InputStreamReader(System.in));
	private ArrayList<IAscenseur> mesAscenseurs = new ArrayList<IAscenseur>();
	static TriRequete triReq = new TriRequeteOptimise();

	public Libre(ArrayList<IAscenseur> mesAscenseurs) throws IOException {
		this.mesAscenseurs = mesAscenseurs;
	}

	public static void trier(Ascenseur ascenseur) {
		triReq.trier(ascenseur); // appel de l'algo de tri des requetes de
									// l'ascenseur
	}

	public static void changerTri(TriRequete typeTri) {
		triReq = typeTri;
	}

	public void creerRequeteExterne(int etageRequeteExterne, String direction)
			throws IOException {

		/*******************************************************************
		 * Cette fonction va permettre d'ajouter une requete externe. Pour *
		 * cela on fait un appel a choisirAscenseur() qui va derterminer *
		 * l'ascenseur pour cette requete *
		 ******************************************************************/

		vueInteractive = new VueInteractiveExterne();
		vueInteractive.affichage();
		System.out.println("Entrer Etage de la requete : ");
		etageRequeteExterne = Integer.parseInt((bufferReader.readLine()));

		/***********************************************************************
		 * condition pour ne pas avoir l'etage de la requete externe superieur *
		 * au nombre d'etage ou inferieur a 0 *
		 ***********************************************************************/

		if (etageRequeteExterne <= Controleur.getInstance().getAscenseurs()
				.get(0).getNombreEtage() - 1
				&& etageRequeteExterne >= 0) {
			System.out.println("Entrer direction : ");
			direction = bufferReader.readLine();
			if (direction.equals("haut") || direction.equals("bas")) {

				Controleur.getInstance().creerRequeteExterne(
						etageRequeteExterne, direction);
				Controleur.getInstance().changerCritereChoix(
						new ChoixMouvementMinimum());
				Controleur.getInstance().choisirAscenseur();

				/*******************************************************
				 * on effectue un nouveau tri pour tout les ascenseurs *
				 *******************************************************/

				for (Ascenseur as : Controleur.getInstance().getAscenseurs()) {
					trier(as);
				}

			} else {
				System.out.println("Direction errone");
			}
		}

		else {
			System.out.println("Etage inconnu");
		}

	}

	public void bloquerOuDebloquer(EtatAscenseur etatSauve, int numeroAscenseur)
			throws NumberFormatException, IOException {
		/*************************************************************************
		 * Si l'utilisateur appuie une premiere fois pour un ascenseur choisi,
		 * on le bloque puis s'il appuie une deuxieme fois pour le meme
		 * ascenseur on le debloque grace a la sauvegarde de l'etat *
		 *************************************************************************/

		System.out.println("Quel ascenseur voulez vous bloquer/debloquer ? ");
		numeroAscenseur = Integer.parseInt((bufferReader.readLine()));
		if (Controleur.getInstance().getAscenseurs().get(numeroAscenseur - 1)
				.getEtat() != Constante.KBloquer()) {
			etatSauve.addMemento(Controleur.getInstance().getAscenseurs()
					.get(numeroAscenseur - 1).sauverMemento());
			Controleur.getInstance().getAscenseurs().get(numeroAscenseur - 1)
					.bloquer();

		} else {
			Controleur.getInstance().getAscenseurs().get(numeroAscenseur - 1)
					.debloquer(etatSauve.getMemento());

		}
	}

	public void creerRequeteInterne(int etageRequeteInterne, int numeroAscenseur)
			throws NumberFormatException, IOException {
		/******************************************************************
		 * Cette fonction va permetre d'ajouter une requete interne a un
		 * ascenseur que l'utilisateur va determiner. On trie ensuite grace a
		 * l'algo de tri
		 *****************************************************************/

		vueInteractive = new VueInteractiveInterne();
		vueInteractive.affichage();
		System.out.println();
		System.out.println("Entrer Etage de la requete : ");
		etageRequeteInterne = Integer.parseInt((bufferReader.readLine()));

		/********************************************************************
		 * condition pour pas avoir l'etage de la requete externe superieur au
		 * nombre d'etage ou inferieur a 0
		 * ******************************************************************/

		if (etageRequeteInterne <= Controleur.getInstance().getAscenseurs()
				.get(0).getNombreEtage() - 1
				&& etageRequeteInterne >= 0) {
			System.out.println("Le numero de l'ascenceur : ");
			numeroAscenseur = Integer.parseInt((bufferReader.readLine()));
			if (numeroAscenseur <= nombreAscenseur) {
				Controleur
						.getInstance()
						.getAscenseurs()
						.get(numeroAscenseur - 1)
						.ajouterRequete(new RequeteInterne(etageRequeteInterne));
				triReq.trier(Controleur.getInstance().getAscenseurs()
						.get(numeroAscenseur - 1));

			} else {
				System.out.println("Numero ascenseur inconnu");
			}
		} else {
			System.out.println("Etage errone");
		}
	}

	public void execIter() throws IOException {

		/**********************************************************************
		 * Dans cette fonction, la boucle principale va executer les iterations
		 * du programme, et donc a chaqu'une d'entre elles, on demande a
		 * l'utilisateur de faire un choix, puis, suivant le choix, on appelle
		 * les fonctions adequates
		 **********************************************************************/

		/**********************************************************************
		 * Declaration et initialisation des variables avant la boucle afin de
		 * pas les redeclarer a chaque iteration.
		 *********************************************************************/

		/*****************************************************************
		 * etatSauve est une variable qui permet de sauver l'etat avant de
		 * bloquer l'ascenseur puis de la restituer lors de l'appel de debloquer
		 ****************************************************************/

		String choix = "";
		int etageRequeteExterne = 0;
		int etageRequeteInterne = 0;
		int numeroAscenseur = 0;
		EtatAscenseur etatSauve = new EtatAscenseur();
		String direction = null;
		vueCoupe.affichage();

		while (!choix.equals("Q")) {
			System.out
					.println("Appuyez sur E pour ajouter une requete externe : ");
			System.out
					.println("Appuyez sur I pour ajouter une requete interne : ");
			System.out.println("Appuyez sur A pour faire une iteration : ");
			System.out.println("Appuyez sur R pour afficher les requetes : ");
			System.out.println("Appuyez sur B pour bloquer l'ascenseur : ");
			System.out.println("Appuyez sur Q pour quitter : ");

			choix = bufferReader.readLine();

			switch (choix.toUpperCase()) {
			case "E": {
				creerRequeteExterne(etageRequeteExterne, direction);
				break;
			}

			case "I": {
				creerRequeteInterne(etageRequeteInterne, numeroAscenseur);
				break;
			}

			case "A": {
				Controleur.getInstance().choisirAscenseur();
				for (int i = 0; i < mesAscenseurs.size(); ++i) {
					mesAscenseurs.get(i).action();
					System.out.println();
				}
				Controleur.getInstance().choisirAscenseur();
				vueCoupe.affichage(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!
				break;
			}

			case "R": {
				vueRequete.affichage();
				break;
			}

			case "B": {
				bloquerOuDebloquer(etatSauve, numeroAscenseur);
				break;
			}
			case "Q": {
				System.out.println("Au revoir");
				break;
			}
			default: {
				System.out.println("Entrer errone");
			}
			}
		}
	}

	public void initialisation() throws IOException {
		/********************************************************
		 * Debut du programme. On rentre dans une boucle qui se termine
		 * seulement si le programme n'a pas genere d'exception. Dans cette
		 * partie on initialise les ascenseurs (avec des options ou pas) on
		 * demande egalement a l'utilisateur de rentrer le nombre d'etages et le
		 * nombre d'ascenseurs puis on appelle la fonction dans laquelle on
		 * effectue les iterations. L'utilisateur a egalement la possibilite de
		 * choisir entre 2 affichages (IHM ou console) *
		 ********************************************************/
		System.out.println("Veuillez choisir l'affichage : ");
		System.out.println("C pour l'affichage console");
		System.out.println("I pour l'affichage de l'IHM");
		String choixAffichage = bufferReader.readLine();

		switch (choixAffichage.toUpperCase()) {

		case "C":
			System.out.println("Veuillez saisir le nombre d'etage : ");
			int nombreEtage = Integer.parseInt((bufferReader.readLine()));

			System.out.println("Veuillez saisir le nombre d'ascenseur : ");
			nombreAscenseur = Integer.parseInt((bufferReader.readLine()));
			System.out.println();

			String choixOption = "";
			for (int i = 0; i < nombreAscenseur; ++i) {
				System.out
						.println("Entrez V pour un ascenseur avec une vitesse accrue");
				System.out
						.println("Entrez M pour un ascenseur avec une musique de fond");
				System.out
						.println("Entrez N pour un ascenseur normal sans option");
				choixOption = bufferReader.readLine();

				switch (choixOption.toUpperCase()) {
				case "M": {
					mesAscenseurs.add(new OptionMusique(new Ascenseur(
							nombreEtage)));
					break;
				}

				case "V": {
					mesAscenseurs.add(new OptionVitesse(new Ascenseur(
							nombreEtage)));
					break;
				}

				case "N": {
					mesAscenseurs.add(new Ascenseur(nombreEtage));
					break;
				}
				default: {
					System.out
							.println("Entrer errone on creer un ascenseur sans option");
					mesAscenseurs.add(new Ascenseur(nombreEtage));
				}
				}
				mesAscenseurs.get(i).enregisterObservateur(vueCoupe);
			}
			vueCoupe.actualiser();
			execIter();
			break;
		case "I":
			new Interaction1();
			break;
		default:
			System.out.println("Entrer errone");
		}
	}
}
