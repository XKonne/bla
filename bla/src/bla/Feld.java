package bla;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Feld extends JButton {

	static boolean abgearbeitet;
	
	public Feld() {
		super();
		abgearbeitet=false;
	}
	
	public boolean getAbgearbeitet() {
		return abgearbeitet;
	}
	
	public static void setAbgearbeitet(boolean set) {
		abgearbeitet=set;
	}
}