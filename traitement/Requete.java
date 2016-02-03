package traitement;



public interface Requete extends Comparable<Requete> {
	public int getEtageDeLaRequete();
	public String getDirection();
	public String toString();
}
