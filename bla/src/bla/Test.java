package bla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Spielprojekt "Seawolf" GameApp-Name <not set/actually nameless>
 *
 * @version B.1.2 vom 18.03.2017
 * @author XKonne
 * @author p0sE-Git
 * 
 */

public class Test extends JFrame {
	
	// Attribute
	static boolean Spielfeldgesperrt = true;
	static int minen = 3;
	static int mine = 3;
	private JButton ButtonSpielNeustart = new JButton();
	private JButton ButtonResetSpielfeld = new JButton();
	private JButton ButtonSpielernameOK = new JButton();
	private JLabel jLabel1 = new JLabel();
	private JTextField jTextField1 = new JTextField();
	
	private static JLabel RestMinen = new JLabel();
	static int minerichtig=0;
	private static JLabel MinenRichtig = new JLabel();

	// Arrays
	// 0=Spielfeld noch nicht geklickt --- 1=Spielfeld bereits 1x gedrückt
	// 5=Linksklick. Spielfeld aufgedeckt. Keine weiteren Aktion möglich
	static int[] Spielfeldgeklickt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // i=1..16
	static JButton[] buttons = new JButton[16];
	// MFeld = Minenfeld Testspielfeld 4x4
	// 4x4-Raster
	// 1 2 3 4 <=> - - 1 M
	// 5 6 7 8 <=> 1 2 3 2
	// 9 10 11 12 <=> 1 M M 1
	// 13 14 15 16 <=> 1 2 2 1
	static String a_btnText[] = { "-", "-", "1", "M", "1", "2", "3", "2", "1", "M", "M", "1", "1", "2", "2", "1" };
	String Spielername;
	// Ende Attribute

	public Test() {
		// Frame-Initialisierung
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 330;
		int frameHeight = 300;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("GameApp-Name actually not set");
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		String a_btnNames[] = { "MFeld1", "MFeld2", "MFeld3", "MFeld4", "MFeld5", "MFeld6", "MFeld7", "MFeld8",
				"MFeld9", "MFeld10", "MFeld11", "MFeld12", "MFeld13", "MFeld14", "MFeld15", "MFeld16" };
		
		// Anfang Komponenten
		// Oberflaeche
		ButtonResetSpielfeld.setBounds(10, 200, 100, 30);
		ButtonResetSpielfeld.setText("Reset Spiel");
		ButtonResetSpielfeld.setMargin(new Insets(2, 2, 2, 2));

		// Spielername-Eingabe
		jTextField1.setBounds(170, 10, 100, 30);
		cp.add(jTextField1);

		ButtonSpielernameOK.setBounds(280, 10, 30, 30);
		ButtonSpielernameOK.setText("Go");
		ButtonSpielernameOK.setMargin(new Insets(2, 2, 2, 2));
		ButtonSpielernameOK.setEnabled(true);

		ButtonSpielNeustart.setBounds(120, 200, 100, 30);
		ButtonSpielNeustart.setText("Neustarten");
		ButtonSpielNeustart.setMargin(new Insets(2, 2, 2, 2));
		ButtonSpielNeustart.setEnabled(false);

		// Spielfeld
		
		// Buttons bauen
		for (int i = 0; i < buttons.length; i++) {
			
			buttons[i] = new JButton(a_btnNames[i]);
			// buttons[i].setBounds(10, 10, 30, 30);
			buttons[i].setText(".");
			buttons[i].setMargin(new Insets(2, 2, 2, 2));
			buttons[i].setEnabled(false);
			
			MouseInput mouse = new MouseInput(i);
			buttons[i].addMouseListener(mouse);
			
			cp.add(buttons[i]);

		}

		buttons[0].setBounds(10, 10, 30, 30);
		buttons[1].setBounds(50, 10, 30, 30);
		buttons[2].setBounds(90, 10, 30, 30);
		buttons[3].setBounds(130, 10, 30, 30);
		buttons[4].setBounds(10, 50, 30, 30);
		buttons[5].setBounds(50, 50, 30, 30);
		buttons[6].setBounds(90, 50, 30, 30);
		buttons[7].setBounds(130, 50, 30, 30);
		buttons[8].setBounds(10, 90, 30, 30);
		buttons[9].setBounds(50, 90, 30, 30);
		buttons[10].setBounds(90, 90, 30, 30);
		buttons[11].setBounds(130, 90, 30, 30);
		buttons[12].setBounds(10, 130, 30, 30);
		buttons[13].setBounds(50, 130, 30, 30);
		buttons[14].setBounds(90, 130, 30, 30);
		buttons[15].setBounds(130, 130, 30, 30);

		jLabel1.setBounds(170, 10, 100, 30);
		jLabel1.setVisible(false);
		cp.add(jLabel1);

	    RestMinen.setBounds(170, 50, 100, 30);
	    RestMinen.setVisible(true);
	    RestMinen.setText("Minen zähler "+Integer.toString(minen));
	    cp.add(RestMinen);
	    
	    MinenRichtig.setBounds(170, 80, 100, 30);
	    MinenRichtig.setVisible(true);
	    MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));
	    cp.add(MinenRichtig);
		
		
		ButtonSpielNeustart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonSpielNeustart_ActionPerformed(evt);
			}
		});

		// Methode zum Button-klick-ausfuehren
		ButtonResetSpielfeld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonResetSpielfeld_ActionPerformed();
			}
		});

		ButtonSpielernameOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonSpielernameOK_ActionPerformed();
			}
		});

		// Komponenten erzeugen
		cp.add(ButtonSpielNeustart);
		cp.add(ButtonResetSpielfeld);
		cp.add(ButtonSpielernameOK);
		// Ende Komponenten

		setVisible(true);
	} // end of public Test

	
	// Anfang Methoden
	public static void main(String[] args) {
		new Test();
	}

	public void ButtonSpielNeustart_ActionPerformed(ActionEvent evt) {
		// TODO hier Quelltext einfÃ¼gen
		// Spielfeld Oberflaeche zu beginn

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(".");
		}

		// Spielfeld aktivieren
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
		}

		Spielfeldgesperrt = false;

		// Spielfeldgeklickt Array zurücksetzen
		for (int i = 0; i < Spielfeldgeklickt.length; i++) {
			Spielfeldgeklickt[i] = 0;
		}
		minen=3;
		RestMinen.setText("Minen zähler "+Integer.toString(minen));
		minerichtig=0;
		MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	
	}

	public void ButtonSpielernameOK_ActionPerformed() {
		/*
		 * !!! Noch zu implementieren "Go"-Button erst aktiviert / klickbar,
		 * wenn mindestens 3 Zeichen eingegeben sind
		 */

		// Spielfeld aktivieren
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
		}

		// Name einlesen
		Spielername = jTextField1.getText();
		jTextField1.setVisible(false);
		jLabel1.setText(Spielername);
		jLabel1.setVisible(true);

		ButtonSpielNeustart.setEnabled(true);
		Spielfeldgesperrt = false;

		// Button zum Schluss deaktivieren
		ButtonSpielernameOK.setEnabled(false);

	} // end of jButton1_ActionPerformed

	public void ButtonResetSpielfeld_ActionPerformed() {

		// TODO hier Quelltext einfÃ¼gen
		// Spielfeld Oberflaeche zu beginn
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(".");
		}

		// Spielfeld deaktivieren
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
		}

		Spielfeldgesperrt = true;
		// Spielfeldgeklickt Array zurücksetzen
		for (int i = 0; i < 17; i++) {
			Spielfeldgeklickt[i] = 0;
		}

		// Spielername zurücksetze, Label weg, Eingabe da
		Spielername = "none";
		jTextField1.setVisible(true);
		jTextField1.setText("");
		jLabel1.setVisible(false);
		ButtonSpielernameOK.setEnabled(true);
		ButtonSpielNeustart.setEnabled(false);
		minen=3;
		RestMinen.setText("Minen zähler "+Integer.toString(minen));
		minerichtig=0;
		MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	
	}

	public static void sieg() {

		   MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	  
			  if (minerichtig==mine && minen==0) {
			  		 JOptionPane.showMessageDialog(null, "Spiel gewonnen");

			// To-Do: Auswahl reset oder nochmal spielen (=neustarten) in der
			// Sieg-Meldung

			// Hier jetzt: Spiel gewonnen = Spielfeld deaktiviert. Reset und
			// Neustart können ausgewählt werden.
			for (int i = 0; i < buttons.length; i++) {
				buttons[i].setEnabled(false);
			}
		}
	}

	public static void mine() {

		JOptionPane.showMessageDialog(null, "Mine! Verloren");

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
		}
		
	}
	
	
	  public static void setMineMarkiert() {
		  minen=minen-1;
		  RestMinen.setText("Minen zähler "+Integer.toString(minen));
	  }
	  public static void setMarkierteMineFreigeben() {
		  minen=minen+1;
		  RestMinen.setText("Minen zähler "+Integer.toString(minen));
	  }
	  
	  public static void mineRichtig(int i) {
		  minerichtig=minerichtig+i;
	  }
	
	
	//Button aus MouseInput heraus deaktivieren
	public static void setDisabled(int i) {
		buttons[i].setEnabled(false);
	}

	public static void setText(int i) {
		buttons[i].setText(a_btnText[i]);
	}
	
	public static void setText(int i,String text) {
		buttons[i].setText(text);
	}
	
	public static void setSpielfeldgeklickt(int i, int j) {
		Spielfeldgeklickt[i] = j;
	}
	// Ende Methoden
	
} // end of class Test