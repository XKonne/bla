package bla;

public class Debug {
	
	static boolean debugAnAus = true;
	
	static boolean msgActiveObjects = true;
	static boolean msgFeld = true;
	static boolean msgFeldIstRand = true;
	static boolean msgLeeresFeld = true;
	static boolean msgSpiel = true;
	
	/**
	 * Konsolenausgabe über Objekte, ob sie aktiv oder nicht-aktiv sind.
	 */
	public static void debugActiveObjects() 
	{
		if ( msgActiveObjects == true ) 
		{
			if (ObjectHandler.getSpiel() != null) {
				System.out.println("Obj: Spiel aktiv!");
			}
			else {
				System.out.println("Obj: Spiel nicht aktiv!");
			}
			
			if (ObjectHandler.getSpieler() != null) {
				System.out.println("Obj: Spieler aktiv!");
			}
			else {
				System.out.println("Obj: Spieler nicht aktiv!");
			}
			
			if (ObjectHandler.getGui_AddMenubar() != null) {
				System.out.println("Obj: gui_AddMenubar aktiv!");
			}
			else {
				System.out.println("Obj: gui_AddMenubar nicht aktiv!");
			}
			
			if (ObjectHandler.getGui_SpielerAuswahl() != null) {
				System.out.println("Obj: gui_SpielerAuswahl aktiv!");
			}
			else {
				System.out.println("Obj: gui_SpielerAuswahl nicht aktiv!");
			}
			
			if (ObjectHandler.getGui_Spielerprofil() != null) {
				System.out.println("Obj: gui_Spielerprofil aktiv!");
			}
			else {
				System.out.println("Obj: gui_Spielerprofil nicht aktiv!");
			}
			
			if (ObjectHandler.getGui_Spielfeld() != null) {
				System.out.println("Obj: gui_Spielfeld aktiv!");
			}
			else {
				System.out.println("Obj: gui_Spielfeld nicht aktiv!");
			}
			
			if (ObjectHandler.getGui_SpielModusBenutzer() != null) {
				System.out.println("Obj: gui_SpielModusBenutzer aktiv!");
			}
			else {
				System.out.println("Obj: gui_SpielModusBenutzer nicht aktiv!");
			}
			
			if (ObjectHandler.getGui_Start() != null) {
				System.out.println("Obj: gui_Start aktiv!");
			}
			else {
				System.out.println("Obj: gui_Start nicht aktiv!");
			}
		}
	}
	
	/**
	 * Konsolenausgabe für Klicks auf Felder[][].
	 */
	public static void debugFeld(int auswahl, int z, int sp) {
		if ( debugAnAus == true )
		{
			if ( msgFeld == true )
			{
				switch ( auswahl )
				{
					case 0: System.out.println("- - - - - - - - - -");
							System.out.println("Feld[" + z + "][" + sp + "] ausgewählt"); break;
					case 1: System.out.print("Rechtsklick "); break;
					case 2: System.out.print("> Fahne gesetzt >");; break;
					case 3: System.out.println(" Mine markiert. (Restminen: " + Spiel.getRestMinen() + ", Minen-Richtig: " + Spiel.getMinenRichtig() + ")"); break;
					case 4: System.out.println(" KEINE Mine! (Restminen: " + Spiel.getRestMinen() + ", Minen-Richtig: " + Spiel.getMinenRichtig() + ")"); break;
					case 5: System.out.println("X Feld[" + z + "][" + sp +"] aufgedeckt. Spielfeld-Status: " + Spiel.getSpielfeldStatus(z,sp)); break;
					case 6: System.out.println("> Fahne entfernt. (Restminen: " + Spiel.getRestMinen() + ", Minen-Richtig: " + Spiel.getMinenRichtig() + ")"); break;
				}
			}
		}
	}
	
	/**
	 * Konsolenausgabe für ob aktuelles Feld am Rand liegt.
	 */
	public static void debugFeldIstRand(int auswahl, int z, int sp) {
		if ( debugAnAus == true )
		{
			if ( msgFeldIstRand == true )
			{
				switch ( auswahl )
				{
					case 0: System.out.print("Überprüfe, ob Feld[" + z + "][" + sp +"] auf dem Rand liegt: "); break;
					case 1: System.out.print("!Z! = " + z + " > "); break;
					case 2: System.out.print("!SP! = " + sp + " > "); break;
				}
			}
		}
	}
	
	
	/**
	 * Konsolenausgabe für Abarbeitung leerer Felder[][].
	 */
	public static void debugLeeresFeld(int auswahl, int z, int sp) {
		if ( debugAnAus == true )
		{
			if ( msgLeeresFeld == true )
			{
				switch ( auswahl )
				{
					case 0: System.out.println("! Decke Umkreis von Feld[" + z + "][" + sp + "] auf"); break;
					case 1: System.out.println("addMerkeArray: Feld[" + z + "][" + sp + "] ist bereits vorhanden."); break;
					case 2: System.out.println("addMerkeArray[" + Spiel.getIndexMerkeArray() + "]: Feld[" + z + "][" + sp + "] zur Liste hinzugefügt."); break;
					case 3: merkeArrayAusgeben(); break;
					case 4: System.out.print("Listenaufruf >");
					// Anmerkung zu case 5: z ist bei der Übergabe anders belegt und gibt den aktuellen Index im Spiel.merkeArray an
					case 5: System.out.println("! Umkreis von ("+Spiel.merkeArray[z][0] + "," + Spiel.merkeArray[z][1] + ") abgearbeitet. Lösche Eintrag.");
				}
			}
		}
	}
	
	/**
	 * Hilsmethode für debugLeeresFeld(): Gibt Spiel.merkeArray[][] in der Konsole aus.
	 */
	private static void merkeArrayAusgeben() {
		for (int i = 0; i < Spiel.merkeArray.length; i++) {
			if (Spiel.merkeArray[i][0] == 0 && Spiel.merkeArray[i][1] == 0)
			{
				//do nothing
			}
			else {
				System.out.println("MerkeArray[" + i + "]: ("+ Spiel.merkeArray[i][0] + "," + Spiel.merkeArray[i][1] + ")");
			}
		}
		System.out.println("v v v v v v v v v v v");
	}
	
	/**
	 * Konsolenausgabe für Spiel-Informationen.
	 */
	public static void debugSpiel() {
		if ( debugAnAus == true )
		{
			if ( msgSpiel == true )
			{
				System.out.println("> > > Spielinformation");
				System.out.println("Spielmodus: " + Spiel.spielModus + " - " + Spiel.getSpielfeldSpalten() + " x " + 
										Spiel.getSpielfeldZeilen() + " (Zeilen x Spalten) = " + Spiel.getSpielfeldSpalten()*Spiel.getSpielfeldZeilen() + " Felder");
				System.out.println("> Davon Felder mit Minen:  " + Spiel.getMinenGesamt());
				System.out.println("> Davon Felder mit Zahlen: " + Spiel.countZahlenFelder);
				System.out.println("> Davon leere Felder:      " + Spiel.leereFelder);
				System.out.println("- - - - - - - - - - - - - - - - - - - -");
			}
		}
	}

}
