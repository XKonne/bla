package bla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Spielprojekt "Seawolf" GameApp-Name <not set/actually nameless>
 *
 * @version A.1.7 vom 14.03.2017
 * @author XKonne
 * @author p0sE-Git
 * 
 */

public class test extends JFrame {
	// Anfang Attribute

	String Spielername;
	int gewonnen = 16;
	int minen = 3;
	
	// Variable für Abfrage für Linkslick ob Spielfeld freigegeben
	boolean Spielfeldgesperrt = true;
	// 0=Spielfeld noch nicht geklickt --- 1=Spielfeld bereits 1x gedrückt
	int[] Spielfeldgeklickt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // i=1..16
																						// genutzt

	private JLabel jLabel1 = new JLabel();
	private JLabel Spielfeldaufdecken = new JLabel();
	private JButton ButtonSpielNeustart = new JButton();

	// Spiel-Oberflaeche
	private JButton ButtonResetSpielfeld = new JButton();

	// Spielername-Eingabefeld
	private JTextField jTextField1 = new JTextField();
	private JButton ButtonSpielernameOK = new JButton();

	// MFeld = Minenfeld Testspielfeld 4x4
	// 4x4-Raster 1 2 3 4 - - 1 M
	// 5 6 7 8 <=> 1 2 3 2
	// 9 10 11 12 1 M M 1
	// 13 14 15 16 1 2 2 1

	private static JButton[] buttons = new JButton[16];
	// Ende Attribute

	public test() {
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
		// Anfang Komponenten

		String a_btnNames[] = { "MFeld1", "MFeld2", "MFeld3", "MFeld4", "MFeld5", "MFeld6", "MFeld7", "MFeld8",
				"MFeld9", "MFeld10", "MFeld11", "MFeld12", "MFeld13", "MFeld14", "MFeld15", "MFeld16" };
		String a_btnText[] = { "-", "-", "1", "M", "1", "2", "3", "2", "1", "M", "M", "1", "1", "2", "2", "1" };

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(a_btnNames[i]);
		}

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
		for (int i = 0; i < buttons.length; i++) {

			// buttons[i].setBounds(10, 10, 30, 30);
			buttons[i].setText(".");
			buttons[i].setMargin(new Insets(2, 2, 2, 2));
			buttons[i].setEnabled(false);

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

		Spielfeldaufdecken.setBounds(170, 150, 100, 30);
		Spielfeldaufdecken.setVisible(true);
		Spielfeldaufdecken.setText(Integer.toString(gewonnen));
		cp.add(Spielfeldaufdecken);

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

//		for (int i = 0; i < buttons.length; i++) {
//
//			buttons[i].addMouseListener(new MouseAdapter() {
//				public void mousePressed(MouseEvent e) {
//					if (e.getButton() == MouseEvent.BUTTON1) {
//						if (Spielfeldgesperrt == false) {
//							// buttons[i].setText(a_btnText[i]);
//							JButton tempbutton = (JButton) e.getSource();
////							tempbutton.setText(a_btnText[i]);
//							tempbutton.setText(tempbutton.getName());
//							if (tempbutton.getName() == "M") {
//								mine();
//								Spielfeldgeklickt[i] = 1;
//							}
//							if (Spielfeldgeklickt[i] == 0) {
//								Spielfeldgeklickt[i] = 1;
//								gewonnen = gewonnen - 1;
//								sieg();
//							}
//						}
//					}
//					if (e.getButton() == MouseEvent.BUTTON3) {
//						if (Spielfeldgesperrt == false) {
//							buttons[i].setText("*");
//						}
//					}
//				}
//			});
//
//		}
		

		// Komponenten erzeugen
		cp.add(ButtonSpielNeustart);
		cp.add(ButtonResetSpielfeld);
		cp.add(ButtonSpielernameOK);

		for (int i = 0; i < buttons.length; i++) {
			cp.add(buttons[i]);
		}
		// Ende Komponenten

		setVisible(true);
	} // end of public test

	// Anfang Methoden

	public static void main(String[] args) {
		new test();
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

		gewonnen = 16;
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

		gewonnen = 16;
	}

	public void sieg() {

		Spielfeldaufdecken.setText(Integer.toString(gewonnen));
		if (gewonnen == minen) {

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

	public void mine() {

		JOptionPane.showMessageDialog(null, "Mine! Verloren");

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
		}

	}

	// Ende Methoden
} // end of class test