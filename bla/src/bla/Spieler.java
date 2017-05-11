package bla;

import java.text.*;

public class Spieler {

	private Double spieleGespielt;
	private Double spieleGewonnen;
	private Double spieleGewonnenProzent;
	private int minenGefunden;
	private int spielerSiegesserie;
	private int spielerMaxSiegesserie;
	private long zeitGesamt;
	private long zeitLetztesSpiel;
	private long zeitSchnellsterSieg;
	private String spielerName;
	private String spielfolge;

	private static DecimalFormat f = new DecimalFormat("#0.0"); // erzeugt einen
																// String

	// Arrays
	// Spielhistorie der letzten 5 Spiele mit Sieg-Niederlage als Eintrag
	static String[] spielHistorie = { "-", "-", "-", "-", "-" };

	// Liste

	public Spieler() {
		// Init Spieler mit Standardwerten
		spielerName = "";
		spieleGespielt = 0.0;
		spieleGewonnen = 0.0;
		spieleGewonnenProzent = 0.0;
		minenGefunden = 0;
		zeitGesamt = 0;
		zeitLetztesSpiel = 0;
		zeitSchnellsterSieg = 0;
		spielerSiegesserie = 0;
		spielerMaxSiegesserie = 0;
		// spielfolge initialisiert mit "nie gespielt": "-", "-", "-", "-", "-"
		spielfolge = "322222";
	}

	public void calculateSpieleGewonnenProzent() {
		spieleGewonnenProzent = (spieleGewonnen) / spieleGespielt * 100;
	}

	private void erhoeheMinenGefunden(int minen) {
		minenGefunden = minenGefunden + minen;
	}

	private void erhoeheSpieleGespielt() {
		spieleGespielt = spieleGespielt + 1;
		calculateSpieleGewonnenProzent();
	}

	private void erhoeheSpieleGewonnen(boolean sieg) {
		if (sieg == true) {
			spieleGewonnen = spieleGewonnen + 1;
		}
	}

	private void erhoeheSpielerSiegesserie(boolean sieg) {
		// falls Sieg, dann erhöhe Siegesserie um 1
		if (sieg == true) {
			spielerSiegesserie = spielerSiegesserie + 1;
			// letztes Spiel war Niederlage => Zähler auf 0 setzen
		} else {
			spielerSiegesserie = 0;
		}
		ueberpruefeMaxSiegesserie();
	}

	private void erhoeheZeitGesamt(long zeitSpiel) {
		zeitGesamt = zeitGesamt + zeitSpiel;
	}

	public Integer getMaxSiegesserie() {
		return spielerMaxSiegesserie;
	}

	public Integer getMinenGefunden() {
		return minenGefunden;
	}

	public String getSpielfolge() {
		// Falls schon einmal gespielt, dann lies spielHistorie aus
		if (spielfolge != "322222") {
			spielfolge = "3";
			for (int i = 0; i < spielHistorie.length; i++) {
				if (spielHistorie[i] == "Gewonnen") {
					spielfolge = spielfolge + 1;
				}
				if (spielHistorie[i] == "-") {
					spielfolge = spielfolge + 2;
				}
				if (spielHistorie[i] == "Verloren") {
					spielfolge = spielfolge + 0;
				}
			}
		}
		return spielfolge;
	}

	public Integer getSpieleGespielt() {
		return spieleGespielt.intValue();
	}

	public Integer getSpieleGewonnen() {
		return spieleGewonnen.intValue();
	}

	public String getSpieleGewonnenProzent() {
		// Keine Spiele gespielt, Profil öffnen, abfangen, dass nicht durch 0
		// geteilt wird
		if (spieleGespielt != 0) {
			// immer wenn der spieleGewonnenProzent-Wert abgefragt wird, wird er
			// vorher neu berechnet
			calculateSpieleGewonnenProzent();
		}
		return f.format(spieleGewonnenProzent);
	}

	public String getSpielerName() {
		return spielerName;
	}

	public Integer getSpielerSiegesserie() {
		return spielerSiegesserie;
	}

	public String getSpielHistorie(int index) {
		return spielHistorie[index];
	}

	public long getZeitGesamt() {
		return zeitGesamt;
	}

	public long getZeitLetztesSpiel() {
		return zeitLetztesSpiel;
	}

	public long getZeitSchnellsterSiegl() {
		return zeitSchnellsterSieg;
	}

	public void setMaxSiegesserieFromData(Integer maxSiegesserie) {
		this.spielerMaxSiegesserie = maxSiegesserie;
	}

	public void setMinenGefunden(Integer minenGefunden) {
		this.minenGefunden = minenGefunden;
	}

	public void setSpieleGespielt(Integer spieleGespielt) {
		this.spieleGespielt = spieleGespielt.doubleValue();
	}

	public void setSpieleGewonnen(Integer spieleGewonnen) {
		this.spieleGewonnen = spieleGewonnen.doubleValue();
	}

	public void setSpielerName(String name) {
		this.spielerName = name;
	}

	public void setSpielerSiegesserie(Integer Siegesserie) {
		this.spielerSiegesserie = Siegesserie;
	}

	private void setSpielfolge(boolean sieg) {

		String tempSpielfolge = this.spielfolge.substring(1, 4);
		
		if (sieg == true) {
			tempSpielfolge = "1" + tempSpielfolge;
		} else {
			tempSpielfolge = "0" + tempSpielfolge;
		}
		tempSpielfolge = "3" + tempSpielfolge;

		this.spielfolge = tempSpielfolge;
	}

	// Notiert Sieg-Niederlage der letzten 5 Spiele
	private void setSpielHistorie(boolean sieg) {

		setSpielfolge(sieg);

		// Einträge um 1 nach "unten" verschieben
		for (int i = 4; i > 0; i--) {
			spielHistorie[i] = spielHistorie[i - 1];
		}
		// Oberstes Element neu setzen
		if (sieg == true) {
			spielHistorie[0] = "Gewonnen";
		} else {
			spielHistorie[0] = "Verloren";
		}
	}

	public void setSpielHistorieFromDataIO(Integer spielfolge) {

		String tmpString = "" + spielfolge;
		this.spielfolge = tmpString;

		for (int i = 1; i < tmpString.length(); i++) {
			// Fälle: =1 ist "Gewonnen"
			// =2 ist nicht gespielt, also "-"
			// =0 ist "Verloren"
			if (Character.getNumericValue(tmpString.charAt(i)) == 0) {
				spielHistorie[i - 1] = "Verloren";
			}
			if (Character.getNumericValue(tmpString.charAt(i)) == 1) {
				spielHistorie[i - 1] = "Gewonnen";
			}
			if (Character.getNumericValue(tmpString.charAt(i)) == 2) {
				spielHistorie[i - 1] = "-";
			}
		}
	}

	public void setZeitGesamt(long zeitGesamt) {
		this.zeitGesamt = zeitGesamt;
	}

	public void setZeitLetztesSpiel(long zeitSpiel) {
		this.zeitLetztesSpiel = zeitSpiel;
	}

	public void setZeitSchnellsterSieg(long zeitSpiel) {

		this.zeitSchnellsterSieg = zeitSpiel;

	}

	private void setZeitSchnellsterSieg(long zeitSpiel, boolean sieg) {
		// nur Siege werden als schnellstes Spiel gewertet
		if (sieg == true) {
			// noch kein schnellstes Spiel absolviert
			if (zeitSchnellsterSieg == 0) {
				zeitSchnellsterSieg = zeitSpiel;
			}
			// vergleich ob aktuelle spielzeit schneller war, als altes
			// schnellstes spiel
			else {
				if (zeitSpiel < zeitSchnellsterSieg) {
					zeitSchnellsterSieg = zeitSpiel;
				}
			}
		}
	}

	// Setzt ggf. ZeitSchnellstesSpiel, setzt ZeitLetztesSpiel und aktualisiert
	// ZeitGesamt
	public void spielerAktualisieren(long spielZeit, int minenRichtig, boolean siegNiederlage) {
		erhoeheSpieleGespielt();

		erhoeheMinenGefunden(minenRichtig);

		erhoeheSpieleGewonnen(siegNiederlage);
		erhoeheSpielerSiegesserie(siegNiederlage);

		setZeitSchnellsterSieg(spielZeit, siegNiederlage);
		setZeitLetztesSpiel(spielZeit);
		erhoeheZeitGesamt(spielZeit);

		setSpielHistorie(siegNiederlage);
	}

	private void ueberpruefeMaxSiegesserie() {
		if (spielerSiegesserie > spielerMaxSiegesserie) {
			spielerMaxSiegesserie = spielerSiegesserie;
		}
	}

}
