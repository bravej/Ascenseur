package test;

import static org.junit.Assert.fail;

import org.junit.Test;

import traitement.Ascenseur;
import traitement.ChoixRepartition;
import traitement.Constante;
import traitement.Controleur;

public class ChoixRepartitionTest {

	@Test
	public void testChoisir() {
		Ascenseur a1 = new Ascenseur(5);
		Ascenseur a2 = new Ascenseur(5);
		Ascenseur a3 = new Ascenseur(5);
		a1.creerRequeteInterne(1);
		
		Controleur.getInstance().creerRequeteExterne(1, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(1, Constante.KHaut());
		Controleur.getInstance().creerRequeteExterne(2, Constante.KBas());

		Controleur.getInstance().changerCritereChoix(new ChoixRepartition());
		Controleur.getInstance().choisirAscenseur();
		
		if (a1.getRequetes().size() != 1 || a2.getRequetes().size() != 1
				|| a3.getRequetes().size() != 2) {
			fail("Not yet implemented");
		}
	}

}
