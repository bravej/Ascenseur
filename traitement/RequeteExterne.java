package traitement;

public class RequeteExterne implements Requete {

	// Donnees membres{

	private int etageDeLaRequete;
	/**************************************************************************
	 * direction pourra prendre les valeurs KMonter et KDescendre (avec
	 * conventions de nommage java) stockees dans la classe Constante
	 **************************************************************************/
	private String direction;

	// }Donnees membres

	public RequeteExterne(int etageDeLaRequete, String direction) {
		super();
		this.etageDeLaRequete = etageDeLaRequete;
		this.direction = direction;
	}

	public int getEtageDeLaRequete() {
		return this.etageDeLaRequete;
	}

	public String getDirection() {
		return this.direction;
	}

	@Override
	public String toString() {
		return "RequeteExterne [etageDeLaRequete=" + etageDeLaRequete
				+ ", direction=" + direction + "]";
	}

	@Override
	public int compareTo(Requete o) {
		return (this.etageDeLaRequete > o.getEtageDeLaRequete()) ? 1
				: (this.etageDeLaRequete == o.getEtageDeLaRequete()) ? 0 : -1;
	}
}
