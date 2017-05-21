package bla;

import java.util.Random;

public class Spiel {

	// Variablen

	// Integer
	private static int minenGesamt = 0;
	private static int minenRichtig = 0;
	private static int restMinen = 0;
	static int anzahlSpalten = 0;
	static int anzahlZeilen = 0;
	static int anzahlMinen = 0;
	static int countZahlenFelder = 0;
	static int leereFelder = 0;
	static int indexMerkeArray=0;
	static int spielPause = 0;
	
	// Long
	private static long zeittmp = 0;
	static long pause = 0;

	// String
	static String spielModus = "noModus";
	static String spielModusT = "";

	// Zufallszahlen
	static Random random = new Random();
	static Random random2 = new Random();

	// Arrays
	public static int[][] merkeArray = new int[0][0];
	public static int[][] spielfeldStatus = new int[anzahlZeilen][anzahlSpalten];
	public static int[][] spielfeldGeklickt = new int[anzahlZeilen][anzahlSpalten];

	public static void main(String[] args) {

		ObjectHandler.createSpieler();
		ObjectHandler.createGui_Start();

	}

	public static void aufSiegpruefen(boolean mineGetroffen) {
		
		// Hilfsvariable für Siegesserie
		boolean winlose = false;
		
		// Spielfeld-Labels aktualisieren
		GUI_Spielfeld.refreshLabels();

		// Sieg (=alle Minen richtig markiert
		 if (getMinenRichtig() == getMinenGesamt() && getRestMinen() == 0 && GUI_Spielfeld.Spielfeldgesperrt == false) {
			 
			 // Setze Hilfsvariable auf Sieg (=true) für Spielhistorie
			 winlose = true;
			 spielEnde(winlose, "Sieg");
			 
		}
		
		// Niederlage (=Mine aufgedeckt)
		if (mineGetroffen == true && GUI_Spielfeld.Spielfeldgesperrt == false ) {
		
			// Setze Hilfsvariable auf Niederlage (=false) für Spielhistorie
			winlose = false;
			spielEnde(winlose, "Niederlage");
			mineGetroffen=false;
		}
	}


	public static void createSpiel() 
	{
		// Erstellt eine neue Spiel-Runde
		initSpielfeldGeklickt();
		
		initSpielfeldStatus();
		setSpielfeldMinen();
		setSpielfeldZahlen();
		
		zeitMessungStart();
		
		Debug.debugSpiel();
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
	
	public static Integer getSpielfeldStatus(int zeile, int spalte) {
		return spielfeldStatus[zeile][spalte];
	}

	public static String getSpielModus() {
		return Integer.toString(anzahlZeilen) + "x" + Integer.toString(anzahlSpalten) + " " + spielModus;
	}
	
	public static Integer getIndexMerkeArray() {
		return indexMerkeArray;
	}

	public static void setSpielfeldgeklickt(int zeile, int spalte, int wert) {
		spielfeldGeklickt[zeile][spalte] = wert;
	}

	public static void initSpielfeldGeklickt() {
		
		// Setze index im MerkeArray zurück
		indexMerkeArray=0;
		
		// Zu beginn ist das spielfeldGeklickt-Status 0 (=nicht angeklickt)
		for (int i = 0; i < anzahlZeilen + 2; i++) {
			for (int j = 0; j < anzahlSpalten + 2; j++) {
				spielfeldGeklickt[i][j] = 0;
			}
		}
	}
	
	private static void initSpielfeldStatus() {

		// Spielfeldstatus ist um 2 Einheiten größer als das angezeigte
		// Spielfeld
		// Zu beginn ist der Spielfeld-Status leer (Wert: 0)
		for (int i = 0; i < anzahlZeilen + 2; i++) {
			for (int j = 0; j < anzahlSpalten + 2; j++) {
				spielfeldStatus[i][j] = 0;
			}
		}
	}

	public static Integer getSpielfeldZeilen() {
		return anzahlZeilen;
	}

	public static Integer getSpielfeldSpalten() {
		return anzahlSpalten;
	}
		
	private static long saveAktuelleZeit() {
		long timeMs = System.currentTimeMillis();
		return timeMs;
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
			if (spielfeldStatus[zeile][spalte] == -1) {
				i = i - 1;
			}
			// Freies Feld > platziere Mine (Wert: -1)
			else {
				spielfeldStatus[zeile][spalte] = -1;
			}
		}
	}

	private static void setSpielfeldZahlen() {
		
		countZahlenFelder = 0;
		leereFelder = 0;
		indexMerkeArray=0;

		// Berechnet für jedes Feld die Anzahl der umliegenden Minen
		for (int i = 0; i < anzahlZeilen+2; i++) {
			for (int j = 0; j < anzahlSpalten+2; j++) {
				if (spielfeldStatus[i][j] == -1) 
				{
					// Schleifen, um 1 Zeile drüber, mittig und drunter
					for (int z = -1; z <= 1; z++) {
						// ..sowie links, mittig, rechts vom Feld
						for (int sp = -1; sp <= 1; sp ++) {
							
							// Wenn keine Mine..
							if (spielfeldStatus[i + z][j + sp] != -1) 
							{
								// ..dann setze spielfeldStatus plus 1, um ein Zahlenfeld zu definieren
								spielfeldStatus[i + z][j + sp] = spielfeldStatus[i + z][j + sp] + 1;
							}
						}
					}
				}
			}
		}
		zaehleFelderMitZahlen();
		zaehleFelderLeer();
	}

	private static void zaehleFelderLeer() {
		leereFelder = (anzahlZeilen*anzahlSpalten)-countZahlenFelder-anzahlMinen;
				
		merkeArray = new int[leereFelder+anzahlMinen][2];
	}
	
	public static void addMerkeArray(int z, int sp) {
		
		boolean eintragGefunden = false;
		
		for (int index=0; index < merkeArray.length; index++) {
			if (merkeArray[index][0] == z && merkeArray[index][1] == sp) 
			{
				eintragGefunden = true;
				Debug.debugLeeresFeld(1, z, sp);
			}
		}
		// Füge Eintrag hinzu
		if (eintragGefunden == false)
			{
				merkeArray[indexMerkeArray][0]=z;
				merkeArray[indexMerkeArray][1]=sp;
				indexMerkeArray=indexMerkeArray+1;
				
				Debug.debugLeeresFeld(2, z, sp);
			}	
		Debug.debugLeeresFeld(3, 0, 0);
	}

	private static void zaehleFelderMitZahlen() {
		
		// durchlaufe inneres 4 eck ohne rand
		for (int i = 1; i < anzahlZeilen+1; i++) {
			for (int j = 1; j < anzahlSpalten+1; j++) {
				if (spielfeldStatus[i][j] != -1 && spielfeldStatus[i][j] != 0) {
					countZahlenFelder = countZahlenFelder + 1;
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

		spielfeldStatus = new int[anzahlZeilen + 2][anzahlSpalten + 2];
		spielfeldGeklickt = new int[anzahlZeilen + 2][anzahlSpalten + 2];

		setupSpiel(spieler);
	}

	private static void setupSpiel(Spieler spieler) {

		setMinenGesamt(anzahlMinen);
		setRestMinen(anzahlMinen);

		createSpiel();
		ObjectHandler.createSpiel();
		ObjectHandler.createGui_Spielfeld();

	}
	
	private static void spielEnde(boolean winlose, String spielEndeText) {
		
		 // Spieler Stats aktualisieren & Zeitmessung stoppen
		 ObjectHandler.getSpieler().spielerAktualisieren(zeitmessungEnde(zeittmp), getMinenRichtig(), winlose);


		// Ausgabe > Spielende
		ObjectHandler.getGui_Spielfeld().GUI_SpielEnde(spielEndeText);
		 
		// Spielfeld deaktivieren
		GUI_Spielfeld.Spielfeldgesperrt = true;
		
		ObjectHandler.getGui_Spielerprofil().updateGUI_Spielerprofil();
		DataIO.updateSpielerData();
		
	}
	
	public static long zeitMessungAktuell() {
		long spielZeitAktuell = System.currentTimeMillis() - zeittmp;
		return spielZeitAktuell;
	}

	public static long zeitmessungEnde(long tmpZeit) {
		long spielZeit = System.currentTimeMillis() - tmpZeit;
		return spielZeit;
	}
	
	public static void zeitMessungPause() {
		if ( spielPause == 0) {
			pause = System.currentTimeMillis() - zeittmp;
		}
		else {
			zeittmp = System.currentTimeMillis() - pause;
		}
		
	}
	
	public static void zeitMessungStart() {
		zeittmp = saveAktuelleZeit();
	}


}
