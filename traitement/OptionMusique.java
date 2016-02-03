package traitement;

import affichage.Vue;

public class OptionMusique implements DecorateurOption {

	// Donnee membre{

	private IAscenseur ascenseur;

	// }Donnee membre

	public OptionMusique(IAscenseur monAscenseur) {
		this.ascenseur = monAscenseur;
	}

	@Override
	public Ascenseur getAscenseur() {
		return this.ascenseur.getAscenseur();
	}

	@Override
	public void action() {
		this.ascenseur.action();
		this.ascenseur.notifierObservateur();
		int numeroAscenseur = ascenseur.getAscenseur().getIndex() + 1;
		System.out.println("Petite musique pour l'ascenseur numero : "
				+ numeroAscenseur);
	}

	// IAscenseur{
	@Override
	public void enregisterObservateur(Vue observateur) {
		this.ascenseur.enregisterObservateur(observateur);

	}

	@Override
	public void supprimerObservateur(Vue observateur) {
		this.ascenseur.supprimerObservateur(observateur);

	}

	@Override
	public void notifierObservateur() {
		this.ascenseur.notifierObservateur();

	}
	// }IAscenseur
}
