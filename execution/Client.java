package execution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import traitement.IAscenseur;

public class Client {
	static ArrayList<IAscenseur> mesAscenseurs = new ArrayList<IAscenseur>();
	
	/******************************************************
	 * L utilisateur choisi entre plusieurs jeu de donnee *
	 ******************************************************/
	
	public static void main(String args[]) {
		boolean bool = false;
		int choix;
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

		/*************************************************
		 * la boucle permet que si on catch une exeption *
		 * on recommence le programme                    *
		 *************************************************/
		
		while (!bool)
		{
			try
			{
				System.out.println("Entrer 1 pour le jeu de donnee 1 : ");
				System.out.println("Entrer 2 pour le jeu de donnee 2 : ");
				System.out.println("Entrer 3 pour le jeu de donnee 3 : ");
				System.out.println("Entrer 4 pour le jeu de donnee libre : ");
				choix = Integer.parseInt((bufferReader.readLine()));
				switch (choix)
				{
					case 4:
						Libre libre = new Libre (mesAscenseurs);
						libre.initialisation();
						break;
					default:
						if (choix >= 1 && choix <= 3)
						{
							JeuDeDonnee jeu = new JeuDeDonnee(choix);
						}
						else 
						{
							System.out.println("Entre errone");
						}
				}
				bool = true;
			}
			catch (NumberFormatException e) {
				System.err.println("Vous avez entrer une lettre a la place d'un nombre ");
				bool = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
			catch (ArrayIndexOutOfBoundsException e){
				System.err.println("Acces a une donne qui n'existe pas");
				bool = true;
			}
		}
	}
}
