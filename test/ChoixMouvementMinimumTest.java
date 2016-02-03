package test;

import static org.junit.Assert.fail;

import org.junit.Test;

import traitement.Ascenseur;
import traitement.ChoixMouvementMinimum;
import traitement.Constante;
import traitement.Controleur;

public class ChoixMouvementMinimumTest {

	@Test
	public void testChoisir() {
		Ascenseur a1 = new Ascenseur(5);
		Ascenseur a2 = new Ascenseur(5);
		Ascenseur a3 = new Ascenseur(5);
		a1.creerRequeteInterne(4);
		a1.action();
		while (a1.getEtat() != Constante.KFerme()) {
			a1.action();
		}

		Controleur.getInstance().creerRequeteExterne(0, Constante.KHaut());

		Controleur.getInstance().changerCritereChoix(new ChoixMouvementMinimum());
		Controleur.getInstance().choisirAscenseur();

		System.out.println(a1.getRequetes().size());
		System.out.println(a2.getRequetes().size());
		System.out.println(a3.getRequetes().size());

		if (a1.getRequetes().size() != 0 || a2.getRequetes().size() != 1 || a3.getRequetes().size() != 0) {
			fail("11111");
		}

		
	}

}
