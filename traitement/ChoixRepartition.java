package traitement;

import java.util.Iterator;

public class ChoixRepartition implements ChoixAscenseur {

	/*
	 * choix d'attribution des requetes de sorte qu'en moyenne tous les
	 * ascenseurs aient le meme nombre de requetes. ecart-type jamais superieur
	 * a 1
	 */
	@Override
	public void choisir() {
		int indexAscenseur = 0; // index de l'ascenseur ayant le moins de
								// requetes : ascenseur de reference

		Iterator<RequeteExterne> iterRequete = Controleur.getInstance()
				.getRequetes().iterator();
		while (iterRequete.hasNext()) { // pour chaque requete
			RequeteExterne requete = iterRequete.next();
			for (Ascenseur a : Controleur.getInstance().getAscenseurs()) { // pour
																			// chaque
																			// ascenseur
				if (a.getRequetes().size() < Controleur.getInstance()
						.getAscenseurs().get(indexAscenseur).getRequetes()
						.size()) { // si le nombre de requetes de l'ascenseur
									// est inferieur au nombre de requetes de
									// l'ascenseur reference
					indexAscenseur = a.getIndex(); // il devient ce dernier
				}
			}
			Controleur.getInstance().getAscenseurs().get(indexAscenseur)
					.ajouterRequete(requete); // on ajoute la requete à
												// l'ascenseur reference
			iterRequete.remove(); // On supprime la requete de la collection
									// (elle a ete attribuee)
		}
	}
}
