package traitement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import affichage.Vue;
import affichage.VueEnCoupe;

public class Ascenseur implements IAscenseur {

	// Donnees membres{

	private int etageCourant;
	private int nombreEtage;
	private String etat;
	private List<Requete> requetes;
	private List<Vue> observateurs;
	private int index; // emplacement de l'ascenseur dans le tableau du
						// contoleur

	// }Donnees membres

	public Ascenseur(int nombreEtage) {
		super();

		this.etageCourant = 0;
		this.nombreEtage = nombreEtage;
		this.etat = Constante.KFerme();
		this.requetes = new ArrayList<Requete>();
		this.index = Controleur.getInstance().ajouterAscenseur(this);
		this.observateurs = new ArrayList<Vue>();
	}

	public void ajouterRequete(Requete requete) {
		this.requetes.add(requete);
		supprDoublons();
	}

	public void creerRequeteInterne(int etage) {
		Requete requete = new RequeteInterne(etage);
		ajouterRequete(requete);
	}

	public void supprDoublons() {
		Iterator<Requete> iterRequete = this.getRequetes().iterator();
		while (iterRequete.hasNext()) {
			Boolean doublon = false;
			Requete requeteAVerifie = iterRequete.next();
			Iterator<Requete> iterRequeteBis = this.getRequetes().iterator();
			while (iterRequeteBis.hasNext()) {
				Requete requeteAComparer = iterRequeteBis.next();
				if ((requeteAVerifie.getEtageDeLaRequete() == requeteAComparer
						.getEtageDeLaRequete() && (doublon == true))) {

					iterRequete.remove();
					break;
				} else if ((requeteAVerifie.getEtageDeLaRequete() == requeteAComparer
						.getEtageDeLaRequete())) {

					doublon = true;

				}

			}
		}
	}

	private void monter() {
		++this.etageCourant;
		this.etat = Constante.KMonte();
	}

	private void descendre() {
		--this.etageCourant;
		this.etat = Constante.KDescend();
	}

	private void fermer() {
		this.etat = Constante.KFerme();
	}

	private void ouvrir(Requete req) {
		this.etat = Constante.KOuvert();
		getRequetes().remove(req);
	}

	public void action() {
		Requete req = null;

		if (!this.getRequetes().isEmpty()) {
			req = this.getRequetes().get(0);
		}
		if (this.etat != Constante.KBloquer()) {

			/****************************************************************
			 * Premiere condition : on ferme l'ascenseur si l'etat actuel est
			 * ouvert, car apres s'etre ouvert il doit forcement fermer *
			 ****************************************************************/

			if (this.etat == Constante.KOuvert() || req == null
					|| this.etat == null) {
				fermer();
			}

			/***********************************************
			 * 2eme condition on fait descendre l'ascenseur*
			 ***********************************************/

			else if ((this.etat == Constante.KFerme() || this.etat == Constante
					.KDescend())
					&& (req != null && req.getEtageDeLaRequete() < this.etageCourant)) {
				descendre();
			}

			/*********************************************
			 * 3eme condition on fait monter l'ascenseur *
			 *********************************************/

			else if ((this.etat == Constante.KFerme() || this.etat == Constante
					.KMonte())
					&& (req != null && req.getEtageDeLaRequete() > this.etageCourant)) {

				monter();
			}

			/******************************************************************
			 * On ouvre les portes de l'ascenseur puis on supprime la requete *
			 ******************************************************************/

			else if (req != null
					&& req.getEtageDeLaRequete() == this.etageCourant) {
				ouvrir(req);
			}

			else {
				System.out.println(req.getEtageDeLaRequete());
				System.out.println("Requete inconnu et ignore");
			}

			notifierObservateur();
		}
		/***********************************
		 * si l'ascenseur est bloque ... *
		 ***********************************/
		else {
			System.out
					.println("L ascenseur est bloque et ne peut pas effectuer d action");
		}
	}

	// Memento{

	public void bloquer() {

		etat = Constante.KBloquer();
		notifierObservateur();
	} // On sauve l'etat precedent dans le memento

	/**********************************************************************
	 * debloquer() correspond a restorerMemento donc on recupere l'etat de
	 * l'ascenseur avant que celui-ci soit bloque
	 **********************************************************************/

	public void debloquer(Memento m) {
		this.etat = m.getEtatSauve();
		notifierObservateur();
	}

	public Memento sauverMemento() {
		return new Memento(this.etat);
	}

	// }Memento

	// Getter{

	public String getEtat() {
		return this.etat;
	}

	public List<Requete> getRequetes() {
		return this.requetes;
	}

	public int getEtageCourant() {
		return this.etageCourant;
	}

	public int getNombreEtage() {

		return this.nombreEtage;
	}

	public int getIndex() {
		return this.index;
	}

	/*
	 * getter revoyant juste l'instance d'ascenseur. Necessaire pour pouvoir
	 * remonter grace a la recursivite jusqu a l'objet decore dans les options.
	 * Sa principale utilite apparait lors de l'utilisation d'un TriRequete
	 */
	public Ascenseur getAscenseur() {
		return this;
	}

	// }Getter

	// Observateur {
	@Override
	public void enregisterObservateur(Vue observateur) {
		observateurs.add(observateur);
	}

	@Override
	public void supprimerObservateur(Vue observateur) {
		observateurs.remove(observateur);
	}

	@Override
	public void notifierObservateur() {
		
		for (Vue o : observateurs) {
			o.actualiser();
		}
	}
	// } Observateur

}
