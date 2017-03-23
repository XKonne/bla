package bla;

public class Spieler {
	
	static String spielerName;
	int spieleGespielt;
	int spieleGewonnen;
	int spieleGewonnenProzent;
	int MinenGefunden;
	int zeitGesamtStd;
	int zeitSchnellstesSpiel;

	public Spieler() {
		super();
		// TODO Auto-generated constructor stub
		spielerName="";
		spieleGespielt=0;
		spieleGewonnen=0;
		spieleGewonnenProzent=0;
		MinenGefunden=0;
		zeitGesamtStd=0;
		zeitSchnellstesSpiel=0;
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//	}
	
	public static void setSpielerName(String name) {
		spielerName=name;
	}
	
	public static String getSpielerName() {
		return spielerName;
	}

}
