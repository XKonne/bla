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
		
		Debug.debugFeld(0, mi_zeile, mi_spalte);
				
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
			}
			
			else {
				// Feld ist aufgedeckt, do nothing (=wie Feld deaktiviert)
				Debug.debugFeld(1, 0, 0);
			}
			
			// Rechtsklick und Feld nicht aufgedeckt
			if (e.getButton() == MouseEvent.BUTTON3 && Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 1) 
			{		
				// Feld ist nicht markiert, setze Fahne
				if (Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 3) 
				{
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setIcon(new ImageIcon(getClass().getResource("img/felder/fahne.gif")));
					Debug.debugFeld(2, 0, 0);
					Spiel.countMinenMarkierung(-1);
					
					// Feld ist Mine, erhöhe Minen-Richtig-Zähler
					if (Spiel.getSpielfeldStatus(mi_zeile, mi_spalte) == -1) 
					{
						Spiel.countMineRichtig(1);
						Debug.debugFeld(3, 0, 0);
					}
					else 
					{
						Debug.debugFeld(4, 0, 0);
					}

					Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 3);
				}
				// Feld ist markiert, Spielfeld auf "nicht-aufgedeckt" zurückgesetzt
				else 
				{
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setIcon(new ImageIcon(getClass().getResource("img/felder/nicht-aufgedeckt.gif")));
					Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 0);
					
					// Fahne entfernt > Minen Markierung erhöhen
					Spiel.countMinenMarkierung(1);
					
					// Falls unter der Fahne eine Mine war, erniedrige Minen-Richtig-Zähler
					if (Spiel.getSpielfeldStatus(mi_zeile, mi_spalte) == -1)
					{
						Spiel.countMineRichtig(-1);
					}
					Debug.debugFeld(6, 0, 0);
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
		}
		// Arbeite eingetragene Felder ab
		else 
		{
			Debug.debugLeeresFeld(4, 0, 0);
			
			// Rufe Eintrag ab und decke sein Umkreis auf
			deckeUmkreisAuf(Spiel.merkeArray[i][0], Spiel.merkeArray[i][1]);
			
			Debug.debugLeeresFeld(5, i, 0);
			
			// Lösche Eintrag von dem eben betrachteten Eintrag
			Spiel.merkeArray[i][0]=0;
			Spiel.merkeArray[i][1]=0;
		}
	}		
	}

private void deckeUmkreisAuf(int z, int sp) {
	
	Debug.debugLeeresFeld(0, z, sp);
	
	// Schleifen, um 1 Zeile drüber, mittig und drunter (hr=hoch-runter) 
	for (int hr = -1; hr <= 1; hr++) {
		// ..sowie links, mittig, rechts vom Feld (lr=links-rechts)
		for (int lr = -1; lr <= 1; lr ++) {
			
			// wenn kein Rand-Feld (=Feld außerhalb des Spielfeldes)
			if (amRand(z + hr, sp + lr) == false) {
				// und es Leer ist und nicht-aufgedeckt und nicht-markiert
				if (Spiel.getSpielfeldStatus(z + hr, sp +lr) == 0 && Spiel.spielfeldGeklickt[z + hr][sp + lr] != 1 && Spiel.spielfeldGeklickt[z + hr][sp + lr] != 3) 
				{
					// füge es dem merkeArray hinzu
			 		Spiel.addMerkeArray(z + hr, sp + lr);
			 		
			 		// decke auf
					setSpielfeldStatusZuFeld(z + hr, sp + lr);
					// setze es als geklickt
					Spiel.spielfeldGeklickt[z + hr][sp + lr] = 1;
				}
				else
				{
					// Nicht Leer und keine Mine, d.h. es ist ein Zahlenfeld!
					// und es soll nicht-aufgedeckt und nicht markiert sein
					if (Spiel.getSpielfeldStatus(z + hr, sp + lr) != -1 && Spiel.spielfeldGeklickt[z + hr][sp + lr] != 1 && Spiel.spielfeldGeklickt[z + hr][sp + lr] != 3) 
					{
						// decke auf
						setSpielfeldStatusZuFeld(z + hr, sp + lr);
						// setze als geklickt
						Spiel.spielfeldGeklickt[z + hr][sp + lr] = 1;
					}
				}
			}
		}
	}
	
	} // end-deckeUmkreisAuf()

	private boolean amRand(int z, int sp) {
		
		// Rückgabe Variable
		boolean antwort=false;
		Debug.debugFeldIstRand(0, z, sp);
		
		// Wenn Zeilen kleiner-gleich 0   oder   Spalten kleiner-gleich 0
		// dann ist das Feld außerhalb des Spielfeldes
		if (z <= 0 || z > Spiel.getSpielfeldZeilen() )
		{
			Debug.debugFeldIstRand(1, z, 0);
			antwort = true;
		}
		// Wenn Zeilen größer-gleich "Spielfeld-Zeilen"   oder   Spalten größer-gleich "Spielfeld-Spalten"
		// dann ist das Feld außerhalb des Spielfeldes
		if (sp <= 0 || sp > Spiel.getSpielfeldSpalten() )
		{
			Debug.debugFeldIstRand(2, 0, sp);
			antwort = true;
		}
		
		if (Debug.debugAnAus == true && Debug.msgFeldIstRand == true) {
			System.out.println(antwort);
		}
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
			Debug.debugFeld(5, z, sp);
						
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