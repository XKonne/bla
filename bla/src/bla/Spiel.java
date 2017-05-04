package bla;

import java.util.Random;

public class Spiel {

	// Variablen

	// Integer
	private static int minenGesamt = 3;
	private static int minenRichtig = 0;
	private static int restMinen = 3;
	static int anzahlSpalten = 0;
	static int anzahlZeilen = 0;
	static int anzahlMinen = 0;

	// String
	static String spielModus = "noModus";
	static String spielModusT = "";

	// Zufallszahlen
	static Random random = new Random();
	static Random random2 = new Random();

	// Arrays
	public static int[][] spielfeld = new int[anzahlZeilen][anzahlSpalten];
	public static int[][] spielfeldGeklickt = new int[anzahlZeilen][anzahlSpalten];

	public static void main(String[] args) {

		ObjectHandler.createSpieler();
		ObjectHandler.createGui_Start();

	}

	public static void aufSiegpruefen(boolean mineGetroffen) {
		// boolean winlose = false;
		// // aufSiegpruefen wird nach _jedem_ Mausklick ausgeführt.
		//
		// // Minen-Markiert-Zähler und Minen-Richtig-Zähler aktualisieren
		// lab_Restminen.setText("Minen: " +
		// Integer.toString(spiel.getRestMinen()));
		// lab_MinenRichtig.setText("Mine Richtig: " +
		// Integer.toString(spiel.getMinenRichtig()));
		//
		// // Sieg
		// if (spiel.getMinenRichtig() == spiel.getMinenGesamt() &&
		// spiel.getRestMinen() == 0
		// && Spielfeldgesperrt == false) {
		// // Variable für Spielhistorie
		// winlose = true;
		//
		// // zeitmesser stoppen und Wert in zeittmp speichern
		// zeittmp = Spiel.zeitmessungEnde(zeittmp);
		//
		// // Ausgabe
		// GUI_SpielEnde(mineGetroffen);
		//
		// // Spieler Stats aktualsieren
		// spielerT.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(),
		// winlose);
		//
		// // Spielfeld deaktivieren
		// setSpielfeldAnAus(false);
		// Spielfeldgesperrt = true;
		// }
		//
		// // Niederlage (=Mine aufgedeckt)
		// if (mineGetroffen == true) {
		//
		// winlose = false;
		// // zeitmesser stoppen und Wert in zeittmp speichern
		// zeittmp = Spiel.zeitmessungEnde(zeittmp);
		//
		// // Ausgabe
		// GUI_SpielEnde(mineGetroffen);
		//
		// // Spieler Stats aktualisieren
		// spielerT.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(),
		// winlose);
		//
		// // Spielfeld deaktivieren
		// setSpielfeldAnAus(false);
		// Spielfeldgesperrt = true;
		// }
	}

	public static void createSpiel() {

		// Erstellt eine neue Spiel-Runde
		initSpielfeldGeklickt();
		initSpielfeldStatus();
		setSpielfeldMinen();
		setSpielfeldZahlen();
	}

	/**
	 * Feld per Rechtsklick markiert > Erniedrige Minenzähler um 1 (=-1).
	 * Markierung per Rechtsklick aufgehoben > +1.
	 * 
	 * @param i
	 *            Integer, der die Änderung beinhaltet
	 */
	public static void countMinenMarkierung(int i) {
		restMinen += i;
	}

	/**
	 * Änderung der Anzahl richtiger Minen
	 * 
	 * @param i
	 *            Integer, der die Änderung beinhaltet
	 */
	public static void countMineRichtig(int i) {
		minenRichtig += i;
	}

	public static int getMinenGesamt() {
		return minenGesamt;
	}

	public static int getMinenRichtig() {
		return minenRichtig;
	}

	public static int getRestMinen() {
		return restMinen;
	}

	public static String getSpielModus() {
		return Integer.toString(anzahlZeilen) + "x" + Integer.toString(anzahlSpalten) + " " + spielModus;
	}


	public static void setSpielfeldgeklickt(int zeile, int spalte, int wert) {
		spielfeldGeklickt[zeile][spalte] = wert;
	}

	public static Integer getSpielfeldStatus(int zeile, int spalte) {
		return spielfeld[zeile][spalte];
	}
	
	private static void initSpielfeldGeklickt() {

		// Spielfeldstatus ist um 2 Einheiten größer als das angezeigte
		// Spielfeld
		// Zu beginn ist das Spielfeld leer (Wert: 0)
		for (int i = 0; i < anzahlZeilen; i++) {
			for (int j = 0; j < anzahlSpalten; j++) {
				spielfeldGeklickt[i][j] = 0;
			}
		}
	}

	private static void initSpielfeldStatus() {

		// Spielfeldstatus ist um 2 Einheiten größer als das angezeigte
		// Spielfeld
		// Zu beginn ist das Spielfeld leer (Wert: 0)
		for (int i = 0; i < anzahlZeilen + 2; i++) {
			for (int j = 0; j < anzahlSpalten + 2; j++) {
				spielfeld[i][j] = 0;
			}
		}
	}

	public static Integer getSpielfeldZeilen() {
		return anzahlZeilen;
	}

	public static Integer getSpielfeldSpalten() {
		return anzahlSpalten;
	}

	public static void setMinenGesamt(int gesamtMinen) {
		minenGesamt = gesamtMinen;
	}

	public static void setMinenRichtig(int minenRichtig1) {
		minenRichtig = minenRichtig1;
	}

	public static void setRestMinen(int minen) {
		restMinen = minen;
	}

	private static void setSpielfeldMinen() {

		for (int i = 0; i < anzahlMinen; i++) {

			int zeile = random.nextInt(anzahlZeilen) + 1;
			int spalte = random2.nextInt(anzahlSpalten) + 1;

			// Auf dem Spielfeld existiert bereits eine Mine. Neuer Versuch
			if (spielfeld[zeile][spalte] == -1) {
				i = i - 1;
				// debug: JOptionPane.showMessageDialog(null, "boing");
			}
			// Freies Feld > platziere Mine (Wert: -1)
			else {
				spielfeld[zeile][spalte] = -1;
			}
			// debug: JOptionPane.showMessageDialog(null, "x: " + x + "/ y: " +
			// y);
		}
	}

	private static void setSpielfeldZahlen() {

		// Berechnet für jedes Feld die Anzahl der umliegenden Minen
		// Es durchläuft den äußeren Rand nicht, da es nicht zum angezeigten
		// Spielfeld gehört
		for (int i = 1; i < anzahlZeilen + 1; i++) {
			for (int j = 1; j < anzahlSpalten + 1; j++) {
				if (spielfeld[i][j] == -1) {
					if (spielfeld[i - 1][j - 1] != -1) {
						spielfeld[i - 1][j - 1] = spielfeld[i - 1][j - 1] + 1;
					}
					if (spielfeld[i][j - 1] != -1) {
						spielfeld[i][j - 1] = spielfeld[i][j - 1] + 1;
					}
					if (spielfeld[i + 1][j - 1] != -1) {
						spielfeld[i + 1][j - 1] = spielfeld[i + 1][j - 1] + 1;
					}
					if (spielfeld[i - 1][j] != -1) {
						spielfeld[i - 1][j] = spielfeld[i - 1][j] + 1;
					}
					if (spielfeld[i + 1][j] != -1) {
						spielfeld[i + 1][j] = spielfeld[i + 1][j] + 1;
					}
					if (spielfeld[i - 1][j + 1] != -1) {
						spielfeld[i - 1][j + 1] = spielfeld[i - 1][j + 1] + 1;
					}
					if (spielfeld[i][j + 1] != -1) {
						spielfeld[i][j + 1] = spielfeld[i][j + 1] + 1;
					}
					if (spielfeld[i + 1][j + 1] != -1) {
						spielfeld[i + 1][j + 1] = spielfeld[i + 1][j + 1] + 1;
					}
				}
			}
		}
	}

	// Spielfeld-Daten
	public static void setSpielModus(int spalte, int zeile, int minen, String modus, Spieler spieler) {
		anzahlSpalten = spalte;
		anzahlZeilen = zeile;
		anzahlMinen = minen;
		spielModus = modus;

		spielfeld = new int[anzahlZeilen + 2][anzahlSpalten + 2];
		spielfeldGeklickt = new int[anzahlZeilen][anzahlSpalten];

		setupSpiel(spieler);
	}

	private static void setupSpiel(Spieler spieler) {

		setMinenGesamt(anzahlMinen);
		setRestMinen(anzahlMinen);

		createSpiel();
		ObjectHandler.createSpiel();
		ObjectHandler.createGui_Spielfeld();

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
