package traitement;

public class Memento {
	private String etatCourant;
	
	public Memento (String etat)
	{
		this.etatCourant = etat;
	}
	
	public String getEtatSauve ()
	{
		return etatCourant;
	}
}
