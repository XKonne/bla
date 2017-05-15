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
		
		System.out.println("X X X X X X X X X X ");
		
		for (int z = 0; z < Spiel.getSpielfeldZeilen(); z++) {
			for (int sp = 0; sp < Spiel.getSpielfeldSpalten(); sp++) {
				if ( e.getSource() == GUI_Spielfeld.Felder[mi_spalte][mi_zeile]) {
					System.out.println("xFeld x: " + GUI_Spielfeld.Felder[z][sp].getmyX() + " , y: " + GUI_Spielfeld.Felder[z][sp].getmyY());
					System.out.println("xFeld Geklickt: " + GUI_Spielfeld.Felder[z][sp].getGeklickt());
					
				}
			}
		}
		
		System.out.println("- - - - - - - - - - - ");
		
		System.out.println("Wert an MouseInput uebergeben: " + mi_zeile + " | " + mi_spalte);
		
		System.out.println("Felder x: " + GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getmyX() + " , Felder y: " + GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getmyY());
		
		System.out.println("Klickstatus Anfang: " + GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getGeklickt());
		
		if (GUI_Spielfeld.Spielfeldgesperrt == false) {
			
			// Linksklick & Feld vorher nicht per Linksklick aufgedeckt
//								if (e.getButton() == MouseEvent.BUTTON1 && Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 1) {	
			if (e.getButton() == MouseEvent.BUTTON1 && GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getGeklickt() != 1) {	
				// Decke Feld auf
				System.out.println("> " + mi_zeile + " | " + mi_spalte);
				setSpielfeldStatusZuFeld(mi_zeile, mi_spalte);
				
				//deckeAuf(mi_zeile, mi_spalte);
				
//				if (Spiel.getSpielfeldStatus(mi_zeile+1,mi_spalte+1) == 0) {
//					umkreis(mi_zeile, mi_spalte);
//					aufdeckenHoch(mi_zeile, mi_spalte);
//					aufdecken(mi_zeile, mi_spalte);
//					aufdeckenRunter(mi_zeile, mi_spalte);
//				}
				
				// Feld als Mine markiert (=3), jetzt wurde es aufgedeckt, dann erhˆhe restMinen um 1
//									if 	(Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] == 3) {
				if (GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getGeklickt() == 3) {
					Spiel.countMinenMarkierung(1);
				}
				
				// Aufgedecktes Feld ist eine Mine, dann aufSiegpruefen(true) (=Niederlage) aufrufen
				if (Spiel.getSpielfeldStatus(mi_zeile+1, mi_spalte+1) == -1) {
					Spiel.aufSiegpruefen(true);
				}
				// Setze Feld auf "aufgedeckt" (=1)
				// 					Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 1);
				GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setGeklickt(1);
				System.out.println("Klickstatus: " + GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getGeklickt());
			}
			
			else {
				// Feld ist aufgedeckt, do nothing (=wie Feld deaktiviert)
			}
			
			// Rechtsklick und Feld nicht aufgedeckt
//			if (e.getButton() == MouseEvent.BUTTON3 && Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 1) {
			if (e.getButton() == MouseEvent.BUTTON3 && GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getGeklickt() != 1) {
				
				// Feld ist nicht markiert, setze Fahne
//				if (Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 3) {
				if (GUI_Spielfeld.Felder[mi_zeile][mi_spalte].getGeklickt() != 3) {
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setIcon(new ImageIcon(getClass().getResource("img/felder/fahne.gif")));
					Spiel.countMinenMarkierung(-1);
					
					// Feld ist Mine, erhˆhe Minen-Richtig-Z‰hler                                hier +1
					if (Spiel.getSpielfeldStatus(mi_zeile+1, mi_spalte+1) == -1) {
						Spiel.countMineRichtig(1);
					}
					
					//Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 3);
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setGeklickt(3);
				}
				// Feld ist markiert, Spielfeld auf "nicht-aufgedeckt" zur¸ckgesetzt
				else {
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setIcon(new ImageIcon(getClass().getResource("img/felder/nicht-aufgedeckt.gif")));
					//Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 0);
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setGeklickt(0);
					Spiel.countMinenMarkierung(1);
				}
			}
		}
		
		
		
		// alter code

//		if (test.Spielfeldgesperrt == false) {
//			if (e.getButton() == MouseEvent.BUTTON1 && test.Spielfeldgeklickt[buttonnummer] != 5) {
//				Test.setText(buttonnummer, test.a_btnText[buttonnummer]);
//				Test.setDisabled(buttonnummer);
//				if (test.Spielfeldgeklickt[buttonnummer] == 2) {
//					spiel.countMinenMarkierung(1);
//				}
//				if (test.a_btnText[buttonnummer] == "M") {
////					test.mineAufgedeckt();
//					test.aufSiegpruefen(true);
//				}
//				Test.setSpielfeldgeklickt(buttonnummer, 5);
//			}
//
//			if (e.getButton() == MouseEvent.BUTTON3 && test.Spielfeldgeklickt[buttonnummer] != 5) {
//
//				if (test.Spielfeldgeklickt[buttonnummer] == 0) {
//					Test.setText(buttonnummer, "*");
//					if (test.Spielfeldgeklickt[buttonnummer] != 2) {
//						if (test.a_btnText[buttonnummer] == ("M")) {
//							spiel.countMineRichtig(1);
//						}
//						spiel.countMinenMarkierung(-1);
//					}
//					Test.setSpielfeldgeklickt(buttonnummer, 2);
//				} else {
//					Test.setText(buttonnummer, ".");
//					if (test.a_btnText[buttonnummer] == ("M")) {
//						spiel.countMineRichtig(-1);
//					}
//					spiel.countMinenMarkierung(1);
//					Test.setSpielfeldgeklickt(buttonnummer, 0);
//				}
//			}
//
//		}
		Spiel.aufSiegpruefen(false);
	} // end-mousePressed

//	private void umkreis(int mi_zeile, int mi_spalte) {
//		if (mi_spalte >= 1) {
//		setSpielfeldStatusZuFeld(mi_zeile, mi_spalte-1);
//		Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte-1, 1);
//		}
//		if (mi_spalte+1 < Spiel.getSpielfeldSpalten()) {
//		setSpielfeldStatusZuFeld(mi_zeile, mi_spalte+1);
//		Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte+1, 1);
//		}
//	}
	
	private void aufdeckenHoch(int mi_zeile, int mi_spalte) {
		for (int i=mi_zeile; i>-1; i--) {
			if (Spiel.getSpielfeldStatus(i, mi_spalte) == 0 ) {
				linieRechts(i, mi_spalte);
				linieLinks(i, mi_spalte);
			}
			else {
				//setSpielfeldStatusZuFeld(i-1, mi_spalte);
				i=0;
			}
		}
	}
	
	private void aufdeckenRunter(int mi_zeile, int mi_spalte) {
		if (Spiel.getSpielfeldStatus(mi_zeile+1, mi_spalte-2) != -1 && mi_spalte-1 > 0 ) {
			setSpielfeldStatusZuFeld(mi_zeile,mi_spalte-1);
		}
		
		for (int i=mi_zeile; i<Spiel.getSpielfeldZeilen(); i++) {
			if (Spiel.getSpielfeldStatus(i, mi_spalte) == 0 ) {
				linieRechts(i, mi_spalte);
				linieLinks(i, mi_spalte);
			}
			else {
				i=Spiel.getSpielfeldSpalten();
			}
		}
	}
	
//	void open(int x, int y)
//	{
//	† † if (outOfBounds(x,y)) return;
//	† † if (statusOf(x,y)==OPEN) return;
//	† † changeStatusTo(x,y,OPEN);
//	† † if (hasNeighborsWithMines(x,y)) return; 
//	† † open(x-1,y);
//	† † open(x+1,y);
//	† † open(x,y-1);
//	† † open(x,y+1);
//	}
	
//	private void aufdecken(int x, int y) {
//		boolean var=false;
//		
//		if (x < 0) {
//			var=true;
//		}
//		if (var == false && Spiel.spielfeldGeklickt[x+1][y+1] != 3) {
//			if (x-1 != 0 ) {
//				// oberes feld
//				if (Spiel.getSpielfeldStatus(x-1,y) != 0) {
//					setSpielfeldStatusZuFeld(x-1, y);
//					Spiel.setSpielfeldgeklickt(x-1, y, 3);
//					System.out.println("Feld+ aufgedeckt: " + (x+1) + " , " + y);
//				}
//				// unteres feld
//				if (Spiel.getSpielfeldStatus(x+1,y) != 0) {
//					setSpielfeldStatusZuFeld(x+1, y);
//					Spiel.setSpielfeldgeklickt(x-1, y, 3);
//					System.out.println("Feld- aufgedeckt: " + (x-1) + " , " + y);
//				}
//			}
//			Spiel.setSpielfeldgeklickt(x-1, y, 3);
//			System.out.println("Feld aufgedeckt: " + x + " , " + y);
//			aufdecken(x-2, y);
//		}
//	}
	
//	procedure TMinesweeperForm.Play(X, Y: integer); // PanelsMouseUp, Button mbLeft;
//	var
//	††I, J: integer;
//	begin
//	††if InPanelGrid(X, Y) // Wenn im Raster;
//	††††and FPanels[X, Y].Enabled // und noch nicht aufgedeckt;
//	††††and (FPanels[X, Y].FlankingMinesCount >= 0) // und keine Mine; (Mine = -1)
//	††††and (FPanels[X, Y].Caption <> cFlagSign) then // und keine Fahne;
//	††begin
//	††††if FPanels[X, Y].FlankingMinesCount > 0 then // Wenn Zahl > Null;
//	††††††FPanels[X, Y].Caption := IntToStr(FPanels[X, Y].FlankingMinesCount)
//	††††else
//	††††††FPanels[X, Y].Caption := ''; // Null;
//	††††FPanels[X, Y].Color := clWindow; // farblich kennzeichnen und
//	††††FPanels[X, Y].Enabled := false; // -> aufdecken;
//	††††if GetFlankingMinesCount(X, Y) = 0 then // Wenn keine NachbarMine;
//	††††††for I := -1 to 1 do
//	††††††††for J := -1 to 1 do
//	††††††††††if ((I <> 0) or (J <> 0)) then // Alle auﬂer X / Y;
//	††††††††††††Play(X + I, Y + J);
//	††end;
//	end;
	
//	private void deckeAuf(int x, int y) {
//		var = true;
//		
//		// Liegt x,y noch im Spielfeld?
//		if (x<0) {
//			var = false;
//			System.out.println("x zu klein " +x+","+y);
//		}
//		if(x>Spiel.getSpielfeldZeilen()-1) {
//			var = false;
//			System.out.println("x zu groﬂ " +x+","+y);
//		}
//		if (y<0) {
//			var = false;
//			System.out.println("y zu klein " +x+","+y);
//		}
//		if (y>Spiel.getSpielfeldSpalten()-1) {
//			var = false;
//			System.out.println("y zu groﬂ " +x+","+y);
//		}		
//		
////		if (
////				(x < 0 || x > Spiel.getSpielfeldZeilen()-1) &&
////				(y < 0 || y > Spiel.getSpielfeldSpalten()-1)) {
////			System.out.println("auﬂerhalb spielfeld " +x+","+y);
////			var = false;
////		}
//		if (var == true) {	// Feld liegt im Spielfeld
//			if (Spiel.getSpielfeldStatus(x+1, y+1) != -1	// keine Mine
//					&& Spiel.getSpielfeldStatus(x+1, y+1) != 0
//					//&& Spiel.spielfeldGeklickt[x][y] != 1	// UND nicht aufgedeckt (angeklickt)
//					//&& Spiel.spielfeldGeklickt[x][y] != 3
//					) 
//			{  // UND nicht markiert
//				
//					System.out.println("if");
//				
//					// wenn keine mine oder nicht leer (also Zahlen)
//					//if (Spiel.getSpielfeldStatus(x+1, y+1) != -1 && Spiel.getSpielfeldStatus(x+1, y+1) != 0) {
//						// aufdecken
//						setSpielfeldStatusZuFeld(x, y);
//						// geklickt setzen
//						Spiel.setSpielfeldgeklickt(x, y, 1);
//					//}
//			}
//			else {
//				System.out.println("else");
//				// es ist ein leeres feld, decke auf
//				if (Spiel.getSpielfeldStatus(x+1, y+1) == 0 && y>=0) {
//					System.out.println("else-if; leeres Feld decke auf " + x + "," +y);
//					setSpielfeldStatusZuFeld(x, y);
//					// und auf aufgedeckt/geklickt setzen
//					Spiel.setSpielfeldgeklickt(x, y, 1);
//				}
//				
//				// wenn keine mine in den umliegenden feldern ist
//				if (mineImUMkreis(x,y) == false) {
//					System.out.println("else-if 2; umkreis aufrufen " + x + "," +y);
//					// betrachte alle umliegenden felder
//					for (int i=-1; i<=1; i++) {
//						for (int j=-1; j<=1; j++) {
//							if (i != 0 || j != 0) {	// alle auﬂer das ausgew‰hlte feld
//								System.out.println("else-if 2 schleifen;  " + (x+i) + "," +(y+j));
//								deckeAuf(x+i, y+j);
//							}
//						}
//					}
//				}
//			} // else-end
//			
//		}
//		System.out.println("nix");
//		
//	}
	
	private boolean mineImUMkreis(int x, int y) {
		
		boolean mineGefunden=false;
		
		if (x < 0 || x > Spiel.getSpielfeldZeilen()-1
				&& (y < 0 || y > Spiel.getSpielfeldSpalten()-1)) {
		
		if (Spiel.getSpielfeldStatus(x-1, y-1) == -1 ||
				Spiel.getSpielfeldStatus(x, y) == -1 ||
				Spiel.getSpielfeldStatus(x-1, y+1) == -1 ||
				Spiel.getSpielfeldStatus(x-1, y) == -1 ||
				Spiel.getSpielfeldStatus(x+1, y) == -1 ||
				Spiel.getSpielfeldStatus(x-1, y+1) == -1 ||
				Spiel.getSpielfeldStatus(x, y) == -1 ||
				Spiel.getSpielfeldStatus(x+1, y+1) == -1 ) {
					mineGefunden = true;
				}
		}
	return mineGefunden;
}

	private void linieRechts(int mi_zeile, int mi_spalte) {
		if (Spiel.getSpielfeldStatus(mi_zeile+1, mi_spalte+2) != -1 && mi_spalte-1 > 0) {
			setSpielfeldStatusZuFeld(mi_zeile, mi_spalte+1);
		}
		
		for (int i=mi_spalte; i>-1; i--) {
			if (Spiel.getSpielfeldStatus(mi_zeile+1, i) == 0) {
				setSpielfeldStatusZuFeld(mi_zeile, i);
				if (mi_zeile-1 > 0) {
					setSpielfeldStatusZuFeld(mi_zeile-1, i);
				}
				if (mi_zeile+1 < Spiel.getSpielfeldZeilen()) {
					setSpielfeldStatusZuFeld(mi_zeile+1, i);
				}
			}
			else {
				setSpielfeldStatusZuFeld(mi_zeile, i);
				setSpielfeldStatusZuFeld(mi_zeile,i-1);
				i=0;
			}
		}
	}
	
	private void linieLinks(int mi_zeile, int mi_spalte) {
		for (int i=mi_spalte; i<Spiel.getSpielfeldSpalten(); i++) {
			if (Spiel.getSpielfeldStatus(mi_zeile+1, i) == 0) {
				setSpielfeldStatusZuFeld(mi_zeile, i);
				
				if (mi_zeile-1 > 0) {
					setSpielfeldStatusZuFeld(mi_zeile-1, i);
				}
				if (mi_zeile+1 < Spiel.getSpielfeldZeilen()) {
					setSpielfeldStatusZuFeld(mi_zeile+1, i);
				}
				
			}
			else {
				//setSpielfeldStatusZuFeld(mi_zeile, i);
				//setSpielfeldStatusZuFeld(mi_zeile,i);
				i=Spiel.getSpielfeldSpalten();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	

	// Deckt einzelnes Feld auf und ordnet ihm sein passenden Status (=Bild) zu
	public void setSpielfeldStatusZuFeld(int z, int sp) {
		
		switch (Spiel.getSpielfeldStatus(z+1,sp+1))
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