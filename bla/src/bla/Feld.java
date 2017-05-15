package bla;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Feld extends JButton {

	// Koordinaten
	static int x=-1;
	static int y=-1;
	
	// Geklickt-Status
	static int geklickt = 0;
	
	// Feld-Inhalt
	static int inhalt = 0;
	
	public Feld() {
		super();
	}
	
	public void setGeklickt(int wert) {
		geklickt = wert;
	}
	
	public int getGeklickt() {
		return geklickt;
	}
	
	public void setInhalt(int wert) {
		inhalt = wert;
	}
	
	public int getInhalt() {
		return inhalt;
	}
	
	public void setmyX(int sp) {
		x=sp;
	}
	
	public void setmyY(int z) {
		y=z;
	}
	
	public int getmyX() {
		return x;
	}
	
	public int getmyY() {
		return y;
	}
}