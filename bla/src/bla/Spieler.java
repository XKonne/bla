package bla;

import java.lang.*;
import java.text.*;

import javax.swing.JOptionPane;

public class Spieler {

	Double spieleGespielt;
	Double spieleGewonnen;
	Double spieleGewonnenProzent;
	int minenGefunden;
	int profilBild;
	int spielerSiegesserie;
	long zeitGesamt;
	long zeitLetztesSpiel;
	long zeitSchnellsterSieg;
	String spielerName;

	static DecimalFormat f = new DecimalFormat("#0.0"); // erzeugt einen String

	// Arrays
	// Spielhistorie der letzten 5 Spiele mit Sieg-Niederlage als Eintrag
	static String[] spielHistorie = { "-", "-", "-", "-", "-" };

	// Liste

	public Spieler() {
		super();
		//Init Spieler mit Standardwerten
		spielerName = "";
		spieleGespielt = 0.0;
		spieleGewonnen = 0.0;
		spieleGewonnenProzent = 0.0;
		minenGefunden = 0;
		zeitGesamt = 0;
		zeitLetztesSpiel = 0;
		zeitSchnellsterSieg = 0;
		profilBild = 0;
		spielerSiegesserie = 0;
	}

	public Integer getMinenGefunden() {
		return minenGefunden;
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
			setSpieleGewonnenProzent();
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

	public void setMinenGefunden(int minen) {
		minenGefunden = minenGefunden + minen;
	}

	public void setSpieleGespielt() {
		spieleGespielt = spieleGespielt + 1;
		setSpieleGewonnenProzent();
	}

	public void setSpieleGewonnen(boolean sieg) {
		if (sieg == true) {
			spieleGewonnen = spieleGewonnen + 1;
		}
	}

	public void setSpieleGewonnenProzent() {
		spieleGewonnenProzent = (spieleGewonnen) / spieleGespielt * 100;
	}

	public void setSpielerName(String name) {
		spielerName = name;
	}

	public void setSpielerSiegesserie(boolean sieg) {
		//falls Sieg, dann erhöhe Siegesserie um 1
		if (sieg == true) {
			spielerSiegesserie = spielerSiegesserie + 1;
		//letztes Spiel war Niederlage => Zähler auf 0 setzen
		} else {
			spielerSiegesserie = 0;
		}
	}

	// Notiert Sieg-Niederlage der letzten 5 Spiele
	public void setSpielHistorie(boolean sieg) {
		// Einträge um 1 nach "unten" verschieben
		for (int i=4; i>0; i--) {
			spielHistorie[i]=spielHistorie[i-1];
		}
		// Oberstes Element neu setzen
		if (sieg == true) {
			spielHistorie[0] = "Gewonnen";
		} else {
			spielHistorie[0] = "Verloren";
		}
	}

	public void setZeitGesamt(long zeitSpiel) {
		zeitGesamt = zeitGesamt + zeitSpiel;
	}

	public void setZeitLetztesSpiel(long zeitSpiel) {
		zeitLetztesSpiel = zeitSpiel;
	}

	public void setZeitSchnellsterSiegl(long zeitSpiel, boolean sieg) {
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
		setSpieleGespielt();

		setMinenGefunden(minenRichtig);

		setSpieleGewonnen(siegNiederlage);
		setSpielerSiegesserie(siegNiederlage);

		setZeitSchnellsterSiegl(spielZeit, siegNiederlage);
		setZeitLetztesSpiel(spielZeit);
		setZeitGesamt(spielZeit);

		setSpielHistorie(siegNiederlage);
	}

}
