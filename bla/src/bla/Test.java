package bla;

import java.awt.*;
import java.awt.event.*;
import java.lang.management.ClassLoadingMXBean;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import java.util.Random;

/**
 * Spielprojekt "Seawolf" GameApp-Name <not set/actually nameless>
 *
 * @version A.2.9 vom 24.03.2017
 * @author XKonne
 * @author p0sE-Git
 */

public class Test extends JFrame {

	// Variablen
	// Integer
	static int mine = 3;
	static int minerichtig = 0;
	// Variablen zur Spielfeld-Button-Ausrichtung
	int top = 10;
	int left = 70;
	int lenght = 30;
	int height = 30;
	int zeile = 0;
	int spalte = 4; // Spalte setzt das Layout fest
	int abstand = 10;

	// String
	static String versiont = "A.2.9";
	// String Spielername="";

	// Boolean
	static boolean Spielfeldgesperrt = true;
	static boolean EingabeRichtig = false;

	// Zufallszahlen
	Random rand = new Random();

	// Buttons
	private JButton btn_SpielNeustart = new JButton();
	private JButton btn_SpielReset = new JButton();
	private JButton btn_Spielstarten = new JButton();
	private JButton btn_SpielerProfil = new JButton();
	private JButton btn_SpielBeenden = new JButton();
	private JButton btn_SpielInformation = new JButton();
	private JButton btn_SpielNeueRunde = new JButton();

	// Labels
	private JLabel lab_Version = new JLabel();
	private JLabel lab_Spielername = new JLabel();
	private JLabel lab_Spielmodus = new JLabel();
	private static JLabel lab_Restminen = new JLabel();
	private static JLabel lab_MinenRichtig = new JLabel();

	// TextField
	private JTextField txt_SpielerName = new JTextField();

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
	// Ende Attribute

	public Test(Spiel spiel) {
		// Frame-Initialisierung
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 375;
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

		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		btn_SpielerProfil.setBounds(10, 5, 40, 40);
		btn_SpielerProfil.setVisible(false);
		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielerProfil);
		


		// Spielername-Eingabe
		txt_SpielerName.setBounds(10, 10, 150, 30);
		cp.add(txt_SpielerName);

		btn_Spielstarten.setBounds(170, 10, 90, 28);
		btn_Spielstarten.setText("Spiel starten");
		btn_Spielstarten.setMargin(new Insets(2, 2, 2, 2));
		btn_Spielstarten.setEnabled(true);
		
		btn_SpielReset.setBounds(10, 230, 80, 30);
		btn_SpielReset.setText("Reset");
		btn_SpielReset.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielReset.setEnabled(false);
		
		btn_SpielNeueRunde.setBounds(100, 230, 80, 30);
		btn_SpielNeueRunde.setText("Neue Runde");
		btn_SpielNeueRunde.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNeueRunde.setEnabled(false);
		cp.add(btn_SpielNeueRunde);

		btn_SpielNeustart.setBounds(190, 230, 80, 30);
		btn_SpielNeustart.setText("Nochmal");
		btn_SpielNeustart.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNeustart.setEnabled(false);

		btn_SpielBeenden.setBounds(280, 230, 80, 30);
		btn_SpielBeenden.setText("Beenden");
		btn_SpielBeenden.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielBeenden);
		
		btn_SpielInformation.setBounds(320, 5, 40, 40);
		btn_SpielInformation.setText("?");
		btn_SpielInformation.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielInformation);
		
		// Spielfeld
		
		// Buttons bauen
		for (int i = 0; i < buttons.length; i++) {
			
			buttons[i] = new JButton(a_btnNames[i]);
			buttons[i].setText(".");
			buttons[i].setMargin(new Insets(2, 2, 2, 2));
			buttons[i].setEnabled(false);
			
			MouseInput mouse = new MouseInput(i);
			buttons[i].addMouseListener(mouse);
			
			cp.add(buttons[i]);
		}
		
		
		
		
		//x-y-Wert setzen bei Spielfeld-Buttons
		for (int i=0; i<buttons.length; i++) {
			    //x-Wert ergibt sich aus Button-Nr MOD spaltenwert (hier 4), damit 4 Buttons hinereinander liegen
				buttons[i].setBounds(top+(i%spalte)*lenght+(i%spalte)*abstand,left+zeile*height+zeile*abstand,lenght,height);
				// Wenn eine Zeile fertig ist, erhöhe Zeilen-Wert (=y-Koordinate)
				if (i%spalte==spalte-1) {
					zeile=zeile+1;
				}
		}
		
		Border border = LineBorder.createGrayLineBorder();
		lab_Spielername.setBounds(55, 5, 160, 40);
		lab_Spielername.setFont(new Font("Dialog", Font.PLAIN, 35));
		lab_Spielername.setBorder(border);
		lab_Spielername.setVisible(false);
		cp.add(lab_Spielername);
		
	    lab_Spielmodus.setBounds(220, 0, 100, 20);
	    lab_Spielmodus.setVisible(false);
	    lab_Spielmodus.setFont(new Font("Dialog", Font.PLAIN, 11));
	    lab_Spielmodus.setText("Modus: 4x4");
	    cp.add(lab_Spielmodus);

	    lab_Restminen.setBounds(220, 14, 100, 20);
	    lab_Restminen.setVisible(false);
	    lab_Restminen.setFont(new Font("Dialog", Font.PLAIN, 11));
	    lab_Restminen.setText("Minen: "+Integer.toString(spiel.getMinen()));
	    cp.add(lab_Restminen);
	    
	    lab_MinenRichtig.setBounds(220, 28, 100, 20);
	    lab_MinenRichtig.setVisible(false);
	    lab_MinenRichtig.setFont(new Font("Dialog", Font.PLAIN, 11));
	    lab_MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));
	    cp.add(lab_MinenRichtig);
	    
	    lab_Version.setBounds(330, 205, 100, 30);
	    lab_Version.setVisible(true);
	    lab_Version.setText(versiont);
	    cp.add(lab_Version);
		
		
		btn_SpielNeustart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonSpielNeustart_ActionPerformed(evt);
			}
		});

		// Methode zum Button-klick-ausfuehren
		btn_SpielReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonResetSpielfeld_ActionPerformed();
			}
		});

		btn_Spielstarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_Spielstarten_ActionPerformed();
			}
		});
		
		btn_SpielBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		
		btn_SpielerProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				GUI_Spielerprofil spieler = new GUI_Spielerprofil();
			}
		});
		
		btn_SpielInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
		        // Erzeugung eines neuen JDialogs 
				JOptionPane.showMessageDialog(null, "Finde (=richtig markiert) alle Minen um zu gewinnen."
						+ " Ein Linksklick deckt ein Feld auf. Ein Rechtsklick markiert ein Feld. Wird ein Feld mit einer Mine aufgedeckt, so ist das Spiel verloren.");
			}
		});

		// Komponenten erzeugen
		cp.add(btn_SpielNeustart);
		cp.add(btn_SpielReset);
		cp.add(btn_Spielstarten);
		// Ende Komponenten

		setVisible(true);
	} // end of public Test

	// Anfang Methoden
//	public static void main(String[] args) {
//		new Test();
//	}

	public void ButtonSpielNeustart_ActionPerformed(ActionEvent evt) {
		// TODO hier Quelltext einfÃ¼gen
		// Spielfeld Oberflaeche zu beginn

		// Spielfeld-Buttons auf Anfangswert
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(".");
		}

		// Spielfeld aktivieren
		setSpielfeldAnAus(true);
		Spielfeldgesperrt = false;

		// Spielfeldgeklickt Array zurücksetzen
		for (int i = 0; i < Spielfeldgeklickt.length; i++) {
			Spielfeldgeklickt[i] = 0;
		}
		Spiel.setMinen(3);
		lab_Restminen.setText("Minen: " + Integer.toString(Spiel.getMinen()));
		minerichtig = 0;
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(minerichtig));
	}

	public void btn_Spielstarten_ActionPerformed() {
		// Spieler-Objekt erstellen
		new Spieler();
		// Name einlesen
		Spieler.setSpielerName(txt_SpielerName.getText());
		// Es wurde kein Spielername eingegeben > Zufallsname
		if (Spieler.getSpielerName().length() == 0 || Spieler.getSpielerName() == "") {
			Spieler.setSpielerName("Rand" + Integer.toString(rand.nextInt(99) + 1));
			EingabeRichtig = true;
		}
		// Spielername hat nur 1 oder 2 Zeichen. Fehlermeldung.
		if (Spieler.getSpielerName().length() == 1 || Spieler.getSpielerName().length() == 2) {
			JOptionPane.showMessageDialog(null,
					"Ein Spielername muss mindestens aus 3 Zeichen bestehen. Lass das Feld leer für einen Zufallsnamen.");
		}
		// Spielername hat 3 oder mehr Zeichen. Das Spiel kann gestartet werden.
		if (Spieler.getSpielerName().length() >= 3) {
			EingabeRichtig = true;
		}

		if (EingabeRichtig == true) {
			// Spielfeld aktivieren
			setSpielfeldAnAus(true);

			txt_SpielerName.setVisible(false);
			lab_Spielername.setText(Spieler.getSpielerName());
			lab_Spielername.setVisible(true);
			lab_Spielmodus.setVisible(true);
			lab_Restminen.setVisible(true);
			lab_MinenRichtig.setVisible(true);

			btn_SpielNeustart.setEnabled(true);
			Spielfeldgesperrt = false;
			btn_SpielerProfil.setVisible(true);

			// Button zum Schluss deaktivieren
			btn_Spielstarten.setVisible(false);
			btn_SpielReset.setEnabled(true);
		}

	} // end of jButton1_ActionPerformed

	public void ButtonResetSpielfeld_ActionPerformed() {

		// TODO hier Quelltext einfÃ¼gen
		// Spielfeld Oberflaeche zu beginn
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(".");
		}

		// Spielfeld deaktivieren
		setSpielfeldAnAus(false);

		Spielfeldgesperrt = true;
		// Spielfeldgeklickt Array zurücksetzen
		for (int i = 0; i < 17; i++) {
			Spielfeldgeklickt[i] = 0;
		}

		// Spielername zurücksetze, Label weg, Eingabe da
		Spieler.setSpielerName("");
		EingabeRichtig = false;
		btn_SpielerProfil.setVisible(false);
		txt_SpielerName.setVisible(true);
		txt_SpielerName.setText("");
		lab_Spielername.setVisible(false);
		lab_Spielmodus.setVisible(false);
		lab_Restminen.setVisible(false);
		lab_MinenRichtig.setVisible(false);
		btn_Spielstarten.setVisible(true);
		btn_SpielNeustart.setEnabled(false);
		Spiel.setMinen(3);
		lab_Restminen.setText("Minen: " + Integer.toString(Spiel.getMinen()));
		minerichtig = 0;
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(minerichtig));
	}

	public static void aufSiegpruefen() {
		// aufSiegpruefen wird nach _jedem_ Mausklick ausgeführt.

		// Minen-Markiert-Zähler und Minen-Richtig-Zähler aktualisieren
		lab_Restminen.setText("Minen: " + Integer.toString(Spiel.getMinen()));
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(minerichtig));

		// Sieg-Bedingung pruefen
		if (minerichtig == mine && Spiel.getMinen() == 0) {
			// Ausgabe
			JOptionPane.showMessageDialog(null, "Spiel gewonnen");

			/*
			 * Neue Ausgabe / FUNKTIONIERT NOCH NICHT
			 * 
			 * 
			 * Object[] options = {"Spielfeld anzeigen", "Nochmal",
			 * "Neue Runde", "Neues Spiel"};
			 * 
			 * int selected = JOptionPane.showOptionDialog(null, "Gewonnen!",
			 * "Spielnachricht", JOptionPane.DEFAULT_OPTION,
			 * JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			 */

			// To-Do: Auswahl reset oder nochmal spielen (=neustarten) in der
			// Sieg-Meldung

			// Spielfeld deaktivieren
			setSpielfeldAnAus(false);
		}
	}

	public static void mineAufgedeckt() {
		// Ausgabe
		JOptionPane.showMessageDialog(null, "Mine! Verloren");
		// Spielfeld deaktivieren
		setSpielfeldAnAus(false);
	}

	// Feld per Rechtsklick markiert > Erniedrige Minenzähler um 1 (=-1).
	// Markierung per Rechtsklick aufgehoben > +1.
	public static void countMinenMarkierung(int i) {
		Spiel.setMinen(Spiel.getMinen() + i);
	}

	// Feld - mit Mine - richtig markiert > +1. Falls die Markierung aufgehoben
	// wird > -1.
	public static void countMineRichtig(int i) {
		minerichtig = minerichtig + i;
	}

	// Alle Spielfeld-Button deaktivieren (false) oder aktivieren (true)
	public static void setSpielfeldAnAus(boolean x) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(x);
		}
	}

	// Einzelnen Spielfeld-Button [aus MouseInput heraus] deaktivieren
	public static void setDisabled(int i) {
		buttons[i].setEnabled(false);
	}

	public static void setText(int i) {
		buttons[i].setText(a_btnText[i]);
	}

	public static void setText(int i, String text) {
		buttons[i].setText(text);
	}

	public static void setSpielfeldgeklickt(int i, int j) {
		Spielfeldgeklickt[i] = j;
	}
	// Ende Methoden

} // end of class Test