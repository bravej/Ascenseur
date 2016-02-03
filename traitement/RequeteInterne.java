package traitement;

public class RequeteInterne implements Requete {

	// Donnees membres{

	/**************************************************************************
	 * direction pourra prendre les valeurs KMonter et KDescendre (avec
	 * conventions de nommage java) stockees dans la classe Constante
	 **************************************************************************/
	private int etageDeLaRequete;

	// }Donnees membres

	public RequeteInterne(int etageDeLaRequete) {
		super();
		this.etageDeLaRequete = etageDeLaRequete;
	}

	@Override
	public String toString() {
		return "RequeteInterne [etageDeLaRequete=" + etageDeLaRequete + "]";
	}

	public int getEtageDeLaRequete() {
		return etageDeLaRequete;
	}

	@Override
	public int compareTo(Requete o) {
		return (this.etageDeLaRequete > o.getEtageDeLaRequete()) ? 1
				: (this.etageDeLaRequete == o.getEtageDeLaRequete()) ? 0 : -1;
	}

	@Override
	public String getDirection() {
		return null;
	}
}