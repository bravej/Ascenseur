package traitement;

public class EtatAscenseur {
	private Memento etatSauves = null;

	public void addMemento(Memento m) {
		etatSauves = m;
	}

	public Memento getMemento() {
		return etatSauves;
	}
}
