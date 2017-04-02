package bla;

import javax.swing.JOptionPane;

public class Spiel {
	static Spieler spieler;

	private int minenGesamt = 3;
	private int minenRichtig = 0;
	private int restMinen = 3;

	static int anzahlSpalten = 0;
	static int anzahlZeilen = 0;
	static int anzahlMinen = 0;
	static String spielModus = "noModus";

	private static Spiel spiel;
	private static Test test;

	public Spiel() {

	}

	// Spielfeld-Daten
	public static void setSpielModus(int spalte, int zeile, int minen, String modus, Spieler spieler) {
		anzahlSpalten = spalte;
		anzahlZeilen = zeile;
		anzahlMinen = minen;
		spielModus = modus;
		JOptionPane.showMessageDialog(null, "Spalten: " + anzahlSpalten + "/ Zeilen: " + anzahlSpalten + " / Minen: "
				+ anzahlMinen + " Modus: " + spielModus);

		setupSpiel(spieler);
	}

	public static void main(String[] args) {
		GUI_Start neu = new GUI_Start();
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

	private static void setupSpiel(Spieler spieler) {

		spiel = new Spiel();
		test = new Test(spiel, spieler);
		DataIO data = new DataIO(spieler);
		data.loadData();
		MouseInput.setupMouseInput(spiel, test);

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
