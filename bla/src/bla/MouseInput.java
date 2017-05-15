package bla;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class MouseInput implements MouseListener {

	int mi_zeile;
	int mi_spalte;
	
	boolean var = true;

	public MouseInput(int zeile, int spalte) {
		mi_zeile = zeile;
		mi_spalte = spalte;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		System.out.println("- - - - - - - - - - - ");
		System.out.println("Wert an MouseInput uebergeben: " + mi_zeile + " | " + mi_spalte);
		
		
		if (GUI_Spielfeld.Spielfeldgesperrt == false) {
			
			// Linksklick & Feld vorher nicht per Linksklick aufgedeckt
			if (e.getButton() == MouseEvent.BUTTON1 && Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 1) {	
				
				// Decke Feld auf
				setSpielfeldStatusZuFeld(mi_zeile, mi_spalte);
				
				// Wenn das aufgedeckte Feld leer ist, suche sein Umfeld ab
				if (Spiel.getSpielfeldStatus(mi_zeile,mi_spalte) == 0) 
				{
					deckeUmkreisAuf(mi_zeile, mi_spalte);
					arbeiteMerkeListeAb();
				}
				
				// Feld als Mine markiert (=3), jetzt wurde es aufgedeckt, dann erhöhe restMinen um 1
				if 	(Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] == 3) {
					Spiel.countMinenMarkierung(1);
				}
				
				// Aufgedecktes Feld ist eine Mine, dann aufSiegpruefen(true) (=Niederlage) aufrufen
				if (Spiel.getSpielfeldStatus(mi_zeile, mi_spalte) == -1) {
					Spiel.aufSiegpruefen(true);
				}
				// Setze Feld auf "aufgedeckt" (=1)
				Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 1);
				System.out.println("Klickstatus: " + GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getGeklickt());
			}
			
			else {
				// Feld ist aufgedeckt, do nothing (=wie Feld deaktiviert)
				System.out.println("nothing");
			}
			
			// Rechtsklick und Feld nicht aufgedeckt
			if (e.getButton() == MouseEvent.BUTTON3 && Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 1) 
			{		
				// Feld ist nicht markiert, setze Fahne
				if (Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 3) 
				{
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setIcon(new ImageIcon(getClass().getResource("img/felder/fahne.gif")));
					System.out.println("Fahne auf " +mi_zeile+","+mi_spalte);
					Spiel.countMinenMarkierung(-1);
					
					// Feld ist Mine, erhöhe Minen-Richtig-Zähler
					if (Spiel.getSpielfeldStatus(mi_zeile, mi_spalte) == -1) 
					{
						Spiel.countMineRichtig(1);
						System.out.println("Mine richtig markiert");
					}

					Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 3);
				}
				// Feld ist markiert, Spielfeld auf "nicht-aufgedeckt" zurückgesetzt
				else 
				{
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setIcon(new ImageIcon(getClass().getResource("img/felder/nicht-aufgedeckt.gif")));
					Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 0);
					Spiel.countMinenMarkierung(1);
				}
			}
		}
		Spiel.aufSiegpruefen(false);
	} // end-mousePressed

	private void arbeiteMerkeListeAb() {
	
	// Rufe leere Felder in "merkeArray" auf
	for (int i=0; i < Spiel.merkeArray.length; i++) {
		
		// Ignoriere falls 0,0 im Array steht (=leerer Eintrag)
		if (Spiel.merkeArray[i][0] == 0 && Spiel.merkeArray[i][1] == 0) 
		{
			// do nothing
			System.out.println("0,0 mache nichts in Liste");
		}
		// Arbeite eingetragene Felder ab
		else 
		{
			System.out.println("Listenaufruf");
			// Rufe Eintrag ab und decke sein Umkreis auf
			deckeUmkreisAuf(Spiel.merkeArray[i][0], Spiel.merkeArray[i][1]);
			System.out.println("Alten Eintrag negieren");
			// Lösche Eintrag von dem eben betrachteten Eintrag
			Spiel.merkeArray[i][0]=0;
			Spiel.merkeArray[i][1]=0;
		}
	}		
	}

private void deckeUmkreisAuf(int z, int sp) {
	System.out.println("Decke Umkreis auf von: " + z + " | " + sp);
	// Zeile über dem Feld
	// X - -
	// - 0 -
	// - - -
	if (amRand(z-1, sp-1) == false) 
	{
		if (Spiel.getSpielfeldStatus(z-1,sp-1) == 0 && Spiel.spielfeldGeklickt[z-1][sp-1] != 1 && Spiel.spielfeldGeklickt[z-1][sp-1] != 3) 
		{
	 		Spiel.addMerkeArray(z-1, sp-1);
			setSpielfeldStatusZuFeld(z-1, sp-1);
			Spiel.spielfeldGeklickt[z-1][sp-1] = 1;
		}
		else
		{
			if (Spiel.getSpielfeldStatus(z-1,sp-1) != -1 && Spiel.spielfeldGeklickt[z-1][sp-1] != 1 && Spiel.spielfeldGeklickt[z-1][sp-1] != 3) 
			{
				if (amRand(z-1, sp-1) == false) 
				{
					setSpielfeldStatusZuFeld(z-1, sp-1);
					Spiel.spielfeldGeklickt[z-1][sp-1] = 1;
				}
			}
		}
	}
	// - X -
	// - 0 -
	// - - -
	if (amRand(z-1, sp) == false) 
	{
		if (Spiel.getSpielfeldStatus(z-1,sp) == 0 && Spiel.spielfeldGeklickt[z-1][sp] != 1 && Spiel.spielfeldGeklickt[z-1][sp] != 3) 
		{
			Spiel.addMerkeArray(z-1, sp);
			setSpielfeldStatusZuFeld(z-1, sp);
			Spiel.spielfeldGeklickt[z-1][sp] = 1;
		}
		else 
		{
			if (Spiel.getSpielfeldStatus(z-1,sp) != -1 && Spiel.spielfeldGeklickt[z-1][sp] != 1 && Spiel.spielfeldGeklickt[z-1][sp] != 3) 
			{
				if (amRand(z-1, sp) == false) 
				{
					setSpielfeldStatusZuFeld(z-1, sp);
					Spiel.spielfeldGeklickt[z-1][sp] = 1;
				}
			}
		}
	}
	// - - X
	// - 0 -
	// - - -
	if (amRand(z-1, sp+1) == false) 
	{
		if (Spiel.getSpielfeldStatus(z-1,sp+1) == 0 && Spiel.spielfeldGeklickt[z-1][sp+1] != 1 && Spiel.spielfeldGeklickt[z-1][sp+1] != 3) 
		{
			Spiel.addMerkeArray(z-1, sp+1);
			setSpielfeldStatusZuFeld(z-1, sp+1);
			Spiel.spielfeldGeklickt[z-1][sp+-1] = 1;
		}
		else 
		{
			if (Spiel.getSpielfeldStatus(z-1,sp+1) != -1 && Spiel.spielfeldGeklickt[z-1][sp+1] != 1 && Spiel.spielfeldGeklickt[z-1][sp+1] != 3) 
			{
				if (amRand(z-1, sp+1) == false) 
				{
					setSpielfeldStatusZuFeld(z-1, sp+1);
					Spiel.spielfeldGeklickt[z-1][sp+1] = 1;
				}
			}
		}
	}
	// Zeile mit Feld
	// - - -
	// X 0 -
	// - - -
	if (amRand(z, sp-1) == false) 
	{
		if (Spiel.getSpielfeldStatus(z,sp-1) == 0 && Spiel.spielfeldGeklickt[z][sp-1] != 1 && Spiel.spielfeldGeklickt[z][sp-1] != 3) 
		{
			Spiel.addMerkeArray(z, sp-1);
			setSpielfeldStatusZuFeld(z, sp-1);
			Spiel.spielfeldGeklickt[z][sp-1] = 1;
		}
		else 
		{
			if (Spiel.getSpielfeldStatus(z,sp-1) != -1 && Spiel.spielfeldGeklickt[z][sp-1] != 1 && Spiel.spielfeldGeklickt[z][sp-1] != 3) 
			{
				if (amRand(z, sp-1) == false) 
				{
					setSpielfeldStatusZuFeld(z, sp-1);
					Spiel.spielfeldGeklickt[z][sp-1] = 1;
				}
			}
		}
	}
	// - - -
	// - 0 X
	// - - -
	if (amRand(z, sp+1) == false) 
	{
		if (Spiel.getSpielfeldStatus(z,sp+1) == 0 && Spiel.spielfeldGeklickt[z][sp+1] != 1 && Spiel.spielfeldGeklickt[z][sp+1] != 3) 
		{
			Spiel.addMerkeArray(z, sp+1);
			setSpielfeldStatusZuFeld(z, sp+1);
			Spiel.spielfeldGeklickt[z][sp+1] = 1;
		}
		else 
		{
			if (Spiel.getSpielfeldStatus(z,sp+1) != -1 && Spiel.spielfeldGeklickt[z][sp+1] != 1 && Spiel.spielfeldGeklickt[z][sp+1] != 3) 
			{
				if (amRand(z, sp+1) == false) 
				{
					setSpielfeldStatusZuFeld(z, sp+1);
					Spiel.spielfeldGeklickt[z][sp+1] = 1;
				}
			}
		}
	}
	// Zeile unter dem Feld
	// - - -
	// - 0 -
	// X - -
	if (amRand(z+1, sp-1) == false) 
	{
		if (Spiel.getSpielfeldStatus(z+1,sp-1) == 0 && Spiel.spielfeldGeklickt[z+1][sp-1] != 1 && Spiel.spielfeldGeklickt[z+1][sp-1] != 3) 
		{
			Spiel.addMerkeArray(z+1, sp-1);
			setSpielfeldStatusZuFeld(z+1, sp-1);
			Spiel.spielfeldGeklickt[z+1][sp-1] = 1;
		}
		else 
		{
			if (Spiel.getSpielfeldStatus(z+1,sp-1) != -1 && Spiel.spielfeldGeklickt[z+1][sp-1] != 1 && Spiel.spielfeldGeklickt[z+1][sp-1] != 3) 
			{
				if (amRand(z+1, sp-1) == false) 
				{
					setSpielfeldStatusZuFeld(z+1, sp-1);
					Spiel.spielfeldGeklickt[z+1][sp-1] = 1;
				}
			}
		}
	}
	// - - -
	// - 0 -
	// - X -
	if (amRand(z+1, sp) == false) 
	{
		if (Spiel.getSpielfeldStatus(z+1,sp) == 0 && Spiel.spielfeldGeklickt[z+1][sp] != 1 && Spiel.spielfeldGeklickt[z+1][sp] != 3) 
		{
			if (amRand(z+1, sp) == false) 
			{
				Spiel.addMerkeArray(z+1, sp);
				setSpielfeldStatusZuFeld(z+1, sp);
				Spiel.spielfeldGeklickt[z+1][sp] = 1;
			}
		}
		else 
		{
			if (Spiel.getSpielfeldStatus(z+1,sp) != -1 && Spiel.spielfeldGeklickt[z+1][sp] != 1 && Spiel.spielfeldGeklickt[z+1][sp] != 3) 
			{
				if (amRand(z+1, sp) == false) 
				{
					setSpielfeldStatusZuFeld(z+1, sp);
					Spiel.spielfeldGeklickt[z+1][sp] = 1;
				}
			}
		}
	}
	// - - -
	// - 0 -
	// - - X
	if (amRand(z+1, sp+1) == false) 
	{
		if (Spiel.getSpielfeldStatus(z+1,sp+1) == 0 && Spiel.spielfeldGeklickt[z+1][sp+1] != 1 && Spiel.spielfeldGeklickt[z+1][sp+1] != 3) 
		{
			Spiel.addMerkeArray(z+1, sp+1);
			setSpielfeldStatusZuFeld(z+1, sp+1);
			Spiel.spielfeldGeklickt[z+1][sp+1] = 1;
		}
		else
		{
			if (Spiel.getSpielfeldStatus(z+1,sp+1) != -1 && Spiel.spielfeldGeklickt[z+1][sp+1] != 1 && Spiel.spielfeldGeklickt[z+1][sp+1] != 3) 
			{
				if (amRand(z+1, sp+1) == false) 
				{
					setSpielfeldStatusZuFeld(z+1, sp+1);
					Spiel.spielfeldGeklickt[z+1][sp+1] = 1;
				}
			}
		}
	}
	System.out.println("Decke Umkreis auf - FERTIG");
	} // end-deckeUmkreisAuf()

	private boolean amRand(int z, int sp) {
		
		// Rückgabe Variable
		boolean antwort=false;
		System.out.println("Überprüfe ob rand: " +"("+ z +"_"+ sp+")");
		
		// Wenn Zeilen kleiner-gleich 0   oder   Spalten kleiner-gleich 0
		// dann ist das Feld außerhalb des Spielfeldes
		if (z <= 0 || sp <= 0)
		{
			System.out.println("ACHTUNG !!! Rand bei Z");
			antwort = true;
		}
		// Wenn Zeilen größer-gleich "Spielfeld-Zeilen"   oder   Spalten größer-gleich "Spielfeld-Spalten"
		// dann ist das Feld außerhalb des Spielfeldes
		if (z > Spiel.getSpielfeldZeilen() || sp > Spiel.getSpielfeldSpalten() )
		{
			System.out.println("ACHTUNG !!! Rand bei SP");
			antwort = true;
		}
		System.out.println(antwort);
		return antwort;
	}

	
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	

	// Deckt einzelnes Feld auf und ordnet ihm sein passenden Status (=Bild) zu
	public void setSpielfeldStatusZuFeld(int z, int sp) {
		
		// Prüfe ob Feld noch im Spielfeld liegt
		if (amRand(z,sp) == false)
		{
			// Decke das Feld mit dem entsprecheneden Bild auf
			switch (Spiel.getSpielfeldStatus(z,sp))
			{
				case -1: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-mine.gif"))); break;
				case 0: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-leer.gif"))); break;
				case 1: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-1.gif"))); break;
				case 2: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-2.gif"))); break;
				case 3: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-3.gif"))); break;
				case 4: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-4.gif"))); break;
				case 5: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-5.gif"))); break;
				case 6: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-6.gif"))); break;
				case 7: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-7.gif"))); break;
				case 8: GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-8.gif"))); break;
			}
		}
	}
	
	
}