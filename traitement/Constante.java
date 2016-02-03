package traitement;

public class Constante {
	private static String KMonte = "monte";
	private static String KDescend = "descend";
	private static String KOuvert = "immobile ouvert";
	private static String KFerme = "immobile ferm�";
	private static String KBloquer = "bloqu�";
	private static String KHaut = "haut";
	private static String KBas = "bas";
	
	public static String KHaut() {
		return KHaut;
	}

	public static String KBas() {
		return KBas;
	}

	private Constante() {
	}

	public static String KMonte() {
		return KMonte;
	}

	public static String KBloquer() {
		return KBloquer;
	}

	public static String KDescend() {
		return KDescend;
	}

	public static String KOuvert() {
		return KOuvert;
	}

	public static String KFerme() {
		return KFerme;
	}
}
