package bla;

public class Spieler {
	
	static String spielerName;
	static int spieleGespielt;
	static int spieleGewonnen;
	static int spieleGewonnenProzent;
	static int minenGefunden;
	static int zeitGesamtStd;
	static int zeitSchnellstesSpiel;
	static int profilBild;

	public Spieler() {
		super();
		spielerName="";
		spieleGespielt=0;
		spieleGewonnen=0;
		spieleGewonnenProzent=0;
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
	}
	
	public static Integer getSpieleGespielt() {
		return spieleGespielt;
	}
	
	//SpieleGewonnen
	public static void setSpieleGewonnen() {
		spieleGewonnen=spieleGewonnen+1;
	}
	
	public static Integer getSpieleGewonnen() {
		return spieleGewonnen;
	}
	
	//SpieleGewonnenProzent
	public static void setSpieleGewonnenProzent() {
		spieleGewonnenProzent=spieleGewonnen/spieleGespielt*100;
	}
	
	public static Integer getSpieleGewonnenProzent() {
		return spieleGewonnenProzent;
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
