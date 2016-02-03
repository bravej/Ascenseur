package traitement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChoixMouvementMinimum implements ChoixAscenseur {

	/*
	 * Permet de savoir si une requete externe est sur le trajet d'un ascenseur
	 */
	private boolean SurLeTrajet(Ascenseur ascenseur, RequeteExterne requete) {
		if ((ascenseur.getRequetes().get(0).getEtageDeLaRequete() > ascenseur
				.getEtageCourant())
				&& requete.getDirection().equals(Constante.KHaut())) {
			return true;
		} else if ((ascenseur.getRequetes().get(0).getEtageDeLaRequete() < ascenseur
				.getEtageCourant())
				&& requete.getDirection().equals(Constante.KBas()))// sinon si
																	// on
																	// descend
		{

			return true;
		} else
			return false;

	}

	/*
	 * methode de choix d'ascenseur la plus optimisee
	 */
	@Override
	public void choisir() {
		Iterator<RequeteExterne> iterRequete = Controleur.getInstance()
				.getRequetes().iterator();
		List<Ascenseur> listeAscenseursImmobiles = new ArrayList<Ascenseur>();

		while (iterRequete.hasNext()) // Pour chaque requete externe
		{
			RequeteExterne requete = iterRequete.next();

			for (Ascenseur ascenseur : Controleur.getInstance().getAscenseurs()) { // Pour
																					// chaque
																					// ascenseur
				if (ascenseur.getRequetes().size() == 0) // Si l'ascenseur
															// est immobile
				{
					// et si c'est un ascenseur immobile se trouvant deja a
					// l'etage
					if (ascenseur.getEtageCourant() == requete
							.getEtageDeLaRequete()) {
						ascenseur.ajouterRequete(requete); // on ajoute la
															// requete
						iterRequete.remove(); // On la supprime de la collection
												// de requetes externes du
												// controleur
						break;
					}
					listeAscenseursImmobiles.add(ascenseur); // on le rajoute
																// a la liste
																// des
																// ascenseurs
																// immobiles
				}

				else if (SurLeTrajet(ascenseur, requete))// sinon si la requete
															// est
															// sur son trajet
				{
					ascenseur.ajouterRequete(requete); // on l'ajoute
					iterRequete.remove(); // On la supprime de la collection
					break;
				}

			}
		}

		Iterator<RequeteExterne> iterRequeteNonAttribuee = Controleur
				.getInstance().getRequetes().iterator();
		Iterator<Ascenseur> iterAscenseurImmobiles = listeAscenseursImmobiles
				.iterator();
		/*
		 * Tant qu'il y a des ascenseurs immobiles et des requetes non
		 * attribuees
		 */
		while ((iterAscenseurImmobiles.hasNext())
				&& (iterRequeteNonAttribuee.hasNext())) {
			// L'ascenseur immobile recoit une requete non attribuee
			iterAscenseurImmobiles.next().ajouterRequete(
					iterRequeteNonAttribuee.next());
			iterRequeteNonAttribuee.remove();
		}

		/*
		 * il se peut qu'il reste toujours des requetes externes non attribuees
		 * apres ca. Il faut donc appeler choisirAscenseur a chaque iteration
		 * meme si aucune nouvelle requete n'a ete saisie
		 */

	}
}