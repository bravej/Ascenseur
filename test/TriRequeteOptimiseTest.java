package test;

import static org.junit.Assert.*;

import org.junit.Test;

import traitement.Ascenseur;
import traitement.ChoixRepartition;
import traitement.Constante;
import traitement.Controleur;
import traitement.Requete;
import traitement.TriRequete;
import traitement.TriRequeteOptimise;

public class TriRequeteOptimiseTest {

	@Test
	public void testTrier() {
		TriRequete tri = new TriRequeteOptimise();
		Ascenseur a1 = new Ascenseur(5);
		a1.creerRequeteInterne(1);

		Controleur.getInstance().creerRequeteExterne(3, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(2, Constante.KBas ());
		Controleur.getInstance().creerRequeteExterne(0, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(2, Constante.KBas());

		Controleur.getInstance().changerCritereChoix(new ChoixRepartition());
		Controleur.getInstance().choisirAscenseur();
		tri.trier(a1);

		String test = new String();
		for (Requete req : a1.getRequetes()) {
			test = test + Integer.toString(req.getEtageDeLaRequete());
		}

		if (!test.equals("12230")){
			fail("Not yet implemented");
		}
	}

}
