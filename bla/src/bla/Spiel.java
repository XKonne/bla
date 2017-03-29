package bla;

public class Spiel {

	private int minenGesamt = 3;
	private int minenRichtig = 0;
	private int restMinen = 3;

	public Spiel() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		Spiel spiel = new Spiel();
		Test test = new Test(spiel);
		MouseInput.setupMouseInput(spiel, test);

	}

	/**
	 * Feld per Rechtsklick markiert > Erniedrige Minenzähler um 1 (=-1).
	 * Markierung per Rechtsklick aufgehoben > +1.
	 * 
	 * @param i
	 *            Integer, der die Änderung beinhaltet
	 */
	public void countMinenMarkierung(int i) {
		restMinen += i;
	}

	/**
	 * Änderung der Anzahl richtiger Minen
	 * 
	 * @param i
	 *            Integer, der die Änderung beinhaltet
	 */
	public void countMineRichtig(int i) {
		minenRichtig += i;
	}

	public int getMinenGesamt() {
		return minenGesamt;
	}

	public int getMinenRichtig() {
		return minenRichtig;
	}

	public int getRestMinen() {
		return restMinen;
	}

	public void setMinenGesamt(int minenGesamt) {
		this.minenGesamt = minenGesamt;
	}

	public void setMinenRichtig(int minenRichtig) {
		this.minenRichtig = minenRichtig;
	}

	public void setRestMinen(int minen) {
		restMinen = minen;
	}

	public static long zeitmessungEnde(long x) {
		long spielZeit = System.currentTimeMillis() - x;
		return spielZeit;
	}

	public static long zeitmessungStart() {
		long spielZeitStart = System.currentTimeMillis();
		return spielZeitStart;
	}

}
