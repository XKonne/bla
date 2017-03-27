package bla;

import java.lang.*;
import java.text.*;

public class Spieler {
	
	static String spielerName;
	static Double spieleGespielt;
	static Double spieleGewonnen;
	static Double spieleGewonnenProzent;
	static int minenGefunden;
	static int zeitGesamtStd;
	static int zeitSchnellstesSpiel;
	static int profilBild;
	
	static DecimalFormat f = new DecimalFormat("#0.0");  // erzeugt einen String
	
	
	public Spieler() {
		super();
		spielerName="";
		spieleGespielt=0.0;
		spieleGewonnen=0.0;
		spieleGewonnenProzent=0.0;
		minenGefunden=0;
		zeitGesamtStd=0;
		zeitSchnellstesSpiel=0;
		profilBild=0;
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
	public static void setSpieleGewonnen() {
		spieleGewonnen=spieleGewonnen+1;
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
	public static void setZeitGesamt() {
		//to-do
	}
	
	public static Integer getZeitGesamt() {
		//to-do return spielerName;
		return minenGefunden;
	}
	
	//ZeitSchnellstesSpiel
	public static void setZeitSchnellstesSpiel() {
		//to-do spielerName=name;
	}
	
	public static Integer getZeitSchnellstesSpiel() {
		//to-do return spielerName;
		return minenGefunden; 
	}
	


}
