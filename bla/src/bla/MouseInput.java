package bla;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class MouseInput implements MouseListener {

	int mi_zeile;
	int mi_spalte;

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
		
		if (GUI_Spielfeld.Spielfeldgesperrt == false) {
			
			// Linksklick & Feld vorher nicht per Linksklick aufgedeckt
			if (e.getButton() == MouseEvent.BUTTON1 && Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 1) {	
				
				// Decke Feld auf
				setSpielfeldStatusZuFeld(mi_zeile, mi_spalte);
				
				// Feld als Mine markiert (=3), jetzt wurde es aufgedeckt, dann erhöhe restMinen um 1
				if (Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] == 3) {
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
			}
			
			// Rechtsklick und Feld nicht aufgedeckt
			if (e.getButton() == MouseEvent.BUTTON3 && Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 1) {
				
				// Feld ist nicht markiert, setze Fahne
				if (Spiel.spielfeldGeklickt[mi_zeile][mi_spalte] != 3) {
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setIcon(new ImageIcon(getClass().getResource("img/felder/fahne.gif")));
					Spiel.countMinenMarkierung(-1);
					
					// Feld ist Mine, erhöhe Minen-Richtig-Zähler
					if (Spiel.getSpielfeldStatus(mi_zeile+1, mi_spalte+1) == -1) {
						Spiel.countMineRichtig(1);
					}
					
					Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 3);
				}
				// Feld ist markiert, Spielfeld auf "nicht-aufgedeckt" zurückgesetzt
				else {
					GUI_Spielfeld.Felder[mi_zeile][mi_spalte].setIcon(new ImageIcon(getClass().getResource("img/felder/nicht-aufgedeckt.gif")));
					Spiel.setSpielfeldgeklickt(mi_zeile, mi_spalte, 0);
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