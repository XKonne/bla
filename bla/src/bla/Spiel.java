package bla;

public class Spiel {

	private static int minenGesamt = 3;
	private int minenRichtig = 0;
	private static int restMinen = 3;

	static int anzahlSpalten = 0;
	static int anzahlZeilen = 0;
	static int anzahlMinen = 0;
	static String spielModus = "noModus";
	static String spielModusT="";

	private static Spiel spiel;
	private static Test test;

	public Spiel() {
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

	public static String getSpielModus() {
		return Integer.toString(anzahlSpalten)+"x"+Integer.toString(anzahlZeilen)+" "+spielModus;
	}

	public static void setMinenGesamt(int gesamtMinen) {
		minenGesamt = gesamtMinen;
	}

	public void setMinenRichtig(int minenRichtig) {
		this.minenRichtig = minenRichtig;
	}

	public static void setRestMinen(int minen) {
		restMinen = minen;
	}
	
	// Spielfeld-Daten
	public static void setSpielModus(int spalte, int zeile, int minen, String modus, Spieler spieler) {
		anzahlSpalten = spalte;
		anzahlZeilen = zeile;
		anzahlMinen = minen;
		spielModus = modus;
//		JOptionPane.showMessageDialog(null, "Spalten: " + anzahlSpalten + "/ Zeilen: " + anzahlSpalten + " / Minen: "
//				+ anzahlMinen + " Modus: " + spielModus);
		setupSpiel(spieler);
	}

	private static void setupSpiel(Spieler spieler) {

		setMinenGesamt(anzahlMinen);
		setRestMinen(anzahlMinen);
		
		spiel = new Spiel();
		test = new Test(spiel, spieler);
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
