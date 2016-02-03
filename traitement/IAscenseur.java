package traitement;

import affichage.Vue;

public interface IAscenseur {

	public void action();

	public void enregisterObservateur(Vue observateur);

	public void supprimerObservateur(Vue observateur);

	public void notifierObservateur();

	/*
	 * getter revoyant juste l'instance d'ascenseur. Necessaire pour pouvoir
	 * remonter jusqu a l'objet decore dans les options. Sa principale utilite
	 * apparait lors de l'utilisation d'un TriRequete
	 */
	public Ascenseur getAscenseur();
}
