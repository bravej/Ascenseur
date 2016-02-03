package test;

import static org.junit.Assert.fail;

import java.awt.Desktop.Action;

import org.junit.Test;

import traitement.Ascenseur;
import traitement.Constante;
import traitement.EtatAscenseur;
import traitement.Requete;
import traitement.RequeteInterne;
import traitement.TriRequete;
import traitement.TriRequeteOptimise;

public class AscenseurTest {

	@Test
	public void supprDoublons() {
		TriRequete triR = new TriRequeteOptimise();
		
		Ascenseur ascenseur = new Ascenseur(10);
		ascenseur.ajouterRequete(new RequeteInterne(4));
		ascenseur.ajouterRequete(new RequeteInterne(1));
		ascenseur.ajouterRequete(new RequeteInterne(4));
		ascenseur.ajouterRequete(new RequeteInterne(3));
		for (Requete r : ascenseur.getRequetes()){
			System.out.println(r);
		}
		
		triR.trier(ascenseur);
		System.out.println("supprDoublons");
		ascenseur.supprDoublons();
		
		for (Requete r : ascenseur.getRequetes()){
			System.out.println(r);
		}
		
		if (ascenseur.getRequetes().size() != 3){
			fail();
		}
	}

	@Test
	public void testBloquer() {

		/***************************************************************
		 * Ici on ne test pas seulement la methide bloquer de la classs *
		 * Ascenseur mais tout les methodes qui permette de bloquer puis* de
		 * restaurer l'etat de l'ascenseur donc les methides : * bloquer (),
		 * debloquer(Memento m) et sauverMemento() * Pour cela on creer un *
		 * ascenseur avec 10 etages, puis on creer un gardien qui va * permettre
		 * de sauvegarder l'etat de l'asenceur avant le * bloquage. Donc le test
		 * sera de vérifier si l'état de * l'ascenseur est egale a
		 * Constante.KFerme() (fermer) * car c'est l'etat initial de l'ascenseur
		 * *
		 ****************************************************************/

		Ascenseur ascenseur = new Ascenseur(10);
		EtatAscenseur gardien = new EtatAscenseur();
		gardien.addMemento(ascenseur.sauverMemento());
		ascenseur.bloquer();
		ascenseur.debloquer(gardien.getMemento());
		if (ascenseur.getEtat() != Constante.KFerme()) {
			fail();
		}

	}

	@Test
	public void testMonter() {
		/********************************************************
		 * Dans ce test on fait fait un test de la fonction * monter () de la
		 * classe Ascenseur. Pour cela on * creer un ascenseur qui à un nombre
		 * d'etage = 10 * puis on le fait monter. Donc ccomme l'etage courant *
		 * de l'ascenseur est 0, après appeler la methode * elle doit être de 1
		 * et sont etat = Constante.KMonte * (Monte) *
		 ********************************************************/

		Ascenseur ascenseur = new Ascenseur(10);
		ascenseur.ajouterRequete(new RequeteInterne(1));
		ascenseur.action();
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KMonte()) {
			fail();
		}
	}

	@Test
	public void testDescendre() {
		/********************************************************
		 * Dans ce test on fait fait un test de la fonction * Descendre() de la
		 * classe Ascenseur. Pour cela on * creer un ascenseur qui à un nombre
		 * d'etage = 10 * puis on le fait monter de 1 etage. Puis on le refait *
		 * descendre donc apres appeler la methode descendre () * l'etage
		 * courant doit être a 0 et l'etat de l'asenceur* = Constante.KDescend
		 * (descend)                                            	* *
		 ********************************************************/
		Ascenseur ascenseur = new Ascenseur(10);
		ascenseur.ajouterRequete(new RequeteInterne(1));
		ascenseur.action();
		ascenseur.action();
		ascenseur.action();
		ascenseur.action();
		ascenseur.ajouterRequete(new RequeteInterne(0));
		ascenseur.action();
		/********************************************************
		 * On monte dabord d'un etage pour pouvoir redescendre à*
		 *  l'etage 0                                           *
		 ********************************************************/
		if (ascenseur.getEtageCourant() != 0 || ascenseur.getEtat() != Constante.KDescend()) {
			fail();
		}
	}

	@Test
	public void testFermer() {
		Ascenseur ascenseur = new Ascenseur(10);
		ascenseur.ajouterRequete(new RequeteInterne(1));
		ascenseur.action(); // monte
		ascenseur.action(); // ouvert
		ascenseur.action(); // ferme
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KFerme()) {
			fail();
		}
		Ascenseur ascenseur2 = new Ascenseur(10);
		ascenseur2.action(); // requete vide donc reste fermer
		if (ascenseur2.getEtageCourant() != 0 || ascenseur2.getEtat() != Constante.KFerme()) {
			fail();
		}
	}

	@Test
	public void testOuvert() {
		Ascenseur ascenseur = new Ascenseur(10);
		Requete requete1 = new RequeteInterne(1);
		ascenseur.ajouterRequete(requete1);
		ascenseur.action(); // monte
		ascenseur.action(); // ouvre
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KOuvert()) {
			fail();
		}
		ascenseur.action();// ferme
		ascenseur.ajouterRequete(requete1);
		ascenseur.action();// ouvre
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KOuvert()) {
			fail();
		}
	}

	@Test
	public void testAction() {
		/**************************************************************
		 * Ici on test la fonction action de la classe Ascenseur. * Pour cela on
		 * creer plusieurs requetes différente, puis * on applique la methode
		 * action plusieurs fois pour * terminer la requete. On test à chaque
		 * appel de action * les valeurs de l'etage courant et l'etat de
		 * l'ascenseur. * Pour finir on test en bloquant l'ascenseur *
		 **************************************************************/

		Ascenseur ascenseur = new Ascenseur(10);

		/********************* Requete qui permet de monter ******************/
		Requete requete0 = new RequeteInterne(1);
		ascenseur.ajouterRequete(requete0);
		ascenseur.action();
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KMonte()) {
			fail();
		}

		ascenseur.action();
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KOuvert()) {
			fail();
		}

		ascenseur.action();
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KFerme()) {
			fail();
		}
		/*******************************************************************/

		/********************* Requete qui ouvre l'ascenseur ****************/
		Requete requete1 = new RequeteInterne(1);
		ascenseur.ajouterRequete(requete1);
		ascenseur.action();
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KOuvert()) {
			fail();
		}

		ascenseur.action();
		if (ascenseur.getEtageCourant() != 1 || ascenseur.getEtat() != Constante.KFerme()) {
			fail();
		}

		/*****************************************************************/

		/*********************
		 * Requete qui permet de Descendre
		 ******************/
		Requete requete2 = new RequeteInterne(0);
		ascenseur.ajouterRequete(requete2);
		ascenseur.action();
		if (ascenseur.getEtageCourant() != 0 || ascenseur.getEtat() != Constante.KDescend()) {
			fail();
		}

		ascenseur.action();
		if (ascenseur.getEtageCourant() != 0 || ascenseur.getEtat() != Constante.KOuvert()) {
			fail();
		}

		ascenseur.action();
		if (ascenseur.getEtageCourant() != 0 || ascenseur.getEtat() != Constante.KFerme()) {
			fail();
		}
		/*********************************************************************/

		/************************ Cas de l'asenceur bloquer ******************/
		Requete requete3 = new RequeteInterne(3);
		ascenseur.ajouterRequete(requete3);
		ascenseur.bloquer();
		ascenseur.action();
		if (ascenseur.getEtageCourant() != 0 || ascenseur.getEtat() != Constante.KBloquer()) {
			fail();
		}
		/*******************************************************************/
	}

}
