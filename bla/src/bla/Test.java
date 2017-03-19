package bla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

/**
 * Spielprojekt "Seawolf" GameApp-Name <not set/actually nameless>
 *
 * @version A.2.4 vom 19.03.2017
 * @author XKonne
 * @author p0sE-Git
 */

public class Test extends JFrame {
	
	// Attribute
	static boolean Spielfeldgesperrt = true;
	static int minen = 3;
	static int mine = 3;
	static String versiont="A.2.4";
	private JButton btn_SpielNeustart = new JButton();
	private JButton btn_SpielReset = new JButton();
	private JButton btn_Spielstarten = new JButton();

	private JLabel lab_Version = new JLabel();
	private JTextField txtfield_Spielername = new JTextField();
	
	//GUI Elemente
	//Bereich "Spielerinformationen"
	private JLabel lab_Spielername = new JLabel();
	private JLabel lab_Spielmodus = new JLabel();	  
	
	private static JLabel lab_Restminen = new JLabel();
	static int minerichtig=0;
	private static JLabel lab_MinenRichtig = new JLabel();

	//Variablen zur Spielfeld-Button-Ausrichtung
	int top=10;
	int left=70;
	int lenght=30;
	int height=30;
	int zeile=0;
	
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
		
		
		btn_SpielReset.setBounds(10, 230, 100, 30);
		btn_SpielReset.setText("Reset Spiel");
		btn_SpielReset.setMargin(new Insets(2, 2, 2, 2));

		// Spielername-Eingabe
		txtfield_Spielername.setBounds(10, 10, 150, 30);
		cp.add(txtfield_Spielername);

		btn_Spielstarten.setBounds(170, 10, 35, 28);
		btn_Spielstarten.setText("Los");
		btn_Spielstarten.setMargin(new Insets(2, 2, 2, 2));
		btn_Spielstarten.setEnabled(true);

		btn_SpielNeustart.setBounds(120, 230, 100, 30);
		btn_SpielNeustart.setText("Neustarten");
		btn_SpielNeustart.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNeustart.setEnabled(false);

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
		//x-y-Wert setzen bei Spielfeld-Buttons
		for (int i=0; i<16; i++) {
			    //x-Wert ergibt sich aus Button-Nr MOD spaltenwert (hier 4), damit 4 Buttons hinereinander liegen
				buttons[i].setBounds(top+(i%4)*lenght+(i%4)*10,left+zeile*height+zeile*10,lenght,height);
				// Wenn eine Zeile fertig ist, erhöhe Zeilen-Wert (=y-Koordinate)
				if (i%4==3) {
					zeile=zeile+1;
				}
		}
		
		/* ALT = Setzen des x-y-Wertes zur Position der Spielfeldbuttons
		 * HIER: Position mit Variablen, abhängig von einander
		 * => Muster erkennbar, damit das Ganze als Schleife programmiert werden kann
		 
		buttons[0].setBounds(top+0*lenght+0*10, left+0*height+0*10, lenght, height);
		buttons[1].setBounds(top+1*lenght+1*10, left+0*height+0*10, lenght, height);
		buttons[2].setBounds(top+2*lenght+2*10, left+0*height+0*10, lenght, height);
		buttons[3].setBounds(top+3*lenght+3*10, left+0*height+0*10, lenght, height);
		
		buttons[4].setBounds(top+0*lenght+0*10, left+1*height+1*10, lenght, height);
		buttons[5].setBounds(top+1*lenght+1*10, left+1*height+1*10, lenght, height);
		buttons[6].setBounds(top+2*lenght+2*10, left+1*height+1*10, lenght, height);
		buttons[7].setBounds(top+3*lenght+3*10, left+1*height+1*10, lenght, height);
		
		buttons[8].setBounds(top+0*lenght+0*10, left+2*height+2*10, lenght, height);
		buttons[9].setBounds(top+1*lenght+1*10, left+2*height+2*10, lenght, height);
		buttons[10].setBounds(top+2*lenght+2*10, left+2*height+2*10, lenght, height);
		buttons[11].setBounds(top+3*lenght+3*10, left+2*height+2*10, lenght, height);
		
		buttons[12].setBounds(top+0*lenght+0*10, left+3*height+3*10, lenght, height);
		buttons[13].setBounds(top+1*lenght+1*10, left+3*height+3*10, lenght, height);
		buttons[14].setBounds(top+2*lenght+2*10, left+3*height+3*10, lenght, height);
		buttons[15].setBounds(top+3*lenght+3*10, left+3*height+3*10, lenght, height);
		*/
		
		Border border = LineBorder.createGrayLineBorder();
		lab_Spielername.setBounds(5, 5, 160, 40);
		lab_Spielername.setFont(new Font("Dialog", Font.PLAIN, 35));
		lab_Spielername.setBorder(border);
		lab_Spielername.setVisible(false);
		cp.add(lab_Spielername);
		
	    lab_Spielmodus.setBounds(170, 0, 100, 20);
	    lab_Spielmodus.setVisible(false);
	    lab_Spielmodus.setFont(new Font("Dialog", Font.PLAIN, 11));
	    lab_Spielmodus.setText("Modus: 4x4");
	    cp.add(lab_Spielmodus);

	    lab_Restminen.setBounds(170, 14, 100, 20);
	    lab_Restminen.setVisible(false);
	    lab_Restminen.setFont(new Font("Dialog", Font.PLAIN, 11));
	    lab_Restminen.setText("Minen: "+Integer.toString(minen));
	    cp.add(lab_Restminen);
	    
	    lab_MinenRichtig.setBounds(170, 28, 100, 20);
	    lab_MinenRichtig.setVisible(false);
	    lab_MinenRichtig.setFont(new Font("Dialog", Font.PLAIN, 11));
	    lab_MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));
	    cp.add(lab_MinenRichtig);
	    
	    lab_Version.setBounds(285, 240, 100, 30);
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

		// Komponenten erzeugen
		cp.add(btn_SpielNeustart);
		cp.add(btn_SpielReset);
		cp.add(btn_Spielstarten);
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
		minen=3;
		lab_Restminen.setText("Minen: "+Integer.toString(minen));
		minerichtig=0;
		lab_MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	
	}

	public void btn_Spielstarten_ActionPerformed() {
		// Spielfeld aktivieren
		setSpielfeldAnAus(true);
		
		// Name einlesen
		Spielername = txtfield_Spielername.getText();
		txtfield_Spielername.setVisible(false);
		lab_Spielername.setText(Spielername);
		lab_Spielername.setVisible(true);
		lab_Spielmodus.setVisible(true);
		lab_Restminen.setVisible(true);
		lab_MinenRichtig.setVisible(true);

		btn_SpielNeustart.setEnabled(true);
		Spielfeldgesperrt = false;

		// Button zum Schluss deaktivieren
		btn_Spielstarten.setVisible(false);

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
		Spielername = "none";
		txtfield_Spielername.setVisible(true);
		txtfield_Spielername.setText("");
		lab_Spielername.setVisible(false);
		lab_Spielmodus.setVisible(false);
		lab_Restminen.setVisible(false);
		lab_MinenRichtig.setVisible(false);
		btn_Spielstarten.setVisible(true);
		btn_SpielNeustart.setEnabled(false);
		minen=3;
		lab_Restminen.setText("Minen zähler "+Integer.toString(minen));
		minerichtig=0;
		lab_MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	
	}

	public static void aufSiegpruefen() {
		// aufSiegpruefen wird nach _jedem_ Mausklick ausgeführt.
		
		// Minen-Markiert-Zähler und Minen-Richtig-Zähler aktualisieren
		lab_Restminen.setText("Minen zähler "+Integer.toString(minen));
		lab_MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));
		
		// Sieg-Bedingung pruefen
		if (minerichtig==mine && minen==0) {
			// Ausgabe
			JOptionPane.showMessageDialog(null, "Spiel gewonnen");

			  		 /*
			 		Object[] options = {"Spielfeld anzeigen", "Nochmal", "Neue Runde", "Neues Spiel"};

	                int selected = JOptionPane.showOptionDialog(null,
	                                                            "Gewonnen!",
	                                                            "Spielnachricht",
								    JOptionPane.DEFAULT_OPTION, 
	                                                            JOptionPane.INFORMATION_MESSAGE, 
								    null, options, options[0]);
			  		 
			  		
	             // Erzeugung eines neuen JDialogs 
	                        JDialog meinJDialog = new JDialog();
	                        meinJDialog.setTitle("Runde beendet");
	                        meinJDialog.setSize(125,200);
	                        meinJDialog.setModal(true);
	                        meinJDialog.add(labMinenRichtig);
	                        labMinenRichtig.setBounds(10, 10, 50, 50);
	                       // meinJDialog.add(new JButton("?"));
	                        //meinJDialog.add(new JLabel("Gewonnen!"));
	                        //meinJDialog.add(new JLabel("Gewonnen!"));
	                        meinJDialog.setVisible(true);

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
	
	// Feld per Rechtsklick markiert > Erniedrige Minenzähler um 1 (=-1). Markierung per Rechtsklick aufgehoben > +1.
	public static void countMinenMarkierung(int i) {
		minen=minen+i;		  
	}
	  
	// Feld - mit Mine - richtig markiert > +1. Falls die Markierung aufgehoben wird > -1.
	public static void countMineRichtig(int i) {
	    minerichtig=minerichtig+i;
	}
	
	// Alle Spielfeld-Button deaktivieren (false) oder aktivieren (true)
	public static void setSpielfeldAnAus(boolean x) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(x);
		}
	}
	
	//Einzelnen Spielfeld-Button [aus MouseInput heraus] deaktivieren
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