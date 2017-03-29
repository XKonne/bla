package bla;

import java.lang.*;
import java.text.*;

import javax.swing.JOptionPane;

public class Spieler {
	
	static String spielerName;
	static Double spieleGespielt;
	static Double spieleGewonnen;
	static Double spieleGewonnenProzent;
	static int minenGefunden;
	static long zeitGesamt;
	static long zeitLetztesSpiel;
	static long zeitSchnellstesSpiel;
	static int profilBild;
	static int spielHistorieZaehler;
	static int spielerSiegesserie;
	
	static DecimalFormat f = new DecimalFormat("#0.0");  // erzeugt einen String
	
	//Arrays
	static String[] spielHistorie = {"-0", "-1", "-2", "-3", "-4", "tmp"};
	
	//Liste

	
	public Spieler() {
		super();
		spielerName="";
		spieleGespielt=0.0;
		spieleGewonnen=0.0;
		spieleGewonnenProzent=0.0;
		minenGefunden=0;
		zeitGesamt=0;
		zeitLetztesSpiel=0;
		zeitSchnellstesSpiel=0;
		profilBild=0;
		spielHistorieZaehler=0;
		spielerSiegesserie=0;
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//	}
	
	//SpielerName
	public static void setSpielerName(String name) {
		spielerName=name;
	}
	
	public static String getSpielerName() {
		return spielerName;
	}
	
	//SpieleGespielt
	public static void setSpieleGespielt() {
		spieleGespielt=spieleGespielt+1;
		setSpieleGewonnenProzent();
	}
	
	public static Integer getSpieleGespielt() {
		int ret=spieleGespielt.intValue();
		return ret;
	}
	
	//SpieleGewonnen
	public static void setSpieleGewonnen(boolean x) {
		if (x==true) {
		spieleGewonnen=spieleGewonnen+1;
		}
	}
	
	public static Integer getSpieleGewonnen() {
		int ret=spieleGewonnen.intValue();
		return ret;
	}
	
	//SpieleGewonnenProzent
	public static void setSpieleGewonnenProzent() {
		spieleGewonnenProzent=(spieleGewonnen)/spieleGespielt*100;
	}
	
	public static String getSpieleGewonnenProzent() {
		//Keine Spiele gespielt, Profil öffnen, abfangen, dass nicht durch 0 geteilt wird
		if (spieleGespielt != 0) {
		//immer wenn der spieleGewonnenProzent-Wert abgefragt wird, wird er vorher neu berechnet
		setSpieleGewonnenProzent();
		}
		return f.format(spieleGewonnenProzent);
	}
	
	//MinenGefunden
	public static void setMinenGefunden(int minen) {
		minenGefunden=minenGefunden+minen;
	}
	
	public static Integer getMinenGefunden() {
		return minenGefunden;
	}
	
	//ZeitGesamt
	public static void setZeitGesamt(long x) {
		zeitGesamt=zeitGesamt+x;
	}
	
	public static long getZeitGesamt() {
		return zeitGesamt;
	}
	
	//ZeitSchnellstesSpiel
	public static void setZeitSchnellstesSpiel(long x, boolean y) {
		// nur Siege werden als schnellstes Spiel gewertet
		if (y == true) {
			// noch kein schnellstes Spiel absolviert
			if (zeitSchnellstesSpiel == 0) {
				zeitSchnellstesSpiel=x;
			}
			// vergleich ob aktuelle spielzeit schneller war, als altes schnellstes spiel
			else {
				if (x < zeitSchnellstesSpiel) {
					zeitSchnellstesSpiel=x;
				}
			}
		}
	}
	
	public static long getZeitSchnellstesSpiel() {
		return zeitSchnellstesSpiel; 
	}
	
	//Zeit Letztes Spiel
	public static void setZeitLetztesSpiel(long x) {
		zeitLetztesSpiel=x;
	}
	
	public static long getZeitLetztesSpiel() {
		return zeitLetztesSpiel; 
	}
	
	//Notiert Sieg-Niederlage der letzten 5 Spiele
	public static void setSpielHistorie(boolean x) {
		if (x == true) {
			spielHistorie[0]="Gewonnen";
			//spielHistorieZaehler=spielHistorieZaehler+1;
		}
		else {
			spielHistorie[0]="Verloren";
			//spielHistorieZaehler=spielHistorieZaehler+1;	
		}
	}
	
	public static String getSpielHistorie(int x) {
		return spielHistorie[x]; 
	}
	
	//Siegesserie
	public static void setSpielerSiegesserie(boolean x) {
		if (x == true) {
			spielerSiegesserie=spielerSiegesserie+1;
		}
		else {
			spielerSiegesserie=0;
		}
	}
	
	public static Integer getSpielerSiegesserie() {
		return spielerSiegesserie;
	}
	
	public static void kopieren() {
		spielHistorie[4]=spielHistorie[3];
		spielHistorie[3]=spielHistorie[2];
		spielHistorie[2]=spielHistorie[1];
		spielHistorie[1]=spielHistorie[0];
	}
	
	
	
	// Setzt ggf. ZeitSchnellstesSpiel, setzt ZeitLetztesSpiel und aktualisiert ZeitGesamt
	public static void spielerAktualisieren(long x, int y, boolean z) {
		setSpieleGespielt();
		
		setMinenGefunden(y);
		
		setSpieleGewonnen(z);
		setSpielerSiegesserie(z);
		setSpielHistorie(z);
		
		setZeitSchnellstesSpiel(x, z);
		setZeitLetztesSpiel(x);
		setZeitGesamt(x);

		kopieren();
		setSpielHistorie(z);
	}
	


}
