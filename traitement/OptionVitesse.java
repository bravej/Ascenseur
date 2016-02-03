package traitement;

import affichage.Vue;

public class OptionVitesse implements DecorateurOption {

	// Donnee membre{

	private IAscenseur ascenseur;

	// }Donnee membre

	public OptionVitesse(IAscenseur ascenseur) {
		this.ascenseur = ascenseur;
	}

	@Override
	public Ascenseur getAscenseur() {
		return this.ascenseur.getAscenseur();
	}

	@Override
	public void action() {
		this.ascenseur.action();
		this.ascenseur.notifierObservateur();
		int numeroAscenseur = this.ascenseur.getAscenseur().getIndex() + 1;
		System.out.println("Vitesse accrue pour l'ascenseur : "
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
	// IAscenseur

}
